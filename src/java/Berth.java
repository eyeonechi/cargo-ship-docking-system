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
        // ship can only dock if shield is deactivated and berth is empty
        while (this.shield == true || this.ship != null) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.ship = ship;
        System.out.println(
          this.ship.toString() + " docks at " + this.name + "."
        );
    }

    /**
     * Undocks a ship from the berth
     */
    public synchronized void undock() {
        // ship can only undock if shield is deactivated
        while (this.shield) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        System.out.println(
          this.ship.toString() + " undocks from " + this.name + "."
        );
        this.ship = null;
    }

    /**
     * Activates the shield
     */
    public synchronized void activateShield() {
        this.shield = true;
        System.out.println("Shield is activated.");
        notifyAll();
    }

    /**
     * Deactivates the shield
     */
    public synchronized void deactivateShield() {
        this.shield = false;
        System.out.println("Shield is deactivated.");
        notifyAll();
    }

}
