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
    // Acquires a newly arrived cargo ship
    while (!this.arrivalZone.hasShip());
    Ship ship = this.arrivalZone.getShip();
    System.out.println("pilot " + id + " acquires " + ship.toString());
    if (tugs.acquire(Params.DOCKING_TUGS)) {
      System.out.println("pilot " + id + " acquires " + Params.DOCKING_TUGS + " tugs (" + (Params.NUM_TUGS - Params.DOCKING_TUGS) + " available.)");
    }

    // Acquires the required number of tugs to dock the ship

    // After the ship is unloaded, acquires tugs for undocking
  }

}