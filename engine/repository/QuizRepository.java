package engine.repository;

import engine.logic.Quiz;
import engine.logic.QuizAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository  extends JpaRepository<Quiz, Long>  {



/*

 private ArrayList<Quiz> listQuiz = new ArrayList<>();

    public Quiz addQuizList(Quiz quiz){

        Long id = (long) listQuiz.size() + 1L;

        quiz.setId(id);

        listQuiz.add(quiz);

        return quiz;

    }

    public Quiz findQuizById(int id) throws Exception {

       int index = id - 1;

       if(index >= listQuiz.size()){
           throw new Exception();
       }

        return listQuiz.get(index);

    }

    public List<Quiz> findAllQuiz(){

        return this.listQuiz;

    }

 */



}
