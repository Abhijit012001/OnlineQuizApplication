package com.quizapp.gui;

import com.quizapp.gui.IndexPage;
import javafx.application.Application;
import javafx.stage.Stage;

public class QuizApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        IndexPage indexPage = new IndexPage();
        indexPage.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
