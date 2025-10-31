package com.shop.ui.theme;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.util.Duration;

public class ThemeManager {

    private Scene currentScene;
    private boolean isDarkMode = false;

    public void setScene(Scene scene) {
        this.currentScene = scene;
    }

    public void toggleTheme() {
        if (currentScene == null) return;
        isDarkMode = !isDarkMode;
        currentScene.getStylesheets().clear();
        String cssPath = isDarkMode ? "/styles/dark-theme.css" : "/styles/styles.css";
        currentScene.getStylesheets().add(ThemeManager.class.getResource(cssPath).toExternalForm());

        // Smooth transition effect
        FadeTransition ft = new FadeTransition(Duration.millis(200), currentScene.getRoot());
        ft.setFromValue(0.8);
        ft.setToValue(1.0);
        ft.play();
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }
}
