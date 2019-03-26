/**
 * Consumes unloaded cargo ships from the departure zone.
 *
 * @author ngeard@unimelb.edu.au
 * @author ichee@student.unimelb.edu.au 736901 (edited by)
 *
 */
public class Consumer extends Thread {

    // the wait zone from which cargo ships depart
    private WaitZone departureZone;

    /**
     * Creates a new consumer for the given wait zone
     */
    Consumer(WaitZone newDepartureZone) {
        this.departureZone = newDepartureZone;
    }

    /**
     * Repeatedly collect waiting ships from the departure zone
     */
    public void run() {
        while (!isInterrupted()) {
            try {
                // remove a vessel that is in the departure wait zone
                Ship ship = departureZone.depart();

                // let some time pass before the next departure
                sleep(Params.departureLapse());
            }
            catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

}
