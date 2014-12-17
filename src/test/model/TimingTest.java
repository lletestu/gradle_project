package model;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import softquality.model.Timing;

/**
 * {@link softquality.model.Timing}
 */
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
        assertEquals(300, Timing.TIME_BEFORE_TIGGER);
    }

    @Test
    public final void testOpenGate () throws Exception {
        assertEquals(1000, Timing.TIME_TO_OPEN_GATE);
    }

    @Test
    public final void testCloseGate () throws Exception {
        assertEquals(1000, Timing.TIME_TO_CLOSE_GATE);
    }

    @Test
    public final void testStabilizedToTransition () throws Exception {
        assertEquals(1000, Timing.TIME_FROM_STABILIZED_TO_TRANSITION);
    }

    @Test
    public final void testTransitionToStabilized () throws Exception {
        assertEquals(1000, Timing.TIME_FROM_TRANSITION_TO_STABILIZED);
    }
}