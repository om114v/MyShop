package com.shop.ui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class Form2Field extends HBox {
	private Label label;
	private TextField textField;
	private Text errorText;

	public Form2Field(String labelText) {
		this.label = new Label(labelText);
		this.textField = new TextField();
		this.errorText = new Text();
		
		// Styling
		this.setSpacing(10);
		this.setPadding(new Insets(10));
		label.setMinWidth(120); // consistent label width
		textField.setMaxWidth(300);
		textField.setPrefWidth(300); // or stretch as needed
		HBox.setHgrow(textField, Priority.ALWAYS); // let the field expand

		this.label.getStyleClass().add("form2-label");
		this.textField.getStyleClass().add("form2-field");
        this.errorText.getStyleClass().add("form2-error");
        
		this.getChildren().addAll(label, textField, errorText);
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
	
	public void showError(String message) {
        errorText.setText(message);
    }

    public void clearError() {
        errorText.setText("");
    }

    public boolean isEmpty() {
        return textField.getText().trim().isEmpty();
    }
}
