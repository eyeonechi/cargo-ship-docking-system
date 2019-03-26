/**
 * Collectively represent a pool of tugs needed by ships during travel to and from the Berth,
 * and during docking and undocking procedure.
 *
 * @author ichee@student.unimelb.edu.au 736901
 *
 */

public class Tugs {

  private Integer tugs;

  public Tugs(Integer tugs) {
    this.tugs = tugs;
  }

  public synchronized void acquire(Pilot pilot, Integer tugs) {
    while ((this.tugs - tugs) < 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    this.tugs -= tugs;
    System.out.println(pilot.toString() + " acquires " + tugs + " tugs (" + this.tugs + " available).");
  }

  public synchronized void release(Pilot pilot, Integer tugs) {
    this.tugs += tugs;
    System.out.println(pilot.toString() + " releases " + tugs + " tugs (" + this.tugs + " available).");
    notifyAll();
  }

}