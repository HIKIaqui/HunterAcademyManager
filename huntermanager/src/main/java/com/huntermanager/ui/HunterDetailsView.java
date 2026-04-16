package com.huntermanager.ui;

import com.huntermanager.Game;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.enums.Trait;
import com.huntermanager.data.enums.Trauma;
import com.huntermanager.ui.components.AcademyHeader;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class HunterDetailsView {

    private final BorderPane root = new BorderPane();
    private final Label contentLabel = new Label();

    public HunterDetailsView(AppNavigator navigator, Game game) {
        HunterAcademy academy = game.getAcademy();
        MonsterHunter selectedHunter = game.getSelectedHunter();

        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #101010;");

        AcademyHeader header = new AcademyHeader(game);
        VBox.setVgrow(header, Priority.NEVER);
        header.setMaxWidth(Double.MAX_VALUE);

        VBox leftMenu = new VBox(10);
        leftMenu.setPadding(new Insets(10));

        Button clinicButton = new Button("Enviar para Clínica");
        Button barButton = new Button("Enviar para Bar");
        Button backButton = new Button("Voltar");

        clinicButton.getStyleClass().add("menu-button");
        barButton.getStyleClass().add("menu-button");
        backButton.getStyleClass().add("menu-button");

        if (academy != null && selectedHunter != null) {
            int selectedHunterIndex = academy.getHunterIndex(selectedHunter);

            clinicButton.setOnAction(e -> {
                academy.addHunterToClinic(selectedHunterIndex);
                navigator.showHunterDetailsView();
            });

            barButton.setOnAction(e -> {
                academy.addHunterToBar(selectedHunterIndex);
                navigator.showHunterDetailsView();
            });
        }

        backButton.setOnAction(e -> navigator.showHuntersView());

        leftMenu.getChildren().addAll(clinicButton, barButton, backButton);

        contentLabel.setStyle("""
            -fx-font-family: 'Consolas';
            -fx-font-size: 15px;
            -fx-text-fill: #dddddd;
        """);

        VBox centerBox = new VBox(10, contentLabel);
        centerBox.setPadding(new Insets(10));

        root.setTop(header);
        root.setLeft(leftMenu);
        root.setCenter(centerBox);

        refresh(academy, selectedHunter);
    }

    private void refresh(HunterAcademy academy, MonsterHunter selectedHunter) {
        if (academy == null || selectedHunter == null) {
            contentLabel.setText("Nenhum caçador selecionado.");
            return;
        }

        int selectedHunterIndex = academy.getHunterIndex(selectedHunter);

        StringBuilder sb = new StringBuilder();

        sb.append("=== FICHA DO CAÇADOR ===\n\n");
        sb.append("Nome: ").append(selectedHunter.getName()).append("\n");
        sb.append("HP: ").append(selectedHunter.getHP()).append("/").append(selectedHunter.getMaxHP()).append("\n");
        sb.append("PE: ").append(selectedHunter.getPE()).append("/").append(selectedHunter.getMaxPE()).append("\n");
        sb.append("Estresse: ").append(selectedHunter.getStress()).append("/").append(selectedHunter.getMaxStress()).append("\n\n");

        sb.append("--- ATRIBUTOS ---\n");
        sb.append("Constituição: ").append(selectedHunter.getConstitution()).append("\n");
        sb.append("Agilidade: ").append(selectedHunter.getAgility()).append("\n");
        sb.append("Mente: ").append(selectedHunter.getMind()).append("\n");
        sb.append("Social: ").append(selectedHunter.getSocial()).append("\n");
        sb.append("Sorte: ").append(selectedHunter.getLuck()).append("\n\n");

        sb.append("--- CARACTERÍSTICAS ---\n");
        if (selectedHunter.getTraits().isEmpty()) {
            sb.append("Nenhuma.\n");
        } else {
            for (Trait trait : selectedHunter.getTraits()) {
                sb.append("-> ").append(trait.getDisplayName()).append("\n");
            }
        }

        sb.append("\n--- TRAUMAS ---\n");
        if (selectedHunter.getTraumas().isEmpty()) {
            sb.append("Nenhum.\n");
        } else {
            for (Trauma trauma : selectedHunter.getTraumas()) {
                sb.append("-> ").append(trauma.getDisplayName()).append("\n");
            }
        }

        sb.append("\n--- SITUAÇÃO ---\n");
        if (academy.isHunterInBar(selectedHunterIndex)) {
            sb.append("< No Bar até o fim do Ciclo >");
        } else if (academy.isHunterInClinic(selectedHunterIndex)) {
            sb.append("< Na Clínica até o fim do Ciclo >");
        } else {
            sb.append("< Livre >");
        }

        contentLabel.setText(sb.toString());
    }

    public Parent getRoot() {
        return root;
    }
}