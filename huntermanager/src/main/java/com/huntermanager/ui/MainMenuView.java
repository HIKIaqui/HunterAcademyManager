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
        title.setStyle("""
            -fx-font-family: 'Consolas';
            -fx-font-size: 28px;
            -fx-text-fill: white;
            """);

        Label subtitle = new Label("""
            ╔════════════════════════════╗
            ║  Sistema da Academia       ║
            ║  dos Caçadores dos Vales   ║
            ╚════════════════════════════╝
            """);
        subtitle.setStyle("""
            -fx-font-family: 'Consolas';
            -fx-font-size: 14px;
            -fx-text-fill: #bbbbbb;
            """);

        Button newGame = makeMenuButton("Novo jogo");
        Button exit = makeMenuButton("Sair");

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

        exit.setOnAction(e -> root.getScene().getWindow().hide());

        root.setStyle("-fx-background-color: #111111;");
        root.getChildren().addAll(title, subtitle, newGame, exit);
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