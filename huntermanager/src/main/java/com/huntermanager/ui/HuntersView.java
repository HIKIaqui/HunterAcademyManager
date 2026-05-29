package com.huntermanager.ui;

import com.huntermanager.Game;
import com.huntermanager.data.HunterAcademy;
import com.huntermanager.data.Item;
import com.huntermanager.data.MonsterHunter;
import com.huntermanager.data.enums.EquipmentSlot;
import com.huntermanager.data.itemTypes.itemData.Equippable;
import com.huntermanager.ui.components.AcademyHeader;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class HuntersView {

    private final BorderPane root = new BorderPane();
    private final HunterAcademy academy;
    private final AcademyHeader header;

    private final VBox hunterListBox = new VBox(8);
    private final Label detailsLabel = new Label();

    private final Label weaponLabel = new Label();
    private final Label suitLabel = new Label();
    private final Label accessoryLabel = new Label();

    private MonsterHunter selectedHunter;

    public HuntersView(AppNavigator navigator, Game game) {
        this.academy = game.getAcademy();
        this.header = new AcademyHeader(game);
        VBox.setVgrow(header, Priority.NEVER);
        header.setMaxWidth(Double.MAX_VALUE);

        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #101010;");

        // TOPO
        root.setTop(header);

        // MENU ESQUERDO
        VBox leftMenu = new VBox(10);
        leftMenu.setPadding(new Insets(10));

        Button backButton = new Button("Voltar");
        backButton.getStyleClass().add("menu-button");
        backButton.setOnAction(e -> navigator.showAcademyView());

        leftMenu.getChildren().add(backButton);
        root.setLeft(leftMenu);

        // LISTA DE CAÇADORES

            VBox listWrapper = new VBox(10);
            Label listTitle = new Label("=== CAÇADORES ===");
            listTitle.getStyleClass().add("details-big");

            listWrapper.getChildren().addAll(listTitle, hunterListBox);
            VBox.setVgrow(hunterListBox, Priority.ALWAYS);
            listWrapper.setPadding(new Insets(10));
            listWrapper.getStyleClass().add("box-background-default");



        // PAINEL DE DETALHES
            detailsLabel.getStyleClass().add("details-small");

            VBox detailPanel = new VBox(10);
            detailPanel.setPadding(new Insets(10));
            detailPanel.getStyleClass().add("box-background-default");

            Label detailTitle = new Label("=== DETALHES ===");
            detailTitle.getStyleClass().add("details-big");

            Button sendToClinicButton = new Button("Enviar p/ Clínica");
            Button sendToBarButton = new Button("Enviar p/ Bar");
            Button openFullDetailsButton = new Button("Ficha Completa");

            sendToClinicButton.getStyleClass().add("menu-button-small");
            sendToBarButton.getStyleClass().add("menu-button-small");
            openFullDetailsButton.getStyleClass().add("menu-button-small");



                sendToClinicButton.setOnAction(e -> {
                    if (selectedHunter != null) {
                        int index = academy.getHunterIndex(selectedHunter);
                        academy.addHunterToClinic(index);
                        header.refresh();
                        populateHunterList(game, navigator);
                        updateDetails();
                    }
                });

                sendToBarButton.setOnAction(e -> {
                    if (selectedHunter != null) {
                        int index = academy.getHunterIndex(selectedHunter);
                        academy.addHunterToBar(index);
                        header.refresh();
                        populateHunterList(game, navigator);
                        updateDetails();
                    }
                });

                openFullDetailsButton.setOnAction(e -> {
                    if (selectedHunter != null) {
                        game.setSelectedHunter(selectedHunter);
                        navigator.showHunterDetailsView();
                    }
                });

        detailPanel.getChildren().addAll(
            detailTitle,
            detailsLabel,
            sendToClinicButton,
            sendToBarButton,
            openFullDetailsButton
        );

        // PAINEL DE EQUIPAMENTO
        VBox equipmentPanel = new VBox(10);
            equipmentPanel.setPadding(new Insets(10));
            equipmentPanel.getStyleClass().add("box-background-default");

            Label equipmentTitle = new Label("=== EQUIPAMENTO ===");
            equipmentTitle.getStyleClass().add("details-big");



        VBox weaponBox = createEquipmentSlotBox(
            "Arma",
            weaponLabel,
            "Equipar Arma",
            EquipmentSlot.WEAPON,
            navigator
        );

        VBox suitBox = createEquipmentSlotBox(
            "Traje",
            suitLabel,
            "Equipar Traje",
            EquipmentSlot.SUIT,
            navigator
        );

        VBox accessoryBox = createEquipmentSlotBox(
            "Acessório",
            accessoryLabel,
            "Equipar Acessório",
            EquipmentSlot.ACCESSORY,
            navigator
        );

        equipmentPanel.getChildren().addAll(
            equipmentTitle,
            weaponBox,
            suitBox,
            accessoryBox
        );

        // CENTRO
            HBox centerContent = new HBox(15, listWrapper, detailPanel, equipmentPanel);
            centerContent.setPadding(new Insets(10));

            listWrapper.setPrefWidth(280);
            detailPanel.setPrefWidth(420);

            root.setCenter(centerContent);

            populateHunterList(game, navigator);

            if (academy != null && academy.getActiveHunters().length > 0) {
                selectedHunter = academy.getActiveHunters()[0];
            }

            updateDetails();
        }

    private void populateHunterList(Game game, AppNavigator navigator) {
        hunterListBox.getChildren().clear();

        if (academy == null) {
            Label errorLabel = new Label("Academia não inicializada.");
            errorLabel.getStyleClass().add("default");
            hunterListBox.getChildren().add(errorLabel);
            return;
        }

        MonsterHunter[] hunters = academy.getActiveHunters();

        if (hunters.length == 0) {
            Label emptyLabel = new Label("Nenhum caçador disponível.");
            emptyLabel.getStyleClass().add("default");
            hunterListBox.getChildren().add(emptyLabel);
            return;
        }

        for (MonsterHunter hunter : hunters) {
            int hunterIndex = academy.getHunterIndex(hunter);

            String localizacao;
            if (academy.isHunterInBar(hunterIndex)) {
                localizacao = "Bar";
            } else if (academy.isHunterInClinic(hunterIndex)) {
                localizacao = "Clínica";
            } else {
                localizacao = "Livre";
            }

            String buttonText = hunter.getName();
            if (!"Livre".equals(localizacao)) {
                buttonText += " (" + localizacao + ")";
            }

            Button hunterButton = new Button(buttonText);
            hunterButton.getStyleClass().add("menu-button");
            hunterButton.setMaxWidth(Double.MAX_VALUE);

            hunterButton.setOnAction(e -> {
                selectedHunter = hunter;
                updateDetails();
            });

            hunterListBox.getChildren().add(hunterButton);
        }
    }


    // Helper

    private String getItemDisplayName(Equippable item, String emptyText) {
        if (item == null) {
            return emptyText;
        }

        if (item instanceof Item realItem) {
            return realItem.getName();
        }

        return "Item equipado";
    }

    // Cria a caixa da UI
    private VBox createEquipmentSlotBox(
        String slotName,
        Label itemLabel,
        String equipButtonText,
        EquipmentSlot slot,
        AppNavigator navigator
    ) {
        Label slotTitle = new Label(slotName);
        slotTitle.getStyleClass().add("details-small");

        itemLabel.getStyleClass().add("default");
        itemLabel.setWrapText(true);

        Button equipButton = new Button(equipButtonText);
        equipButton.getStyleClass().add("menu-button-small");
        equipButton.setMaxWidth(Double.MAX_VALUE);

        Button unequipButton = new Button("Desequipar");
        unequipButton.getStyleClass().add("menu-button-small");
        unequipButton.setMaxWidth(Double.MAX_VALUE);

        equipButton.setOnAction(e -> {
            if (selectedHunter != null) {
                navigator.showHunterEquipItemView(selectedHunter, slot);
            }
        });

        unequipButton.setOnAction(e -> {
            if (selectedHunter != null) {
                selectedHunter.unequip(slot);
                updateDetails();
            }
        });

        VBox box = new VBox(6, slotTitle, itemLabel, equipButton, unequipButton);
        box.setPadding(new Insets(8));
        box.getStyleClass().add("equipment-slot-box");

        return box;
    }


//Autoexplicativo
    private void updateEquipmentPanel() {
        if (selectedHunter == null) {
            weaponLabel.setText("Nenhuma arma equipada.");
            suitLabel.setText("Nenhum traje equipado.");
            accessoryLabel.setText("Nenhum acessório equipado.");
            return;
        }

        weaponLabel.setText(getItemDisplayName(selectedHunter.getEquippedWeapon(), "Nenhuma arma equipada."));
        suitLabel.setText(getItemDisplayName(selectedHunter.getEquippedSuit(), "Nenhum traje equipado."));
        accessoryLabel.setText(getItemDisplayName(selectedHunter.getEquippedAccessory(), "Nenhum acessório equipado."));
    }

    private void updateDetails() {
        if (academy == null || selectedHunter == null) {
            detailsLabel.setText("""
                Nenhum caçador selecionado.
            """);
            updateEquipmentPanel();

            weaponLabel.setText("Nenhuma arma equipada.");
            suitLabel.setText("Nenhuma armadura equipada.");
            accessoryLabel.setText("Nenhum acessório equipado.");

            return;
        }

        int selectedHunterIndex = academy.getHunterIndex(selectedHunter);

        String situacao;
        if (academy.isHunterInBar(selectedHunterIndex)) {
            situacao = "No Bar";
        } else if (academy.isHunterInClinic(selectedHunterIndex)) {
            situacao = "Na Clínica";
        } else {
            situacao = "Livre";
        }

        detailsLabel.setText(
            "Nome: " + selectedHunter.getName() + "\n" +
            "HP: " + selectedHunter.getHP() + "/" + selectedHunter.getMaxHP() + "\n" +
            "PE: " + selectedHunter.getPE() + "/" + selectedHunter.getMaxPE() + "\n" +
            "Estresse: " + selectedHunter.getStress() + "/" + selectedHunter.getMaxStress() + "\n\n" +
            "--- ATRIBUTOS ---\n" +
            "Constituição: " + selectedHunter.getConstitution() + "\n" +
            "Agilidade: " + selectedHunter.getAgility() + "\n" +
            "Mente: " + selectedHunter.getMind() + "\n" +
            "Social: " + selectedHunter.getSocial() + "\n" +
            "Sorte: " + selectedHunter.getLuck() + "\n\n" +
            "Situação: " + situacao
        );

        updateEquipmentPanel();
    }
    

    public Parent getRoot() {
        return root;
    }
}