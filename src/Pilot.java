public class Pilot extends Thread {

  private Integer id;
  private WaitZone arrivalZone;
  private WaitZone departureZone;
  private Tugs tugs;
  private Berth berth;

  public Pilot(Integer id, WaitZone arrivalZone, WaitZone departureZone, Tugs tugs, Berth berth) {
    this.id = id;
    this.arrivalZone = arrivalZone;
    this.departureZone = departureZone;
    this.tugs = tugs;
    this.berth = berth;
  }

  public void run() {
    while (!isInterrupted()) {
      try {
        // Acquires a newly arrived cargo ship
        Boolean acquire = false;
        while (acquire == false) {
          acquire = this.arrivalZone.acquireShip(this);
        }

        // Acquires the required number of tugs to dock the ship
        Boolean tugs = false;
        while (tugs == false) {
          tugs = this.tugs.acquire(this, Params.DOCKING_TUGS);
        }

        Ship ship = this.arrivalZone.depart();

        sleep(Params.TRAVEL_TIME);
        sleep(Params.DOCKING_TIME);

        // Dock the ship and release tugs
        Boolean dock = false;
        while (dock == false) {
          dock = this.berth.dock(ship);
        }
        this.tugs.release(this, Params.DOCKING_TUGS);

        ship.unload();
        sleep(Params.UNLOADING_TIME);

        // After the ship is unloaded, acquires tugs for undocking
        tugs = false;
        while (tugs == false) {
          tugs = this.tugs.acquire(this, Params.UNDOCKING_TUGS);
        }

        Boolean undock = false;
        while (undock == false) {
          undock = this.berth.undock();
        }

        sleep(Params.UNDOCKING_TIME);
        sleep(Params.TRAVEL_TIME);

        // Undock the ship and place it into the departureZone, releasing the undocking tugs
        this.departureZone.arrive(ship);
        System.out.println(this.toString() + " releases " + ship.toString());
        ship = null;
        this.tugs.release(this, Params.UNDOCKING_TUGS);

      } catch (InterruptedException e) {
          this.interrupt();
      }
    }
  }

  public String toString() {
    return "pilot " + id;
  }

}