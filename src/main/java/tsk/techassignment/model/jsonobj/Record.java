package tsk.techassignment.model.jsonobj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tsk.techassignment.model.FinancialInstitute;

public class Record {
	private String end_of_month;
	private Double banks_fixed_deposits_3m; 
	private Double banks_fixed_deposits_6m;
	private Double banks_fixed_deposits_12m;
	private Double banks_savings_deposits;
	private Double fc_fixed_deposits_3m;
	private Double fc_fixed_deposits_6m;
	private Double fc_fixed_deposits_12m;
	private Double fc_savings_deposits;
	
	public Record() {}
		
	public Record(String end_of_month, Double banks_fixed_deposits_3m, Double banks_fixed_deposits_6m,
			Double banks_fixed_deposits_12m, Double banks_savings_deposits, Double fc_fixed_deposits_3m,
			Double fc_fixed_deposits_6m, Double fc_fixed_deposits_12m, Double fc_savings_deposits) {
		this.end_of_month = end_of_month;
		this.banks_fixed_deposits_3m = banks_fixed_deposits_3m;
		this.banks_fixed_deposits_6m = banks_fixed_deposits_6m;
		this.banks_fixed_deposits_12m = banks_fixed_deposits_12m;
		this.banks_savings_deposits = banks_savings_deposits;
		this.fc_fixed_deposits_3m = fc_fixed_deposits_3m;
		this.fc_fixed_deposits_6m = fc_fixed_deposits_6m;
		this.fc_fixed_deposits_12m = fc_fixed_deposits_12m;
		this.fc_savings_deposits = fc_savings_deposits;
	}

	public String getEnd_of_month() {
		if (end_of_month == null) return end_of_month;
		
		try {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			final Date date = sdf.parse(end_of_month);

			sdf.applyPattern("MMMM yyyy");

			return sdf.format(date);
		} catch (ParseException parseExp) {
			return end_of_month;
		}
	}
	public void setEnd_of_month(String end_of_month) {
		this.end_of_month = end_of_month;
	}
	public Double getBanks_fixed_deposits_3m() {
		return banks_fixed_deposits_3m;
	}
	public void setBanks_fixed_deposits_3m(Double banks_fixed_deposits_3m) {
		this.banks_fixed_deposits_3m = banks_fixed_deposits_3m;
	}
	public Double getBanks_fixed_deposits_6m() {
		return banks_fixed_deposits_6m;
	}
	public void setBanks_fixed_deposits_6m(Double banks_fixed_deposits_6m) {
		this.banks_fixed_deposits_6m = banks_fixed_deposits_6m;
	}
	public Double getBanks_fixed_deposits_12m() {
		return banks_fixed_deposits_12m;
	}
	public void setBanks_fixed_deposits_12m(Double banks_fixed_deposits_12m) {
		this.banks_fixed_deposits_12m = banks_fixed_deposits_12m;
	}
	public Double getBanks_savings_deposits() {
		return banks_savings_deposits;
	}
	public void setBanks_savings_deposits(Double banks_savings_deposits) {
		this.banks_savings_deposits = banks_savings_deposits;
	}
	public Double getFc_fixed_deposits_3m() {
		return fc_fixed_deposits_3m;
	}
	public void setFc_fixed_deposits_3m(Double fc_fixed_deposits_3m) {
		this.fc_fixed_deposits_3m = fc_fixed_deposits_3m;
	}
	public Double getFc_fixed_deposits_6m() {
		return fc_fixed_deposits_6m;
	}
	public void setFc_fixed_deposits_6m(Double fc_fixed_deposits_6m) {
		this.fc_fixed_deposits_6m = fc_fixed_deposits_6m;
	}
	public Double getFc_fixed_deposits_12m() {
		return fc_fixed_deposits_12m;
	}
	public void setFc_fixed_deposits_12m(Double fc_fixed_deposits_12m) {
		this.fc_fixed_deposits_12m = fc_fixed_deposits_12m;
	}
	public Double getFc_savings_deposits() {
		return fc_savings_deposits;
	}
	public void setFc_savings_deposits(Double fc_savings_deposits) {
		this.fc_savings_deposits = fc_savings_deposits;
	}
	
	public FinancialInstitute getWhichFixD3MHigher() {		
		return whichRateHigher(this.banks_fixed_deposits_3m, this.fc_fixed_deposits_3m);		
	}
	
	public FinancialInstitute getWhichFixD6MHigher() {
		return whichRateHigher(this.banks_fixed_deposits_6m, this.fc_fixed_deposits_6m);
	}
	
	public FinancialInstitute getWhichFixD12MHigher() {
		return whichRateHigher(this.banks_fixed_deposits_12m, this.fc_fixed_deposits_12m);
	}
	
	public FinancialInstitute getWhichSaveDHigher() {
		return whichRateHigher(this.banks_savings_deposits, this.fc_savings_deposits);
	}
	
	private FinancialInstitute whichRateHigher(final Double bankRate, final Double fcRate) {
		if (bankRate == null && fcRate == null) return FinancialInstitute.None;
		else if (bankRate != null && fcRate == null) return FinancialInstitute.Bank;
		else if (bankRate == null && fcRate != null) return FinancialInstitute.FinancialCompanies;
		
		int i = bankRate.compareTo(fcRate);
		
		if (i == 0) return FinancialInstitute.None;
		else if (i > 0) return FinancialInstitute.Bank;
		else return FinancialInstitute.FinancialCompanies;
	}
}
