import java.util.ArrayList;
import java.util.List;

/**
 * Represents a designated area of space where ships wait after returning from asteroid
 * mining or wait to return to mining after unloading their cargo.
 *
 * @author ichee@student.unimelb.edu.au 736901
 *
 */
public class WaitZone {

  private static final Integer MAX_SHIPS = 2;
  private String type;
  private List<Ship> ships;

  public WaitZone(String type) {
    this.type = type;
    this.ships = new ArrayList<Ship>();
  }

  public synchronized void arrive(Ship ship) {
    this.ships.add(ship);
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
      while (this.ships.get(0).hasPilot()) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println(this.ships.get(0).toString() + " departs " + this.type + " zone");
    }
    return this.ships.remove(0);
  }

  public synchronized void acquireShip(Pilot pilot) {
    while (this.ships.size() == 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    while (this.ships.get(0).hasPilot()) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    this.ships.get(0).setPilot(true);
    System.out.println(pilot.toString() + " acquires " + this.ships.get(0).toString() + ".");
  }

  public synchronized void releaseShip(Pilot pilot) {
    while (this.ships.size() == 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    this.ships.get(0).setPilot(false);
    System.out.println(pilot.toString() + " releases " + this.ships.get(0).toString());
  }

  public synchronized Boolean isFull() {
    return this.ships.size() == MAX_SHIPS;
  }

}