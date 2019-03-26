/**
 * The berth of the USS Emafor at which ships dock and unload their cargo.
 * Contains a shield which is activated on detection of space debris.
 *
 * @author ichee@student.unimelb.edu.au 736901
 *
 */
public class Berth {

    // name of the berth
    private String name;

    // ship currently in the berth
    private Ship ship;

    // a flag indicating whether the shield is activated or not
    private Boolean shield;

    /**
     * Creates a new berth with a given name
     */
    public Berth(String name) {
        this.name = name;
        this.shield = false;
    }

    /**
     * Docks a ship in the berth
     */
    public synchronized void dock(Ship ship) {
        while (this.shield == true || this.ship != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.ship = ship;
        System.out.println(this.ship.toString() + " docks at " + this.name + ".");
    }

    /**
     * Undocks a ship from the berth
     */
    public synchronized void undock() {
        while (this.shield) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.ship.toString() + " undocks from " + this.name + ".");
        this.ship = null;
    }

    /**
     * Activates the shield
     */
    public synchronized void activate() {
        this.shield = true;
        System.out.println("Shield is activated.");
        notifyAll();
    }

    /**
     * Deactivates the shield
     */
    public synchronized void deactivate() {
        this.shield = false;
        System.out.println("Shield is deactivated.");
        notifyAll();
    }

}
