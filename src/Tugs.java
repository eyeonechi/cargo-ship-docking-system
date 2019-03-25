public class Tugs {

  private Integer tugs;

  public Tugs(Integer tugs) {
    this.tugs = tugs;
  }

  public synchronized Boolean acquire(Pilot pilot, Integer tugs) {
    if ((this.tugs - tugs) >= 0) {
      this.tugs -= tugs;
      System.out.println(pilot.toString() + " acquires " + tugs + " tugs (" + this.tugs + " available).");
      return true;
    }
    return false;
  }

  public synchronized void release(Pilot pilot, Integer tugs) {
    this.tugs += tugs;
    System.out.println(pilot.toString() + " releases " + tugs + " tugs (" + this.tugs + " available).");
  }

  public synchronized Integer getNumTugs() {
    return this.tugs;
  }

}