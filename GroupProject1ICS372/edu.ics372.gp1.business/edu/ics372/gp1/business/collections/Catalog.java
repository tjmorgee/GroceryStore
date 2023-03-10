package edu.ics372.gp1.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.business.entities.Product;

/**
 * 
 * @author Brahma Dathan, Sarnath Ramnath, and Jonathon Voss
 * @Copyright (c) 2010, 2023
 * 
 *            Redistribution and use with or without modification, are permitted
 *            provided that the following conditions are met:
 *
 *            - the use is for academic purpose only - Redistributions of source
 *            code must retain the above copyright notice, this list of
 *            conditions and the following disclaimer. - Neither the name of
 *            Brahma Dathan or Sarnath Ramnath may be used to endorse or promote
 *            products derived from this software without specific prior written
 *            permission.
 *
 *            The authors do not make any claims regarding the correctness of
 *            the code in this module and are not responsible for any loss or
 *            damage resulting from its use.
 */

/**
 * The collection class for Product objects
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */
public class Catalog implements Iterable<Product>, Serializable {
	private static final long serialVersionUID = 1L;
	private static Catalog catalog;
	private List<Product> products = new LinkedList<Product>();

	private Catalog() {

	}

	public static Catalog getInstance() {
		if (catalog == null) {
			catalog = new Catalog();
		}
		return catalog;
	}

	/**
	 * Checks whether a product with a given product id exists.
	 * 
	 * @param productId the id of the product
	 * @return true iff the product exists
	 * 
	 */
	public Product search(String productId) {
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if (product.getId().equals(productId)) {
				return product;
			}
		}
		return null;
	}

	/**
	 * Inserts a product into the collection
	 * 
	 * @param product the product to be inserted
	 * @return true iff the product could be inserted. Currently always true
	 */
	public boolean insertProduct(Product product) {
		products.add(product);
		return true;
	}

	/**
	 * Returns an iterator to all products
	 * 
	 * @return iterator to the collection
	 */
	public Iterator<Product> iterator() {
		return products.iterator();
	}

	/**
	 * String form of the collection
	 * 
	 */
	public String toString() {
		return products.toString();
	}
}
