public class Tugs {

  private Integer tugs;

  public Tugs(Integer tugs) {
    this.tugs = tugs;
  }

  public synchronized Boolean acquire(Integer tugs) {
    if (this.tugs > 0) {
      this.tugs -= tugs;
      return true;
    }
    return false;
  }

  public synchronized void release(Integer tugs) {
    this.tugs += tugs;
  }

  public synchronized Integer getNumTugs() {
    return tugs;
  }

}