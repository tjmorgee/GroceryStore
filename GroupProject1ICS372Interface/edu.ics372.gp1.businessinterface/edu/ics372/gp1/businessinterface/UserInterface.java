package edu.ics372.gp1.businessinterface;


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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.oobook.libraryv1.business.facade.Library;
import org.oobook.libraryv1interface.UserInterface;

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
	private static final int RETRIEVE = 14;
	private static final int HELP = 15;

	/**
	 * Made private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton GroceryStore object.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			groceryStore = GroceryStore.instance();
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
		System.out.println("Enter a number between 0 and 12 as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ADD_MEMBER + " to add a member");
		System.out.println(REMOVE_MEMBER + " to remove member");
		System.out.println(RETRIEVE_MEMBER_INFO + " to retrieve member info");
		System.out.println(ADD_PRODUCTS + " to add products");
		System.out.println(CHECK_OUT + " to check out");
		System.out.println(RETRIEVE_PRODUCT_INFO + " to retrive product info");
		System.out.println(PROCESS_SHIPMENT + " to  place a hold on a book");
		System.out.println(CHANGE_PRICE + " to  remove a hold on a book");
		System.out.println(PRINT_TRANSACTIONS + " to  process holds");
		System.out.println(LIST_MEMBERS + " to  print transactions");
		System.out.println(LIST_PRODUCTS + " to  print all members");
		System.out.println(LIST_ORDERS + " to  print all books");
		System.out.println(SAVE + " to  save data");
		System.out.println(RETRIEVE + " to retrive data");  //should only work before other commands are issued.
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
		//TODO:  ADD SOMETHING TO INCLUDE FEE
		Result result = groceryStore.addMember(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member");
		} else {
			System.out.println(result.getMemberName() + "'s id is " + result.getMemberId());
		}
	}
	
	/**
	 * Method to be called for removing members. Prompts the user for the appropriate
	 * values and uses the appropriate GroceryStore method for removing memberss.
	 * 
	 */
	public void removeMembers() {
		do {
			switch (result.getResultCode()) {
			}
			if (!yesOrNo("Remove more members?")) {
				break;
			}
		} while (true);
	}

	/**
	 * Method to be called for adding a book. Prompts the user for the appropriate
	 * values and uses the appropriate GroceryStore method for adding the book.
	 * 
	 */
	public void addBooks() {
		do {
			Request.instance().setBookTitle(getName("Enter  title"));
			Request.instance().setBookId(getToken("Enter id"));
			Request.instance().setBookAuthor(getName("Enter author"));
			Result result = groceryStore.addBook(Request.instance());
			if (result.getResultCode() != Result.OPERATION_COMPLETED) {
				System.out.println("Book could not be added");
			} else {
				System.out.println("Book " + result.getBookTitle() + " added");
			}
		} while (yesOrNo("Add more books?"));
	}

	/**
	 * Method to be called for issuing books. Prompts the user for the appropriate
	 * values and uses the appropriate GroceryStore method for issuing books.
	 * 
	 */
	public void issueBooks() {
		Request.instance().setMemberId(getToken("Enter member id"));
		Result result = groceryStore.searchMembership(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("No member with id " + Request.instance().getMemberId());
			return;
		}
		do {
			Request.instance().setBookId(getToken("Enter book id"));
			result = groceryStore.issueBook(Request.instance());
			if (result.getResultCode() == Result.OPERATION_COMPLETED) {
				System.out.println("Book " + result.getBookTitle() + " issued to " + result.getMemberName()
						+ " is due on " + result.getBookDueDate());
			} else {
				System.out.println("Book could not be issued");
			}
		} while (yesOrNo("Issue more books?"));
	}


	/**
	 * Method to be called for processing books. Prompts the user for the
	 * appropriate values and uses the appropriate GroceryStore method for processing
	 * books.
	 * 
	 */
	public void processHolds() {
		do {
			Request.instance().setBookId(getToken("Enter book id"));
			Result result = groceryStore.processHold(Request.instance());
			if (result.getResultCode() == Result.OPERATION_COMPLETED) {
				System.out.println("Book " + result.getBookTitle() + " should be issued to " + result.getMemberName()
						+ " phone " + result.getMemberPhone());
			} else if (result.getResultCode() == Result.BOOK_ISSUED) {
				System.out.println("This book " + result.getBookTitle() + " is still issued");
			} else {
				System.out.println("No valid holds left");
			}
			if (!yesOrNo("Process more books?")) {
				break;
			}
		} while (true);
	}

	/**
	 * Method to be called for displaying transactions. Prompts the user for the
	 * appropriate values and uses the appropriate GroceryStore method for displaying
	 * transactions.
	 * 
	 */
	public void getTransactions() {
		Request.instance().setMemberId(getToken("Enter member id"));
		Request.instance().setDate(getDate("Please enter the date for which you want records as mm/dd/yy"));
		Iterator<Result> result = groceryStore.getTransactions(Request.instance());
		while (result.hasNext()) {
			Result transaction = result.next();
			System.out.println(transaction.getTransactionDate() + "   " + transaction.getPaid() + "\n");
		}
		System.out.println("\n End of transactions \n");
	}

	/**
	 * Displays all members
	 */
	public void getMembers() {
		Iterator<Result> iterator = groceryStore.getMembers();
		System.out.println("List of members (name, address, phone, id)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getMemberName() + " " + result.getMemberAddress() + " " + result.getMemberPhone()
					+ " " + result.getMemberId());
		}
		System.out.println("End of listing");
	}

	/**
	 * Gets and prints all books.
	 */
	public void getBooks() {
		Iterator<Result> iterator = groceryStore.getBooks();
		System.out.println("List of books (title, author, id, borrower id, due date)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getBookTitle() + " " + result.getBookAuthor() + " " + result.getBookId() + " "
					+ result.getBookBorrower() + " " + result.getBookDueDate());
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
			case ADD_BOOKS:
				addBooks();
				break;
			case ISSUE_BOOKS:
				issueBooks();
				break;
			case RETURN_BOOKS:
				returnBooks();
				break;
			case REMOVE_BOOKS:
				removeBooks();
				break;
			case RENEW_BOOKS:
				renewBooks();
				break;
			case PLACE_HOLD:
				placeHold();
				break;
			case REMOVE_HOLD:
				removeHold();
				break;
			case PROCESS_HOLD:
				processHolds();
				break;
			case GET_TRANSACTIONS:
				getTransactions();
				break;
			case GET_MEMBERS:
				getMembers();
				break;
			case GET_BOOKS:
				getBooks();
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