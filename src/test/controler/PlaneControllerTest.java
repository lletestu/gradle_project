package controler;

import softquality.controller.PlaneController;
import softquality.view.MainView;
import softquality.model.*;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link softquality.controller.PlaneController}
 */
public class PlaneControllerTest extends TestCase {

    PlaneController myPlaneController;
    MainView myView;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        myPlaneController = new PlaneController();
        myView = new MainView(myPlaneController);
    }

    @After
    @Override
    public void tearDown() throws Exception {

    }

    /**
     * Test setting view of my controller
     * @throws Exception
     */
    @Test
    public void testSetMyView() throws Exception {
        assertFalse(myPlaneController.isSettingView());
        myPlaneController.setMyView(myView);
        assertTrue(myPlaneController.isSettingView());
    }

    @Test
    public void testInWheels() throws Exception {
        assertFalse(myPlaneController.isSettingView());
        myPlaneController.setMyView(myView);
        assertTrue(myPlaneController.isSettingView());

        myPlaneController.inWheels();
        assertEquals(Leds.ORANGE,myPlaneController.getMyLedState());
        assertEquals(Handle.UP,myPlaneController.getMyHandleState());

        //let's wait the appropriate time
        try {
            Thread.sleep(Timing.TIME_TO_OPEN_GATE);
            Thread.sleep(Timing.TIME_FROM_STABILIZED_TO_TRANSITION);
            Thread.sleep(Timing.TIME_FROM_TRANSITION_TO_STABILIZED);
            Thread.sleep(Timing.TIME_TO_CLOSE_GATE);
            Thread.sleep(500);//tolerance time for propagation
        } catch (InterruptedException e) {
            assertTrue(false); //timing fail, test is irrelevant
        }
        assertEquals(Leds.NONE,myPlaneController.getMyLedState());
        for(Doors d : myPlaneController.getMyDoorsState()){
            assertEquals(d,Doors.CLOSE);
        }
        for(Wheel w : myPlaneController.getMyWheelsState()){
            assertEquals(w,Wheel.IN);
        }
    }

    @Test
    public void testOutWheels() throws Exception {
        assertFalse(myPlaneController.isSettingView());
        myPlaneController.setMyView(myView);
        assertTrue(myPlaneController.isSettingView());

        myPlaneController.outWheels();
        assertEquals(Leds.ORANGE,myPlaneController.getMyLedState());
        assertEquals(Handle.DOWN,myPlaneController.getMyHandleState());

        //let's wait the appropriate time
        try {
            Thread.sleep(Timing.TIME_TO_OPEN_GATE);
            Thread.sleep(Timing.TIME_FROM_STABILIZED_TO_TRANSITION);
            Thread.sleep(Timing.TIME_FROM_TRANSITION_TO_STABILIZED);
            Thread.sleep(Timing.TIME_TO_CLOSE_GATE);
            Thread.sleep(500);//tolerance time for propagation
        } catch (InterruptedException e) {
            assertTrue(false); //timing fail, test is irrelevant
        }

        assertEquals(Leds.GREEN,myPlaneController.getMyLedState());
        for(Doors d : myPlaneController.getMyDoorsState()){
            assertEquals(d,Doors.CLOSE);
        }
        for(Wheel w : myPlaneController.getMyWheelsState()){
            assertEquals(w,Wheel.OUT);
        }
    }

    /**
     * Test executing action
     * @throws Exception
     */
    @Test
    public void testExecuteActionWithTriggeredTime() throws Exception {
        //how to test this?
    }

    @Test
    public void testUpdateFinalState() throws Exception {
        //how to test this?
    }

    @Test
    public void testInterruptPreviousActions() throws Exception {
        assertFalse(myPlaneController.isSettingView());
        myPlaneController.setMyView(myView);
        assertTrue(myPlaneController.isSettingView());

        myPlaneController.inWheels();
        //interrupt with out wheel
        myPlaneController.outWheels();
        assertEquals(Leds.ORANGE,myPlaneController.getMyLedState());
        assertEquals(Handle.DOWN,myPlaneController.getMyHandleState());

        //let's wait the appropriate time
        try {
            Thread.sleep(Timing.TIME_TO_OPEN_GATE);
            Thread.sleep(Timing.TIME_FROM_STABILIZED_TO_TRANSITION);
            Thread.sleep(Timing.TIME_FROM_TRANSITION_TO_STABILIZED);
            Thread.sleep(Timing.TIME_TO_CLOSE_GATE);
            Thread.sleep(500);//tolerance time for propagation
        } catch (InterruptedException e) {
            assertTrue(false); //timing fail, test is irrelevant
        }

        //check if state if the outWheel one and not the in wheel
        assertEquals(Leds.GREEN,myPlaneController.getMyLedState());
        for(Doors d : myPlaneController.getMyDoorsState()){
            assertEquals(d,Doors.CLOSE);
        }
        for(Wheel w : myPlaneController.getMyWheelsState()){
            assertEquals(w,Wheel.OUT);
        }

        myPlaneController.outWheels();
        //interrupt with in wheel
        myPlaneController.inWheels();
        assertEquals(Leds.ORANGE,myPlaneController.getMyLedState());
        assertEquals(Handle.UP,myPlaneController.getMyHandleState());

        //let's wait the appropriate time
        try {
            Thread.sleep(Timing.TIME_TO_OPEN_GATE);
            Thread.sleep(Timing.TIME_FROM_STABILIZED_TO_TRANSITION);
            Thread.sleep(Timing.TIME_FROM_TRANSITION_TO_STABILIZED);
            Thread.sleep(Timing.TIME_TO_CLOSE_GATE);
            Thread.sleep(500);//tolerance time for propagation
        } catch (InterruptedException e) {
            assertTrue(false); //timing fail, test is irrelevant
        }
        //check if out state
        assertEquals(Leds.NONE,myPlaneController.getMyLedState());
        for(Doors d : myPlaneController.getMyDoorsState()){
            assertEquals(d,Doors.CLOSE);
        }
        for(Wheel w : myPlaneController.getMyWheelsState()){
            assertEquals(w,Wheel.IN);
        }
    }

    @Test
    public void testRedLeds(){
        //set view
        assertFalse(myPlaneController.isSettingView());
        myPlaneController.setMyView(myView);
        assertTrue(myPlaneController.isSettingView());

        myPlaneController.setError();
        assertEquals(myPlaneController.getMyLedState(),Leds.RED);
    }
}