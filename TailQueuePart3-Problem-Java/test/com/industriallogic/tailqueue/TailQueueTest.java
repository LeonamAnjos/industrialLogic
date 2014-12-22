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

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

public class TailQueueTest {
	private TailQueue queue = new TailQueue();

	@Test
	public void addOneTail() {
		Tail tail = new Tail(1, Tail.IMPORTANT, null);
		queue.add(tail);
		assertSame(tail, queue.get());
	}

	@Test(expected = TailAlreadyInQueueException.class)
	public void addThrowsOnExistingTail() {
		Tail tail = new Tail(1, Tail.URGENT, null);
		queue.add(tail);

		Tail secondTailWithSameId = new Tail(1, Tail.IMPORTANT, Calendar.getInstance());
		queue.add(secondTailWithSameId);
	}

	@Test
	public void getTailFromEmptyTailQueue() {
		Assert.assertNull(queue.get());
	}

	@Test
	public void getDismissedFirstIfThereIsNoOtherTail() {
		add(1, Tail.DISMISSED);
		add(2, Tail.DISMISSED);
		
		assertGetEquals(1);
		assertGetEquals(2);
	}
	
	@Test
	public void getRequestingFirstIfThereIsNoUrgentOrImportantTail() {
		add(1, Tail.DISMISSED);
		add(2, Tail.REQUESTING);
		add(3, Tail.REQUESTING);

		assertGetEquals(2);
		assertGetEquals(3);
		assertGetEquals(1);
	}
	
	@Test
	public void getImportantFirstIfThereIsNoUrgentTail() {
		add(1, Tail.REQUESTING);
		add(2, Tail.DISMISSED);
		add(3, Tail.IMPORTANT);

		assertGetEquals(3);
	}
	
	@Test
	public void getUrgentTailFirst() {
		add(1, Tail.IMPORTANT);
		add(2, Tail.REQUESTING);
		add(3, Tail.DISMISSED);
		add(4, Tail.URGENT);
		add(5, Tail.URGENT);
		add(6, Tail.URGENT);
		
		assertGetEquals(4);
		assertGetEquals(5);
		assertGetEquals(6);
	}
	
	@Test
	public void getShouldRotateBetweenImportantAndRequesting() {
		add(1, Tail.IMPORTANT);
		add(2, Tail.IMPORTANT);
		add(3, Tail.IMPORTANT);
		add(4, Tail.IMPORTANT);
		add(5, Tail.REQUESTING);
		add(6, Tail.REQUESTING);
		
		assertGetEquals(1);
		assertGetEquals(5);
		assertGetEquals(2);
		assertGetEquals(3);
		assertGetEquals(6);
		assertGetEquals(4);
	}
	
	@Test
	public void missingImportantDoesntChangeSequance() {
		add(1, Tail.REQUESTING);
		add(2, Tail.REQUESTING);
		add(3, Tail.REQUESTING);
		add(4, Tail.REQUESTING);
		assertGetEquals(1);
		assertGetEquals(2);
		
		add(5, Tail.IMPORTANT);
		add(6, Tail.IMPORTANT);
		assertGetEquals(5);
		assertGetEquals(3);
		assertGetEquals(6);
	}
	
	@Test
	public void missingRequestingDoesntChangeSequance() {
		add(1, Tail.IMPORTANT);
		add(2, Tail.IMPORTANT);
		assertGetEquals(1);
		assertGetEquals(2);
		add(3, Tail.IMPORTANT);
		add(4, Tail.REQUESTING);
		assertGetEquals(4);
		assertGetEquals(3);
	}
	
	@Test
	public void urgentDoesntChangeSequence() {
		add(1, Tail.REQUESTING);
		add(2, Tail.REQUESTING);
		add(3, Tail.IMPORTANT);
		add(4, Tail.IMPORTANT);
		add(5, Tail.IMPORTANT);
		add(6, Tail.URGENT);
		assertGetEquals(6);
		assertGetEquals(3);
		
		add(7, Tail.URGENT);
		assertGetEquals(7);
		assertGetEquals(1);
		
		add(8, Tail.URGENT);
		assertGetEquals(8);
		assertGetEquals(4);
		
		add(9, Tail.URGENT);
		assertGetEquals(9);
		assertGetEquals(5);
		
		add(10, Tail.URGENT);
		assertGetEquals(10);
		assertGetEquals(2);
	}

	@Test
	public void dismissDoesntChangeSequence() {
		add(1, Tail.REQUESTING);
		add(2, Tail.REQUESTING);
		add(3, Tail.IMPORTANT);
		add(4, Tail.IMPORTANT);
		add(5, Tail.IMPORTANT);
		add(6, Tail.URGENT);
		assertGetEquals(6);
		assertGetEquals(3);
		
		add(7, Tail.DISMISSED);
		assertGetEquals(1);
		
		add(8, Tail.DISMISSED);
		assertGetEquals(4);
		
		add(9, Tail.DISMISSED);
		assertGetEquals(5);
		
		add(10, Tail.DISMISSED);
		assertGetEquals(2);
	}
	
	private Tail add(int id, int tailType) {
		Tail tail = new Tail(id, tailType, Calendar.getInstance());
		queue.add(tail);
		return tail;
	}
	
	private void assertGetEquals(int id) {
		assertEquals(id, queue.get().id);
	}
	
}
