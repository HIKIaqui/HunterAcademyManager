package com.huntermanager;

import com.huntermanager.ui.AppNavigator;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Game game = new Game();
        AppNavigator navigator = new AppNavigator(stage, game);

        stage.setTitle("O Diretor nos Vales");
        navigator.showMainMenu();
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}