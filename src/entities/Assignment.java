package entities;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Assignment {

    private Driver driver;

    private List<RouteDetail> routes;

    private double totalDistance;

    public Assignment(Driver driver, List<RouteDetail> routes) {
        this.driver = driver;
        this.routes = routes;
        setTotalDistance();
    }

    public Driver getDriver() {
        return driver;
    }

    public List<RouteDetail> getRoutes() {
        return routes;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance() {
        if (routes == null || routes.isEmpty()) {
            this.totalDistance = 0;
            return;
        }
        AtomicReference<Double> totalDistance = new AtomicReference<>((double) 0);
        this.routes.forEach(routeDetail -> {
            totalDistance.updateAndGet(v -> v + routeDetail.getRoute().getDistance() * routeDetail.getNumberOfTurns());
        });
        this.totalDistance = totalDistance.get();
    }
}
