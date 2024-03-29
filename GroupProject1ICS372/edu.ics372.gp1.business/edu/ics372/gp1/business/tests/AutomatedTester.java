package edu.ics372.gp1.business.tests;
import edu.ics372.gp1.business.entities.Member;
import edu.ics372.gp1.business.entities.Product;
import edu.ics372.gp1.business.facade.GroceryStore;
import edu.ics372.gp1.business.facade.Request;
import edu.ics372.gp1.business.facade.Result;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * This class generates a test bed of objects for the program and tests
 * given methods using assert statements.
 * 
 * @author Thomas Morgenstern and Jonathan Voss
 *
 */
public class AutomatedTester {
	private GroceryStore groceryStore = GroceryStore.instance();
	
	public AutomatedTester() {
		test();
	}
	
	/**
	 * Method test for adding 5 members to grocery store (Business Process 1)
	 * 
	 */
	public void testAddMembers() {
		Result result;
		List<Member> members = new LinkedList<Member>();
		Calendar date = Calendar.getInstance();
		Member member1 = new Member("Tilly", "1234 This Way", "651-654-1234", date, 25.00);
		Member member2 = new Member("Jenkins", "12104 Yellow St N", "651-320-5546", date, 25.00);
		Member member3 = new Member("Tuck", "1108 Quay St NE", "763-222-1981", date, 25.00);
		date.set(2022, 4, 15);
		Member member4 = new Member("Bill", "15 Rodeo Way", "612-999-0091", date, 30.00);
		Member member5 = new Member("Ricky", "99 Superior St", "320-543-9876", date, 35.00);
		members.add(member1);
		members.add(member2);
		members.add(member3);
		members.add(member4);
		members.add(member5);
		for(int i = 0; i < members.size(); i++) {
			Request.instance().reset();
			Request.instance().setMemberName(members.get(i).getName());
			Request.instance().setMemberAddress(members.get(i).getAddress());
			Request.instance().setMemberPhone(members.get(i).getPhone());
			Request.instance().setMemberDateJoined(members.get(i).getDateJoined());
			Request.instance().setMemberFee(members.get(i).getFee());
			result = groceryStore.addMember(Request.instance());
			assert result.getMemberName() == members.get(i).getName();
			assert result.getMemberAddress() == members.get(i).getAddress();
			assert result.getMemberPhone() == members.get(i).getPhone();
			assert result.getMemberDateJoined() == members.get(i).getDateJoined();
			assert result.getMemberFee() == members.get(i).getFee();
		}
	}
	
	/**
	 * Method test for removing a member from grocery store (Business Process 2)
	 * 
	 */
	public void testRemoveMember() {
		Result result;
		Request.instance().reset();
		Request.instance().setMemberId("M7");
		result = groceryStore.removeMember(Request.instance());
		assert result.getResultCode() == Result.OPERATION_COMPLETED;
		Request.instance().setMemberId("G4");
		result = groceryStore.removeMember(Request.instance());
		assert result.getResultCode() == Result.OPERATION_FAILED;
	}
	
	/**
	 * Method test for adding 20 products to catalog (Business Process 4)
	 * 
	 */
	public void testAddProducts() {
		Result result;
		List<Product> products = new LinkedList<Product>();
		Product product1 = new Product("Apple", 1.99, 100);
		Product product2 = new Product("Orange", 2.19, 75);
		Product product3 = new Product("Banana", .79, 60);
		Product product4 = new Product("Donut", 1.25, 30);
		Product product5 = new Product("Soda", 2.99, 85);
		Product product6 = new Product("Cheese", 4.99, 45);
		Product product7 = new Product("Pizza", 9.99, 40);
		Product product8 = new Product("Chips", 3.99, 60);
		Product product9 = new Product("Fries", 8.99, 35);
		Product product10 = new Product("Tots", 7.99, 45);
		Product product11 = new Product("Lollipop", .59, 100);
		Product product12 = new Product("Snickers", 1.99, 50);
		Product product13 = new Product("Gum", .99, 75);
		Product product14 = new Product("Soup", 1.99, 150);
		Product product15 = new Product("Noodles", 4.99, 80);
		Product product16 = new Product("Water", 1.99, 100);
		Product product17 = new Product("Pop-Tarts", 4.99, 35);
		Product product18 = new Product("Cereal", 2.99, 70);
		Product product19 = new Product("Waffles", 5.99, 50);
		Product product20 = new Product("Hamburger", 10.99, 70);
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product4);
		products.add(product5);
		products.add(product6);
		products.add(product7);
		products.add(product8);
		products.add(product9);
		products.add(product10);
		products.add(product11);
		products.add(product12);
		products.add(product13);
		products.add(product14);
		products.add(product15);
		products.add(product16);
		products.add(product17);
		products.add(product18);
		products.add(product19);
		products.add(product20);
		for(int i = 0; i < products.size(); i++) {
			Request.instance().reset();
			Request.instance().setProductName(products.get(i).getName());
			Request.instance().setProductPrice(products.get(i).getPrice());
			Request.instance().setProductReorderLevel(products.get(i).getReorderLevel());
			result = groceryStore.addProduct(Request.instance());
			assert result.getProductName() == products.get(i).getName();
			assert result.getProductPrice() == products.get(i).getPrice();
			assert result.getProductReorderLevel() == products.get(i).getReorderLevel();
			assert result.getProductStock() == 0;
		}
	}
	
	/**
	 * Method test for processing shipments (Business Process 7)
	 * 
	 */
	public void testProcessShipments() {
		Request.instance().reset();
		Result result;
		Request.instance().setProductId("P25");
		result = groceryStore.processShipment(Request.instance());
		assert result.getResultCode() == Result.OPERATION_COMPLETED;
		Request.instance().setProductId("P27");
		result = groceryStore.processShipment(Request.instance());
		assert result.getResultCode() == Result.OPERATION_COMPLETED;
		Request.instance().setProductId("P100");
		result = groceryStore.processShipment(Request.instance());
		assert result.getResultCode() == Result.OPERATION_FAILED;
	}
	
	/**
	 * Method test for checking out (Business Process 5)
	 * 
	 */
	public void testCheckOut() {
		Request.instance().reset();
		Result result;
		double total = 0;
		Request.instance().setProductId("P25");
		Request.instance().setQuantityPurchased(4);
		result = groceryStore.addLineItem(Request.instance());
		total += result.getProductPrice() * result.getQuantityPurchased();
		Request.instance().setProductId("P27");
		Request.instance().setQuantityPurchased(2);
		result = groceryStore.addLineItem(Request.instance());
		total += result.getProductPrice() * result.getQuantityPurchased();
		Request.instance().reset();
		Request.instance().setMemberId("M6");
		Request.instance().setTransactionAmount(total);
		result = groceryStore.addTransaction(Request.instance());
		assert result.getResultCode() == Result.OPERATION_COMPLETED;
	}
	
	/**
	 * Method test for changing price of a product (Business Process 8)
	 */
	public void testChangePrice() {
		Request.instance().reset();
		Result result;
		Request.instance().setProductId("P25");
		Request.instance().setProductPrice(20.25);
		result = groceryStore.changePrice(Request.instance());
		assert result.getResultCode() == Result.OPERATION_COMPLETED;
		Request.instance().setProductId("P99");
		Request.instance().setProductPrice(100.00);
		result = groceryStore.changePrice(Request.instance());
		assert result.getResultCode() == Result.OPERATION_FAILED;
	}
	
	/**
	 * Method to run all tests
	 * 
	 */
	public void test() {
		testAddMembers();
		testRemoveMember();
		testAddProducts();
		testProcessShipments();
		testCheckOut();
		testChangePrice();
	}
	
	public static void main(String[] args) {
		AutomatedTester test = new AutomatedTester();
	}
}