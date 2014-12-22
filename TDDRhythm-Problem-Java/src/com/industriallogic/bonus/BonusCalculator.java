package com.industriallogic.bonus;

public class BonusCalculator {

	public double individualBonus(int sales, int quota, double commission, double tax) {
		if (!hasBonus(sales, quota))
			return 0;
		
		return calcBonus(sales, quota, commission) * calcTax(tax);
	}

	public double teamBonus(int sales, int quota, double commission, int numberOfTeamMembers) {
		if (!hasBonus(sales, quota))
			return 0;
		
		if (!isNumberOfTeamMembersValid(numberOfTeamMembers))
			return 0;
		
		return calcBonus(sales, quota, commission) / numberOfTeamMembers;
	}
	
	private double calcBonus(int sales, int quota, double commission) {
		return (sales - quota) * (commission / 100);
	}

	private boolean isNumberOfTeamMembersValid(int numberOfTeamMembers) {
		return numberOfTeamMembers > 0;
	}
	
	private boolean hasBonus(int sales, int quota) {
		return sales > quota;
	}

	private double calcTax(double tax) {
		return 1 - (tax / 100);
	}
}
