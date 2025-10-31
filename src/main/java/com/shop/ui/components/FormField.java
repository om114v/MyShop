package com.shop.ui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FormField extends VBox {
    private Label label;
    private TextField textField;

    public FormField(String labelText) {
        this.label = new Label(labelText);
        this.textField = new TextField();

        // Styling
        this.setSpacing(5);
        this.setPadding(new Insets(10));
        this.label.getStyleClass().add("form-label");
        this.textField.getStyleClass().add("form-field");

        this.getChildren().addAll(label, textField);
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public TextField getTextField() {
        return textField;
    }
}
