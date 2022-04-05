package engine.logic;

import engine.repository.QuizRepository;
import engine.repository.UserQuizCompletRepository;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Converter {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserQuizCompletRepository userQuizCompletRepository;

    public QuizDTO convertQuizToQuizDTO(Quiz quiz) {


        QuizDTO quizDTO = new QuizDTO();

        quizDTO.setTitle(quiz.getTitle());

        quizDTO.setText(quiz.getText());

        ArrayList<String> optionsDTO = new ArrayList<>();

        for (String s : quiz.getOptions()) {

            optionsDTO.add(s);

        }

        quizDTO.setOptions(optionsDTO);

        return quizDTO;
    }


    public Quiz convertQuizAnsqwerDTOTOQuiz(QuizAnswerDTO quizAnswerDTO) {

        Quiz quiz = new Quiz();

        quiz.setTitle(quizAnswerDTO.getTitle());

        quiz.setText(quizAnswerDTO.getText());

        ArrayList<Integer> answerDTO = new ArrayList<>();

        if (quizAnswerDTO.getAnswer() != null) {

            for (Integer i : quizAnswerDTO.getAnswer()) {

                answerDTO.add(i);

            }
        }

        quiz.setAnswer(answerDTO);

        ArrayList<String> optionsDTO = new ArrayList<>();

        for (String s : quizAnswerDTO.getOptions()) {

            optionsDTO.add(s);

        }

        quiz.setOptions(optionsDTO);

        quiz.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        return quiz;

    }

    public QuizWithoutAnswerDTO convertQuizToQuizWithoutAnswer(Quiz quiz) {

        QuizWithoutAnswerDTO quizWithoutAnswerDTO = new QuizWithoutAnswerDTO();

        quizWithoutAnswerDTO.setId(quiz.getId());

        quizWithoutAnswerDTO.setTitle(quiz.getTitle());

        quizWithoutAnswerDTO.setText(quiz.getText());

        ArrayList<String> optionsDTO = new ArrayList<>();

        for (String s : quiz.getOptions()) {

            optionsDTO.add(s);

        }

        quizWithoutAnswerDTO.setOptions(optionsDTO);

        return quizWithoutAnswerDTO;


    }

    public List<QuizWithoutAnswerDTO> getAllQuiz() {


        List<QuizWithoutAnswerDTO> quizWithoutAnswerDTOList = new ArrayList<>();


        for (Quiz q : quizRepository.findAll()) {

            quizWithoutAnswerDTOList.add(this.convertQuizToQuizWithoutAnswer(q));

        }

        return quizWithoutAnswerDTOList;
    }

    public List<QuizWithoutAnswerDTO> getAllQuizPage(List<Quiz> lsQuiz) {


        List<QuizWithoutAnswerDTO> quizWithoutAnswerDTOList = new ArrayList<>();


        for (Quiz q : lsQuiz) {

            quizWithoutAnswerDTOList.add(this.convertQuizToQuizWithoutAnswer(q));

        }

        return quizWithoutAnswerDTOList;
    }


    public User convertUserDTOToUser(UserDTO userDTO){

        User user = new User();

        user.setActive(true);
        user.setUserName(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles("");


        System.out.println("user: " + userDTO.getEmail());
        System.out.println("password: " + userDTO.getPassword());


        return user;

    }


    public Page<QuizWithoutAnswerDTO> getAllQuiz(Integer pageNo, Integer pageSize, String sortBy) {


        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Quiz> pagedResult = quizRepository.findAll(paging);

       // if(pagedResult.hasContent()) {
            return  new PageImpl<QuizWithoutAnswerDTO>(this.getAllQuizPage(pagedResult.getContent()), paging, pagedResult.getTotalElements());
      /*  }
        else {
            return null;
        }*/

    }

    public Page<UserQuizCompletDTO> getUserCompletQuiz(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> uOpt = userRepository.findByUserName(username);

        System.out.println(username + " " + uOpt.get().getId());

        Page<UserQuizComplet> pagedResult = userQuizCompletRepository.findByIdUser(uOpt.get().getId(), paging);

        for(UserQuizComplet uj : pagedResult.getContent()){

            System.out.println(uj.getId());
        }

        return  new PageImpl<UserQuizCompletDTO>(this.getAllQuizCompletPage(pagedResult.getContent()), paging, pagedResult.getTotalElements());

    }

    public List<UserQuizCompletDTO> getAllQuizCompletPage(List<UserQuizComplet> ls){

        List<UserQuizCompletDTO> lsuqc = new ArrayList<>();

        for(UserQuizComplet u : ls){

            UserQuizCompletDTO udto = new UserQuizCompletDTO();

            udto.setId(u.getId());

            udto.setCompletedAt(u.getLocalDateTime());

            System.out.println(u.getLocalDateTime());

            lsuqc.add(udto);

        }

        return lsuqc;

    }

}
