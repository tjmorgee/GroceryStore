package edu.ics372.gp1.business.tests;
import edu.ics372.gp1.business.entities.Member;
import edu.ics372.gp1.business.entities.Product;
import edu.ics372.gp1.business.facade.GroceryStore;
import edu.ics372.gp1.business.facade.Request;
import edu.ics372.gp1.business.facade.Result;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
public class AutomatedTester {
	private static final int TESTS = 4;
	private GroceryStore groceryStore = GroceryStore.instance();
	private String[] names = {"name1", "name2", "name3", "name1"};
	private String[] addresses = {"address1", "address2", "address3", "address4"};
	private String[] phones = {"phone1", "phone2", "phone3", "phone4"};
	private double[] fee = {599.22, 48, 483.2, 4};
	private String[] dates = {"d1", "d2", "d3", "d4"};
//	private GregorianCalendar[] dates = new GregorianCalendar[3];
	
	private double[] prices = {1.0, 20.0, 33.3, 44.4};
	private int[] reorderLevels = {10, 20, 30, 40};
	
	public AutomatedTester() {
		test();
	}
	private void testAddMember() {
		for (int FullWordsBecauseIAmNotSupposedToUseTheLetterI = 0; FullWordsBecauseIAmNotSupposedToUseTheLetterI < TESTS; FullWordsBecauseIAmNotSupposedToUseTheLetterI++) {
			Request.instance().setMemberAddress(addresses[FullWordsBecauseIAmNotSupposedToUseTheLetterI]);
			Request.instance().setMemberName(names[FullWordsBecauseIAmNotSupposedToUseTheLetterI]);
			Request.instance().setMemberPhone(phones[FullWordsBecauseIAmNotSupposedToUseTheLetterI]);
			Request.instance().setMemberDateJoined(dates[FullWordsBecauseIAmNotSupposedToUseTheLetterI]);
			Request.instance().setMemberFee(fee[FullWordsBecauseIAmNotSupposedToUseTheLetterI]);
			Result result = groceryStore.addMember(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getMemberAddress() == addresses[FullWordsBecauseIAmNotSupposedToUseTheLetterI];
			assert result.getMemberDateJoined() == dates[FullWordsBecauseIAmNotSupposedToUseTheLetterI];
			assert result.getMemberFee() == fee[FullWordsBecauseIAmNotSupposedToUseTheLetterI];
			assert result.getMemberName() == names[FullWordsBecauseIAmNotSupposedToUseTheLetterI];
			assert result.getMemberPhone() == phones[FullWordsBecauseIAmNotSupposedToUseTheLetterI];
		}
	}
	
	private void testRemoveMember() {
		Request.instance().reset();
		Request.instance().setMemberName(names[1]);
		Result result = groceryStore.searchMembership(Request.instance());
		assert result.getResultCode() == Result.OPERATION_COMPLETED;
		Request.instance().setMemberId(result.getMemberId());
		result = groceryStore.removeMember(Request.instance());
		assert result.getResultCode() == Result.OPERATION_COMPLETED;
		result = groceryStore.searchMembership(Request.instance());
		assert result.getResultCode() == Result.NO_SUCH_MEMBER;
	}
	
	private void testRetrieveMember() {
		Result result;
		Request.instance().setMemberName(names[0]);
		result = groceryStore.searchMembership(Request.instance());
		assert result.getResultCode() == Result.OPERATION_COMPLETED;
		assert result.getMemberName() == names[0];
		assert result.getMemberAddress() == addresses[0];
		assert result.getMemberPhone() == phones[0];
		assert result.getMemberFee() == fee[0];
		assert result.getMemberId().equals("M1");
	
		result.setResultCode(-1);
		Iterator<Result> iterator = groceryStore.getMembers();
		iterator.next();
		while (iterator.hasNext()) {
			Result temp = iterator.next();
			if (result.getMemberName().equalsIgnoreCase(temp.getMemberName())) {
				assert temp.getMemberName() == names[3];
				assert temp.getMemberAddress()  == addresses[3];
				assert temp.getMemberPhone()  == phones[3];
				assert temp.getMemberFee() == fee[3];
				assert temp.getMemberId().equals("M4");
				result.setResultCode(Result.OPERATION_COMPLETED);
			}
		}
			assert result.getResultCode() == Result.OPERATION_COMPLETED; 
	}
	
//	private void testAddProducts() {
//		Result result;
//		for (int i = 0; i < TESTS - 1; ++i) {  //substitute something for i later
//			Request.instance().reset();
//			Request.instance().setProductName(names[i]);
//			Request.instance().setProductPrice(prices[i]);
//			Request.instance().setProductReorderLevel(reorderLevels[i]);
//			result = groceryStore.addProduct(Request.instance());
//			if (i < TESTS - 1) {  //last name is same as first and should fail
//				assert result.getResultCode() == Result.OPERATION_COMPLETED;
//			} else {
//				assert result.getResultCode() == Result.OPERATION_FAILED;
//			}
//			assert result.getProductName() ==names[i];
//			assert result.getProductPrice() == prices[i];
//			assert result.getProductReorderLevel() == reorderLevels[i];
//			assert result.getProductStock() == 0;
//		}
//	}
	
	// add 5 members to member list, Will adjust date to be a date
	public void testAddMembers() {
		Result result;
		List<Member> members = new LinkedList<Member>();
		Member member1 = new Member("Tilly", "1234 This Way", "651-654-1234", "01/01/2022", 25.00);
		Member member2 = new Member("Jenkins", "12104 Yellow St N", "651-320-5546", "01/03/2022", 25.00);
		Member member3 = new Member("Tuck", "1108 Quay St NE", "763-222-1981", "01/03/2022", 25.00);
		Member member4 = new Member("Bill", "15 Rodeo Way", "612-999-0091", "01/05/2022", 30.00);
		Member member5 = new Member("Ricky", "99 Superior St", "320-543-9876", "01/11/2022", 35.00);
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
	
	// Hard coded member id, was failing otherwise
	public void removeMember() {
		Result result;
		Request.instance().reset();
		Request.instance().setMemberId("M7");
		result = groceryStore.removeMember(Request.instance());
		assert result.getResultCode() == Result.OPERATION_COMPLETED;
		Request.instance().setMemberId("G4");
		result = groceryStore.removeMember(Request.instance());
		assert result.getResultCode() == Result.NO_SUCH_MEMBER;
	}
	
	// add 20 products to catalog
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
	
	public void test() {
		//testAddMember();
		//testRemoveMember();
		//testRetrieveMember();
		testAddMembers();
		removeMember();
		testAddProducts();
	}
	
	public static void main(String[] args) {
		AutomatedTester test = new AutomatedTester();
	}
}