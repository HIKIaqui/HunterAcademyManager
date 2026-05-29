package com.huntermanager.ui;

import com.huntermanager.Game;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.ui.components.AcademyScreenTemplate;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BarView {

    private final AcademyScreenTemplate root;
    private final Label contentLabel = new Label();

    public BarView(AppNavigator navigator, Game game) {
        HunterAcademy academy = game.getAcademy();

        Button backButton = new Button("Voltar");
        backButton.getStyleClass().add("menu-button");
        backButton.setOnAction(e -> navigator.showAcademyView());

        VBox leftMenu = new VBox(10, backButton);
        leftMenu.setPadding(new Insets(10));

        contentLabel.getStyleClass().add("details-big");

        VBox centerBox = new VBox(10, contentLabel);
        centerBox.setPadding(new Insets(10));
        VBox.setVgrow(contentLabel, Priority.ALWAYS);

        BorderPane content = new BorderPane();
        content.setLeft(leftMenu);
        content.setCenter(centerBox);

        this.root = new AcademyScreenTemplate(game, content);

        refresh(academy);
    }

    private void refresh(HunterAcademy academy) {
        if (academy == null) {
            contentLabel.setText("Academia não inicializada.");
            root.refresh();
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== BAR ===\n\n");
        sb.append("Caçadores alocados aqui permanecem indisponíveis até o fim do ciclo.\n\n");

        int[] barSlots = academy.getBarSlots();

        for (int i = 0; i < barSlots.length; i++) {
            int hunterIndex = barSlots[i];

            if (hunterIndex == -1) {
                sb.append(i + 1).append(" - [ Vazio ]\n\n");
            } else {
                MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);

                if (hunter == null) {
                    sb.append(i + 1).append(" - [ Erro: caçador não encontrado ]\n\n");
                    continue;
                }

                int recoveryStress = 1 + hunter.getSocial();
                int recoveryPE = 2 * Math.max(0, hunter.getMind() - 1);

                sb.append(i + 1).append(" - ").append(hunter.getName()).append("\n");
                sb.append("    Estresse atual: ")
                  .append(hunter.getStress())
                  .append("/")
                  .append(hunter.getMaxStress())
                  .append("\n");

                if (recoveryPE > 0) {
                    sb.append("    Recuperação prevista: -")
                      .append(recoveryStress)
                      .append(" Estresse e +")
                      .append(recoveryPE)
                      .append(" PE\n\n");
                } else {
                    sb.append("    Recuperação prevista: -")
                      .append(recoveryStress)
                      .append(" Estresse\n\n");
                }
            }
        }

        contentLabel.setText(sb.toString());
        root.refresh();
    }

    public Parent getRoot() {
        return root;
    }
}