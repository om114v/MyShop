package com.shop.ui.components;

import javafx.scene.control.Button;

public class IconButton extends Button {
    public IconButton(String text) {
        super(text);
        this.getStyleClass().add("icon-btn");
        this.setPrefWidth(50);
        this.setPrefHeight(40);
    }
}
