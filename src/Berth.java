public class Berth {

  private String name;
  private Ship ship;
  private Boolean shield;

  public Berth(String name) {
    this.name = name;
    this.shield = false;
  }

  public synchronized void dock(Ship ship) {
    while (this.shield == true || this.ship != null) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    this.ship = ship;
    System.out.println(this.ship.toString() + " docks at " + this.name + ".");
  }

  public synchronized void undock() {
    while (this.shield) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(this.ship.toString() + " undocks from " + this.name + ".");
    this.ship = null;
  }

  public synchronized void activate() {
    this.shield = true;
    System.out.println("Shield is activated.");
    notifyAll();
  }

  public synchronized void deactivate() {
    this.shield = false;
    System.out.println("Shield is deactivated.");
    notifyAll();
  }

}
