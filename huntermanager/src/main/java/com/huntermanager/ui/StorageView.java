package com.huntermanager.ui;

import com.huntermanager.Game;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.Item;
import com.huntermanager.data.itemTypes.itemData.Equippable;
import com.huntermanager.ui.components.AcademyFeed;
import com.huntermanager.ui.components.AcademyHeader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class StorageView {

    private final BorderPane root = new BorderPane();

    private final HunterAcademy academy;
    private final AppNavigator navigator;
    private final Game game;

    private final TilePane inventoryPane = new TilePane();

    public StorageView(AppNavigator navigator, Game game) {
        this.navigator = navigator;
        this.game = game;
        this.academy = game.getAcademy();

        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #101010;");

        AcademyHeader header = new AcademyHeader(game);
        VBox.setVgrow(header, Priority.NEVER);
        header.setMaxWidth(Double.MAX_VALUE);

        AcademyFeed feed = new AcademyFeed(game);
        VBox.setVgrow(feed, Priority.NEVER);
        feed.setMaxWidth(Double.MAX_VALUE);

        Button backButton = new Button("Voltar");
        backButton.getStyleClass().add("menu-button");
        backButton.setOnAction(e -> navigator.showAcademyView());

        VBox leftMenu = new VBox(10, backButton);
        leftMenu.setPadding(new Insets(10));

        Label titleLabel = new Label("ARMAZÉM");
        titleLabel.getStyleClass().add("details-big");

        inventoryPane.setHgap(12);
        inventoryPane.setVgap(12);
        inventoryPane.setPadding(new Insets(10));
        inventoryPane.setPrefColumns(4);

        ScrollPane scrollPane = new ScrollPane(inventoryPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox centerBox = new VBox(10, titleLabel, scrollPane);
        centerBox.setPadding(new Insets(10));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        root.setTop(header);
        root.setLeft(leftMenu);
        root.setCenter(centerBox);

        refresh();
    }

    private void refresh() {
        inventoryPane.getChildren().clear();

        if (academy == null) {
            Label errorLabel = new Label("Academia não inicializada.");
            errorLabel.getStyleClass().add("details-big");
            inventoryPane.getChildren().add(errorLabel);
            return;
        }

        Item[] inventory = academy.getInventory();

        for (int i = 0; i < inventory.length; i++) {
            Item item = inventory[i];
            VBox itemCard = createItemCard(i, item);
            inventoryPane.getChildren().add(itemCard);
        }
    }

    private VBox createItemCard(int slotIndex, Item item) {
        boolean isEmpty = item == null;

        Label slotLabel = new Label("Slot " + (slotIndex + 1));
        slotLabel.getStyleClass().add("storage-slot-label");

        Label nameLabel = new Label(isEmpty ? " " : item.getName());
        nameLabel.getStyleClass().add("storage-item-name");
        nameLabel.setWrapText(true);

        Label typeLabel = new Label(isEmpty ? " " : item.getType().toString());
        typeLabel.getStyleClass().add("storage-item-type");

        Label equippedByLabel = new Label(getEquippedByText(item));
        equippedByLabel.getStyleClass().add("storage-item-type");

        Button detailsButton = new Button("Info");
        detailsButton.getStyleClass().add("menu-button-tiny");
        detailsButton.setDisable(isEmpty);

        Button equipButton = new Button("Equipar");
        equipButton.getStyleClass().add("menu-button-tiny");
        equipButton.setDisable(isEmpty);

        detailsButton.setOnAction(e -> {
            // Lógica futura: abrir painel/modal de detalhes do item
            System.out.println("Ver detalhes do item no slot " + slotIndex);
        });

        equipButton.setOnAction(e -> {
            // Lógica futura: abrir seleção de caçador para equipar
            System.out.println("Equipar item do slot " + slotIndex);
        });

        HBox buttonBox = new HBox(8, detailsButton, equipButton);
        buttonBox.setAlignment(Pos.BOTTOM_LEFT);

        VBox card = new VBox(8, slotLabel, nameLabel, typeLabel, equippedByLabel, buttonBox);
        card.setPadding(new Insets(10));
        card.setPrefSize(220, 150);
        card.setMinSize(220, 150);
        card.setMaxSize(220, 150);

        card.getStyleClass().add(isEmpty ? "storage-card-empty" : "storage-card");

        return card;
    }


//Helper
    private String getEquippedByText(Item item) {
        if (item instanceof Equippable equippable && equippable.isEquipped()) {
            return "Equipado por: " + equippable.getEquippedBy().getName();
        }

        return "No armazém";
    }
    public Parent getRoot() {
        return root;
    }
}