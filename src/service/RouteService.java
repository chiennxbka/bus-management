package service;

import entities.Driver;
import entities.Route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class RouteService {
    private static final List<Route> routes = new ArrayList<>();

    public static void inputNewRoute() {

        System.out.print("Xin mời nhập số lượng tuyến đường mới muốn thêm: ");
        int numberOfDriver = new Scanner(System.in).nextInt();
        for (int i = 0; i < numberOfDriver; i++) {
            Route route = new Route();
            route.enterInfo();
            routes.add(route);
        }
        showRoutesInfo();
    }

    public static void showRoutesInfo(){
        // print with format table
        String leftAlignFormat = "| %-9d | %-17f | %-18d |%n";

        System.out.format("+-----------+-------------------+--------------------+%n");
        System.out.format("| Route ID  |    Distance       |   Number Of Stops  |%n");
        System.out.format("+-----------+-------------------+--------------------+%n");
        routes.forEach(route -> {
            System.out.format(leftAlignFormat, route.getRouteId(),
                    route.getDistance(), route.getNumberOfStops());
        });
        System.out.format("+-----------+-------------------+--------------------+%n");
    }

    public static boolean isEmptyRoute() {
        return routes.isEmpty();
    }

    public static List<Route> getRoutes() {
        return routes;
    }

    public static Route findById(int routeId) {
        return routes.stream().filter(r -> r.getRouteId() == routeId).findAny().orElse(null);
    }

    public static int generateId() {
        if (routes.isEmpty())
            return 10000;
        return routes.stream().map(Route::getRouteId)
                .max(Comparator.comparing(Integer::valueOf)).get() + 1;
    }
}
