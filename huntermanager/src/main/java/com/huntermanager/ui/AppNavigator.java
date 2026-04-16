package com.huntermanager.ui;

import com.huntermanager.Game;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppNavigator {

    private final Stage stage;
    private final Game game;
    private final Scene scene;

    public AppNavigator(Stage stage, Game game) {
        this.stage = stage;
        this.game = game;

        this.scene = new Scene(new javafx.scene.layout.StackPane(), 1280, 720);
        this.scene.getStylesheets().add(
            getClass().getResource("/css/style.css").toExternalForm()
        );
    }

    private void setRoot(Parent root) {
        scene.setRoot(root);

        if (stage.getScene() == null) {
            stage.setScene(scene);
        }
    }

    public void showMainMenu() {
        MainMenuView view = new MainMenuView(this, game);
        setRoot(view.getRoot());
    }

    public void showAcademyView() {
        AcademyView view = new AcademyView(this, game);
        setRoot(view.getRoot());
    }

    public void showHuntersView() {
        HuntersView view = new HuntersView(this, game);
        setRoot(view.getRoot());
    }

    public void showHunterDetailsView() {
        HunterDetailsView view = new HunterDetailsView(this, game);
        setRoot(view.getRoot());
    }

    public void showClinicView() {
        ClinicView view = new ClinicView(this, game);
        setRoot(view.getRoot());
    }

    public void showBarView() {
        BarView view = new BarView(this, game);
        setRoot(view.getRoot());
    }

    public void showStorageView() {
        StorageView view = new StorageView(this, game);
        setRoot(view.getRoot());
    }
}