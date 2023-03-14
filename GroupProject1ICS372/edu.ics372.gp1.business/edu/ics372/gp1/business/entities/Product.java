package edu.ics372.gp1.business.entities;

import java.io.Serializable;
/**
 * Product represents a product of the GroceryStore.
 * 
 * @author Thomas Morgenstern
 *
 */
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
	
	/**
	 * Helper method for updating stock upon processing a shipment
	 * 
	 * @param quantity of product ordered
	 */
	public void updateStock(int newStock) {
		this.stock += newStock;
	}
	
	/**
	 * Helper method for changing the price of a product
	 * 
	 * @param new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
