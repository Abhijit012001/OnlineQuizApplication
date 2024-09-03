package com.quizapp.gui;

import com.quizapp.dao.QuestionDAO;
import com.quizapp.model.Question;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuizPage {

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private Set<Integer> visitedQuestions = new HashSet<>();

    public QuizPage() {
        try {
            System.out.println("Fetching questions...");
            QuestionDAO questionDAO = new QuestionDAO();
            questions = questionDAO.getAllQuestions();
            System.out.println("Questions fetched: " + questions.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void start(Stage primaryStage) {
        System.out.println("Starting QuizPage...");
        showQuestion(primaryStage);
    }

    private void showQuestion(Stage primaryStage) {
        System.out.println("Current question index: " + currentQuestionIndex);

        if (currentQuestionIndex >= questions.size()) {
            showResults(primaryStage);
            return;
        }

        Question currentQuestion = questions.get(currentQuestionIndex);
        
        if (!visitedQuestions.add(currentQuestion.getId())) {
            System.out.println("Duplicate question found: " + currentQuestion.getQuestionText());
            currentQuestionIndex++;
            showQuestion(primaryStage);
            return;
        }

        Label questionLabel = new Label(currentQuestion.getQuestionText());
        questionLabel.setStyle("-fx-font-size: 18px;");

        ToggleGroup answersGroup = new ToggleGroup();

        RadioButton answer1 = new RadioButton(currentQuestion.getOptionA());
        answer1.setToggleGroup(answersGroup);

        RadioButton answer2 = new RadioButton(currentQuestion.getOptionB());
        answer2.setToggleGroup(answersGroup);

        RadioButton answer3 = new RadioButton(currentQuestion.getOptionC());
        answer3.setToggleGroup(answersGroup);

        RadioButton answer4 = new RadioButton(currentQuestion.getOptionD());
        answer4.setToggleGroup(answersGroup);

        Button nextButton = new Button("Next");
        nextButton.setStyle("-fx-font-size: 16px;");
        nextButton.setOnAction(e -> {
            RadioButton selectedAnswer = (RadioButton) answersGroup.getSelectedToggle();
            if (selectedAnswer != null) {
                String selectedText = selectedAnswer.getText().trim();
                String correctOption = currentQuestion.getCorrectOption().trim();

                
                String correctText = "";
                switch (correctOption.toUpperCase()) {
                    case "A":
                        correctText = currentQuestion.getOptionA().trim();
                        break;
                    case "B":
                        correctText = currentQuestion.getOptionB().trim();
                        break;
                    case "C":
                        correctText = currentQuestion.getOptionC().trim();
                        break;
                    case "D":
                        correctText = currentQuestion.getOptionD().trim();
                        break;
                }

             
                System.out.println("Selected Answer: " + selectedText);
                System.out.println("Correct Answer: " + correctText);

                if (selectedText.equalsIgnoreCase(correctText)) {
                    score++;
                    System.out.println("Score incremented: " + score);
                } else {
                    System.out.println("Incorrect answer. Score remains: " + score);
                }
            } else {
                System.out.println("No answer selected for question index: " + currentQuestionIndex);
            }

            currentQuestionIndex++;
            showQuestion(primaryStage);
        });

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(30));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getChildren().addAll(questionLabel, answer1, answer2, answer3, answer4, nextButton);

        Scene scene = new Scene(vbox, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showResults(Stage primaryStage) {
        System.out.println("Quiz completed! Final score: " + score + "/" + questions.size());

        Label resultLabel = new Label("Quiz completed! Your score is: " + score + "/" + questions.size());
        resultLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #0076a3;");

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(30));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(resultLabel);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
