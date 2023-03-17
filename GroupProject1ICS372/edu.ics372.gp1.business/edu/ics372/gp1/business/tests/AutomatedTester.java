package edu.ics372.gp1.business.tests;
import edu.ics372.gp1.business.facade.GroceryStore;
import edu.ics372.gp1.business.facade.Request;
import edu.ics372.gp1.business.facade.Result;

import java.util.GregorianCalendar;
import java.util.Iterator;
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
	
	private void testAddProducts() {
		Result result;
		for (int i = 0; i < TESTS - 1; ++i) {  //substitute something for i later
			Request.instance().reset();
			Request.instance().setProductName(names[i]);
			Request.instance().setProductPrice(prices[i]);
			Request.instance().setProductReorderLevel(reorderLevels[i]);
			result = groceryStore.addProduct(Request.instance());
			if (i < TESTS - 1) {  //last name is same as first and should fail
				assert result.getResultCode() == Result.OPERATION_COMPLETED;
			} else {
				assert result.getResultCode() == Result.OPERATION_FAILED;
			}
			assert result.getProductName() ==names[i];
			assert result.getProductPrice() == prices[i];
			assert result.getProductReorderLevel() == reorderLevels[i];
			assert result.getProductStock() == 0;
		}
	}
	
	public void test() {
		testAddMember();
		testRemoveMember();
		testRetrieveMember();
		testAddProducts();
	}
}