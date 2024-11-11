package com.tac.quizservice.service;

import com.tac.quizservice.entities.Quiz;

import java.util.List;

public interface QuizService {
    Quiz add(Quiz quiz);

    List<Quiz> get();

    Quiz get(Long id);
}
