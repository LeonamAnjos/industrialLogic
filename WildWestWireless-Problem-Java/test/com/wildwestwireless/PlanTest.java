package com.wildwestwireless;

import static junit.framework.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class PlanTest {

	private PlanTestDataHelper helper;
	private Plan silverPlan;
	private Plan goldPlan;

	@Before
	public void setUp() {
		helper = new PlanTestDataHelper();
		goldPlan = helper.getGoldPlan();
		silverPlan = helper.getSilverPlan();
	}
	
	@Test
	public void testGetBasicMonthlyRate() {
		Assert.assertEquals(49.95, goldPlan.getBasicMonthlyRate());
		Assert.assertEquals(29.95, silverPlan.getBasicMonthlyRate());
	}
	
	@Test
	public void testGetRatePerAdditionalLine() {
		assertEquals(14.50, goldPlan.getRatePerAdditionalLine());
		assertEquals(21.50, silverPlan.getRatePerAdditionalLine());
	}

	@Test
	public void testGetIncludedMinutes() {
		assertEquals(1000, goldPlan.getIncludedMinutes());
		assertEquals(500, silverPlan.getIncludedMinutes());
	}

	@Test
	public void testRatePerExcessMinute() {
		assertEquals(0.45, goldPlan.getRatePerExcessMinute());
		assertEquals(0.54, silverPlan.getRatePerExcessMinute());
	}
	
	@Test
	public void testFamilyDiscount() {
		assertEquals(5.00, goldPlan.getPromotionalRatePerAdditionalLine());
		assertEquals(5.00, silverPlan.getPromotionalRatePerAdditionalLine());
	}
}
