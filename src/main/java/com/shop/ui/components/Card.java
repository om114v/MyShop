package com.shop.ui.components;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Card extends VBox {
    private Label titleLabel;
    private Label descriptionLabel;
    private ModernButton actionButton;

    public Card(String title, String description, String actionText) {
        this.titleLabel = new Label(title);
        this.descriptionLabel = new Label(description);
        this.actionButton = new ModernButton(actionText);

        // Add components to VBox
        this.getChildren().addAll(titleLabel, descriptionLabel, actionButton);

        // Apply styling
        this.getStyleClass().add("card");
        this.setSpacing(10);
        this.setPrefWidth(300);
        this.setPrefHeight(200);

    }

    public void setOnAction(EventHandler<ActionEvent> handler) {
        actionButton.setOnAction(handler);
    }
}
