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
        Ship ship = this.arrivalZone.depart();
        ship.setPilot(true);
        System.out.println(this.toString() + " acquires " + ship.toString() + ".");

        // Acquires the required number of tugs to dock the ship
        this.tugs.acquire(this, Params.DOCKING_TUGS);

        sleep(Params.TRAVEL_TIME);
        sleep(Params.DOCKING_TIME);

        // Dock the ship and release tugs
        this.berth.dock(ship);
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
        System.out.println(this.toString() + " releases " + ship.toString());
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
