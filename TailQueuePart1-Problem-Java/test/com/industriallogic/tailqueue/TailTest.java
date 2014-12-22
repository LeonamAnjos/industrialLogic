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

package com.industriallogic.tailqueue;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

public class TailTest {

	@Test
	public void compareEqualsTails() {
		Tail target = new Tail(1, Tail.URGENT, null);
		Assert.assertEquals(0, target.compareTo(target));
	}
	
	@Test
	public void compareDifferentTailsWithSameIds() {
		Tail tailA = new Tail(1, Tail.URGENT, null);
		Tail tailB = new Tail(1, Tail.DISMISSED, null);
		Assert.assertEquals(0, tailA.compareTo(tailB));
		Assert.assertEquals(0, tailB.compareTo(tailA));
	}
	
	@Test
	public void compareTailsWithDifferntsIdsAndPriority() {
		Tail tailA = new Tail(1, Tail.URGENT, null);
		Tail tailB = new Tail(2, Tail.DISMISSED, null);
		
		Assert.assertEquals(-1, tailA.compareTo(tailB));
		Assert.assertEquals(1, tailB.compareTo(tailA));
	}
	
	@Test
	public void compareTailsWithDifferntsIdsAndCalendars() {
		Tail tailA = new Tail(1, Tail.URGENT, new GregorianCalendar(2014, 1, 1));
		Tail tailB = new Tail(2, Tail.URGENT, new GregorianCalendar(2014, 2, 1));
		
		Assert.assertEquals(-1, tailA.compareTo(tailB));
		Assert.assertEquals(1, tailB.compareTo(tailA));
	}
	
	@Test
	public void compareTailsWithAllEqualsButDifferentsIds() {
		Tail tailA = new Tail(1, Tail.URGENT, new GregorianCalendar(2014, 1, 1));
		Tail tailB = new Tail(2, Tail.URGENT, new GregorianCalendar(2014, 1, 1));
		
		Assert.assertEquals(-1, tailA.compareTo(tailB));
		Assert.assertEquals(1, tailB.compareTo(tailA));
	}
	
	
}
