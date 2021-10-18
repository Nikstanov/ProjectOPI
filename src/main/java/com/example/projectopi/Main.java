package com.example.projectopi;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.Objects;


public class Main extends Application {


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setTitle("Математический тренажер");
        stage.setWidth(400);
        stage.setHeight(500);
        stage.setResizable(false);
        stage.show();

        super.stop();
    }
}