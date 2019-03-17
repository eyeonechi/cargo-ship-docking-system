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
    while(!isInterrupted()) {
      // Acquires a newly arrived cargo ship
      Boolean acquire = false;
      while (acquire == false) {
        acquire = this.arrivalZone.acquireShip(this);
      }

      // Acquires the required number of tugs to dock the ship
      Boolean tugs = false;
      while (tugs == false) {
        tugs = this.tugs.acquire(Params.DOCKING_TUGS);
      }
      System.out.println(this.toString() + " acquires " + Params.DOCKING_TUGS + " tugs (" + this.tugs.getNumTugs() + " available).");
      Ship ship = this.arrivalZone.depart();

      // Dock the ship and release tugs
      Boolean dock = false;
      while (dock == false) {
        dock = this.berth.dock(ship);
      }
      this.tugs.release(Params.DOCKING_TUGS);
      System.out.println(this.toString() + " releases " + Params.DOCKING_TUGS + " tugs (" + (this.tugs.getNumTugs() + Params.DOCKING_TUGS) + " available).");

      ship.unload();

      // After the ship is unloaded, acquires tugs for undocking
      tugs = false;
      while (tugs == false) {
        tugs = this.tugs.acquire(Params.UNDOCKING_TUGS);
      }
      System.out.println(this.toString() + " acquires " + Params.UNDOCKING_TUGS + " tugs (" + this.tugs.getNumTugs() + " available).");
      Boolean undock = false;
      while (undock == false) {
        undock = this.berth.undock();
      }

      // Undock the ship and place it into the departureZone, releasing the undocking tugs
      this.departureZone.arrive(ship);
      this.tugs.release(Params.UNDOCKING_TUGS);
      System.out.println(this.toString() + " releases " + Params.UNDOCKING_TUGS + " tugs (" + (this.tugs.getNumTugs() + Params.UNDOCKING_TUGS) + " available).");
    }
  }

  public String toString() {
    return "pilot [" + id + "]";
  }

}