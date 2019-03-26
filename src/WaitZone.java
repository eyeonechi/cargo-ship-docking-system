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

    private String type;
    private List<Ship> ships;

    public WaitZone(String type) {
        this.type = type;
        this.ships = new ArrayList<Ship>();
    }

    public synchronized void arrive(Ship ship) {
        while (this.ships.size() == Params.MAX_SHIPS) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        if (this.type.equals("arrival")) {
            System.out.println(ship.toString() + " arrives at " + this.type + " zone");
        }
        this.ships.add(ship);
        notifyAll();
    }

    public synchronized Ship depart() {
        while (this.ships.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        if (this.type.equals("departure")) {
            while (this.ships.get(0).hasPilot()) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            System.out.println(this.ships.get(0).toString() + " departs " + this.type + " zone");
        }
        Ship ship = this.ships.remove(0);
        notifyAll();
        return ship;
    }

    public synchronized void acquireShip(Pilot pilot) {
        while (this.ships.size() == 0 || this.ships.get(0).hasPilot()) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.ships.get(0).setPilot(true);
        System.out.println(pilot.toString() + " acquires " + this.ships.get(0).toString() + ".");
        notifyAll();
    }

    public synchronized void releaseShip(Pilot pilot, Integer shipId) {
        for (Ship ship: this.ships) {
            if (ship.getId().equals(shipId)) {
                ship.setPilot(false);
                System.out.println(pilot.toString() + " releases " + this.ships.get(0).toString());
                notifyAll();
                break;
            }
        }
    }

}