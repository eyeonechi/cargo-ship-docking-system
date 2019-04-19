/**
 * A pilot which operates the arriving ships to dock,
 * unload, and undock safely at the Berth,
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
     * @param id            : pilot id
     * @param arrivalZone   : the arrival zone
     * @param departureZone : the departure zone
     * @param tugs          : the pool of tugs
     * @param berth         : the USS Emafor berth
     */
    public Pilot(
      Integer id,
      WaitZone arrivalZone,
      WaitZone departureZone,
      Tugs tugs,
      Berth berth
    ) {
        this.id = id;
        this.arrivalZone = arrivalZone;
        this.departureZone = departureZone;
        this.tugs = tugs;
        this.berth = berth;
    }

    /**
     * Continuously pilots newly arrived ships to the berth for unloading,
     * then to the departure zone to await departure
     */
    public void run() {
        while (!isInterrupted()) {
            try {
                // Acquires a newly arrived cargo ship
                this.arrivalZone.acquireShip(this);

                // Acquires the required number of tugs to dock the ship
                this.tugs.acquire(this, Params.DOCKING_TUGS);

                // Depart from the arrival zone and head to the berth
                Ship ship = this.arrivalZone.depart();
                sleep(Params.TRAVEL_TIME);

                // Dock the ship and release tugs
                this.berth.dock(ship);
                sleep(Params.DOCKING_TIME);
                this.tugs.release(this, Params.DOCKING_TUGS);

                // Commence unloading process
                ship.unload();
                sleep(Params.UNLOADING_TIME);

                // After unloading, acquires tugs and undock the ship
                this.tugs.acquire(this, Params.UNDOCKING_TUGS);
                this.berth.undock();
                sleep(Params.UNDOCKING_TIME);

                // Guide the ship to the departureZone
                sleep(Params.TRAVEL_TIME);
                this.departureZone.arrive(ship);

                // Release the ship and tugs
                this.departureZone.releaseShip(this, ship.getId());
                this.tugs.release(this, Params.UNDOCKING_TUGS);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

    /**
     * Produce an identifying string for the pilot
     */
    public String toString() {
        return "pilot " + id;
    }

}
