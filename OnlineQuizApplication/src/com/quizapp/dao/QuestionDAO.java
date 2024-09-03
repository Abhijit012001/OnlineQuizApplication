package com.quizapp.dao;

import com.quizapp.model.Question;
import com.quizapp.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    public List<Question> getAllQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT id, question_text, option_a, option_b, option_c, option_d, correct_option FROM questions";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionText(rs.getString("question_text"));
                question.setOptionA(rs.getString("option_a"));
                question.setOptionB(rs.getString("option_b"));
                question.setOptionC(rs.getString("option_c"));
                question.setOptionD(rs.getString("option_d"));
                question.setCorrectOption(rs.getString("correct_option"));
                
                System.out.println("Retrieved Question ID: " + question.getId());
                System.out.println("Question: " + question.getQuestionText());
                System.out.println("Option A: " + question.getOptionA());
                System.out.println("Option B: " + question.getOptionB());
                System.out.println("Option C: " + question.getOptionC());
                System.out.println("Option D: " + question.getOptionD());
                System.out.println("Correct Option: " + question.getCorrectOption());
                questions.add(question);
            }
        }
        return questions;
    }
}
