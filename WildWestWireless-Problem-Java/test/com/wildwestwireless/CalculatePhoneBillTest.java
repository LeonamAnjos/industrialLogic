// ***************************************************************************
// Copyright (c) 2014, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.wildwestwireless;

import static junit.framework.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class CalculatePhoneBillTest {
	
	private static final double precision = 0.001;
	
	private CalculatePhoneBill target = new CalculatePhoneBill();
	private PlanTestDataHelper helper = new PlanTestDataHelper();;
	private Plan goldPlan;
	private Plan silverPlan;
	
	@Before
	public void setUp() {
		goldPlan = helper.getGoldPlan();
		silverPlan = helper.getSilverPlan();
	}
	
	@Test
	public void calculateBasicMonthlyRate() {
		assertEquals(49.95, target.calculatePhoneBill(goldPlan, 1, 0));
		assertEquals(29.95, target.calculatePhoneBill(silverPlan, 1, 0));
	}
	
	@Test
	public void calculateExpectedBill() {
		assertEquals(64.45, target.calculatePhoneBill(goldPlan, 2, 0));
		assertEquals(72.95, target.calculatePhoneBill(silverPlan, 3, 0));
	}
	
	@Test 
	public void calculateExcessMinutes() {
		assertEquals(49.95, target.calculatePhoneBill(goldPlan, 1, 999));
		assertEquals(49.95, target.calculatePhoneBill(goldPlan, 1, 1000));
		assertEquals(50.40, target.calculatePhoneBill(goldPlan, 1, 1001), precision);
		assertEquals(54.45, target.calculatePhoneBill(goldPlan, 1, 1010), precision);
		assertEquals(29.95, target.calculatePhoneBill(silverPlan, 1, 499), precision);
		assertEquals(29.95, target.calculatePhoneBill(silverPlan, 1, 500), precision);
		assertEquals(40.75, target.calculatePhoneBill(silverPlan, 1, 520), precision);
	}
	
	@Test
	public void calculateFamilyDiscount() {
		assertEquals(78.95, target.calculatePhoneBill(goldPlan, 3, 999));
		assertEquals(83.95, target.calculatePhoneBill(goldPlan, 4, 999));
		assertEquals(77.95, target.calculatePhoneBill(silverPlan, 4, 499));
		assertEquals(82.95, target.calculatePhoneBill(silverPlan, 5, 499));
	}
	
	@Test
	public void calculateCompletePhoneBill() {
		assertEquals(83.95, target.calculatePhoneBill(goldPlan, 4, 878), precision);
		assertEquals(105.3, target.calculatePhoneBill(goldPlan, 1, 1123), precision);
		assertEquals(139.3, target.calculatePhoneBill(goldPlan, 4, 1123), precision);
		assertEquals(63.87, target.calculatePhoneBill(silverPlan, 2, 523), precision);
		assertEquals(82.95, target.calculatePhoneBill(silverPlan, 5, 44), precision);
		assertEquals(94.29, target.calculatePhoneBill(silverPlan, 5, 521), precision);
		
	}
	
}
