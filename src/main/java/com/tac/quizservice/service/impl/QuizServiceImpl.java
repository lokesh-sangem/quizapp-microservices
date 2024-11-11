package com.tac.quizservice.service.impl;

import com.tac.quizservice.entities.Quiz;
import com.tac.quizservice.repo.QuizRepository;
import com.tac.quizservice.service.QuestionClient;
import com.tac.quizservice.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizRepository quizRepository;

//    public QuizServiceImpl(QuizRepository quizRepository) {
//        this.quizRepository = quizRepository;
//    }

    public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
        this.quizRepository = quizRepository;
        this.questionClient = questionClient;
    }

    private QuestionClient questionClient;



    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz) ;
    }

    @Override
    public List<Quiz> get() {
         List<Quiz>quizzes=quizRepository.findAll();
         //we are setting questions for quizzes by streaming it
        List<Quiz>newQuizList=quizzes.stream().map(quiz -> {
            quiz.setQuestions(questionClient.getQuestionsOfQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());

                //this newQuizList contains questions also including quiz details.
        return newQuizList;
    }

    @Override
    public Quiz get(Long id) {
         Quiz quiz=quizRepository.findById(id).orElseThrow(()->new RuntimeException("Quiz Not Found"));
        quiz.setQuestions(questionClient.getQuestionsOfQuiz(quiz.getId()));
        return quiz;
    }
}
