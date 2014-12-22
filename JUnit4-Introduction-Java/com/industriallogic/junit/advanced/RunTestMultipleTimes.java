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
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

// RepeatedTest is present in JUnit4 for backward compatibility, and uses JUnit 3-style definitions.
public class RunTestMultipleTimes extends TestCase {

	private static Test additionTest() {
		Test testCase = new TestCase("addition test") {
			protected void runTest() throws Exception { 
				assertEquals(4, 2 + 2);
			}
		};
		return testCase;
	}

	public static TestSuite suite() throws Exception {
		TestSuite suite = new TestSuite();
		Test singleTestRunThreeTimes =
			new RepeatedTest(additionTest(), 3); 
		suite.addTest(singleTestRunThreeTimes);
		return suite;
	}
}
