public class WaitZone {

  private String type;
  private Ship ship;

  public WaitZone(String type) {
    this.type = type;
    this.ship = null;
  }

  public synchronized void arrive(Ship ship) {
    this.ship = ship;
    System.out.println(this.ship.toString() + " arrives at " + this.type + " zone");
  }

  public synchronized void depart() {
    while (ship == null);
    System.out.println(this.ship.toString() + " departs " + this.type + " zone");
    this.ship = null;
  }

  public synchronized Boolean hasShip() {
    return this.ship != null;
  }

  public synchronized Ship getShip() {
    return this.ship;
  }

}