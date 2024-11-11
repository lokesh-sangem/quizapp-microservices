package com.tac.quizservice.service;

import com.tac.quizservice.entities.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url="http://localhost:8080",value="Question-Client")
//@FeignClient(name="QUESTION-SERVICE")
public interface QuestionClient {

    @GetMapping("/question/quiz/{quizId}")
    public List<Question> getQuestionsOfQuiz(@PathVariable Long quizId);



}
