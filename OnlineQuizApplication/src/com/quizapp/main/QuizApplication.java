package com.quizapp.main;

import com.quizapp.dao.QuestionDAO;
import com.quizapp.dao.UserManager;
import com.quizapp.model.Question;
import com.quizapp.utils.DatabaseUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class QuizApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static UserManager userManager = new UserManager();

    public static void main(String[] args) {
        if (!DatabaseUtil.connect()) {
            System.out.println("Failed to connect to the database.");
            return;
        }

        try {
            loginOrRegister();
            startQuiz();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.disconnect();
        }
    }

    private static void loginOrRegister() throws SQLException {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                loginUser();
                break;
            case 2:
                registerUser();
                break;
            default:
                System.out.println("Invalid choice.");
                loginOrRegister();
                break;
        }
    }

    private static void loginUser() throws SQLException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (!userManager.login(username, password)) {
            System.out.println("Login failed.");
            loginOrRegister();
        }
    }

    private static void registerUser() throws SQLException {
        System.out.print("Enter desired username: ");
        String username = scanner.nextLine();
        System.out.print("Enter desired password: ");
        String password = scanner.nextLine();

        if (userManager.register(username, password)) {
            System.out.println("Registration successful. You can now log in.");
            loginUser();
        } else {
            System.out.println("Registration failed. Please try again.");
            loginOrRegister();
        }
    }

    private static void startQuiz() throws SQLException {
        QuestionDAO questionDAO = new QuestionDAO();
        List<Question> questions = questionDAO.getAllQuestions();
        int score = 0;

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            System.out.println("A. " + question.getOptionA());
            System.out.println("B. " + question.getOptionB());
            System.out.println("C. " + question.getOptionC());
            System.out.println("D. " + question.getOptionD());
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase(question.getCorrectOption())) {
                score++;
            }
        }

        System.out.println("Quiz completed! Your score is: " + score + "/" + questions.size());
    }
}
