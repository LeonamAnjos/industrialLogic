package com.wildwestwireless;

public class Plan {

	private double basicMonthlyRate;
	private double ratePerAdditionalLine;
	private int includedMinutes;
	private double ratePerExcessMinute;
	private double promotionalRatePerAdditionalLine;

	public Plan(double basicMonthlyRate) {
		this.basicMonthlyRate = basicMonthlyRate;
	}

	public double getBasicMonthlyRate() {
		return basicMonthlyRate;
	}

	public double getRatePerAdditionalLine() {
		return ratePerAdditionalLine;
	}

	public void setIncludedMinutes(int minutes) {
		this.includedMinutes = minutes;
	}

	public int getIncludedMinutes() {
		return includedMinutes;
	}

	public void setRatePerAdditionalLine(double value) {
		this.ratePerAdditionalLine = value;
	}

	public double getRatePerExcessMinute() {
		return ratePerExcessMinute;
	}

	public void setRatePerExcessMinute(double value) {
		this.ratePerExcessMinute = value;
		
	}

	public double getPromotionalRatePerAdditionalLine() {
		return promotionalRatePerAdditionalLine;
	}

	public void setPromotionalRatePerAdditionalLine(double value) {
		this.promotionalRatePerAdditionalLine = value;
	}

	
}
