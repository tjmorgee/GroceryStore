package edu.ics372.gp1.business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Order represents an Order made by the GroceryStore.
 * 
 * @author Thomas Morgenstern
 *
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private String productId;
	private String productName;
	private int quantityOrdered;
	
	/**
	 * Creates an Order with the given id, name, and quantity
	 * 
	 * @param productId
	 * @param productName
	 * @param quantityOrdered
	 */
	public Order(String productId, String productName, int quantityOrdered) {
		this.productId = productId;
		this.productName = productName;
		this.quantityOrdered = quantityOrdered;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public int getQuantityOrdered() {
		return quantityOrdered;
	}
}
