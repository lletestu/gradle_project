package softquality.model;

/**
 * Timing constants for each step of wheel movement
 * @author Lauren Letestu, Vincent Francolin
 *
 */
public final class Timing {

    public static final int TIME_BEFORE_TIGGER = 300;
    public static final long TIME_TO_OPEN_GATE = 1000;
    public static final long TIME_TO_CLOSE_GATE = 1000;
    public static final long TIME_FROM_STABILIZED_TO_TRANSITION = 1000;
    public static final long TIME_FROM_TRANSITION_TO_STABILIZED = 1000;

    /**
     * constructor to avoid instantiation of utility class
     */
    private Timing(){
    }
}
