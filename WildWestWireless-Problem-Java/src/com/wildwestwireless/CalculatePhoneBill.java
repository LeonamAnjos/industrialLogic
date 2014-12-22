package com.wildwestwireless;

public class CalculatePhoneBill {

	private static final int NUMBER_OF_MAX_ADDITIONAL_LINES = 2;
	private static final int NUMBER_OF_CONTRACTED_LINES = 1;

	public double calculatePhoneBill(Plan plan, int numberOfLines, int minutesUsed) {
		double additionalLinesValue = calculateAdditionalLinesValue(plan, numberOfLines);
		double promotionalLinesValue = calculatePromotionalAdditionalLinesValue(plan, numberOfLines);
		double excessMinutesRate = getExcessMinutesRate(plan, minutesUsed);
		
		return plan.getBasicMonthlyRate() + additionalLinesValue + promotionalLinesValue + excessMinutesRate;
	}

	private double calculatePromotionalAdditionalLinesValue(Plan plan, int numberOfLines) {
		int lines = getNumberOfPromotionalAdditionalLines(numberOfLines);
		return lines * plan.getPromotionalRatePerAdditionalLine();
	}

	private double calculateAdditionalLinesValue(Plan plan, int numberOfLines) {
		int lines = getNumberOfAdditionalLines(numberOfLines);
		return lines * plan.getRatePerAdditionalLine();
	}

	private int getNumberOfAdditionalLines(int numberOfLines) {
		int number = numberOfLines - NUMBER_OF_CONTRACTED_LINES;
		return number > NUMBER_OF_MAX_ADDITIONAL_LINES ? NUMBER_OF_MAX_ADDITIONAL_LINES : number;
	}
	
	private int getNumberOfPromotionalAdditionalLines(int numberOfLines) {
		int lines = numberOfLines - NUMBER_OF_CONTRACTED_LINES - getNumberOfAdditionalLines(numberOfLines);
		return lines > 0 ? lines : 0;
	}

	private double getExcessMinutesRate(Plan plan, int minutesUsed) {
		int difference = minutesUsed - plan.getIncludedMinutes();
		if (difference > 0) 
			return plan.getRatePerExcessMinute() * difference;
		return 0;
	}

}
