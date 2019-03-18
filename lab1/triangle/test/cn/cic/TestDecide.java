package cn.cic;

import static org.junit.Assert.assertEquals;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestDecide {
	Decide dec;
	String lessThanZero = "输入的金钱数额小于0";
	String moreThanTotal = "输入的金钱数额超出钱币总量";
	String success = "输入数额能由现有钱币拼凑";
	String fail = "输入数额能不由现有钱币拼凑";
	@Before
	public void setUp() {
		dec = new Decide();
	}
	
	@Test
	public void testlessThanZero() {
		assertEquals(lessThanZero, dec.decide(-3));
	}
	@Test
	public void testmoreThanTotal() {
		assertEquals(moreThanTotal, dec.decide(300));
	}
	@Test
	public void testsuccess() {
		assertEquals(success, dec.decide(57));
	}
	@Test
	public void testfail() {
		assertEquals(fail, dec.decide(39));
	}
	
}
