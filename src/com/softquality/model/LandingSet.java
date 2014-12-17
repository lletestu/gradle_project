package softquality.model;

/**
 * describe the model of a plane
 * @author Lauren Letestu, Vincent Francolin
 *
 */
public class LandingSet {
    private Wheel wheelState;
    private Doors doorState;

    /**
     * default constructor, initialize wheel to out and door to close
     */
    public LandingSet(){
        wheelState = Wheel.OUT;
        doorState = Doors.CLOSE;
    }

    /**
     * retrieve the state of a door
     * @return door state
     */
    public Doors getDoorState() {
        return doorState;
    }

    /**
     * set a new state to the door
     * @param doorState new state
     */
    public void setDoorState(Doors doorState) {
        this.doorState = doorState;
    }

    /**
     * set a new state to the wheel
     * @param state the new wheel state
     */
    public void setWheelState(Wheel state){
        wheelState = state;
    }

    /**
     * retrieve the state of a wheel
     * @return wheel state
     */
    public Wheel getWheelState(){
        return wheelState;
    }

    /**
     * check if model state is consistent
     * @param supposedWheelState the wheel state wanted
     * @param supposedDoorsState the door state wanted
     * @return true if consistent, false otherwise
     */
    public boolean checkValidity(Wheel supposedWheelState,Doors supposedDoorsState){
        return ( (supposedWheelState.equals(wheelState))
                && (supposedDoorsState.equals(doorState)) );
    }
}
