package com.shop.ui.views;

import com.shop.controllers.ProfileController;
import com.shop.ui.components.FormField;
import com.shop.ui.components.ModernButton;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import java.io.File;

public class ProfileView extends VBox {
	private ProfileController controller;

	// Form fields
	private FormField shopNameField;
	private FormField gstNumberField;
	private FormField addressField;
	private FormField phoneNumberField;
	private Label logoPathLabel;
	private Button chooseLogoButton;
	private ModernButton saveButton;

	public ProfileView() {
		controller = new ProfileController();
		initializeUI();
		setupEventHandlers();
		loadInitialData();
	}

	private void initializeUI() {
		this.setSpacing(20);
		this.setPadding(new Insets(30));
		this.getStyleClass().add("profile-view");

		Label title = new Label("Profile Management");
		title.getStyleClass().add("view-title");

		// Create form fields
		shopNameField = new FormField("Shop Name");
		gstNumberField = new FormField("GST Number");
		addressField = new FormField("Address");
		phoneNumberField = new FormField("Phone Number");

		// Logo upload section
		HBox logoSection = new HBox(10);
		logoSection.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
		Label logoLabel = new Label("Logo:");
		logoPathLabel = new Label("No file selected");
		chooseLogoButton = new Button("Choose File");
		chooseLogoButton.getStyleClass().add("choose-file-btn");
		logoSection.getChildren().addAll(logoLabel, logoPathLabel, chooseLogoButton);

		// Save button
		saveButton = new ModernButton("Save Profile");

		this.getChildren().addAll(title, shopNameField, gstNumberField, addressField, phoneNumberField, logoSection, saveButton);
	}

	private void setupEventHandlers() {
		chooseLogoButton.setOnAction(e -> handleLogoSelection());
		saveButton.setOnAction(e -> controller.saveProfile(
			shopNameField.getText(),
			gstNumberField.getText(),
			addressField.getText(),
			phoneNumberField.getText(),
			logoPathLabel.getText().equals("No file selected") ? null : logoPathLabel.getText()
		));
	}

	private void loadInitialData() {
		controller.loadProfile(this);
	}

	private void handleLogoSelection() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Logo Image");
		fileChooser.getExtensionFilters().addAll(
			new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
		);
		File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
		if (selectedFile != null) {
			logoPathLabel.setText(selectedFile.getAbsolutePath());
		}
	}

	public void populateFields(String shopName, String gstNumber, String address, String phoneNumber, String logoPath) {
		shopNameField.setText(shopName != null ? shopName : "");
		gstNumberField.setText(gstNumber != null ? gstNumber : "");
		addressField.setText(address != null ? address : "");
		phoneNumberField.setText(phoneNumber != null ? phoneNumber : "");
		logoPathLabel.setText(logoPath != null ? logoPath : "No file selected");
	}

	public void refreshView() {
		loadInitialData();
	}
}