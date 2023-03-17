package edu.ics372.gp1.business.entities;

/**
 * 
 * @author Brahma Dathan, Sarnath Ramnath, and Jonathon Voss
 * @Copyright (c) 2010, 2023
 
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a single Transaction (purchase of items)
 * 
 * @author Thomas Morgenstern
 *
 */
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private double total;
	private Calendar date;

	/**
	 * Creates the transaction with the amount paid. The date is the current date.
	 * 
	 * @param paid  The amount paid
	 * 
	 */
	public Transaction(double paid) {
		total = paid;
		date = Calendar.getInstance();
	}

	/**
	 * Checks whether this transaction is on the given date
	 * 
	 * @param date The date for which transactions are being sought
	 * @return true iff the dates match
	 */
	public boolean onDate(Calendar date) {
		return ((date.get(Calendar.YEAR) == this.date.get(Calendar.YEAR))
				&& (date.get(Calendar.MONTH) == this.date.get(Calendar.MONTH))
				&& (date.get(Calendar.DATE) == this.date.get(Calendar.DATE)));
	}
	
	public boolean betweenDates(Calendar date1, Calendar date2) {
		date2.add(Calendar.DAY_OF_YEAR, 1);
		return (this.date.after(date1) && this.date.before(date2));
	}


	/**
	 * Returns the paid field
	 * 
	 * @return paid field
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Returns the date as a String
	 * 
	 * @return date with month, date, and year
	 */
	public String getDate() {
		int month = Calendar.MONTH + 1;
		return month + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
	}
	
	public Calendar getTransactionDate() {
		return date;
	}

	/**
	 * String form of the transaction
	 * 
	 */
	@Override
	public String toString() {
		return getDate() + "  " + total;
	}
}