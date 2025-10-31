package com.shop.ui;

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

		primaryStage.setTitle("My Creation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}