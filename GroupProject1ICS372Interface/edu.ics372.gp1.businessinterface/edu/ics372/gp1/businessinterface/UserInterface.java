package edu.ics372.gp1.businessinterface;


/**
 * test
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

import edu.ics372.gp1.business.facade.GroceryStore;
import edu.ics372.gp1.business.facade.Request;
import edu.ics372.gp1.business.facade.Result;
/**
 * 
 * This class implements the user interface for the GroceryStore project. The
 * commands are encoded as integers using a number of static final variables. A
 * number of utility methods exist to make it easier to parse the input.
 *
 */
public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static GroceryStore groceryStore;
	private static final int EXIT = 0;
	private static final int ADD_MEMBER = 1;
	private static final int REMOVE_MEMBER= 2;
	private static final int RETRIEVE_MEMBER_INFO = 3;
	private static final int ADD_PRODUCTS = 4;
	private static final int CHECK_OUT = 5;
	private static final int RETRIEVE_PRODUCT_INFO = 6;
	private static final int PROCESS_SHIPMENT = 7;
	private static final int CHANGE_PRICE = 8;
	private static final int PRINT_TRANSACTIONS = 9;
	private static final int LIST_MEMBERS = 10;
	private static final int LIST_PRODUCTS = 11;
	private static final int LIST_ORDERS = 12;
	private static final int SAVE = 13;
	private static final int HELP = 15; //needs to be 15 as specified in the requirements

	/**
	 * Made private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton GroceryStore object.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and use it?")) {
			retrieve();
		} else {
			groceryStore = GroceryStore.instance();
			if (yesOrNo("Do you want to generate a test bed and invoke the functionality using asserts?")) {
				groceryStore.test();
			}
		}

	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Gets a name after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getName(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				return line;
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);

	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Converts the string to a number
	 * 
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}
	
	/**
	 * Converts the string to a double
	 * 
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public double getDouble(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Double number = Double.valueOf(item);
				return number.doubleValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * Prompts for a date and gets a date object
	 * 
	 * @param prompt the prompt
	 * @return the data as a Calendar object
	 */
	public Calendar getDate(String prompt) {
		do {
			try {
				Calendar date = new GregorianCalendar();
				String item = getToken(prompt);
				DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {
				System.out.println("Please input a date as mm/dd/yy");
			}
		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Displays the help screen
	 * 
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 15 as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ADD_MEMBER + " to add a member");
		System.out.println(REMOVE_MEMBER + " to remove member");
		System.out.println(RETRIEVE_MEMBER_INFO + " to retrieve member info");
		System.out.println(ADD_PRODUCTS + " to add products");
		System.out.println(CHECK_OUT + " to check out");
		System.out.println(RETRIEVE_PRODUCT_INFO + " to retrive product info");
		System.out.println(PROCESS_SHIPMENT + " to process a shipment");
		System.out.println(CHANGE_PRICE + " to change the price of a product");
		System.out.println(PRINT_TRANSACTIONS + " to print transactions");
		System.out.println(LIST_MEMBERS + " to list all members");
		System.out.println(LIST_PRODUCTS + " to list all products");
		System.out.println(LIST_ORDERS + " to list all outstanding orders");
		System.out.println(SAVE + " to save data");
		System.out.println(HELP + " for help");
	}

	/**
	 * Method to be called for adding a member. Prompts the user for the appropriate
	 * values and uses the appropriate GroceryStore method for adding the member.
	 * 
	 */
	public void addMember() {
		Request.instance().setMemberName(getName("Enter member name"));
		Request.instance().setMemberAddress(getName("Enter address"));
		Request.instance().setMemberPhone(getName("Enter phone"));
		Request.instance().setMemberDateJoined(getDate("Enter date joined"));
		Request.instance().setMemberFee(getDouble("Enter fee"));
		Result result = groceryStore.addMember(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member");
		} else {
			System.out.println(result.getMemberName() + "'s id is " + result.getMemberId());
		}
	}

	/**
	 * Method to be called for removing a member.  Prompts the user for the appropriate
	 * values and uses the appropriate method for removing a member
	 * 
	 */
	public void removeMember() {
		Request.instance().setMemberId(getToken("Enter member id"));
		Result result = groceryStore.removeMember(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not remove member");
		} else {
			System.out.println("Member has been removed");
		}
	}
	
	/**
	 * Method to display member info. Prompts the user for a member's name and displays
	 * member's address, fee paid, and id.  If there is more than one member with the 
	 * same name, print all such members.
	 * 
	 */
	public void retrieveMemberInfo() {
		Request.instance().setMemberName(getToken("Enter member name"));
		Result result = groceryStore.searchMembership(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("No such member");
		} else {
			System.out.println("Member name " + result.getMemberName() + " (address, fee, id)");
			Iterator<Result> iterator = groceryStore.getMembers();
			while (iterator.hasNext()) {
				Result temp = iterator.next();
				if (result.getMemberName().equalsIgnoreCase(temp.getMemberName())) {
					System.out.println(temp.getMemberAddress() + ", " + temp.getMemberFee() + ", "
							+ temp.getMemberId());
				}
			}
		}
	}
	/**
	 * Method to add products to catalog. Prompts the user for name, price, and reorder level.
	 * The method uses necessary methods within grocery store to add product.
	 * 
	 */
	public void addProducts() {
		Request.instance().setProductName(getName("Enter product name"));
		Request.instance().setProductPrice(getDouble("Enter product price"));
		Request.instance().setProductReorderLevel(getNumber("Enter product reorder level"));
		Result result = groceryStore.addProduct(Request.instance());
		if (result.getResultCode() == Result.OPERATION_COMPLETED) {
			System.out.println("Product added to catalog");
		} else {
			System.out.println("Product could not be added to catalog");
		}
	}

	/**
	 * Method to check out a member's cart. Prompts the user to enter the product id
	 * and quantity for each item. Method finds and displays the name of each item, the
	 * number of units, the unit price, and the total price for each item.  Also calculates
	 * and displays the total price for all items combined.  Reorder's product if needed.
	 * Generates a transaction for the purchase.
	 * 
	 */
	public void checkOut() {
		Request.instance().setMemberId(getToken("Enter member id"));
		Result result = groceryStore.checkMembership(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("No member with id " + Request.instance().getMemberId());
		} else {
			String receipt = "";
			double total = 0;
			do {
				Request.instance().setProductId(getToken("Enter product id"));
				result = groceryStore.retrieveProductRequest(Request.instance());
				if (result.getResultCode() != Result.OPERATION_COMPLETED) {
					displayResultCode(result.getResultCode());
				} else {
					Request.instance().setProductName(result.getProductName());
					Request.instance().setProductPrice(result.getProductPrice());
					Request.instance().setQuantityPurchased(getNumber("Enter the quantity."));
				}
				result = groceryStore.addLineItem(Request.instance());
				if (result.getResultCode() == Result.ORDER_PLACED) {
					System.out.printf("Order for %s will be placed.\n", result.getProductName());
				} else if (result.getResultCode() != Result.OPERATION_COMPLETED) {
					displayResultCode(result.getResultCode());
				}
				receipt += result.getProductName() + "\t" + result.getQuantityPurchased() + "\t$" + result.getProductPrice()
					+ "\t$" + result.getProductPrice() * result.getQuantityPurchased() + "\n";
				total += result.getProductPrice() * result.getQuantityPurchased();
			} while (yesOrNo("Check out more items?"));
			Request.instance().setTransactionAmount(total);
			result = groceryStore.addTransaction(Request.instance());
			receipt += "Total\t\t\t$" + total + "\n";
			System.out.println(receipt);
		}
	}
	
	/**
	 * Method for retrieving info regarding a product in the catalog. Prompts the user
	 * for the product name and uses the appropriate GroceryStore method for retrieving the
	 * product info. 
	 * 
	 */
	public void retrieveProductInfo() {
		Request.instance().setProductName(getToken("Enter product name"));
		Result result = groceryStore.retrieveProductInfo(Request.instance());
		if (result.getResultCode() == Result.OPERATION_COMPLETED) {
			System.out.println("(Product id, price, stock)");
			System.out.println(result.getProductId() + ", " + result.getProductPrice() + ", " + result.getProductStock());
		} else {
			System.out.println("Product not found within catalog");
		}
	}
	
	/**
	 * Method for processing shipments for the GroceryStore. Prompts the user
	 * for the appropriate value and uses the appropriate GroceryStore method for
	 * processing an outstanding order. User can process multiple orders if needed.
	 * 
	 */
	public void processShipment() {
		do {
			Request.instance().setProductId(getToken("Enter product id"));
			Result result = groceryStore.processShipment(Request.instance());
			if(result.getResultCode() == Result.OPERATION_COMPLETED) {
				System.out.println("(Product id, name, updated stock)");
				System.out.println(result.getProductId() + ", " + result.getProductName() + ", " + result.getProductStock());
			} else {
				System.out.println("Order not found within system");
			}
		} while (yesOrNo("Process another shipment?"));
	}

	/**
	 * Method to change the price of a given product within the catalog. Prompts the user
	 * for the appropriate values and uses the appropriate GroceryStore method for changing
	 * the price of a product.
	 * 
	 */
	public void changePrice() {
		Request.instance().setProductId(getName("Enter product id"));
		Request.instance().setProductPrice(getDouble("Enter new price of product"));
		Result result = groceryStore.changePrice(Request.instance());
		if(result.getResultCode() == Result.OPERATION_COMPLETED) {
			System.out.println("Product name and updated price");
			System.out.println(result.getProductName() + " " + result.getProductPrice());
		}
		else {
			System.out.println("Product does not exist within catalog");
		}
	}
	
	/**
	 * Method to be called for displaying transactions. Prompts the user for the
	 * appropriate values and uses the appropriate GroceryStore method for displaying
	 * transactions. (Currently works for single date)
	 * 
	 */
	public void getTransactions() {
		Request.instance().setMemberId(getName("Enter member id"));
		Request.instance().setDate(getDate("Please enter the start date for which you want records as mm/dd/yy"));
		Request.instance().setEndDate(getDate("Please enter the end date for which you want records as mm/dd/yy"));
		Iterator<Result> result = groceryStore.getTransactions(Request.instance());
		while (result.hasNext()) {
			Result transaction = result.next();
			System.out.println(transaction.getTransactionDate() + "   " + transaction.getTransactionAmount() + "\n");
		}
		System.out.println("\n End of transactions \n");
	}

	/**
	 * Displays all members.
	 */
	public void getMembers() {
		Iterator<Result> iterator = groceryStore.getMembers();
		System.out.println("List of members (name, id, address)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getMemberName() + ", " + result.getMemberId() + ", " + result.getMemberAddress());
		}
		System.out.println("End of listing");
	}

	/**
	 * Displays all products in the catalog.
	 */
	public void getProducts() {
		Iterator<Result> iterator = groceryStore.getProducts();
		System.out.println("List of products (name, id, price, reorder level)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getProductName() + ", " + result.getProductId() + ", " + result.getProductPrice() + ", "
					+ result.getProductReorderLevel());
		}
		System.out.println("End of listing");
	}
	
	/**
	 * Displays all outstanding orders of the GroceryStore.
	 */
	public void getOrders() {
		Iterator<Result> iterator = groceryStore.getOrders();
		System.out.println("List of outstanding orders (product name, product id, quantity ordered)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getProductName() + ", " + result.getProductId() + ", " + result.getQuantityOrdered());
		}
		System.out.println("End of listing");
	}

	/**
	 * Method to be called for saving the GroceryStore object. Uses the appropriate
	 * GroceryStore method for saving.
	 * 
	 */
	private void save() {
		if (groceryStore.save()) {
			System.out.println(" The groceryStore has been successfully saved in the file GroceryStoreData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}

	/**
	 * Method to be called for retrieving saved data. Uses the appropriate GroceryStore
	 * method for retrieval.
	 * 
	 */
	private void retrieve() {
		try {
			if (groceryStore == null) {
				groceryStore = GroceryStore.retrieve();
				if (groceryStore != null) {
					System.out.println(" The groceryStore has been successfully retrieved from the file GroceryStoreData \n");
				} else {
					System.out.println("File doesnt exist; creating new groceryStore");
					groceryStore = GroceryStore.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Method to display info about a result code.
	 * @param resultCode  The result code to display the message for.
	 */
	private void displayResultCode(int resultCode) {
		String message = "";
		switch(resultCode) {
		case Result.OPERATION_COMPLETED:
			message = "Operation Completed.";
			break;
		case Result.OPERATION_FAILED:
			message = "Operation Failed.";
			break;
		case Result.PRODUCT_NOT_FOUND:
			message = "Product not found.";
			break;
		case Result.ORDER_PLACED:
			message = "Order placed.";
			break;
		case Result.NO_ORDER_FOUND:
			message = "No order found.";
			break;
		case Result.NO_SUCH_MEMBER:
			message = "No such member.";
			break;
		}
	
		System.out.println(message);
	}

	/**
	 * Orchestrates the whole process. Calls the appropriate method for the
	 * different functionalities.
	 * 
	 */
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_MEMBER:
				addMember();
				break;
			case REMOVE_MEMBER:
				removeMember();
				break;
			case RETRIEVE_MEMBER_INFO:
				retrieveMemberInfo();
				break;
			case ADD_PRODUCTS:
				addProducts();
				break;
			case CHECK_OUT:
				checkOut();
				break;
			case RETRIEVE_PRODUCT_INFO:
				retrieveProductInfo();
				break;
			case PROCESS_SHIPMENT:
				processShipment();
				break;
			case CHANGE_PRICE:
				changePrice();
				break;
			case PRINT_TRANSACTIONS:
				getTransactions();
				break;
			case LIST_MEMBERS:
				getMembers();
				break;
			case LIST_PRODUCTS:
				getProducts();
				break;
			case LIST_ORDERS:
				getOrders();
				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			}
		}
	}

	/**
	 * The method to start the application. Simply calls process().
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}
