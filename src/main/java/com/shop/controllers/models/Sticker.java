package com.shop.controllers.models;

public class Sticker {
	private String shop = "Akshar Creation";
	private String item;
	private String supplier;
	private int price;

	public Sticker() {
	};

	public Sticker(String item, String supplier, int price) {
		this.item = item;
		this.supplier = supplier;
		this.price = price;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
