package com.sun.support.demo;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2016/1/20.
 */
public class OkHttpDemoTest extends TestCase {
    OkHttpDemo calculator;
    @Before
    public void setUp() throws Exception {
        calculator = new OkHttpDemo();
    }

    @Test
    public void testSum() throws Exception {
        //expected: 6, sum of 1 and 5
        assertEquals(6d, calculator.get());
    }
}