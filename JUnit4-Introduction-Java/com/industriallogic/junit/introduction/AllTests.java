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

package com.industriallogic.junit.introduction;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FailingTests.class,
	PassingTests.class,
	ErrorTest.class,
	UsingCustomAssert.class,
	TestSetupAndTearDown.class
})
public class AllTests {

	// The above annotations suffice for declaring the suite.
	
	// We define the JUnit 3-style suite() method here for the sake of our RunSuiteMultipleTimes example,
	// which uses the JUnit 3 extension RepeatedTest.
	public static Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}
}
