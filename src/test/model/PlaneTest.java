package model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import softquality.model.*;

import java.util.HashMap;

/**
 * {@link softquality.model.Plane}
 */
public class PlaneTest extends TestCase {
    Plane p;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        p = new Plane();
    }

    @Test
    public void testInitialState() {
        p = new Plane();
        assertTrue(p.checkFinalState(true));
    }

    @Test
    public void testGetAllWheels(){
        p = new Plane();
        HashMap<Integer,Wheel> wheels = p.getAllWheel();
        int i = 0;
        for(Wheel w : wheels.values()){
            assertTrue(w==Wheel.OUT); //initial value
        }
    }

    @Test
    public void testGetAllDoors(){
        p = new Plane();
        HashMap<Integer,Doors> doors = p.getAllDoors();
        int i = 0;
        for(Doors d : doors.values()){
            assertTrue(d==Doors.CLOSE); //initial value
        }
    }

    @Test
    public void testGetDoor(){
        p = new Plane();
        //test initial value
        assertTrue(p.getDoor(Position.FRONT)==Doors.CLOSE &&
                p.getDoor(Position.LEFT)==Doors.CLOSE &&
                p.getDoor(Position.RIGHT)==Doors.CLOSE);
    }

    @Test
    public void testGetWheel(){
        p = new Plane();
        //test initial value
        assertTrue(p.getWheel(Position.FRONT)==Wheel.OUT &&
                p.getWheel(Position.LEFT)==Wheel.OUT &&
                p.getWheel(Position.RIGHT)==Wheel.OUT);
    }

    @Test
    public void testGetLed(){
        p = new Plane();
        //test initial value
        assertEquals(p.getLedsState(), Leds.GREEN);
    }

    @Test
    public void testGetHandle(){
        p = new Plane();
        //test initial value
        assertEquals(p.getHandle(),Handle.DOWN);
    }

    @Test
    public void testSetLeds(){
        p = new Plane();
        p.setLeds(Leds.NONE);
        assertEquals(p.getLedsState(),Leds.NONE);
        p.setLeds(Leds.GREEN);
        assertEquals(p.getLedsState(),Leds.GREEN);
        p.setLeds(Leds.ORANGE);
        assertEquals(p.getLedsState(),Leds.ORANGE);
        p.setLeds(Leds.RED);
        assertEquals(p.getLedsState(),Leds.RED);
    }

    @Test
    public void testSetHandle(){
        p = new Plane();
        p.setHandle(Handle.UP);
        assertEquals(p.getHandle(),Handle.UP);
        p.setHandle(Handle.DOWN);
        assertEquals(p.getHandle(),Handle.DOWN);
    }

    @Test
    public void testSetWheel(){
        p = new Plane();
        p.setWheel(Wheel.TRANSITION, Position.FRONT);
        assertEquals(Wheel.TRANSITION,p.getWheel(Position.FRONT));
        p.setWheel(Wheel.TRANSITION, Position.LEFT);
        assertEquals(Wheel.TRANSITION,p.getWheel(Position.LEFT));
        p.setWheel(Wheel.TRANSITION, Position.RIGHT);
        assertEquals(Wheel.TRANSITION,p.getWheel(Position.RIGHT));

        p.setWheel(Wheel.IN, Position.FRONT);
        assertEquals(Wheel.IN,p.getWheel(Position.FRONT));
        p.setWheel(Wheel.IN, Position.LEFT);
        assertEquals(Wheel.IN,p.getWheel(Position.LEFT));
        p.setWheel(Wheel.IN, Position.RIGHT);
        assertEquals(Wheel.IN,p.getWheel(Position.RIGHT));

        p.setWheel(Wheel.OUT, Position.FRONT);
        assertEquals(Wheel.OUT,p.getWheel(Position.FRONT));
        p.setWheel(Wheel.OUT, Position.LEFT);
        assertEquals(Wheel.OUT,p.getWheel(Position.LEFT));
        p.setWheel(Wheel.OUT, Position.RIGHT);
        assertEquals(Wheel.OUT,p.getWheel(Position.RIGHT));
    }

    @Test
    public void testSetDoors(){
        p = new Plane();

        p.setDoors(Doors.OPEN, Position.FRONT);
        assertEquals(Doors.OPEN, p.getDoor(Position.FRONT));
        p.setDoors(Doors.OPEN, Position.LEFT);
        assertEquals(Doors.OPEN, p.getDoor(Position.LEFT));
        p.setDoors(Doors.OPEN, Position.RIGHT);
        assertEquals(Doors.OPEN, p.getDoor(Position.RIGHT));

        p.setDoors(Doors.CLOSE, Position.FRONT);
        assertEquals(Doors.CLOSE, p.getDoor(Position.FRONT));
        p.setDoors(Doors.CLOSE, Position.LEFT);
        assertEquals(Doors.CLOSE, p.getDoor(Position.LEFT));
        p.setDoors(Doors.CLOSE, Position.RIGHT);
        assertEquals(Doors.CLOSE, p.getDoor(Position.RIGHT));
    }

    @Test
    public void checkInvalidState() {
        p = new Plane();
        p.setWheel(Wheel.IN, Position.FRONT);
        assertFalse(p.checkFinalState(true));
    }
}