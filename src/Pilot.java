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
      Ship ship = null;
      while (ship == null) {
        ship = this.arrivalZone.getShip();
      }
      System.out.println("pilot " + id + " acquires " + ship.toString());

      // Acquires the required number of tugs to dock the ship
      if (this.tugs.acquire(Params.DOCKING_TUGS)) {
        System.out.println("pilot " + id + " acquires " + Params.DOCKING_TUGS + " tugs (" + this.tugs.getNumTugs() + " available.)");
      }

      // Dock the ship and release tugs
      this.arrivalZone.depart();
      this.berth.dock(ship);
      this.tugs.release(Params.DOCKING_TUGS);

      ship.unload();

      // After the ship is unloaded, acquires tugs for undocking
      if (this.tugs.acquire(Params.UNDOCKING_TUGS)) {
        System.out.println("pilot " + id + " acquires " + Params.UNDOCKING_TUGS + " tugs (" + this.tugs.getNumTugs() + " available.)");
      }

      // Undock the ship and place it into the departureZone, releasing the undocking tugs
      this.departureZone.arrive(ship);
      this.tugs.release(Params.UNDOCKING_TUGS);
    }
  }

}