package com.huntermanager.ui;

import com.huntermanager.Game;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.ui.components.AcademyScreenTemplate;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class AcademyView {
    
    private final AcademyScreenTemplate root;

    private final Label infoLabel = new Label();
    private final HunterAcademy academy;
    
    public AcademyView(AppNavigator navigator, Game game) {
        this.academy = game.getAcademy();

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
            if (academy != null) {
                academy.advanceDayTime();
            }

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

        infoLabel.getStyleClass().add("default-big");

        VBox centerBox = new VBox(15, infoLabel);
        centerBox.setPadding(new Insets(10));

        BorderPane content = new BorderPane();
        content.setLeft(leftMenu);
        content.setCenter(centerBox);

        this.root = new AcademyScreenTemplate(game, content);

        refresh();
    }

    private void refresh() {
        if (academy == null) {
            infoLabel.setText("Academia não inicializada.");
            root.refresh();
            return;
        }

        infoLabel.setText("""
            ║ Isso aqui existe!
            """);

        root.refresh();
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