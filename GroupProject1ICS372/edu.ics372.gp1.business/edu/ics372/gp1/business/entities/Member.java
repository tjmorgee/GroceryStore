package edu.ics372.gp1.business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.business.facade.Result;
import edu.ics372.gp1.business.iterators.FilteredIterator;
import edu.ics372.gp1.business.iterators.SafeTransactionIterator;

/**
 * Member represents a member of the GroceryStore.
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String phone;
	private String id;
	private Calendar dateJoined;
	private double fee;
	private static final String MEMBER_STRING = "M";
	private List<Transaction> transactions = new LinkedList<Transaction>();
	private static int idCounter;

	/**
	 * Creates a single member
	 * 
	 * @param name    name of the member
	 * @param address address of the member
	 * @param phone   phone number of the member
	 * @param dateJoined date that member joined the GroceryStore
	 * @param fee	  fee paid by member when joining
	 */
	public Member(String name, String address, String phone, Calendar dateJoined, double fee) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.dateJoined = dateJoined;
		this.fee = fee;
		id = MEMBER_STRING + ++idCounter;
	}

	/**
	 * Gets an iterator to a collection of selected transactions
	 * 
	 * @param date the date for which the transactions have to be retrieved
	 * @return the iterator to the collection
	 */
	public Iterator<Result> getTransactionsOnDate(Calendar date) {
		return new SafeTransactionIterator(
				new FilteredIterator(transactions.iterator(), transaction -> transaction.onDate(date)));
	}

	/**
	 * Returns the list of all transactions for this member.
	 * 
	 * @return the iterator to the list of Transaction objects
	 */
	public Iterator<Transaction> getTransactions() {
		return transactions.iterator();
	}

	public void addTransaction(double total) {
		Transaction transaction = new Transaction(total);
		transactions.add(transaction);
	}
	
	/**
	 * Getter for name
	 * 
	 * @return member name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for phone number
	 * 
	 * @return phone number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Getter for address
	 * 
	 * @return member address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Getter for id
	 * 
	 * @return member id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Getter for dateJoined
	 * 
	 * @return date joined
	 */
	public Calendar getDateJoined() {
		return dateJoined;
	}
	
	/**
	  * Getter for fee
	  * 
	  * @return member fee
	  */
	public double getFee() {
		return fee;
	}

	/**
	 * Setter for name
	 * 
	 * @param newName member's new name
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Setter for address
	 * 
	 * @param newName member's new address
	 */
	public void setAddress(String newAddress) {
		address = newAddress;
	}

	/**
	 * Setter for phone
	 * 
	 * @param newName member's new phone
	 */
	public void setPhone(String newPhone) {
		phone = newPhone;
	}
	
	/**
	 * Setter for fee
	 * 
	 * @param fee	member registration fee
	 */
	public void setFee(double newFee) {
		fee = newFee;
	}
	
	/**
	 * Setter for date joined
	 * 
	 * @param newDateJoined	date member joined
	 */
	public void setDateJoined(Calendar newDateJoined) {
		dateJoined = newDateJoined;
	}
	

	/**
	 * String form of the member
	 * 
	 */
	@Override
	public String toString() {
		String string = "Member name " + name + " address " + address + " id " + id + "phone " + phone + " fee " + fee;
		string += "] transactions: [";
		for (Iterator iterator = transactions.iterator(); iterator.hasNext();) {
			string += (Transaction) iterator.next();
		}
		string += "]";
		return string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Checks whether the member is equal to the one supplied
	 * 
	 * @param object the member who should be compared
	 * @return true iff the member ids match
	 */

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Member other = (Member) object;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(idCounter);
	}

	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}

}