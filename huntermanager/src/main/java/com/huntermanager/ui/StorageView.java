package com.huntermanager.ui;

import com.huntermanager.Game;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.Item;
import com.huntermanager.ui.components.AcademyHeader;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class StorageView {

    private final BorderPane root = new BorderPane();
    private final Label contentLabel = new Label();

    public StorageView(AppNavigator navigator, Game game) {
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

        refresh(academy);
    }

    private void refresh(HunterAcademy academy) {
        if (academy == null) {
            contentLabel.setText("Academia não inicializada.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("======= ARMAZÉM =======\n\n");

        for (Item item : academy.getActiveItems()) {
            String status = item.isEquipped()
                    ? " [Equipado por " + item.getEquippedBy().getName() + "]"
                    : " [Livre]";

            sb.append(item.getName())
              .append(" - ")
              .append(item.getType())
              .append(status)
              .append("\n");
        }

        contentLabel.setText(sb.toString());
    }

    public Parent getRoot() {
        return root;
    }
}