package soft_quality.model;

/**
 * describe the model of a plane
 * @author Lauren Letestu, Vincent Francolin
 *
 */
public class LandingSet {
	private Wheel wheelState;
	private Doors doorState;
	
	public LandingSet(){
		wheelState = Wheel.OUT;
		doorState = Doors.CLOSE;
	}
	
	public Doors getDoorState() {
		return doorState;
	}

	public void setDoorState(Doors doorState) {
		this.doorState = doorState;
	}

	public void setWheelState(Wheel state){
		wheelState = state;
	}
	
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
		return ( (supposedWheelState == wheelState) 
				&& (supposedDoorsState == doorState) );
	}
}
