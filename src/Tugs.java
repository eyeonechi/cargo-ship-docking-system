/**
 * Collectively represent a pool of tugs needed by ships during travel to and from the Berth,
 * and during docking and undocking procedure.
 *
 * @author ichee@student.unimelb.edu.au 736901
 *
 */
public class Tugs {

    // total number of tugs in the pool
    private Integer tugs;

    /**
     * Creates a new pool of tugs
     * @param tugs : number of tugs
     */
    public Tugs(Integer tugs) {
        this.tugs = tugs;
    }

    /**
     * Allocate tugs to a pilot
     * @param pilot : the pilot acquiring tugs
     * @param tugs  : how many tugs
     */
    public synchronized void acquire(Pilot pilot, Integer tugs) {
        while ((this.tugs - tugs) < 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.tugs -= tugs;
        System.out.println(pilot.toString() + " acquires " + tugs + " tugs (" + this.tugs + " available).");
    }

    /**
     * Return allocated tugs to the pool
     * @param pilot : the pilot releasing tugs
     * @param tugs  : how many tugs
     */
    public synchronized void release(Pilot pilot, Integer tugs) {
        while ((this.tugs + tugs) > Params.NUM_TUGS) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.tugs += tugs;
        System.out.println(pilot.toString() + " releases " + tugs + " tugs (" + this.tugs + " available).");
        notifyAll();
    }

}