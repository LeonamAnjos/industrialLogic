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

import junit.extensions.RepeatedTest;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.industriallogic.junit.introduction.AllTests;

public class RunSuiteMultipleTimes extends TestCase {
	private static final int TIMES_TO_RUN = 2;
	
	public RunSuiteMultipleTimes(String name) {
		super(name);
	}

	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new RepeatedTest(AllTests.suite(), TIMES_TO_RUN));
		return suite;
	}
}
