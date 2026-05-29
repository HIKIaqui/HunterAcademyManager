package com.huntermanager.ui;

import com.huntermanager.Game;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.enums.Trait;
import com.huntermanager.data.enums.Trauma;
import com.huntermanager.ui.components.AcademyScreenTemplate;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ClinicView {

    private final AcademyScreenTemplate root;
    private final Label contentLabel = new Label();

    public ClinicView(AppNavigator navigator, Game game) {
        HunterAcademy academy = game.getAcademy();

        Button backButton = new Button("Voltar");
        backButton.getStyleClass().add("menu-button");
        backButton.setOnAction(e -> navigator.showAcademyView());

        VBox leftMenu = new VBox(10, backButton);
        leftMenu.setPadding(new Insets(10));

        contentLabel.getStyleClass().add("details-big");
        contentLabel.setWrapText(true);

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

        sb.append("=== CLÍNICA ===\n\n");
        sb.append("Caçadores alocados aqui permanecem indisponíveis até o fim do ciclo.\n\n");

        int[] clinicSlots = academy.getClinicSlots();

        for (int i = 0; i < clinicSlots.length; i++) {
            int hunterIndex = clinicSlots[i];

            if (hunterIndex == -1) {
                sb.append(i + 1).append(" - [ Vazio ]\n\n");
                continue;
            }

            MonsterHunter hunter = academy.getHunterByIndex(hunterIndex);

            if (hunter == null) {
                sb.append(i + 1).append(" - [ Erro: caçador não encontrado ]\n\n");
                continue;
            }

            int recoveryHP = calculateHpRecovery(hunter);
            int stressChange = calculateStressChange(hunter);

            sb.append(i + 1).append(" - ").append(hunter.getName()).append("\n");
            sb.append("    HP atual: ")
              .append(hunter.getHP())
              .append("/")
              .append(hunter.getMaxHP())
              .append("\n");

            if (hunter.getTraumas().contains(Trauma.LATROFOBIA)) {
                sb.append("    Tratamento previsto: +")
                  .append(recoveryHP)
                  .append(" HP e +")
                  .append(stressChange)
                  .append(" Estresse\n");

                sb.append("    Observação: Latrofobia torna a clínica estressante.\n\n");
            } else if (stressChange > 0) {
                sb.append("    Recuperação prevista: +")
                  .append(recoveryHP)
                  .append(" HP e -")
                  .append(stressChange)
                  .append(" Estresse\n\n");
            } else {
                sb.append("    Recuperação prevista: +")
                  .append(recoveryHP)
                  .append(" HP\n\n");
            }
        }

        contentLabel.setText(sb.toString());
        root.refresh();
    }

    private int calculateHpRecovery(MonsterHunter hunter) {
        if (hunter.getTraits().contains(Trait.FAST_RECOVERY)) {
            return 8 + (hunter.getConstitution() * 4);
        }

        return 8 + (hunter.getConstitution() * 2);
    }

    private int calculateStressChange(MonsterHunter hunter) {
        if (hunter.getTraumas().contains(Trauma.LATROFOBIA)) {
            return Math.max(1, 3 - hunter.getSocial());
        }

        return Math.max(0, hunter.getSocial() - 1);
    }

    public Parent getRoot() {
        return root;
    }
}