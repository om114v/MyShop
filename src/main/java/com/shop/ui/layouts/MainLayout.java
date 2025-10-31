package com.shop.ui.layouts;

import com.shop.ui.animations.UIAnimations;
import com.shop.ui.theme.ThemeManager;
import com.shop.ui.views.DashboardView;
import com.shop.ui.views.ProfileView;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import java.util.HashMap;
import java.util.Map;

public class MainLayout extends BorderPane {

    private Sidebar sidebar;
    private TopBar topBar;
    private Map<String, Node> viewCache = new HashMap<>();
    private boolean sidebarVisible = true;
    private ThemeManager themeManager;

    public MainLayout(ThemeManager themeManager) {
        this.themeManager = themeManager;
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        navigateTo("Dashboard"); // Default view
    }

    private void initializeComponents() {
        sidebar = new Sidebar(this::navigateTo);
        topBar = new TopBar(this::toggleSidebar, themeManager);
    }

    private void setupLayout() {
        this.setLeft(sidebar);
        this.setTop(topBar);
    }

    private void setupEventHandlers() {
        // Any additional setup can go here
    }

    public void navigateTo(String viewName) {
        Node view = viewCache.get(viewName);
        if (view == null) {
            view = createView(viewName);
            if (view != null) {
                viewCache.put(viewName, view);
            }
        }
        if (view != null) {
            this.setCenter(view);
            sidebar.setActiveView(viewName);
        }
    }

    private Node createView(String viewName) {
        switch (viewName) {
            case "Dashboard":
                return new DashboardView();
            case "Profile":
                return new ProfileView();
            case "Print Stickers":
                // Placeholder for Print Stickers view
                return new DashboardView(); // Temporary, replace with actual view
            default:
                return new DashboardView();
        }
    }

    private void toggleSidebar() {
        if (sidebarVisible) {
            // Hide sidebar with animation
            TranslateTransition tt = new TranslateTransition(Duration.millis(300), sidebar);
            tt.setToX(-sidebar.getPrefWidth());
            tt.play();
            sidebarVisible = false;
        } else {
            // Show sidebar with animation
            TranslateTransition tt = new TranslateTransition(Duration.millis(300), sidebar);
            tt.setToX(0);
            tt.play();
            sidebarVisible = true;
        }
    }
}
