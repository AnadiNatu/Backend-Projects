package com.system.quizservice.service;

import com.system.quizservice.dao.QuizDao;
import com.system.quizservice.feign.QuizInterface;
import com.system.quizservice.model.Question;
import com.system.quizservice.model.QuestionWrapper;
import com.system.quizservice.model.Quiz;
import com.system.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionForQuiz(category,numQ).getBody();
    //To contact the api endpoint of the question service . we need a Rest Template
        Quiz quiz = new Quiz();

        quiz.setTitle(title);
        quiz.setQuestionsId(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success" , HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Quiz quiz = quizDao.findById(id).get();

        List<Integer> questionIds = quiz.getQuestionsId();

        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionFromId(questionIds);

        return questions;


    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses){

        ResponseEntity<Integer> score = quizInterface.getScore(responses);

        return score;

    }

}

