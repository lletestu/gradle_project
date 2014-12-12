package com.soft_quality.test;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.soft_quality.model.Timing;

public class TimingTest extends TestCase {

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    @Override
    public void tearDown() throws Exception {

    }

    @Test
    public final void testBeforeTigger () throws Exception {
        assertEquals(300, Timing.timeBeforeTigger);
    }

    @Test
    public final void testOpenGate () throws Exception {
        assertEquals(1000, Timing.timeToOpenGate);
    }

    @Test
    public final void testCloseGate () throws Exception {
        assertEquals(1000, Timing.timeToCloseGate);
    }

    @Test
    public final void testStabilizedToTransition () throws Exception {
        assertEquals(1000, Timing.timeFromStabilizedToTransition);
    }

    @Test
    public final void testTransitionToStabilized () throws Exception {
        assertEquals(1000, Timing.timeFromTransitionToStabilized);
    }
}