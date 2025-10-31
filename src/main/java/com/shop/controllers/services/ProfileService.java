package com.shop.controllers.services;

import com.shop.controllers.models.Profile;
import com.shop.controllers.repositories.ProfileRepository;
import java.util.Optional;

public class ProfileService {

    private ProfileRepository repository;

    public ProfileService() {
        this.repository = new ProfileRepository();
    }

    public void saveProfile(Profile profile) {
        try {
            repository.save(profile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save profile: " + e.getMessage(), e);
        }
    }

    public Optional<Profile> getProfile() {
        try {
            return repository.load();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load profile: " + e.getMessage(), e);
        }
    }

    public void deleteProfile(int id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete profile: " + e.getMessage(), e);
        }
    }

}
