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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BarView {

    private final BorderPane root = new BorderPane();
    private final Label contentLabel = new Label();

    public BarView(AppNavigator navigator, Game game) {
        HunterAcademy academy = game.getAcademy();

        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #101010;");

        AcademyHeader header = new AcademyHeader(game);
        VBox.setVgrow(header, Priority.NEVER);
        header.setMaxWidth(Double.MAX_VALUE);

        Button backButton = new Button("Voltar");
        backButton.getStyleClass().add("menu-button");
        backButton.setOnAction(e -> navigator.showAcademyView());

        VBox leftMenu = new VBox(10, backButton);
        leftMenu.setPadding(new Insets(10));

        contentLabel.getStyleClass().add("details-big");

        VBox centerBox = new VBox(10, contentLabel);
        centerBox.setPadding(new Insets(10));

        root.setTop(header);
        root.setLeft(leftMenu);
        root.setCenter(centerBox);

        refresh(academy);
    }

    private void refresh(HunterAcademy academy) {
        if (academy == null) {
            contentLabel.setText("Academia não inicializada.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== BAR ===\n\n");
        sb.append("Caçadores alocados aqui permanecem indisponíveis até o fim do ciclo.\n\n");

        int[] barSlots = academy.getBarSlots();

        for (int i = 0; i < barSlots.length; i++) {
            int hunterIndex = barSlots[i];

            if (hunterIndex == -1) {
                sb.append(i + 1).append(" - [Vazio]\n\n");
            } else {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);
                int recoveryStress = 1 + hunter.getSocial();
                int recoveryPE = 2 * Math.max(0, hunter.getMind() - 1);

                sb.append(i + 1).append(" - ").append(hunter.getName()).append("\n");
                sb.append("    Estresse atual: ").append(hunter.getStress()).append("/").append(hunter.getMaxStress()).append("\n");

                if (recoveryPE > 0) {
                    sb.append("    Recuperação prevista: -").append(recoveryStress)
                      .append(" Estresse e +").append(recoveryPE).append(" PE\n\n");
                } else {
                    sb.append("    Recuperação prevista: -").append(recoveryStress).append(" Estresse\n\n");
                }
            }
        }

        contentLabel.setText(sb.toString());
    }

    public Parent getRoot() {
        return root;
    }
}