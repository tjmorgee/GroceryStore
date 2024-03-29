/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
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

package edu.ics372.gp1.business.facade;

import edu.ics372.gp1.business.facade.DataTransfer;

/**
 * This class is used for returning many of the results of the library system's
 * business logic to user interface.
 * 
 * At present, the Result object returns an int code,plus values of selected
 * fields of Book and Member. They are the book title, id, borrower id, due
 * date, member name, member phone, and member id.
 * 
 * @author Brahma Dathan
 *
 */
public class Result extends DataTransfer {
	public static final int OPERATION_COMPLETED = 0;
	public static final int OPERATION_FAILED = 1;
	public static final int PRODUCT_NOT_FOUND = 2;
	public static final int ORDER_PLACED = 3;
	public static final int NO_ORDER_FOUND = 4;
	public static final int NO_SUCH_MEMBER = 5;
	public static final int NOT_ENOUGH_STOCK = 6;

	private int resultCode;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

}
