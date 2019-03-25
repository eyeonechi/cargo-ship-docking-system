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
    ship.setPilot(false);
    if (this.type.equals("arrival")) {
      System.out.println(this.ships.get(0).toString() + " arrives at " + this.type + " zone");
    }
  }

  public synchronized Ship depart() {
    if (this.ships.size() > 0) {
      if (this.type.equals("departure")) {
        System.out.println(this.ships.get(0).toString() + " departs " + this.type + " zone");
      }
      return this.ships.remove(0);
    }
    return null;
  }

  public synchronized Boolean acquireShip(Pilot pilot) {
    for (Ship ship: this.ships) {
      if (!ship.hasPilot()) {
        ship.setPilot(true);
        System.out.println(pilot.toString() + " acquires " + ship.toString() + ".");
        return true;
      }
    }
    return false;
  }

  public synchronized Boolean isFull() {
    return this.ships.size() == 1;
  }

}