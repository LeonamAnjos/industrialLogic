package com.wildwestwireless;

public class PlanTestDataHelper {
	private Plan goldPlan;
	private Plan silverPlan;

	public PlanTestDataHelper() {
		createGoldPlan();
		createSilverPlan();
	}

	private void createSilverPlan() {
		silverPlan = new Plan(29.95);
		silverPlan.setRatePerAdditionalLine(21.50);
		silverPlan.setIncludedMinutes(500);
		silverPlan.setRatePerExcessMinute(0.54);
		silverPlan.setPromotionalRatePerAdditionalLine(5.00);
	}

	private void createGoldPlan() {
		goldPlan = new Plan(49.95);
		goldPlan.setRatePerAdditionalLine(14.50);
		goldPlan.setIncludedMinutes(1000);
		goldPlan.setRatePerExcessMinute(0.45);
		goldPlan.setPromotionalRatePerAdditionalLine(5.00);
	}

	Plan getSilverPlan() {
		return this.silverPlan;
	}

	Plan getGoldPlan() {
		return this.goldPlan;
	}
}