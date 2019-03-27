/**
 * A pilot which operates the arriving ships to dock, unload, and undock safely at the Berth,
 * until it safely arrives at the departure zone.
 *
 * @author ichee@student.unimelb.edu.au 736901
 *
 */
public class Pilot extends Thread {

    // pilot id
    private Integer id;

    // arrival and departure zones
    private WaitZone arrivalZone;
    private WaitZone departureZone;

    // pool of tugs
    private Tugs tugs;

    // berth of the USS Emafor
    private Berth berth;

    /**
     * Creates a new pilot to start work
     */
    public Pilot(Integer id, WaitZone arrivalZone, WaitZone departureZone, Tugs tugs, Berth berth) {
        this.id = id;
        this.arrivalZone = arrivalZone;
        this.departureZone = departureZone;
        this.tugs = tugs;
        this.berth = berth;
    }

    /**
     * Continuously pilots a newly arrived ship to the berth for unloading,
     * then to the departure zone to await departure
     */
    public void run() {
        while (!isInterrupted()) {
            try {
                // Acquires a newly arrived cargo ship
                this.arrivalZone.acquireShip(this);

                // Acquires the required number of tugs to dock the ship
                this.tugs.acquire(this, Params.DOCKING_TUGS);
                Ship ship = this.arrivalZone.depart();
                sleep(Params.TRAVEL_TIME);

                // Dock the ship and release tugs
                this.berth.dock(ship);
                sleep(Params.DOCKING_TIME);
                this.tugs.release(this, Params.DOCKING_TUGS);

                // Commence unloading process
                ship.unload();
                sleep(Params.UNLOADING_TIME);

                // After the ship is unloaded, acquires tugs for undocking
                this.tugs.acquire(this, Params.UNDOCKING_TUGS);
                this.berth.undock();
                sleep(Params.UNDOCKING_TIME);
                sleep(Params.TRAVEL_TIME);

                // Undock the ship and place it into the departureZone, releasing the undocking tugs
                this.departureZone.arrive(ship);
                this.departureZone.releaseShip(this, ship.getId());
                this.tugs.release(this, Params.UNDOCKING_TUGS);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

    /**
     * Prints a pilot
     */
    public String toString() {
        return "pilot " + id;
    }

}
