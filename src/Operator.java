public class Operator extends Thread {

  private Berth berth;

  public Operator(Berth berth) {
    this.berth = berth;
  }

  public void run() {
    while(!isInterrupted()) {
      try {
        // Activate shield
        this.berth.activate();
        sleep(Params.DEBRIS_TIME);

        // Deactivate shield
        this.berth.deactivate();
        sleep(Params.debrisLapse());
      } catch (InterruptedException e) {
          this.interrupt();
      }
    }
  }

}