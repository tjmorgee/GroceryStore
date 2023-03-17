package edu.ics372.gp1.business.collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import edu.ics372.gp1.business.entities.LineItem;
import edu.ics372.gp1.business.entities.Product;

public class Cart implements Serializable {
	private static final long serialVersionUID = 1l;
	private List<Product> items = new LinkedList<Product>();
	private List<Integer> quantities = new LinkedList<Integer>();
	private static Cart cart;
	
	private Cart() {
	}
	
	public static Cart getInstance() {
		if (cart == null) {
			cart = new Cart();
		}
		return cart;
	}
	
	public void addItem(Product product) {
		items.add(product);
	}
	
	public void addQuantity(int quantity) {
		quantities.add(quantity);
	}
	
	public Product removeItem(int index) {
		return items.remove(index);
	}
	
	public int getQuantity(int index) {
		return quantities.remove(index);
	}
	
	public int size() {
		return items.size();
	}
	
}
