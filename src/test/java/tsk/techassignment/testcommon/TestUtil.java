package tsk.techassignment.testcommon;

import static org.junit.Assert.assertTrue;

import tsk.techassignment.model.jsonobj.Record;

public class TestUtil {
	public static void verifyRecordData(final String endOfMonth, final Double bankFixD3m, final Double bankFixD6M, final Double bankFixD12M,
			final Double bankSavD, final Double fcFixD3M, final Double fcFixD6M, final Double fcFixD12M, final Double fcSavD, 
			final Record recordToCompare) {
		assertTrue(compareString(endOfMonth,recordToCompare.getEnd_of_month()));
		assertTrue(checkDoubleWithNull(bankFixD3m, recordToCompare.getBanks_fixed_deposits_3m()));
		assertTrue(checkDoubleWithNull(bankFixD6M,recordToCompare.getBanks_fixed_deposits_6m()));
		assertTrue(checkDoubleWithNull(bankFixD12M,recordToCompare.getBanks_fixed_deposits_12m()));
		assertTrue(checkDoubleWithNull(bankSavD,recordToCompare.getBanks_savings_deposits()));
		assertTrue(checkDoubleWithNull(fcFixD3M,recordToCompare.getFc_fixed_deposits_3m()));
		assertTrue(checkDoubleWithNull(fcFixD6M,recordToCompare.getFc_fixed_deposits_6m()));
		assertTrue(checkDoubleWithNull(fcFixD12M,recordToCompare.getFc_fixed_deposits_12m()));
		assertTrue(checkDoubleWithNull(fcSavD,recordToCompare.getFc_savings_deposits()));
	}
	
	public static boolean compareString(final String expectedValue, final String valueToVerify) {
		if (expectedValue == null && valueToVerify == null) return true;
		
		return valueToVerify.equals(expectedValue);
	}
	
	public static boolean checkDoubleWithNull(final Double expectedValue, final Double valueToVerify) {
		if (expectedValue == null && valueToVerify == null) return true;
		
		return valueToVerify.equals(expectedValue);
	}
}
