package com.shop.ui;

import java.io.File;

import com.shop.ui.layouts.MainLayout;
import com.shop.ui.theme.ThemeManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

	@Override
	public void start(Stage primaryStage) {
		ThemeManager themeManager = new ThemeManager();
		MainLayout mainLayout = new MainLayout(themeManager);

		// Scene
		Scene scene = new Scene(mainLayout, 1200, 700);
		themeManager.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

		primaryStage.setTitle("MyShop");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		getHomeAppFolder();
		launch(args);
	}

	public static String getHomeAppFolder() {
		String homePath = System.getProperty("user.home");
		File appFolder = new File(homePath + File.separator + ".myshop");

		if (!appFolder.exists()) {
			boolean created = appFolder.mkdirs();
			if (!created) {
				throw new RuntimeException("Failed to create app folder: " + appFolder.getAbsolutePath());
			}
		}

		return appFolder.getAbsolutePath();
	}

}