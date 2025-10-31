package com.shop.ui.components;

import javafx.scene.control.Button;

public class ModernButton extends Button {
    public ModernButton(String text) {
        super(text);
        this.getStyleClass().add("modern-btn");
        this.setPrefWidth(150);
        this.setPrefHeight(40);
    }
}
