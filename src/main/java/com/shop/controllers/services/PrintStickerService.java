package com.shop.controllers.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Destination;
import javax.print.attribute.standard.MediaPrintableArea;

import com.shop.controllers.models.Sticker;

import javafx.stage.DirectoryChooser;

public class PrintStickerService {

	// Print sticker silently without user interaction.
	public void printSticker2(Sticker sticker, int number) throws PrintException, IOException {

		sticker.setShop();

		// Sticker size in mm → points
		float widthMM = 30;
		float heightMM = 20;

		// Convert mm → pixels (assuming 300 DPI for crisp thermal print)
		double dpi = 300.0;
		int widthPx = (int) Math.round(widthMM / 25.4 * dpi);
		int heightPx = (int) Math.round(heightMM / 25.4 * dpi);

		// Create in-memory image
		BufferedImage img = new BufferedImage(widthPx, heightPx, BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D g = img.createGraphics();

		// Draw content
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, widthPx, heightPx);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 35));
		g.drawString(sticker.getShop(), 30, 60);

		g.setFont(new Font("Arial", Font.PLAIN, 35));
		g.drawString(sticker.getItem() + " - " + sticker.getPrice(), 30, 120);
		g.drawString(sticker.getDealer(), 30, 180);

		g.drawRoundRect(10, 10, widthPx - 20, heightPx - 20, 20, 20);

		g.dispose();

		ByteArrayOutputStream pngBytes = new ByteArrayOutputStream();
		ImageIO.write(img, "png", pngBytes);
		pngBytes.flush();

		ByteArrayInputStream pngInput = new ByteArrayInputStream(pngBytes.toByteArray());
		DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
		Doc doc = new SimpleDoc(pngInput, flavor, null);

		PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
		attrs.add(new Copies(number));
		attrs.add(new MediaPrintableArea(0, 0, widthMM, heightMM, MediaPrintableArea.MM));

		PrintService service = PrintServiceLookup.lookupDefaultPrintService();

		if (service == null) {
			throw new RuntimeException("No printer found.");
		}
		if (service.getName().toLowerCase().contains("microsoft print to pdf")) {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Select folder to save PDF");

			File selectedFolder = directoryChooser.showDialog(null); // can use null if no stage
			if (selectedFolder == null) {
				throw new RuntimeException("User cancelled folder selection.");
			}

			// Generate a unique filename based on timestamp
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String fileName = "Sticker_" + timestamp + ".pdf";

			File file = new File(selectedFolder, fileName);
			attrs.add(new Copies(1));
			attrs.add(new Destination(file.toURI()));
		}
		DocPrintJob job = service.createPrintJob();
		job.print(doc, attrs);
		System.out.println("Printed using: " + service.getName());
	}

	public void printSticker(Sticker sticker, int rows) throws PrintException, IOException {
		sticker.setShop();

		// Single sticker dimensions
		float stickerWidthMM = 30;
		float stickerHeightMM = 20;

		int stickersPerRow = 3;

		double dpi = 300.0;
		int stickerWidthPx = (int) Math.round(stickerWidthMM / 25.4 * dpi);
		int stickerHeightPx = (int) Math.round(stickerHeightMM / 25.4 * dpi);

		// Create image for full row (3 stickers wide)
		int fullWidthPx = stickerWidthPx * stickersPerRow;
		BufferedImage img = new BufferedImage(fullWidthPx, stickerHeightPx, BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D g = img.createGraphics();

		// Fill white background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, fullWidthPx, stickerHeightPx);

		// Draw each sticker in the row
		for (int i = 0; i < stickersPerRow; i++) {
			int xOffset = i * stickerWidthPx;

			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString(sticker.getShop(), xOffset + 30, 60);

			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.drawString(sticker.getItem() + " - " + sticker.getDealer(), xOffset + 30, 120);
			g.setFont(new Font("Arial", Font.BOLD, 45));
			g.drawString(String.valueOf(sticker.getPrice()), xOffset + 30, 180);

			g.drawRoundRect(xOffset + 10, 10, stickerWidthPx - 20, stickerHeightPx - 20, 20, 20);
		}

		g.dispose();

		// Convert to PNG and print
		ByteArrayOutputStream pngBytes = new ByteArrayOutputStream();
		ImageIO.write(img, "png", pngBytes);
		pngBytes.flush();

		ByteArrayInputStream pngInput = new ByteArrayInputStream(pngBytes.toByteArray());
		DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
		Doc doc = new SimpleDoc(pngInput, flavor, null);

		PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
		attrs.add(new Copies(rows));
		attrs.add(
				new MediaPrintableArea(0, 0, stickerWidthMM * stickersPerRow, stickerHeightMM, MediaPrintableArea.MM));

		PrintService service = PrintServiceLookup.lookupDefaultPrintService();

		if (service == null) {
			throw new RuntimeException("No printer found.");
		}

		if (service.getName().toLowerCase().contains("microsoft print to pdf")) {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Select folder to save PDF");
			File selectedFolder = directoryChooser.showDialog(null);
			if (selectedFolder == null) {
				throw new RuntimeException("User cancelled folder selection.");
			}
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String fileName = "Sticker_" + timestamp + ".pdf";
			File file = new File(selectedFolder, fileName);
			attrs.add(new Destination(file.toURI()));
		}
		DocPrintJob job = service.createPrintJob();
		job.print(doc, attrs);
		System.out.println("Printed " + rows + " rows with " + stickersPerRow + " stickers");
	}

}