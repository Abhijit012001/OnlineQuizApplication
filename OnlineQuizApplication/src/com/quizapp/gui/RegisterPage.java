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

public class RegisterPage {

    private UserManager userManager = new UserManager();

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Register");

        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            try {
                if (userManager.register(usernameField.getText(), passwordField.getText())) {
                    LoginPage loginPage = new LoginPage();
                    loginPage.start(primaryStage);
                } else {
                    System.out.println("Registration failed. Username may already exist.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(userLabel, usernameField, passLabel, passwordField, registerButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
