package com.huntermanager.ui;

import com.huntermanager.Game;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.ui.components.AcademyHeader;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AcademyView {

    private final BorderPane root = new BorderPane();

    private final Label infoLabel = new Label();
    private final HunterAcademy academy;
    private final AcademyHeader academyHeader;
    
    

    public AcademyView(AppNavigator navigator, Game game) {
        
        this.academy = game.getAcademy();
        this.academyHeader = new AcademyHeader(game);
        VBox.setVgrow(academyHeader, Priority.NEVER);
        academyHeader.setMaxWidth(Double.MAX_VALUE);

        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #101010;");

        VBox leftMenu = new VBox(10);
        leftMenu.setPadding(new Insets(10));

        Button huntersButton = makeButton("Caçadores");
        Button assignmentsButton = makeButton("Contratos");
        Button clinicButton = makeButton("Clínica");
        Button barButton = makeButton("Bar");
        Button storageButton = makeButton("Armazém");
        Button advanceTimeButton = makeButton("Avançar tempo");
        Button backButton = makeButton("Voltar ao menu");

        advanceTimeButton.setOnAction(e -> {
            academy.advanceDayTime();
            academyHeader.refresh();
            refresh();
        });

        backButton.setOnAction(e -> navigator.showMainMenu());

        huntersButton.setOnAction(e -> navigator.showHuntersView());
        clinicButton.setOnAction(e -> navigator.showClinicView());
        barButton.setOnAction(e -> navigator.showBarView());
        storageButton.setOnAction(e -> navigator.showStorageView());

        leftMenu.getChildren().addAll(
            huntersButton,
            assignmentsButton,
            clinicButton,
            barButton,
            storageButton,
            advanceTimeButton,
            backButton
        );

        VBox centerBox = new VBox(15, infoLabel);
        centerBox.setPadding(new Insets(10));

        infoLabel.setStyle("""
            -fx-font-family: 'Consolas';
            -fx-font-size: 16px;
            -fx-text-fill: #cccccc;
            """);

        root.setTop(academyHeader);
        root.setLeft(leftMenu);
        root.setCenter(centerBox);

        refresh();
    }

    private void refresh() {
        if (academy == null) {
            infoLabel.setText("Academia não inicializada.");
            return;
        }

        infoLabel.setText("""
            ║ Isso aqui existe!
            """);
    }

    private Button makeButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("menu-button");
        return button;
    }

    public Parent getRoot() {
        return root;
    }
}