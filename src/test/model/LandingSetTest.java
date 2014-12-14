package model;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import soft_quality.model.Doors;
import soft_quality.model.LandingSet;
import soft_quality.model.Wheel;

/**
 * {@link soft_quality.model.LandingSet}
 */
public class LandingSetTest extends TestCase {

    LandingSet ls1;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        ls1 = new LandingSet();
    }

    @After
    @Override
    public void tearDown() throws Exception {

    }

    @Test
    public final void testGetDoor() throws Exception {
        assertEquals(Doors.CLOSE, ls1.getDoorState());
    }

    @Test
    public final void testGetWheel() throws Exception {
        assertEquals(Wheel.OUT, ls1.getWheelState());
    }

    @Test
    public final void testSetDoor() throws Exception {
        ls1.setDoorState(Doors.OPEN);
        assertEquals(Doors.OPEN, ls1.getDoorState());
        ls1.setDoorState(Doors.CLOSE);
        assertEquals(Doors.CLOSE, ls1.getDoorState());

    }

    @Test
    public final void testSetWheel() throws Exception {
        ls1.setWheelState(Wheel.IN);
        assertEquals(Wheel.IN, ls1.getWheelState());
        ls1.setWheelState(Wheel.OUT);
        assertEquals(Wheel.OUT, ls1.getWheelState());
        ls1.setWheelState(Wheel.TRANSITION);
        assertEquals(Wheel.TRANSITION, ls1.getWheelState());
    }

    @Test
    public final void testCheckValidity() throws Exception {
        ls1.setWheelState(Wheel.IN);
        ls1.setDoorState(Doors.OPEN);
        assertTrue(ls1.checkValidity(Wheel.IN, Doors.OPEN));

        ls1.setWheelState(Wheel.IN);
        ls1.setDoorState(Doors.CLOSE);
        assertTrue(ls1.checkValidity(Wheel.IN, Doors.CLOSE));

        ls1.setWheelState(Wheel.TRANSITION);
        ls1.setDoorState(Doors.OPEN);
        assertTrue(ls1.checkValidity(Wheel.TRANSITION, Doors.OPEN));

        ls1.setWheelState(Wheel.TRANSITION);
        ls1.setDoorState(Doors.CLOSE);
        assertTrue(ls1.checkValidity(Wheel.TRANSITION, Doors.CLOSE));

        ls1.setWheelState(Wheel.OUT);
        ls1.setDoorState(Doors.OPEN);
        assertTrue(ls1.checkValidity(Wheel.OUT, Doors.OPEN));

        ls1.setWheelState(Wheel.OUT);
        ls1.setDoorState(Doors.CLOSE);
        assertTrue(ls1.checkValidity(Wheel.OUT, Doors.CLOSE));




    }
}