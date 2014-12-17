package softquality.controller;

import javax.swing.Timer;
import java.awt.event.*;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.SwingWorker;
import softquality.model.Doors;
import softquality.model.Handle;
import softquality.model.Leds;
import softquality.model.Plane;
import softquality.model.Position;
import softquality.model.Timing;
import softquality.model.Wheel;
import softquality.view.MainView;

/**
 * controller class for plane
 *
 * @author Lauren Letestu, Vincent Francolin
 *
 */
public class PlaneController {
    private Plane myModel;
    private MainView myView;
    private GearWorker front;
    private GearWorker left;
    private GearWorker right;
    private Timer trigger;

    /**
     * main controller, initialize the view
     */
    public PlaneController() {
        myModel = new Plane();
    }

    /**
     * set the reference to the view for the controller
     *
     * @param myView
     *            reference to the main window
     */
    public void setMyView(MainView myView) {
        this.myView = myView;
    }

    /**
     * execute the action with a waiting time before real execution
     *
     * @param out
     *            true if new state is out, false otherwise
     */
    public void executeActionWithTriggeredTime(final boolean out) {
        if (trigger != null && trigger.isRunning()) {
            trigger.stop();
        } else {
            trigger = new Timer(Timing.timeBeforeTigger, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (out) {
                        outWheels();
                    } else {
                        inWheels();
                    }
                }
            });
            trigger.setRepeats(false);
            trigger.start();
        }
    }

    /**
     * launch the process to set the wheel in OUT state
     */
    public void outWheels() {
        // Step 1 change to transition state
        interruptPreviousActions();
        myModel.setLeds(Leds.ORANGE);
        myModel.setHandle(Handle.DOWN);
        updateViews();

        // Step 2 update all wheel
        front = new GearWorker(this, Position.FRONT, true);
        front.execute();
        left = new GearWorker(this, Position.LEFT, true);
        left.execute();
        right = new GearWorker(this, Position.RIGHT, true);
        right.execute();
    }

    /**
     * launch the process to set the wheel in IN state
     */
    public void inWheels() {
        // Step 1 change to transition state
        interruptPreviousActions();
        myModel.setLeds(Leds.ORANGE);
        myModel.setHandle(Handle.UP);
        updateViews();

        // Step 2 update wheel
        front = new GearWorker(this, Position.FRONT, false);
        front.execute();
        left = new GearWorker(this, Position.LEFT, false);
        left.execute();
        right = new GearWorker(this, Position.RIGHT, false);
        right.execute();
    }

    /**
     * Update the final state of a transition
     *
     * @param out
     *            true if final state is out, false if it's in
     */
    public void updateFinalState(boolean out) {
        if (front.isDone() && left.isDone() && right.isDone()) {
            boolean error = !myModel.checkFinalState(out);
            if (error) {
                myModel.setLeds(Leds.RED);
            } else {
                if (out) {
                    myModel.setLeds(Leds.GREEN);
                }
                else {
                    myModel.setLeds(Leds.NONE);
                }
            }
            updateViews();
        }
    }

    /**
     * interrupt changing state thread
     */
    public void interruptPreviousActions() {
        // nothing to interrupt
        if (front == null || left == null || right == null){
            return;
        }

        // already finished nothing to do
        if (front.isDone() && left.isDone() && right.isDone()) {
            return;
        }

        // need to check, on Javadoc it's said that isDone return always true
        // after cancel, but if the cancel fails?
        front.cancel(true);
        left.cancel(true);
        right.cancel(true);
    }

    /**
     * update the view
     */
    private void updateViews() {
        myView.updateData(myModel.getLedsState(), myModel.getAllWheel(),
                myModel.getAllDoors());
    }

    /**
     * Thread to change the state of the plane
     *
     * @author Lauren Letestu, Vincent Francolin
     *
     */
    class GearWorker extends SwingWorker<Object, Object> {

        private PlaneController ctrl;
        private Position pos;
        private boolean out;

        /**
         * thread constructor
         *
         * @param c
         *            reference to controller
         * @param p
         *            position of the wheel we change
         * @param o
         *            true if new state is out, false if is in
         */
        public GearWorker(PlaneController c, Position p, boolean o) {
            ctrl = c;
            pos = p;
            out = o;
        }

        /**
         * Thread movement landing set
         * @return  null
         * @throws Exception
         */
        @Override
        protected Object doInBackground() throws Exception {
            try {
                Thread.sleep(Timing.timeToOpenGate);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            ctrl.myModel.setDoors(Doors.OPEN, pos);
            updateViews();

            try {
                Thread.sleep(Timing.timeFromStabilizedToTransition);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            ctrl.myModel.setWheel(Wheel.TRANSITION, pos);
            updateViews();

            try {
                Thread.sleep(Timing.timeFromTransitionToStabilized);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            if (out) {
                ctrl.myModel.setWheel(Wheel.OUT, pos);
            } else {
                ctrl.myModel.setWheel(Wheel.IN, pos);
            }
            updateViews();

            try {
                Thread.sleep(Timing.timeToCloseGate);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            ctrl.myModel.setDoors(Doors.CLOSE, pos);
            updateViews();

            return null;
        }

        /**
         * Update view at done state
         */
        @Override
        protected void done() {
            updateFinalState(out);
        }
    }

    /**
     * Adding method for test
     */

    /**
     * Return boolean to indicated if view is setting or not
     *
     * @return true if view is setting, false otherwise
     */
    public boolean isSettingView() {
        return (myView != null);
    }

    /**
     * Getter used for test
     */

    /**
     * get led state
     * @return Leds state
     */
    public Leds getMyLedState() {
        return myModel.getLedsState();
    }

    /**
     * get handle state
     * @return handle state
     */
    public Handle getMyHandleState() {
        return myModel.getHandle();
    }

    /**
     * Get doors state
     * @return doors state
     */
    public Collection<Doors> getMyDoorsState() {
        HashMap<Integer,Doors> doors = myModel.getAllDoors();
        return doors.values();
    }

    /**
     * Get wheel state
     * @return wheel state
     */
    public Collection<Wheel> getMyWheelsState() {
        HashMap<Integer,Wheel> wheel = myModel.getAllWheel();
        return wheel.values();
    }


}
