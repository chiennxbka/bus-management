package service;

import entities.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AssignmentService {

    private static final List<Assignment> assignments = new ArrayList<>();

    public static void assignment() {
        if (DriverService.isEmptyDriver() || RouteService.isEmptyRoute()) {
            System.out.println("Cần thực hiện nhập lái xe và tuyến đường trước khi phân công lái xe");
            return;
        }

        System.out.print("Nhập vào số lượng lái xe muốn phân công: ");
        int readerNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < readerNumber; i++) {
            Driver driver = inputDriverInfo(i);
            List<RouteDetail> routeDetails = inputRouteDetail(driver.getDriverId());
            Assignment assignment = new Assignment(driver, routeDetails);
            assignments.add(assignment);
        }
        showAssignment(assignments);
    }

    public static int getTotalRoute(int driverId) {
        List<RouteDetail> details = assignments.stream().filter(assignment -> assignment.getDriver().getDriverId() == driverId).map(Assignment::getRoutes).findAny().orElse(Collections.emptyList());
        return details.stream().map(RouteDetail::getNumberOfTurns).mapToInt(Integer::intValue).sum();
    }

    private static List<RouteDetail> inputRouteDetail(int driverId) {
        RouteService.showRoutesInfo();
        List<RouteDetail> routeDetails = new ArrayList<>();
        System.out.println("---------Nhập mã tuyến và số lượng lượt lái xe trên mỗi tuyến đường đó---------");
        System.out.println("So luong tuyen lai xe:");
        int routes = new Scanner(System.in).nextInt();
        int totalDrive = getTotalRoute(driverId);
        for (int i = 1; i <= routes; i++) {
            System.out.println("Nhập mã tuyến xe thứ: " + i);
            int routeId = new Scanner(System.in).nextInt();
            Route route;
            do {
                route = RouteService.findById(routeId);
                if (route == null) {
                    System.out.println("Tuyen duong khong ton tai, vui long nhap lai");
                }
            } while (route == null);
            System.out.println("Nhap so luot lai xe cho tuyen xe thu: " + i);
            int numberOfRoute;
            do {
                numberOfRoute = new Scanner(System.in).nextInt();
                if (numberOfRoute + totalDrive > 15) {
                    System.out.print("Ban khong the lai xe qua 15 luot/ngay, tong so luot ban da lai la: " + totalDrive + " vui long nhap lai");
                } else {
                    break;
                }

            } while ((numberOfRoute + totalDrive) < 15);
            routeDetails.add(new RouteDetail(route, numberOfRoute));
        }
        return routeDetails;
    }

    private static Driver inputDriverInfo(int i) {
        DriverService.showDriversInfo();
        System.out.print("Nhập vào mã lái xe thứ " + (i + 1) + " muốn phân công: ");
        Driver driver;
        do {
            int driverId = new Scanner(System.in).nextInt();
            driver = DriverService.getDrivers().stream().filter(d -> d.getDriverId() == driverId).findAny().orElse(null);
            if (driver != null) {
                break;
            }
            System.out.print("Không tìm thấy lái xe vừa nhập, vui lòng nhập lại: ");
        } while (true);
        return driver;
    }

    public static void sort() {
        System.out.println("Nhập lựa chọn của bạn: ");
        System.out.println("1.Theo Họ tên lái xe.");
        System.out.println("2.Theo Số lượng tuyến đảm nhận trong ngày (giảm dần).");
        int choice;
        do {
            choice = new Scanner(System.in).nextInt();
            if (choice == 1 || choice == 2) {
                break;
            }
            System.out.print("Lựa chọn không hợp lệ, vui lòng chọn lại: ");
        } while (true);
        if (assignments.isEmpty()) {
            System.out.println("Chưa có dữ liệu, vui lòng nhập thông tin");
            return;
        }
        switch (choice) {
            case 1 -> {
                assignments.sort(Comparator.comparing(o -> o.getDriver().getDriverName()));
                showAssignment(assignments);
            }
            case 2 -> {
                assignments.sort(Comparator.comparing(Assignment::getTotalDistance).reversed());
                Map<Driver, Double> maps = assignments.stream().collect(Collectors.groupingBy(Assignment::getDriver, Collectors.summingDouble(value -> value.getRoutes().stream().mapToDouble(value1 -> value.getTotalDistance()).sum())));
                List<AssignmentSortByDistance> sorted = new ArrayList<>();
                for (Map.Entry<Driver, Double> entry : maps.entrySet()) {
                    sorted.add(new AssignmentSortByDistance(entry.getKey().getDriverName(), entry.getValue()));
                }
                sorted.sort(Comparator.comparing(AssignmentSortByDistance::getTotalDistance).reversed());
                // print info
                String leftAlignFormat = "| %-15s | %-14f |%n";
                System.out.format("+-----------------+----------------+%n");
                System.out.format("|   Driver Name   | Total Distance |%n");
                System.out.format("+-----------------+----------------+%n");
                sorted.forEach(s -> System.out.format(leftAlignFormat, s.getDriverName(), s.getTotalDistance()));
            }
        }
    }

    public static void statistic() {
        String leftAlignFormat = "| %-15s | %-14f |%n";

        System.out.format("+-----------------+----------------+%n");
        System.out.format("|   Driver Name   | Total Distance |%n");
        System.out.format("+-----------------+----------------+%n");
        if (!assignments.isEmpty()) {
            assignments.forEach(assignment -> {
                List<RouteDetail> details = assignment.getRoutes();
                if (!details.isEmpty()) {
                    System.out.format(leftAlignFormat, assignment.getDriver().getDriverName(), assignment.getTotalDistance());
                } else {
                    System.out.format(leftAlignFormat, assignment.getDriver().getDriverName(), 0);
                }
            });
            System.out.format("+-----------------+----------------+%n");
        }
    }

    public static void showAssignment(List<Assignment> data) {
        // print with format table
        String leftAlignFormat = "| %-15s | %-14d | %-14d | %-14f |%n";

        System.out.format("+-----------------+----------------+----------------+----------------+%n");
        System.out.format("|   Driver Name   |    Route Id    | Number of Stop | Total Distance |%n");
        System.out.format("+-----------------+----------------+----------------+----------------+%n");
        data.forEach(assignment -> {
            Driver driver = assignment.getDriver();
            List<RouteDetail> details = assignment.getRoutes();
            if (!details.isEmpty()) {
                details.forEach(routeDetail -> {
                    Route route = routeDetail.getRoute();
                    System.out.format(leftAlignFormat, driver.getDriverName(), route.getRouteId(), route.getNumberOfStops(), route.getDistance() * routeDetail.getNumberOfTurns());
                });
            }
        });
        System.out.format("+-----------------+----------------+----------------+----------------+%n");
    }
}
