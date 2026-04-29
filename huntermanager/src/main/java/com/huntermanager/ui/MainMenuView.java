package com.huntermanager.ui;

import com.huntermanager.Game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenuView {

    private final VBox root = new VBox(15);

    public MainMenuView(AppNavigator navigator, Game game) {
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("O DIRETOR NOS VALES");
        title.getStyleClass().add("main-title");

        Label subtitle = new Label("""
            ╔════════════════════════════╗
            ║  Sistema da Academia       ║
            ║  dos Caçadores dos Vales   ║
            ╚════════════════════════════╝
            """);
        subtitle.getStyleClass().add("main-subtitle");

        Button continueGame = makeMenuButton("Continuar jogo");
        Button newGame = makeMenuButton("Novo jogo");
        Button loadGame = makeMenuButton("Carregar jogo");
        Button exit = makeMenuButton("Sair");

        continueGame.setOnAction(e -> {
            try {
                System.out.println("Cliquei em Continuar jogo");
                navigator.showAcademyView();
                System.out.println("Tela da academia chamada");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        newGame.setOnAction(e -> {
            try {
                System.out.println("Cliquei em Novo jogo");
                game.setupNewGame();
                System.out.println("Jogo iniciado");
                navigator.showAcademyView();
                System.out.println("Tela da academia chamada");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        loadGame.setOnAction(e -> {
            try {
                System.out.println("Cliquei em Carregar jogo");
                navigator.showAcademyView();
                System.out.println("Tela da academia chamada");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        exit.setOnAction(e -> root.getScene().getWindow().hide());

        root.setStyle("-fx-background-color: #111111;");
        root.getChildren().addAll(title, subtitle, continueGame, newGame, loadGame, exit);
    }

    private Button makeMenuButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("menu-button");
        return button;
    }

    public Parent getRoot() {
        return root;
    }
}