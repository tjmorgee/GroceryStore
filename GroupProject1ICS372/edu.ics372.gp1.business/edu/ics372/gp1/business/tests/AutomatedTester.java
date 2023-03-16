package edu.ics372.gp1.business.tests;
import edu.ics372.gp1.business.facade.GroceryStore;
import edu.ics372.gp1.business.facade.Request;
import edu.ics372.gp1.business.facade.Result;

import java.util.GregorianCalendar;
public class AutomatedTester {
	int FullWordsBecauseIAmNotSupposedToUseTheLetterI = 0;
	private static final int TESTS = 3;
	private GroceryStore groceryStore = GroceryStore.instance();
	private String[] names = {"name1", "name2", "name3"};
	private String[] addresses = {"address1", "address2", "address3"};
	private String[] phones = {"phone1", "phone2", "phone3"};
	private double[] fee = {599.22, 48, 483.2};
	private String[] dates = {"d1", "d2", "d3"};
//	private GregorianCalendar[] dates = new GregorianCalendar[3];

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
	
	public void test() {
		testAddMember();
	}
}
