package entities;

import service.DriverService;
import service.RouteService;

import java.util.Scanner;

public class Route implements InputInfo{

    private int routeId;

    private double distance;

    private int numberOfStops;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    @Override
    public void enterInfo() {
        this.setRouteId(RouteService.generateId());
        System.out.println("Nhập chiều dài tuyến(m): ");
        this.setDistance(new Scanner(System.in).nextInt());
        System.out.print("Nhập số điểm dừng: ");
        this.setNumberOfStops(new Scanner(System.in).nextInt());
    }
}
