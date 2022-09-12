package entities;

public class RouteDetail {

    private Route route;

    private int numberOfTurns;

    public RouteDetail(Route route, int numberOfRoute) {
        this.route = route;
        this.numberOfTurns = numberOfRoute;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }
}
