package com.shop.controllers.repositories;

import com.shop.controllers.models.Profile;
import com.shop.ui.MainApplication;

import java.io.File;
import java.sql.*;
import java.util.Optional;

public class ProfileRepository {

	private static final String DB_URL = "jdbc:sqlite:" + MainApplication.getHomeAppFolder() + File.separator
			+ "shop.db";
	private static final String CREATE_TABLE_SQL = """
			CREATE TABLE IF NOT EXISTS profile (
			    id INTEGER PRIMARY KEY AUTOINCREMENT,
			    shop_name TEXT,
			    gst_number TEXT,
			    address TEXT,
			    phone_number TEXT,
			    logo_path TEXT
			);
			""";

	public ProfileRepository() {
		createTableIfNotExists();
	}

	private void createTableIfNotExists() {
		try (Connection conn = DriverManager.getConnection(DB_URL); Statement stmt = conn.createStatement()) {
			stmt.execute(CREATE_TABLE_SQL);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to create profile table", e);
		}
	}

	public void save(Profile profile) {
		String sql = "INSERT OR REPLACE INTO profile (id, shop_name, gst_number, address, phone_number, logo_path) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, profile.getId());
			pstmt.setString(2, profile.getShopName());
			pstmt.setString(3, profile.getGstNumber());
			pstmt.setString(4, profile.getAddress());
			pstmt.setString(5, profile.getPhoneNumber());
			pstmt.setString(6, profile.getLogoPath());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Failed to save profile", e);
		}
	}

	public Optional<Profile> load() {
		String sql = "SELECT id, shop_name, gst_number, address, phone_number, logo_path FROM profile LIMIT 1";
		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				Profile profile = new Profile(rs.getInt("id"), rs.getString("shop_name"), rs.getString("gst_number"),
						rs.getString("address"), rs.getString("phone_number"), rs.getString("logo_path"));
				return Optional.of(profile);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to load profile", e);
		}
		return Optional.empty();
	}

	public void delete(int id) {
		String sql = "DELETE FROM profile WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Failed to delete profile", e);
		}
	}
}
