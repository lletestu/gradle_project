package soft_quality.model;

/**
 * Timing constants for each step of wheel movement
 * @author Lauren Letestu, Vincent Francolin
 *
 */
public class Timing {

	public static int timeBeforeTigger = 300;
	public static long timeToOpenGate = 1000;
	public static long timeToCloseGate = 1000;
	public static long timeFromStabilizedToTransition = 1000;
	public static long timeFromTransitionToStabilized = 1000;

    /**
     * constructor to avoid instantiation of utility class
     */
    private Timing(){
    }
	
}
