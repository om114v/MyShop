package com.shop.ui.views;

import com.shop.controllers.PrintStickerController;
import com.shop.ui.components.Form2Field;
import com.shop.ui.components.ModernButton;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PrintStickerView extends VBox {
	private PrintStickerController controller;

	// Form fields
	private Form2Field itemField;
	private Form2Field dealerField;
	private Form2Field priceField;
	private Form2Field numberField;
	private ModernButton printButton;

	public PrintStickerView() {
		controller = new PrintStickerController();
		initializeUI();
		validateFields();
		setupEventHandlers();
		loadInitialData();
	}

	private void initializeUI() {
		this.setSpacing(20);
		this.setPadding(new Insets(30));
		this.getStyleClass().add("printSticker-view");

		Label title = new Label("Print Stickers");
		title.getStyleClass().add("view-title");

		// Create form fields
		itemField = new Form2Field("Item");
		dealerField = new Form2Field("Dealer");
		priceField = new Form2Field("Price");
		numberField = new Form2Field("Number of Sticker");

		// Print button
		printButton = new ModernButton("Print Sticker");

		this.getChildren().addAll(title, itemField, dealerField, priceField, numberField, printButton);
	}

	private void validateFields() {
		priceField.getTextField().textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				priceField.getTextField().setText(newValue.replaceAll("[^\\d]", ""));
			}
		});
		numberField.getTextField().textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				numberField.getTextField().setText(newValue.replaceAll("[^\\d]", ""));
			}
		});
	}

	private void setupEventHandlers() {
		printButton.setOnAction(e -> {
			boolean hasError = false;

			if (itemField.isEmpty()) {
				itemField.showError("Item name cannot be empty!");
				hasError = true;
			} else
				itemField.clearError();

			if (dealerField.isEmpty()) {
				dealerField.showError("Dealer name cannot be empty!");
				hasError = true;
			} else
				dealerField.clearError();

			if (priceField.isEmpty()) {
				priceField.showError("Price is required!");
				hasError = true;
			} else
				priceField.clearError();

			if (numberField.isEmpty()) {
				numberField.showError("Number of stickers is required!");
				hasError = true;
			} else
				numberField.clearError();

			if (hasError)
				return; // Stop if any field invalid
			controller.printSticker(itemField.getText(), dealerField.getText(), Integer.valueOf(priceField.getText()),
					Integer.valueOf(numberField.getText()));
		});
	}

	private void loadInitialData() {

	}

	public void populateFields(String item, String dealer, String price, String number) {
		itemField.setText(item != null ? item : "");
		dealerField.setText(dealer != null ? dealer : "");
		priceField.setText(price != null ? price : "0");
		numberField.setText(number != null ? number : "0");
	}

	public void refreshView() {
		loadInitialData();
	}
}