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

package edu.ics372.gp1.business.facade;

//import edu.ics372.gp1.business.entities.Book;
import edu.ics372.gp1.business.entities.Member;
import edu.ics372.gp1.business.entities.Order;
import edu.ics372.gp1.business.entities.Product;
import edu.ics372.gp1.business.entities.Transaction;
import edu.ics372.gp1.business.entities.LineItem;

/**
 * The DataTransfer class is used to facilitate data transfer between Library
 * and UserInterface. It is also used to support iterating over Member and Book
 * objects. The class stores copies of fields that may be sent in either
 * direction.
 * 
 * @author Brahma Dathan
 *
 */
public abstract class DataTransfer {
	private String productId;
	private String productName;
	private double productPrice;
	private int productReorderLevel;
	private int productStock;
	private String memberId;
	private String memberName;
	private String memberAddress;
	private String memberPhone;
	private String memberDateJoined;
	private double memberFee;
	private double transactionAmount;
	private String transactionDate;
	private int quantityOrdered;
	private int quantityPurchased;
	private double totalPrice;

	/**
	 * This sets all fields to "none".
	 */
	public DataTransfer() {
		reset();
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductReorderLevel() {
		return productReorderLevel;
	}

	public void setProductReorderLevel(int productReorderLevel) {
		this.productReorderLevel = productReorderLevel;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	
	public String getMemberDateJoined() {
		return memberDateJoined;
	}
	
	public void setMemberDateJoined(String memberDateJoined) {
		this.memberDateJoined = memberDateJoined;
	}

	public double getMemberFee() {
		return memberFee;
	}

	public void setMemberFee(double memberFee) {
		this.memberFee = memberFee;
	}

	/**
	 * Sets all the member-related fields using the Member parameter.
	 * 
	 * @param member the member whose fields should be copied.
	 */
	public void setMemberFields(Member member) {
		memberId = member.getId();
		memberName = member.getName();
		memberPhone = member.getPhone();
		memberAddress = member.getAddress();
		memberDateJoined = member.getDateJoined();
		memberFee = member.getFee();
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}
	
	public int getQuantityPurchased() {
		return quantityPurchased;
	}
	
	public void setQuantityPurchased(int quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}

	public void setTransactionFields(Transaction transaction) {
		setTransactionDate(transaction.getDate());
		setTransactionAmount(transaction.getTotal());
	}

	/**
	 * Sets all the product-related fields using the Book parameter. If the product is not
	 * issued "none" is stored in the borrower and due date fields.
	 * 
	 * @param product the product whose fields should be copied.
	 */
	public void setProductFields(Product product) {
		productId = product.getId();
		productName = product.getName();
		productPrice = product.getPrice();
		productReorderLevel = product.getReorderLevel();
		productStock = product.getStock();
	}
	
	public void setOrderFields(Order order) {
		productId = order.getProductId();
		productName = order.getProductName();
		quantityOrdered = order.getQuantityOrdered();
	}
	public void setLineItemFields(LineItem lineItem) {
		productName = lineItem.getProductName();
		productPrice = lineItem.getProductPrice();
		quantityPurchased = lineItem.getQuantity();
		totalPrice = lineItem.getTotal();
	}
	
	/**
	 * Sets all String fields to "none"
	 */
	public void reset() {
		productId = "No such Product";
		productName = "No such product";
		memberId = "Invalid member id";
		memberName = "No such member";
		memberPhone = "No such member";
		memberAddress = "No such member";
	}
}
