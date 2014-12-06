package com.soft_quality.model;

import java.util.HashMap;

public class Plane {

	private Handle handleState;
	private Leds ledsState;
	private LandingSet[] landingSets;
	
	public Plane(){
		handleState = Handle.DOWN;
		ledsState = Leds.GREEN;
		landingSets = new LandingSet[3];
		for(int i=0;i<3;i++){
			landingSets[i] = new LandingSet();
		}
	}
	
	public void setLeds(Leds state){
		ledsState = state;
	}
	
	public Leds getLedsState(){
		return ledsState;
	}
	
	public void setHandle(Handle state){
		handleState = state;
	}
	
	public Handle getHandle(){
		return handleState;
	}
	
	public Wheel getWheel(Position pos){
		return landingSets[pos.ordinal()].getWheelState();
	}

    public HashMap<Integer, Wheel> getAllWheel() {
        HashMap<Integer, Wheel> wheelState = new HashMap<Integer, Wheel>();
        for (int i = 0; i <landingSets.length; ++i) {
            wheelState.put(i, landingSets[i].getWheelState());
        }
        return wheelState;
    }
    
    public HashMap<Integer, Doors> getAllDoors() {
    	HashMap<Integer, Doors> doorsState = new HashMap<Integer, Doors>();
        for (int i = 0; i <landingSets.length; ++i) {
            doorsState.put(i, landingSets[i].getDoorState());
        }
        return doorsState;
	}
	
	public void setWheel(Wheel state,Position pos){
		landingSets[pos.ordinal()].setWheelState(state);
	}
	
	public Doors getDoor(Position pos){
		return landingSets[pos.ordinal()].getDoorState();
		
	}
	
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
