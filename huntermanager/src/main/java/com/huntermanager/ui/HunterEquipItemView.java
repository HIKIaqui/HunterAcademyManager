package com.huntermanager.ui;

import com.huntermanager.Game;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.Item;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.enums.EquipmentSlot;
import com.huntermanager.data.itemTypes.itemData.Equippable;
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

public class HunterEquipItemView {

    private final BorderPane root = new BorderPane();

    private final AppNavigator navigator;
    private final Game game;
    private final HunterAcademy academy;
    private final MonsterHunter hunter;
    private final EquipmentSlot targetSlot;

    private final TilePane inventoryPane = new TilePane();

    public HunterEquipItemView(
        AppNavigator navigator,
        Game game,
        MonsterHunter hunter,
        EquipmentSlot targetSlot
    ) {
        this.navigator = navigator;
        this.game = game;
        this.academy = game.getAcademy();
        this.hunter = hunter;
        this.targetSlot = targetSlot;

        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #101010;");

        AcademyHeader header = new AcademyHeader(game);
        VBox.setVgrow(header, Priority.NEVER);
        header.setMaxWidth(Double.MAX_VALUE);

        Button backButton = new Button("Voltar");
        backButton.getStyleClass().add("menu-button");
        backButton.setOnAction(e -> navigator.showHuntersView());

        VBox leftMenu = new VBox(10, backButton);
        leftMenu.setPadding(new Insets(10));

        Label titleLabel = new Label(getTitleText());
        titleLabel.getStyleClass().add("details-big");

        Label subtitleLabel = new Label(getSubtitleText());
        subtitleLabel.getStyleClass().add("details-small");

        inventoryPane.setHgap(12);
        inventoryPane.setVgap(12);
        inventoryPane.setPadding(new Insets(10));
        inventoryPane.setPrefColumns(4);

        ScrollPane scrollPane = new ScrollPane(inventoryPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox centerBox = new VBox(10, titleLabel, subtitleLabel, scrollPane);
        centerBox.setPadding(new Insets(10));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        root.setTop(header);
        root.setLeft(leftMenu);
        root.setCenter(centerBox);

        refresh();
    }

    private String getTitleText() {
        return "EQUIPAR " + getSlotDisplayName().toUpperCase();
    }

    private String getSubtitleText() {
        if (hunter == null) {
            return "Nenhum caçador selecionado.";
        }

        return "Caçador: " + hunter.getName();
    }

    private String getSlotDisplayName() {
        return switch (targetSlot) {
            case WEAPON -> "Arma";
            case SUIT -> "Traje";
            case ACCESSORY -> "Acessório";
        };
    }

    private void refresh() {
        inventoryPane.getChildren().clear();

        if (academy == null) {
            Label errorLabel = new Label("Academia não inicializada.");
            errorLabel.getStyleClass().add("details-big");
            inventoryPane.getChildren().add(errorLabel);
            return;
        }

        if (hunter == null) {
            Label errorLabel = new Label("Nenhum caçador selecionado.");
            errorLabel.getStyleClass().add("details-big");
            inventoryPane.getChildren().add(errorLabel);
            return;
        }

        Item[] inventory = academy.getInventory();

        boolean foundCompatibleItem = false;

        for (int i = 0; i < inventory.length; i++) {
            Item item = inventory[i];

            if (!isCompatibleItem(item)) {
                continue;
            }

            VBox itemCard = createItemCard(i, item);
            inventoryPane.getChildren().add(itemCard);
            foundCompatibleItem = true;
        }

        if (!foundCompatibleItem) {
            Label emptyLabel = new Label("Nenhum item compatível encontrado no armazém.");
            emptyLabel.getStyleClass().add("details-small");
            inventoryPane.getChildren().add(emptyLabel);
        }
    }

    private boolean isCompatibleItem(Item item) {
        if (item == null) {
            return false;
        }

        if (!(item instanceof Equippable equippable)) {
            return false;
        }

        return equippable.getSlot() == targetSlot;
    }

    private VBox createItemCard(int slotIndex, Item item) {
        Equippable equippable = (Equippable) item;

        Label slotLabel = new Label("Slot " + (slotIndex + 1));
        slotLabel.getStyleClass().add("storage-slot-label");

        Label nameLabel = new Label(item.getName());
        nameLabel.getStyleClass().add("storage-item-name");
        nameLabel.setWrapText(true);

        Label typeLabel = new Label(item.getType().toString());
        typeLabel.getStyleClass().add("storage-item-type");

        Button equipButton = new Button("Equipar");
        equipButton.getStyleClass().add("menu-button-tiny");

        equipButton.setOnAction(e -> {
            hunter.equip(equippable);

            // Por enquanto, só equipa.
            // Depois dá pra decidir se o item sai do inventário ou continua no armazém.
            navigator.showHuntersView();
        });

        HBox buttonBox = new HBox(8, equipButton);
        buttonBox.setAlignment(Pos.BOTTOM_LEFT);

        VBox card = new VBox(8, slotLabel, nameLabel, typeLabel, buttonBox);
        card.setPadding(new Insets(10));
        card.setPrefSize(220, 135);
        card.setMinSize(220, 135);
        card.setMaxSize(220, 135);
        card.getStyleClass().add("storage-card");

        return card;
    }

    public Parent getRoot() {
        return root;
    }
}