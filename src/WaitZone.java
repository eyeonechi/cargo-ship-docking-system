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
    notifyAll();
  }

  public synchronized Ship depart() {
    while (this.ships.size() == 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    if (this.type.equals("departure")) {
      System.out.println(this.ships.get(0).toString() + " departs " + this.type + " zone");
    }
    return this.ships.remove(0);
  }

  public synchronized Boolean isFull() {
    return this.ships.size() == 1;
  }

}