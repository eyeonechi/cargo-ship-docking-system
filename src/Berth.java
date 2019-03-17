public class Berth {

  private String name;
  private Ship ship;

  public Berth(String name) {
    this.name = name;
  }

  public synchronized void dock(Ship ship) {
    this.ship = ship;
    System.out.println(this.ship.toString() + " docks at " + this.name);
  }

  public synchronized void undock() {
    System.out.println(this.ship.toString() + " undocks from " + this.name);
    this.ship = null;
  }

}
