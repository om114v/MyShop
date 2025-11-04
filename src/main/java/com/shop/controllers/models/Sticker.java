package com.shop.controllers.models;

import java.util.Optional;

import com.shop.controllers.services.ProfileService;

public class Sticker {

	private ProfileService service;

	private String shop = "Title";
	private String item;
	private String supplier;
	private int price;

	public Sticker(String item, String supplier, int price) {
		this.service = new ProfileService();
		this.item = item;
		this.supplier = supplier;
		this.price = price;
	}

	public String getShop() {
		return this.shop;
	}

	public void setShop() {
		Optional<Profile> profileOpt = this.service.getProfile();
		if (profileOpt.isPresent()) {
			this.shop = profileOpt.get().getShopName();
		}
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
