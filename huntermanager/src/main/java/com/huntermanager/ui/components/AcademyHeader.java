package com.huntermanager.ui.components;

import com.huntermanager.Game;
import com.huntermanager.data.HunterAcademy;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class AcademyHeader extends VBox {

    private final Label headerLabel = new Label();
    private final Game game;
    private final int justSomeExtraPadding = 3;

    public AcademyHeader(Game game) {
        this.game = game;
        widthProperty().addListener((obs, oldVal, newVal) -> {
            refresh();
        });

        setPadding(new Insets(0, 0, 10, 0));

        headerLabel.setStyle("""
            -fx-font-family: 'Consolas';
            -fx-font-size: 18px;
            -fx-text-fill: white;
            """);

        getChildren().add(headerLabel);
        refresh();
    }

    public void refresh() {
        HunterAcademy academy = game.getAcademy();

        int width = Math.max(64, (int) (getWidth() / 10)); // largura total da caixa
        int innerWidth = Math.max(10, width - 2); // remove as bordas

        if (academy == null) {
            String line = "═".repeat(innerWidth);

            headerLabel.setText(
                "╔" + line + "╗\n" +
                "║" + center("JOGO NÃO INICIADO", innerWidth) + "║\n" +
                "╚" + line + "╝"
            );
            return;
        }

        String timeName = switch (academy.getCurrentDayTime()) {
            case HunterAcademy.MORNING -> "MANHÃ  ▓░░";
            case HunterAcademy.AFTERNOON -> "TARDE  ▓▓░";
            case HunterAcademy.NIGHT -> "NOITE  ▓▓▓";
            default -> "???";
        };

        String left = " DIA " + academy.getCurrentDay() + " - " + timeName;
        String right = "★ " + academy.getStars();

        int spaces = (innerWidth - left.length() - right.length()) - 1;
        spaces -= justSomeExtraPadding;
        if (spaces < 1) spaces = 1;

        String middle = " ".repeat(spaces);
        String extraPadding = " ".repeat(justSomeExtraPadding);
        String line = "═".repeat(innerWidth);

        headerLabel.setText(
            "╔" + line + "╗\n" +
            "║" + left + middle + right + extraPadding + "║\n" +
            "╠" + line + "╣"
        );
    }

    private String center(String text, int width) {
        int padding = width - text.length();
        int left = padding / 2;
        int right = padding - left;

        return " ".repeat(left) + text + " ".repeat(right);
    }
}