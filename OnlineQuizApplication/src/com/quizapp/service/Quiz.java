package com.quizapp.service;

import com.quizapp.model.Question;

import java.util.List;

public class Quiz {

    private String quizName;
    private List<Question> questions;
    private int score = 0;

    public Quiz(String quizName, List<Question> questions) {
        this.quizName = quizName;
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public boolean answerQuestion(Question question, int answerIndex) {
        
        String answerOption = "";
        switch (answerIndex) {
            case 0:
                answerOption = "A";
                break;
            case 1:
                answerOption = "B";
                break;
            case 2:
                answerOption = "C";
                break;
            case 3:
                answerOption = "D";
                break;
            default:
                throw new IllegalArgumentException("Invalid answer index: " + answerIndex);
        }

        
        if (question.getCorrectOption().equals(answerOption)) {
            score++;
            return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}
