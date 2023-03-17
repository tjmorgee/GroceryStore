package edu.ics372.gp1.business.entities;

import java.io.Serializable;
/**
 * LineItem represents a line item on a receipt
 * 
 * @author Jonathon Voss 
 *
 */
public class LineItem implements Serializable {
	private static final long serialVersionUID = 1l;
	private String productName;
	private double productPrice;
	private int quantity;
	private double total;
	
	public LineItem(String productName, double productPrice, int quantity) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
		total = productPrice * quantity;
	}

	/**
	 * Getter for product name
	 * 
	 * @return name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Getter for product price
	 * 
	 * @return price
	 */
	public double getProductPrice() {
		return productPrice;
	}

	/**
	 * Getter for product quantity
	 * 
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Getter for lineItem total
	 * 
	 * @return total
	 */
	public double getTotal() {
		return total;
	}
}
