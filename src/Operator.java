/**
 * Controls the activation and deactivation of the shield in the Berth
 *
 * @author ichee@student.unimelb.edu.au 736901
 *
 */

public class Operator extends Thread {

    // the berth where the operator resides
    private Berth berth;


    /**
     * Creates a new operator in a berth
     */
    public Operator(Berth berth) {
        this.berth = berth;
    }

    public void run() {
        while(!isInterrupted()) {
            try {
                // Activate shield for DEBRIS_TIME period
                this.berth.activate();
                sleep(Params.DEBRIS_TIME);

                // Deactivate shield for debrisLapse period
                this.berth.deactivate();
                sleep(Params.debrisLapse());
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

}