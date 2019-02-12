package johnson.michael.ticketsimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Street {

  private final String name;
  private final List<ParkingMeter> parkingMeters = new ArrayList<>();
  private PoliceOfficer assignedOfficer;

  public Street(final String name, final PoliceOfficer assignedOfficer) {
    this.name = name;
    this.setAssignedOfficer(assignedOfficer);
  }

  public void addParkingMeter(final ParkingMeter meter) {
    if (this.parkingMeters.contains(meter)) { // If we already contain this exact meter, no-op
      return;
    }

    this.parkingMeters.add(meter);
  }

  public List<ParkingMeter> getParkingMeters() {
    return Collections.unmodifiableList(this.parkingMeters);
  }

  public PoliceOfficer getAssignedOfficer() {
    return this.assignedOfficer;
  }

  public void setAssignedOfficer(final PoliceOfficer assignedOfficer) {
    this.assignedOfficer = assignedOfficer;
  }

  public String getName() {
    return this.name;
  }
}
