package com.shop.ui.components;

import javafx.scene.control.Button;

public class SidebarButton extends Button {
    public SidebarButton(String text) {
        super(text);
        this.getStyleClass().add("sidebar-btn");
        this.setPrefWidth(150);
        this.setPrefHeight(40);
    }
}