package com.system.quizservice.controller;

import com.system.quizservice.dto.QuizDto;
import com.system.quizservice.model.QuestionWrapper;
import com.system.quizservice.model.Response;
import com.system.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// controller for quiz
@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        //instead of sending params indivisually . Make a request dto of the request and then send it
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumberQuestions(), quizDto.getTitle());
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }



    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }


}

