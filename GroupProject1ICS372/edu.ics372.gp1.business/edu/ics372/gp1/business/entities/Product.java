package edu.ics372.gp1.business.entities;

import java.io.Serializable;
// I'll comment this all to the professor's specifications later
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	private double price;
	private int reorderLevel;
	private int stock;
	private static final String PRODUCT_STRING = "P";
	private static int productIdCounter;
	
	public Product(String name, double price, int reorderLevel) {
		this.name = name;
		this.price = price;
		this.reorderLevel = reorderLevel;
		this.stock = 0;
		id = PRODUCT_STRING + ++productIdCounter;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getId() {
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
	
	// Business Process 6 (May actually go within Catalog class, just iterate over catalog to find product given name)
	public void productInfo(String name) {
		System.out.println("Product [ID: " + this.id + ", Price: " + this.price + ", Stock: " + this.stock + "]");
	}
	
	// Business Process 8
	public void setPrice(double price) {
		this.price = price;
	}
}
