package com.huntermanager.ui.components;

import com.huntermanager.Game;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.enums.AcademyFeedType;
import com.huntermanager.data.feed.AcademyFeedEntry;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class AcademyFeed extends VBox {

    private final Game game;
    private final Label titleLabel = new Label("CORREDORES");
    private final VBox entriesBox = new VBox(6);
    private final ScrollPane scrollPane = new ScrollPane(entriesBox);

    public AcademyFeed(Game game) {
        this.game = game;

        setPrefWidth(320);
        setMinWidth(260);
        setMaxWidth(360);

        setPadding(new Insets(10));
        setSpacing(8);

        setStyle("""
            -fx-background-color: #111111;
            -fx-border-color: #555555;
            -fx-border-width: 1px;
            """);

        titleLabel.setStyle("""
            -fx-font-family: 'Consolas';
            -fx-font-size: 16px;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            """);

        entriesBox.setPadding(new Insets(4));
        entriesBox.setStyle("""
            -fx-background-color: #111111;
            """);

        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setStyle("""
            -fx-background: #111111;
            -fx-background-color: #111111;
            -fx-border-color: transparent;
            """);

        getChildren().addAll(titleLabel, scrollPane);

        refresh();
    }

    public void refresh() {
        entriesBox.getChildren().clear();

        HunterAcademy academy = game.getAcademy();

        if (academy == null) {
            entriesBox.getChildren().add(createEntryLabel(
                "Jogo não iniciado.\nAté a desgraça precisa de um começo.",
                AcademyFeedType.SYSTEM
            ));
            return;
        }

        if (academy.getFeedEntries().isEmpty()) {
            entriesBox.getChildren().add(createEntryLabel(
                "A academia está quieta.\nIsso raramente é um elogio.",
                AcademyFeedType.SYSTEM
            ));
            return;
        }

        for (AcademyFeedEntry entry : academy.getFeedEntries()) {
            entriesBox.getChildren().add(createEntryLabel(formatEntry(entry), entry.getType()));
        }

        scrollPane.layout();
        scrollPane.setVvalue(1.0);
    }

    private String formatEntry(AcademyFeedEntry entry) {
        return "[Dia " + entry.getDay() + " - " + entry.getTimeLabel() + "]\n" + entry.getText();
    }

    private Label createEntryLabel(String text, AcademyFeedType type) {
        Label label = new Label(text);

        label.setWrapText(true);
        label.setMaxWidth(Double.MAX_VALUE);

        label.setStyle("""
            -fx-font-family: 'Consolas';
            -fx-font-size: 13px;
            -fx-text-fill: %s;
            -fx-background-color: #1a1a1a;
            -fx-border-color: #333333;
            -fx-border-width: 1px;
            -fx-padding: 7px;
            """.formatted(getTextColor(type)));

        return label;
    }

    private String getTextColor(AcademyFeedType type) {
        return switch (type) {
            case WARNING -> "#ffcc66";
            case DEATH -> "#aaaaaa";
            case CLINIC -> "#99ddff";
            case BAR -> "#d6a86c";
            case CONTRACT -> "#ff9966";
            case HUNTER -> "#eeeeee";
            case FLAVOR -> "#cccccc";
            case SYSTEM -> "#bbbbbb";
        };
    }
}