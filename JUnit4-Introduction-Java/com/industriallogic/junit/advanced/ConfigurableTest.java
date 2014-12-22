/// ***************************************************************************
/// Copyright (c) 2010, Industrial Logic, Inc., All Rights Reserved.
///
/// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
/// used by students during Industrial Logic's workshops or by individuals
/// who are being coached by Industrial Logic on a project.
///
/// This code may NOT be copied or used for any other purpose without the prior
/// written consent of Industrial Logic, Inc.
/// ****************************************************************************

package com.industriallogic.junit.advanced;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ConfigurableTest {

	private final int sum;
	private final int arg1;
	private final int arg2;

	public ConfigurableTest(int sum, int arg1, int arg2) {
		this.sum = sum;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	@Test	
	public void addition() {
		assertEquals(sum, arg1 + arg2);
	}

	@Parameters
	public static Collection<Integer[]> data() {
		List<Integer[]> params = new ArrayList<Integer[]>();
		params.add(new Integer[] { 2, 2, 0 });
		params.add(new Integer[] { 4, 2, 2 });
		params.add(new Integer[] { 6, 2, 4 });
		params.add(new Integer[] { 0, 2, -2 });
		return params;
	}

}
