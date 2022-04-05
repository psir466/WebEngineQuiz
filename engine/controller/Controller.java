package engine.controller;

import engine.logic.*;
import engine.repository.QuizRepository;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Controller est utilisé dans les systèmes hérités
// qui utilisent des JSP. il peut renvoyer des vues.
// @RestController consiste à marquer que le contrôleur
// fournit des services REST avec le type de réponse JSON.
// il encapsule donc les annotations @Controller et @ResponseBody ensemble.


@RestController
public class Controller {

    @Autowired
    Converter converter;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/api/register")
    public void apiRegister(@Valid @RequestBody UserDTO userDTO) {

        Optional<User> userOption = userRepository.findByUserName(userDTO.getEmail());

        if (userOption.isPresent()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User already exists");

        } else {
            User u = userRepository.save(converter.convertUserDTOToUser(userDTO));
        }

    }


    @GetMapping("/api/quiz")
    public QuizDTO returnQuiz() {

        System.out.println("gkjgkjg");

        ArrayList<String> optionsJava = new ArrayList<>();
        optionsJava.add("Robot");
        optionsJava.add("Tea leaf");
        optionsJava.add("Cup of coffee");
        optionsJava.add("Bug");

        Quiz quiz = new Quiz(1L, "The Java Logo", "What is depicted on the Java logo?", optionsJava);

        return converter.convertQuizToQuizDTO(quiz);
    }

    // @RequestParam : les paramétre sont dans l'URL Post !!! (pas dans le body)
    @PostMapping("/api/quiz")
    public Success answerToQuiz(@RequestParam Integer answer) {

        Success success = new Success();


        if (answer == 2) {

            success.setSuccess(true);
            success.setFeedback("Congratulations, you're right!");

            return success;

        }

        success.setSuccess(false);
        success.setFeedback("Wrong answer! Please, try again.");

        return success;
    }

    // post with body
    @PostMapping("/api/quizzes")
    public QuizWithoutAnswerDTO postQuizzes(@Valid @RequestBody QuizAnswerDTO quizAnswerDTO) {

        Quiz quiz = quizRepository.save(converter.convertQuizAnsqwerDTOTOQuiz(quizAnswerDTO));

        return converter.convertQuizToQuizWithoutAnswer(quiz);
    }


    @GetMapping("/api/quizzes/{id}")
    public QuizWithoutAnswerDTO returnQuizWithId(@PathVariable int id) {

        try {

            long idLong = id;

            Quiz quiz = quizRepository.findById(idLong).get();

            return converter.convertQuizToQuizWithoutAnswer(quiz);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "id not found");
        }

    }

    /*@GetMapping("/api/quizzes")
    public List<QuizWithoutAnswerDTO> returnAllQuiz() {

        return converter.getAllQuiz();

    }*/

    @GetMapping("/api/quizzes")
    public Page<QuizWithoutAnswerDTO> returnAllQuizWithPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {


        return converter.getAllQuiz(page, pageSize, sortBy);

    }

    // @RequestParam : les paramétre sont dans l'URL Post !!! (pas dans le body)
  /*  @PostMapping("/api/quizzes/{id}/solve")
    //c'est une {id} PathVariable et pas @RequestParam et du coup
    // c'est @RequestParam pour answer
    public Success answerToQuizz(@PathVariable int id, @RequestParam List<Integer> answer) {


        try {

            Quiz quiz = quizRepository.findQuizById(id);

            Success success = new Success();

            if (answer.containsAll(quiz.getAnswer())) {

                success.setSuccess(true);
                success.setFeedback("Congratulations, you're right!");

                return success;

            }

            success.setSuccess(false);
            success.setFeedback("Wrong answer! Please, try again.");

            return success;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "id not found");
        }

    }*/

    @PostMapping("/api/quizzes/{id}/solve")
    //c'est une {id} PathVariable et pas @RequestParam et du coup
    // c'est @RequestParam pour answer
    public Success answerToQuizz(@PathVariable int id, @RequestBody Answer answer) {
        //public Success answerToQuizz(@RequestBody Answer answer) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> uOpt = userRepository.findByUserName(username);

        try {

            long idLong = id;

            Quiz quiz = quizRepository.findById(idLong).get();

            Success success = new Success();

            if (answer.getAnswer().size() == quiz.getAnswer().size() && answer.getAnswer().containsAll(quiz.getAnswer())) {

                success.setSuccess(true);
                success.setFeedback("Congratulations, you're right!");

                UserQuizComplet usc = new UserQuizComplet();

                usc.setId(quiz.getId());
                usc.setLocalDateTime(LocalDateTime.now());

                // In a bidirectional OneToMany, the Many side MUST be the owner.
                // donc il faut aussi faire
                usc.setUser(uOpt.get());

                uOpt.get().getLsQuiz().add(usc);

                userRepository.save(uOpt.get());

                return success;

            }

            success.setSuccess(false);
            success.setFeedback("Wrong answer! Please, try again.");

            return success;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "id not found");
        }

    }

    @PostMapping("/actuator/shutdown")
    public void apiShutDown() {

    }

    @DeleteMapping("/api/quizzes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable int id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println(username);


        long idLong = id;

        Optional<Quiz> quizOpt = quizRepository.findById(idLong);

        if (quizOpt.isPresent()) {

            Quiz quiz = quizOpt.get();

            System.out.println(quiz.getUsername());
            if (quiz.getUsername().equals(username)) {


                quizRepository.delete(quiz);

                // Plante quand on fait Throw ... avec No_Content ??
                // du coup utilisation de l'annotation @ResponseStatus( sur la méthode
           /*     try {
                    throw new ResponseStatusException(HttpStatus.NO_CONTENT);
                } catch (ResponseStatusException e) {
                    e.printStackTrace();
                }*/


            } else {


                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "forbidden");

            }


        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "id not found");
        }


    }

    @GetMapping("/api/quizzes/completed")
    public Page<UserQuizCompletDTO> returnAllQuizComplet(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "localDateTime") String sortBy) {


        // dans les données pour trier plante si on utilise le nom de
        // la donnée dans la table apparemment à besoin du nom de la donnée dans la class
        // contrairement à ce qui est dit dans https://howtodoinjava.com/spring-boot2/pagination-sorting-example/
        // de plus contrairement à ce qui est dit l'odre par défaut de trie était ascendant
        // j'ai du préciser descendant !!!

        return converter.getUserCompletQuiz(page, pageSize, sortBy);

    }

}
