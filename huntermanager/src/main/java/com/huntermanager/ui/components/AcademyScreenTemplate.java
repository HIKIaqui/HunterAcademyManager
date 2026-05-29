package com.huntermanager.ui.components;

import com.huntermanager.Game;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AcademyScreenTemplate extends BorderPane {

    private final AcademyHeader header;
    private final AcademyFeed feed;

    public AcademyScreenTemplate(Game game, Node content) {
        this.header = new AcademyHeader(game);
        this.feed = new AcademyFeed(game);

        setPadding(new Insets(20));
        setStyle("-fx-background-color: #101010;");

        VBox mainBox = new VBox(10);
        VBox.setVgrow(header, Priority.NEVER);
        header.setMaxWidth(Double.MAX_VALUE);

        mainBox.getChildren().addAll(header, content);
        VBox.setVgrow(content, Priority.ALWAYS);

        setCenter(mainBox);
        setRight(feed);

        BorderPane.setMargin(feed, new Insets(0, 0, 0, 15));
    }

    public void refresh() {
        header.refresh();
        feed.refresh();
    }

    public AcademyHeader getHeader() {
        return header;
    }

    public AcademyFeed getFeed() {
        return feed;
    }
}