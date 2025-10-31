package com.shop.ui.layouts;

import com.shop.ui.components.IconButton;
import com.shop.ui.animations.UIAnimations;
import com.shop.ui.theme.ThemeManager;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class TopBar extends HBox {

    private Runnable menuToggleCallback;
    private ThemeManager themeManager;

    public TopBar(Runnable menuToggleCallback, ThemeManager themeManager) {
        this.menuToggleCallback = menuToggleCallback;
        this.themeManager = themeManager;

        initializeUI();
    }

    private void initializeUI() {
        // Set style class for the top bar
        this.getStyleClass().add("top-bar");
        this.setSpacing(10);
        this.setPadding(new Insets(10, 20, 10, 20));

        // Menu button with hamburger icon
        IconButton menuButton = new IconButton("☰");
        menuButton.getStyleClass().add("menu-btn");
        menuButton.setOnAction(e -> {
            if (menuToggleCallback != null) {
                menuToggleCallback.run();
            }
        });
        UIAnimations.scaleOnHover(menuButton);

        // Title label
        Label titleLabel = new Label("MyShop");
        titleLabel.getStyleClass().add("page-title");

        // Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        // Theme toggle button
        IconButton themeToggleButton = new IconButton("🌙");
        themeToggleButton.getStyleClass().add("theme-toggle");
        themeToggleButton.setOnAction(e -> {
            if (themeManager != null) {
                themeManager.toggleTheme();
            }
        });
        UIAnimations.scaleOnHover(themeToggleButton);

        // Notification button
        IconButton notificationButton = new IconButton("🔔");
        notificationButton.getStyleClass().add("icon-btn");
        UIAnimations.scaleOnHover(notificationButton);

        // Profile button
        IconButton profileButton = new IconButton("👤");
        profileButton.getStyleClass().add("icon-btn");
        UIAnimations.scaleOnHover(profileButton);

        // Add all components to the HBox
        this.getChildren().addAll(menuButton, titleLabel, spacer, themeToggleButton, notificationButton, profileButton);
    }
}
