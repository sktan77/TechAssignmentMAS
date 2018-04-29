package tsk.techassignment.model.jsonobj;

import static org.junit.Assert.*;

import org.junit.Test;

import tsk.techassignment.model.FinancialInstitute;

public class RecordTest {
	private final Record bankHigherRecord = new Record(null, new Double(2.0), new Double(3.0), new Double(4.0), new Double(5.0),
			new Double(1.0), new Double(1.1), new Double(1.2), new Double(1.3));
	private final Record bankHigherFCNullRecord = new Record(null, new Double(2.0), new Double(3.0), new Double(4.0), new Double(5.0),
			null, null, null, null);
	private final Record fcHigherRecord = new Record(null, new Double(2.0), new Double(3.0), new Double(4.0), new Double(5.0),
			new Double(2.1), new Double(3.2), new Double(4.5), new Double(6.3));
	private final Record fcHigherBankNullRecord = new Record(null, null, null, null, null,
			new Double(2.1), new Double(3.2), new Double(4.5), new Double(6.3));
	
	@Test
	public void testBankRateHigher() {		
		assertTrue(bankHigherRecord.getWhichFixD3MHigher() == FinancialInstitute.Bank);
		assertTrue(bankHigherRecord.getWhichFixD6MHigher() == FinancialInstitute.Bank);
		assertTrue(bankHigherRecord.getWhichFixD12MHigher() == FinancialInstitute.Bank);
		assertTrue(bankHigherRecord.getWhichSaveDHigher() == FinancialInstitute.Bank);
	}
	
	@Test
	public void testBankRateHigherIfFCNull() {				
		assertTrue(bankHigherFCNullRecord.getWhichFixD3MHigher() == FinancialInstitute.Bank);
		assertTrue(bankHigherFCNullRecord.getWhichFixD6MHigher() == FinancialInstitute.Bank);
		assertTrue(bankHigherFCNullRecord.getWhichFixD12MHigher() == FinancialInstitute.Bank);
		assertTrue(bankHigherFCNullRecord.getWhichSaveDHigher() == FinancialInstitute.Bank);
	}	
	
	@Test
	public void testFCRateHigher() {				
		assertTrue(fcHigherRecord.getWhichFixD3MHigher() == FinancialInstitute.FinancialCompanies);
		assertTrue(fcHigherRecord.getWhichFixD6MHigher() == FinancialInstitute.FinancialCompanies);
		assertTrue(fcHigherRecord.getWhichFixD12MHigher() == FinancialInstitute.FinancialCompanies);
		assertTrue(fcHigherRecord.getWhichSaveDHigher() == FinancialInstitute.FinancialCompanies);
	}	
	
	@Test
	public void testFCRateHigherIfBankNull() {				
		assertTrue(fcHigherBankNullRecord.getWhichFixD3MHigher() == FinancialInstitute.FinancialCompanies);
		assertTrue(fcHigherBankNullRecord.getWhichFixD6MHigher() == FinancialInstitute.FinancialCompanies);
		assertTrue(fcHigherBankNullRecord.getWhichFixD12MHigher() == FinancialInstitute.FinancialCompanies);
		assertTrue(fcHigherBankNullRecord.getWhichSaveDHigher() == FinancialInstitute.FinancialCompanies);
	}	

}
