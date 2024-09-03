package com.quizapp.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IndexPage {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Java Quiz Application");

        Label welcomeLabel = new Label("Welcome to the Java Quiz Application!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #0076a3;");

        Image logo = new Image("file:resources/logo.png"); // Add a logo image in the resources folder
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(100);
        logoView.setFitWidth(100);

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(200);
        loginButton.setStyle("-fx-font-size: 16px;");

        loginButton.setOnAction(e -> {
            LoginPage loginPage = new LoginPage();
            loginPage.start(primaryStage);
        });

        Button registerButton = new Button("Register");
        registerButton.setPrefWidth(200);
        registerButton.setStyle("-fx-font-size: 16px;");

        registerButton.setOnAction(e -> {
            RegisterPage registerPage = new RegisterPage();
            registerPage.start(primaryStage);
        });

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(30));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(logoView, welcomeLabel, loginButton, registerButton);

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
