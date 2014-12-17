package softquality.model;

/**
 * Timing constants for each step of wheel movement
 * @author Lauren Letestu, Vincent Francolin
 *
 */
public final class Timing {

    public static final int timeBeforeTigger = 300;
    public static final long timeToOpenGate = 1000;
    public static final long timeToCloseGate = 1000;
    public static final long timeFromStabilizedToTransition = 1000;
    public static final long timeFromTransitionToStabilized = 1000;

    /**
     * constructor to avoid instantiation of utility class
     */
    private Timing(){
    }
}
