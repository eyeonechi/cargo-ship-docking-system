public class Pilot extends Thread {

  private Integer pilots;
  private WaitZone arrivalZone;
  private WaitZone departureZone;
  private Tugs tugs;
  private Berth berth;

  public Pilot(Integer pilots, WaitZone arrivalZone, WaitZone departureZone, Tugs tugs, Berth berth) {
    this.pilots = pilots;
    this.arrivalZone = arrivalZone;
    this.departureZone = departureZone;
    this.tugs = tugs;
    this.berth = berth;
  }

  public void run() {
  }

}