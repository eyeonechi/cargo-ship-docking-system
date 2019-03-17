public class Berth {

  private String name;
  private Ship ship;
  private Boolean shield;

  public Berth(String name) {
    this.name = name;
    this.shield = false;
  }

  public synchronized Boolean dock(Ship ship) {
    if (this.shield == true || this.ship != null) {
      return false;
    }
    this.ship = ship;
    System.out.println(this.ship.toString() + " docks at " + this.name + ".");
    return true;
  }

  public synchronized Boolean undock() {
    if (this.shield) {
      return false;
    }
    System.out.println(this.ship.toString() + " undocks from " + this.name + ".");
    this.ship = null;
    return true;
  }

  public synchronized void activate() {
    this.shield = true;
    System.out.println("Shield is activated.");
  }

  public synchronized void deactivate() {
    this.shield = false;
    System.out.println("Shield is deactivated.");
  }

}
