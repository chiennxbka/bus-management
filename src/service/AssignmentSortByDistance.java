package service;

public class AssignmentSortByDistance {

    private String driverName;

    private double totalDistance;

    public AssignmentSortByDistance() {
    }

    public AssignmentSortByDistance(String driverName, double totalDistance) {
        this.driverName = driverName;
        this.totalDistance = totalDistance;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }
}
