package com.system.questionservice.controller;


import com.system.questionservice.model.Question;
import com.system.questionservice.model.QuestionWrapper;
import com.system.questionservice.model.Response;
import com.system.questionservice.service.QuestionService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;
    //This class/interface is in-built . It has properties that help us know about the environment variables , for exampe port number .getProperty("local.server.port")

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return  questionService.addQuestion(question);
    }

    //generate the quiz
    //getQuestion(question Id)
    //get Score


    @GetMapping("generate")
    public ResponseEntity<List<Integer>>  getQuestionForQuiz(@RequestParam String categoryName , @RequestParam Integer numQuestions){
        return questionService.getQuestionForQuiz(categoryName , numQuestions);

    }

    @PostMapping("getQuestion")
    public ResponseEntity<List<QuestionWrapper>>  getQuestionFromId(@RequestBody List<Integer> questionIds ){

        System.out.println(environment.getProperty("local.server.port"));

        return questionService.getQuestionFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){

        return questionService.getScore(responses);

    }
}

