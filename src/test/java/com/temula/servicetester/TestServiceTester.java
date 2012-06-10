package com.temula.servicetester;

import junit.framework.TestCase;

public class TestServiceTester extends TestCase {
	public void testServiceTester(){
		ServiceTester st = new MockServiceTester();
		assertTrue(st.testServices());
	}
}
