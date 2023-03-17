package edu.ics372.gp1.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import edu.ics372.gp1.business.entities.LineItem;

/**
 * Cart represents a singleton class used for holding products
 * that are being checked out.
 * 
 * @author Jonathan Voss
 *
 */
public class Cart implements Iterable<LineItem>, Serializable {
	private static final long serialVersionUID = 1l;
	private Queue<LineItem> lineItems = new LinkedList<LineItem>();
	private static Cart cart;

	private Cart() {
	}

	public static Cart getInstance() {
		if (cart == null) {
			cart = new Cart();
		}
		return cart;
	}

	/**
	 * Adds an item to the cart
	 * 
	 * @param lineItem to be added
	 * @return true
	 */
	public boolean addLineItem(LineItem lineItem) {
		lineItems.add(lineItem);
		return true;
	}

	/**
	 * Removes an item from the cart
	 * 
	 * @return lineItem
	 */
	public LineItem removeLineItem() {
		return lineItems.remove();
	}

	/**
	 * Returns an iterator to lineItems
	 * 
	 */
	@Override
	public Iterator<LineItem> iterator() {
		return lineItems.iterator();
	}

}