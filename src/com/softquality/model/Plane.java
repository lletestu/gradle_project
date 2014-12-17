package softquality.model;

import java.util.HashMap;

/**
 * model class for the plane
 * @author Lauren Letestu, Vincent Francolin
 *
 */
public class Plane {

    private Handle handleState;
    private Leds ledsState;
    private LandingSet[] landingSets;
    private static final int numberOfLandingSets = 3;

    /**
     * default constructor, initialize to OUT state by default
     */
    public Plane(){
        handleState = Handle.DOWN;
        ledsState = Leds.GREEN;
        landingSets = new LandingSet[numberOfLandingSets];
        for(int i=0;i<numberOfLandingSets;i++){
            landingSets[i] = new LandingSet();
        }
    }

    /**
     * change the state of the led
     * @param state new led state
     */
    public void setLeds(Leds state){
        ledsState = state;
    }

    /**
     * retrieve the state of the led
     * @return led state
     */
    public Leds getLedsState(){
        return ledsState;
    }

    /**
     * set the state of the handle
     * @param state the new state of the handle
     */
    public void setHandle(Handle state){
        handleState = state;
    }

    /**
     * retrieve the state of the handle
     * @return handle state
     */
    public Handle getHandle(){
        return handleState;
    }

    /**
     * retrieve a wheel state from one landing set
     * @param pos the landing set position
     * @return wheel state
     */
    public Wheel getWheel(Position pos){
        return landingSets[pos.ordinal()].getWheelState();
    }

    /**
     * return a hashmap of the state of all the wheels
     * @return hashmap with the key as the position and the wheel state
     */
    public HashMap<Integer, Wheel> getAllWheel() {
        HashMap<Integer, Wheel> wheelState = new HashMap<Integer, Wheel>();
        for (int i = 0; i <landingSets.length; ++i) {
            wheelState.put(i, landingSets[i].getWheelState());
        }
        return wheelState;
    }

    /**
     * set a new wheel state
     * @param state new state
     * @param pos landing set to change
     */
    public void setWheel(Wheel state,Position pos){
        landingSets[pos.ordinal()].setWheelState(state);
    }

    /**
     * return a hashmap of the state of all the doors
     * @return hashmap with the key as the position and the door state
     */
    public HashMap<Integer, Doors> getAllDoors() {
        HashMap<Integer, Doors> doorsState = new HashMap<Integer, Doors>();
        for (int i = 0; i <landingSets.length; ++i) {
            doorsState.put(i, landingSets[i].getDoorState());
        }
        return doorsState;
    }

    /**
     * get a door state
     * @param pos the landing set position
     * @return door state
     */
    public Doors getDoor(Position pos){
        return landingSets[pos.ordinal()].getDoorState();
    }

    /**
     * set a new door state
     * @param state the news door state
     * @param pos the position of the landing set
     */
    public void setDoors(Doors state,Position pos){
        landingSets[pos.ordinal()].setDoorState(state);
    }

    /**
     * check if the final state is consistent
     * @param out true if we test out state, false if we test in state
     * @return state consistency
     */
    public boolean checkFinalState(boolean out){
        if(out){
            return (landingSets[0].checkValidity(Wheel.OUT, Doors.CLOSE)
                    && landingSets[1].checkValidity(Wheel.OUT, Doors.CLOSE)
                    && landingSets[2].checkValidity(Wheel.OUT, Doors.CLOSE) );
        }
        return (landingSets[0].checkValidity(Wheel.IN, Doors.CLOSE)
                && landingSets[1].checkValidity(Wheel.IN, Doors.CLOSE)
                && landingSets[2].checkValidity(Wheel.IN, Doors.CLOSE) );
    }
}
