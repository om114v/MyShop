package com.shop.controllers;

import java.util.Optional;

import com.shop.controllers.models.Profile;
import com.shop.controllers.services.ProfileService;
import com.shop.ui.views.ProfileView;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProfileController {

    private ProfileService service;

    public ProfileController() {
        service = new ProfileService();
    }

    public void saveProfile(String shopName, String gstNumber, String address, String phoneNumber, String logoPath) {
        try {
            // Validation
            if (shopName == null || shopName.trim().isEmpty()) {
                showError("Shop Name is required");
                return;
            }
            
//            Temporary Validation disabled.
            
//            if (gstNumber == null || gstNumber.trim().isEmpty()) {
//                showError("GST Number is required");
//                return;
//            }
//            if (address == null || address.trim().isEmpty()) {
//                showError("Address is required");
//                return;
//            }
//            if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
//                showError("Phone Number is required");
//                return;
//            }
//
//            // Validate GST number format (basic check)
//            if (!gstNumber.matches("\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}")) {
//                showError("Invalid GST Number format");
//                return;
//            }
//
//            // Validate phone number (basic check)
//            if (!phoneNumber.matches("\\d{10}")) {
//                showError("Phone Number must be 10 digits");
//                return;
//            }

            // Create profile object
            Profile profile = new Profile(shopName.trim(), gstNumber.trim(), address.trim(), phoneNumber.trim(), logoPath);

            // Save to service
            service.saveProfile(profile);

            // Show success message
            showSuccess("Profile saved successfully");

        } catch (Exception e) {
            showError("Failed to save profile: " + e.getMessage());
        }
    }

    public void loadProfile(ProfileView view) {
        try {
            Optional<Profile> profileOpt = service.getProfile();
            if (profileOpt.isPresent()) {
                Profile profile = profileOpt.get();
                view.populateFields(
                    profile.getShopName(),
                    profile.getGstNumber(),
                    profile.getAddress(),
                    profile.getPhoneNumber(),
                    profile.getLogoPath()
                );
            } else {
                // Load empty form for new profile
                view.populateFields("", "", "", "", null);
            }
        } catch (Exception e) {
            showError("Failed to load profile: " + e.getMessage());
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