// ***************************************************************************
// Copyright (c) 2013, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.industriallogic.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TagNodeTest {
	
	private TagNode orders = new TagNode("orders");;

	@Test
	public void testChildren()
	{
		orders.add(new TagNode("order0"));
		orders.add(new TagNode("order1"));
		orders.add(new TagNode("order2"));
		orders.add(new TagNode("order3"));
		
		TagNode order4 = new TagNode("order4");
		order4.add(new TagNode("order4-child"));
		orders.add(order4);
		
		assertEquals(5,orders.children.size());
		assertEquals("order4",orders.children.get(4).getName());
		assertEquals("order4-child",orders.children.get(4).children.get(0).getName());
	}

	@Test
	public void testGetValue() {
		orders.addValue("a value");
		assertEquals("a value", orders.getValue());
	}

	@Test
	public void testAddAttribute() {
		orders.addAttribute("Date", "20130204");
		orders.addAttribute("OrderCount", "453");
		assertEquals(2,orders.attributes.size());
		assertEquals("<orders Date=\"20130204\" OrderCount=\"453\"/>",orders.toString());
	}
	
	@Test
	public void testAddNode() {
		TagNode order = new TagNode("order");
		order.addValue("carDoor");
		orders.add(order);

		String expected =
			"<orders>" +
				"<order>" +
				"carDoor" + 
				"</order>" +
			"</orders>";
		
		assertEquals(expected, orders.toString());
	}

	@Test
	public void testGetAttribute() {
		orders.addAttribute("Date", "20130204");
		orders.addAttribute("OrderCount", "453");
		assertEquals("20130204", orders.getAttributeNamed("Date"));
		assertEquals("453", orders.getAttributeNamed("OrderCount"));
	}

	@Test
	public void testTagNodeCreate() {
		assertEquals("<orders/>", orders.toString());
	}
}
