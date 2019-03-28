import java.util.Random;

/**
 * A class which, for convenience, gathers together various
 * system-wide parameters, including time intervals.
 *
 * @author ngeard@unimelb.edu.au
 * @author ichee@student.unimelb.edu.au 736901 (edited by)
 *
 */
public class Params {

    // maximum number of ships in a waitzone
    static final int MAX_SHIPS = 3;

    // number of pilots operating ships
    static final int NUM_PILOTS = 3;

    // total number of tugs available
    static final int NUM_TUGS = 5;

    // number of tugs required for docking a ship
    static final int DOCKING_TUGS = 3;

    // number of tugs required for undocking a ship
    static final int UNDOCKING_TUGS = 2;

    // amount of time needed for docking (ms)
    static final int DOCKING_TIME = 800;

    // amount of time needed for undocking (ms)
    static final int UNDOCKING_TIME = 400;

    // amount of time needed for unloading (ms)
    static final int UNLOADING_TIME = 1200;

    // amount of time needed for travelling (ms)
    static final int TRAVEL_TIME = 800;

    // shield uptime (ms)
    static final int DEBRIS_TIME = 1800;

    // maximum time between ship arrival (ms)
    private static final int MAX_ARRIVAL_INTERVAL = 400;

    // maximum time between ship departure (ms)
    private static final int MAX_DEPARTURE_INTERVAL = 1000;

    // maximum time between space debris hitting the berth
    private static final int MAX_DEBRIS_INTERVAL = 2400;

    /**
     * Returns a random time between 0 and MAX_DEBRIS_INTERVAL
     * @return : debris lapse
     */
    static int debrisLapse() {
        Random rnd = new Random();
        return rnd.nextInt(MAX_DEBRIS_INTERVAL);
    }

    /**
     * Returns a random time between 0 and MAX_ARRIVAL_INTERVAL
     * @return : arrival lapse
     */
    static int arrivalLapse() {
        Random rnd = new Random();
        return rnd.nextInt(MAX_ARRIVAL_INTERVAL);
    }

    /**
     * Returns a random time between 0 and MAX_DEPARTURE_INTERVAL
     * @return : departure lapse
     */
    static int departureLapse() {
        Random rnd = new Random();
        return rnd.nextInt(MAX_DEPARTURE_INTERVAL);
    }

}
