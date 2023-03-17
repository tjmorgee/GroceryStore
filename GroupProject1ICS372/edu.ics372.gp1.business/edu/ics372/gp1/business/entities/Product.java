package edu.ics372.gp1.business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Product represents a product of the grocery store.
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
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getReorderLevel() {
		return reorderLevel;
	}
	
	public int getStock() {
		return stock;
	}
	
	/**
	 * Helper method for updating stock upon processing a shipment
	 * 
	 * @param quantity of product ordered
	 */
	public void updateStock(int newStock) {
		stock += newStock;
	}
	
	/**
	 * Helper method for changing the price of a product
	 * 
	 * @param new price
	 */
	public void setPrice(double newPrice) {
		price = newPrice;
	}
	
	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(productIdCounter);
	}

	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		productIdCounter = (int) input.readObject();
	}

}
