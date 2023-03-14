package edu.ics372.gp1.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.business.entities.Order;
import edu.ics372.gp1.business.entities.Product;

/**
 * The collection class for Orders outstanding by the GroceryStore.
 * 
 * @author Thomas Morgenstern
 *
 */
public class OutstandingOrderList implements Iterable<Order>, Serializable{
	private static final long serialVersionUID = 1L;
	private static OutstandingOrderList orders;
	private List<Order> orderList = new LinkedList<Order>();

	private OutstandingOrderList() {
		
	}
	
	public static OutstandingOrderList getInstance() {
		if (orders == null) {
			return orders = new OutstandingOrderList();
		}
		else {
			return orders;
		}
	}
	
	/**
	 * Searches outstanding order list for pre-existing order of product.
	 * 
	 * @param order to be made.
	 * 
	 * @return order if found otherwise null.
	 */
	public Order search(Order order) {
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order tempOrder = (Order) iterator.next();
			if (order.getProductId().equals(tempOrder.getProductId())) {
				return tempOrder;
			}
		}
		return null;
	}
	
	/**
	 * Helper method to add orders to the list from GroceryStore.
	 * 
	 * @param order to be added.
	 */
	public void addOrder(Order order) {
		orderList.add(order);
	}
	
	/**
	 * Returns an iterator to all outstanding orders.
	 * 
	 * @return iterator to the collection
	 */
	@Override
	public Iterator<Order> iterator() {
		return orderList.iterator();
	}

}
