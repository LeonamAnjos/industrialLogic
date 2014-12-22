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

package com.industriallogic.bonus;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class BonusCalculatorTest {
    private static final double precision = 0.001;
    private BonusCalculator bonusCalculator;
	
    @Before
    public void setUp() {
    	bonusCalculator = new BonusCalculator();
    }
    
	@Test
	public void testIndividualBonusWhenSalesAreEqualOrSmallerThanQuota() {
		Assert.assertEquals(0.0, bonusCalculator.individualBonus(10, 11, 10.0, 10), precision);
		Assert.assertEquals(0.0, bonusCalculator.individualBonus(11, 11, 10.0, 10), precision);
	}
	
	@Test
	public void testIndividualBonusWhenSalesAreBiggerThanQuotaAndCommissionIsZero() {
		Assert.assertEquals(0.0, bonusCalculator.individualBonus(12, 11, 0, 10), precision);
	}
	
	@Test
	public void testIndividualBonusWhenSalesAreBiggerThanQuotaAndTaxIsZero() {
		Assert.assertEquals(0.10, bonusCalculator.individualBonus(12, 11, 10, 0), precision);
		Assert.assertEquals(0.40, bonusCalculator.individualBonus(13, 11, 20, 0), precision);
	}
	
	@Test
	public void testIndividualBonusWhenSalesAreBiggerThanQuota() {
		Assert.assertEquals(0.90, bonusCalculator.individualBonus(120, 110, 10, 10), precision);
		Assert.assertEquals(3.60, bonusCalculator.individualBonus(130, 110, 20, 10), precision);
		Assert.assertEquals(3.80, bonusCalculator.individualBonus(130, 110, 20, 5), precision);
	}
	
	@Test
	public void testTeamBonusWhenSalesGroupAreEqualOrSmallerThanQuota() {
		Assert.assertEquals(0.0, bonusCalculator.teamBonus(10, 11, 10.0, 10), precision);
		Assert.assertEquals(0.0, bonusCalculator.teamBonus(11, 11, 10.0, 10), precision);
	}
	
	@Test
	public void testTeamBonusWhenSalesGroupAreBiggerThanQuota() {
		Assert.assertEquals(0.0, bonusCalculator.teamBonus(12, 11, 0, 10), precision);
	}
	
	@Test
	public void testTeamBonusWhenSalesAreBiggerThanQuotaAndTeamMembersIsInvalid() {
		Assert.assertEquals(0.0, bonusCalculator.teamBonus(12, 11, 10, -1), precision);
		Assert.assertEquals(0.0, bonusCalculator.teamBonus(12, 11, 10, 0), precision);
		Assert.assertEquals(0.0, bonusCalculator.teamBonus(13, 11, 20, 0), precision);
	}
	
	@Test
	public void testTeamBonusWhenSalesAreBiggerThanQuotaAndTeamHasOnlyOneMember() {
		Assert.assertEquals(1.0, bonusCalculator.teamBonus(120, 110, 10, 1), precision);
		Assert.assertEquals(4.0, bonusCalculator.teamBonus(130, 110, 20, 1), precision);
	}
	
	@Test
	public void testTeamBonusWhenSalesAreBiggerThanQuotaAndTeamHasMoreThanOneMember() {
		Assert.assertEquals(0.5, bonusCalculator.teamBonus(120, 110, 10, 2), precision);
		Assert.assertEquals(1.0, bonusCalculator.teamBonus(130, 110, 20, 4), precision);
	}
}
	
