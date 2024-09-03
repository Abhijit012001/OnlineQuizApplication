package com.quizapp.gui;

import com.quizapp.dao.UserManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginPage {

    private UserManager userManager = new UserManager();

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            try {
                boolean loginSuccessful = userManager.login(usernameField.getText(), passwordField.getText());
                System.out.println("Login successful: " + loginSuccessful);
                if (loginSuccessful) {
                    System.out.println("Initializing QuizPage...");
                    QuizPage quizPage = new QuizPage();
                    quizPage.start(primaryStage);  
                } else {
                    System.out.println("Login failed. Incorrect username or password.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(userLabel, usernameField, passLabel, passwordField, loginButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
