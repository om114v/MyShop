package com.shop.controllers;

import com.shop.controllers.models.Sticker;
import com.shop.controllers.services.PrintStickerService;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PrintStickerController {
	private PrintStickerService service;

	public PrintStickerController() {
		this.service = new PrintStickerService();
	}

	public void printSticker(String item, String dealer, int price, int number) {
		try {
			Sticker sticker = new Sticker(item, dealer, price);

			this.service.printSticker2(sticker, number);

			this.showSuccess("Stickers printed successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			showError("Failed to print stickers: " + e.getMessage());
		}
	}

	private void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void showSuccess(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
