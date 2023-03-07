package edu.ics372.gp1.business.entities;

public class Product {
	private String name;
	private int id;
	private double price;
	private int reorderLevel;
	private int stock;
	private static int productIdCounter;
	
	public Product(String name, double price, int reorderLevel) {
		this.name = name;
		this.price = price;
		this.reorderLevel = reorderLevel;
		this.stock = 0;
		id = ++productIdCounter;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public int getReorderLevel() {
		return this.reorderLevel;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	// Business Process 6
	public void retrieveProductInfo(String name) {
		System.out.println("Product [ID: " + this.id + ", Price: " + this.price + ", Stock: " + this.stock + "]");
	}
	
	// Business Process 8
	public void setPrice(double price) {
		this.price = price;
	}
}
