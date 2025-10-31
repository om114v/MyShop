package com.shop.ui.layouts;

import com.shop.ui.components.SidebarButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Sidebar extends VBox {

    private Consumer<String> navigationCallback;
    private Map<String, SidebarButton> buttons = new HashMap<>();
    private String activeView = null;

    public Sidebar(Consumer<String> navigationCallback) {
        this.navigationCallback = navigationCallback;
        initialize();
    }

    private void initialize() {
        this.setPrefWidth(200);
        this.setPadding(new Insets(20));
        this.setSpacing(15);
        this.setAlignment(Pos.TOP_CENTER);
        this.getStyleClass().add("sidebar");

        // Logo
        Label logo = new Label("Shop Name");
        logo.getStyleClass().add("logo");

        // Navigation Buttons
        SidebarButton printStickersBtn = new SidebarButton("Print Stickers");
        SidebarButton profileBtn = new SidebarButton("Profile");

        buttons.put("Print Stickers", printStickersBtn);
        buttons.put("Profile", profileBtn);


        // Set actions
        printStickersBtn.setOnAction(e -> navigationCallback.accept("Print Stickers"));
        profileBtn.setOnAction(e -> navigationCallback.accept("Profile"));

        // Separator
        Separator separator = new Separator();

        // Add components
        this.getChildren().addAll(logo, separator, printStickersBtn, profileBtn);
    }

    public void setActiveView(String viewName) {
        if (activeView != null && buttons.containsKey(activeView)) {
            buttons.get(activeView).getStyleClass().remove("active");
        }
        activeView = viewName;
        if (buttons.containsKey(viewName)) {
            buttons.get(viewName).getStyleClass().add("active");
        }
    }
}
