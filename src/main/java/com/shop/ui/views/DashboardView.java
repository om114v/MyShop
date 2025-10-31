package com.shop.ui.views;

import com.shop.ui.components.Card;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DashboardView extends VBox {

    public DashboardView() {
        initializeUI();
    }

    private void initializeUI() {
        this.setSpacing(20);
        this.setPadding(new Insets(30));
        this.getStyleClass().add("dashboard-view");
        this.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Dashboard");
        title.getStyleClass().add("view-title");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        // Create cards for dashboard
        Card profileCard = new Card("Profile Management", "Manage your shop profile and settings", "Go to Profile");
        Card printStickersCard = new Card("Print Stickers", "Generate and print product stickers", "Start Printing");
        Card analyticsCard = new Card("Analytics", "View sales and performance metrics", "View Reports");
        Card inventoryCard = new Card("Inventory", "Manage your product inventory", "Manage Inventory");

        // Add cards to grid
        grid.add(profileCard, 0, 0);
        grid.add(printStickersCard, 1, 0);
        grid.add(analyticsCard, 0, 1);
        grid.add(inventoryCard, 1, 1);

        this.getChildren().addAll(title, grid);
    }
}