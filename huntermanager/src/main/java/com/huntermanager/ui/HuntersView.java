package com.huntermanager.ui;

import com.huntermanager.Game;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.ui.components.AcademyHeader;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class HuntersView {

    private final BorderPane root = new BorderPane();
    private final HunterAcademy academy;
    private final AcademyHeader header;

    private final VBox hunterListBox = new VBox(8);
    private final Label detailsLabel = new Label();

    private MonsterHunter selectedHunter;

    public HuntersView(AppNavigator navigator, Game game) {
        this.academy = game.getAcademy();
        this.header = new AcademyHeader(game);
        VBox.setVgrow(header, Priority.NEVER);
        header.setMaxWidth(Double.MAX_VALUE);

        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #101010;");

        // TOPO
        root.setTop(header);

        // MENU ESQUERDO
        VBox leftMenu = new VBox(10);
        leftMenu.setPadding(new Insets(10));

        Button backButton = new Button("Voltar");
        backButton.getStyleClass().add("menu-button");
        backButton.setOnAction(e -> navigator.showAcademyView());

        leftMenu.getChildren().add(backButton);
        root.setLeft(leftMenu);

        // LISTA DE CAÇADORES
        hunterListBox.setPadding(new Insets(10));
        hunterListBox.setStyle("""
            -fx-background-color: #151515;
            -fx-border-color: #444444;
            -fx-border-width: 1px;
        """);

        VBox listWrapper = new VBox(10);
        Label listTitle = new Label("=== CAÇADORES ===");
        listTitle.setStyle("""
            -fx-font-family: 'Consolas';
            -fx-font-size: 16px;
            -fx-text-fill: white;
        """);

        listWrapper.getChildren().addAll(listTitle, hunterListBox);
        VBox.setVgrow(hunterListBox, Priority.ALWAYS);

        // PAINEL DE DETALHES
        detailsLabel.setStyle("""
            -fx-font-family: 'Consolas';
            -fx-font-size: 15px;
            -fx-text-fill: #dddddd;
        """);

        VBox detailPanel = new VBox(10);
        detailPanel.setPadding(new Insets(10));
        detailPanel.setStyle("""
            -fx-background-color: #151515;
            -fx-border-color: #444444;
            -fx-border-width: 1px;
        """);

        Label detailTitle = new Label("=== DETALHES ===");
        detailTitle.setStyle("""
            -fx-font-family: 'Consolas';
            -fx-font-size: 16px;
            -fx-text-fill: white;
        """);

        Button sendToClinicButton = new Button("Enviar p/ Clínica");
        Button sendToBarButton = new Button("Enviar p/ Bar");
        Button openFullDetailsButton = new Button("Ficha Completa");

        sendToClinicButton.getStyleClass().add("menu-button");
        sendToBarButton.getStyleClass().add("menu-button");
        openFullDetailsButton.getStyleClass().add("menu-button");

        sendToClinicButton.setOnAction(e -> {
            if (selectedHunter != null) {
                int index = academy.getHunterIndex(selectedHunter);
                academy.addHunterToClinic(index);
                header.refresh();
                populateHunterList(game, navigator);
                updateDetails();
            }
        });

        sendToBarButton.setOnAction(e -> {
            if (selectedHunter != null) {
                int index = academy.getHunterIndex(selectedHunter);
                academy.addHunterToBar(index);
                header.refresh();
                populateHunterList(game, navigator);
                updateDetails();
            }
        });

        openFullDetailsButton.setOnAction(e -> {
            if (selectedHunter != null) {
                game.setSelectedHunter(selectedHunter);
                navigator.showHunterDetailsView();
            }
        });

        detailPanel.getChildren().addAll(
            detailTitle,
            detailsLabel,
            sendToClinicButton,
            sendToBarButton,
            openFullDetailsButton
        );

        // CENTRO
        HBox centerContent = new HBox(15, listWrapper, detailPanel);
        centerContent.setPadding(new Insets(10));

        listWrapper.setPrefWidth(280);
        detailPanel.setPrefWidth(420);

        root.setCenter(centerContent);

        populateHunterList(game, navigator);

        if (academy != null && academy.getActiveHunters().length > 0) {
            selectedHunter = academy.getActiveHunters()[0];
        }

        updateDetails();
    }

    private void populateHunterList(Game game, AppNavigator navigator) {
        hunterListBox.getChildren().clear();

        if (academy == null) {
            Label errorLabel = new Label("Academia não inicializada.");
            errorLabel.setStyle("""
                -fx-font-family: 'Consolas';
                -fx-text-fill: #dddddd;
            """);
            hunterListBox.getChildren().add(errorLabel);
            return;
        }

        MonsterHunter[] hunters = academy.getActiveHunters();

        if (hunters.length == 0) {
            Label emptyLabel = new Label("Nenhum caçador disponível.");
            emptyLabel.setStyle("""
                -fx-font-family: 'Consolas';
                -fx-text-fill: #dddddd;
            """);
            hunterListBox.getChildren().add(emptyLabel);
            return;
        }

        for (MonsterHunter hunter : hunters) {
            int hunterIndex = academy.getHunterIndex(hunter);

            String localizacao;
            if (academy.isHunterInBar(hunterIndex)) {
                localizacao = "Bar";
            } else if (academy.isHunterInClinic(hunterIndex)) {
                localizacao = "Clínica";
            } else {
                localizacao = "Livre";
            }

            String buttonText = hunter.getName();
            if (!"Livre".equals(localizacao)) {
                buttonText += " (" + localizacao + ")";
            }

            Button hunterButton = new Button(buttonText);
            hunterButton.getStyleClass().add("menu-button");
            hunterButton.setMaxWidth(Double.MAX_VALUE);

            hunterButton.setOnAction(e -> {
                selectedHunter = hunter;
                updateDetails();
            });

            hunterListBox.getChildren().add(hunterButton);
        }
    }

    private void updateDetails() {
        if (academy == null || selectedHunter == null) {
            detailsLabel.setText("""
                Nenhum caçador selecionado.
            """);
            return;
        }

        int selectedHunterIndex = academy.getHunterIndex(selectedHunter);

        String situacao;
        if (academy.isHunterInBar(selectedHunterIndex)) {
            situacao = "No Bar";
        } else if (academy.isHunterInClinic(selectedHunterIndex)) {
            situacao = "Na Clínica";
        } else {
            situacao = "Livre";
        }

        detailsLabel.setText(
            "Nome: " + selectedHunter.getName() + "\n" +
            "HP: " + selectedHunter.getHP() + "/" + selectedHunter.getMaxHP() + "\n" +
            "PE: " + selectedHunter.getPE() + "/" + selectedHunter.getMaxPE() + "\n" +
            "Estresse: " + selectedHunter.getStress() + "/" + selectedHunter.getMaxStress() + "\n\n" +
            "--- ATRIBUTOS ---\n" +
            "Constituição: " + selectedHunter.getConstitution() + "\n" +
            "Agilidade: " + selectedHunter.getAgility() + "\n" +
            "Mente: " + selectedHunter.getMind() + "\n" +
            "Social: " + selectedHunter.getSocial() + "\n" +
            "Sorte: " + selectedHunter.getLuck() + "\n\n" +
            "Situação: " + situacao
        );
    }

    public Parent getRoot() {
        return root;
    }
}