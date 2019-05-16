/**
 * A cargo ship, with a unique id, that arrives at
 * the space station to deliver its cargo.
 *
 * @author ngeard@unimelb.edu.au
 * @author ichee@student.unimelb.edu.au 736901 (edited by)
 *
 */
public class Ship {

    // a unique identifier for this cargo ship
    private int id;

    // the next ID to be allocated
    private static int nextId = 1;

    // a flag indicating whether the ship is currently loaded
    private Boolean loaded;

    // a flag indicating whether a pilot is in the ship
    private Boolean pilot;

    /**
     * Create a new vessel with a given identifier
     * @param id : the id of the vessel
     */
    private Ship(int id) {
        this.id = id;
        this.loaded = true;
        this.pilot = false;
    }

    /**
     * Get a new Ship instance with a unique identifier
     * @return : a new ship
     */
    public static Ship getNewShip() {
        return new Ship(nextId++);
    }

    /**
     * Returns the id of the ship
     * @return : ship id
     */
    public Integer getId() {
      return this.id;
    }

    /**
     * Produce an identifying string for the cargo ship
     * @return : ship identifying string
     */
    public String toString() {
        return "ship [" + id + "]";
    }

    /**
     * Unload cargo from the ship
     */
    public void unload() {
        System.out.println(this.toString() + " being unloaded.");
        this.loaded = false;
    }

    /**
     * Determines whether the ship is occupied by a pilot
     * @return : whether the ship is occupied or not
     */
    public Boolean hasPilot() {
        return this.pilot;
    }

    /**
     * Sets or unsets a pilot in the ship
     * @param pilot : whether there is a pilot
     */
    public void setPilot(Boolean pilot) {
        this.pilot = pilot;
    }

}
