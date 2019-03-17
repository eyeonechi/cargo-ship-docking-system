import java.util.ArrayList;
import java.util.List;

public class WaitZone {

  private String type;
  private List<Ship> ships;

  public WaitZone(String type) {
    this.type = type;
    this.ships = new ArrayList<Ship>();
  }

  public synchronized void arrive(Ship ship) {
    this.ships.add(ship);
    System.out.println(this.ships.get(0).toString() + " arrives at " + this.type + " zone");
  }

  public synchronized void depart() {
    if (this.ships.size() > 0) {
      System.out.println(this.ships.get(0).toString() + " departs " + this.type + " zone");
      this.ships.remove(0);
    }
  }

  public synchronized Ship getShip() {
    if (this.ships.size() > 0) {
      return this.ships.remove(0);
    }
    return null;
  }

  public synchronized Boolean isFull() {
    return this.ships.size() == 1;
  }

}