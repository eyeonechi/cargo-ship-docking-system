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

    private Boolean pilot;

    // create a new vessel with a given identifier
    private Ship(int id) {
        this.id = id;
        this.loaded = true;
        this.pilot = false;
    }

    // get a new Ship instance with a unique identifier
    public static Ship getNewShip() {
        return new Ship(nextId++);
    }

    public Integer getId() {
      return this.id;
    }

    // produce an identifying string for the cargo ship
    public String toString() {
        return "ship [" + id + "]";
    }

    public void unload() {
      System.out.println(this.toString() + " being unloaded.");
      this.loaded = false;
    }

    public Boolean hasPilot() {
      return this.pilot;
    }

    public void setPilot(Boolean pilot) {
      this.pilot = pilot;
    }

}
