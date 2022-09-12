package service;

import entities.Driver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DriverService {

    private static final List<Driver> drivers = new ArrayList<>();

    public static void inputNewDriver() {
        System.out.print("Xin mời nhập số lượng lái xe mới muốn thêm: ");
        int numberOfDriver = new Scanner(System.in).nextInt();
        for (int i = 0; i < numberOfDriver; i++) {
            Driver driver = new Driver();
            driver.enterInfo();
            drivers.add(driver);
        }
        showDriversInfo();
    }

    public static void showDriversInfo(){
        // print with format table
        String leftAlignFormat = "| %-9s | %-17s | %-30s | %-11s | %-5s |%n";

        System.out.format("+-----------+-------------------+--------------------------------+-------------+-------+%n");
        System.out.format("| Driver ID |    Driver Name    |             Address            |    Phone    | Level |%n");
        System.out.format("+-----------+-------------------+--------------------------------+-------------+-------+%n");
        drivers.forEach(driver -> {
            System.out.format(leftAlignFormat, driver.getDriverId(),
                    driver.getDriverName(), driver.getAddress(), driver.getPhone(), driver.getLevel());
        });
        System.out.format("+-----------+-------------------+--------------------------------+-------------+-------+%n");
    }

    public static int generateId() {
        if (drivers.isEmpty())
            return 10000;
        return drivers.stream().map(Driver::getDriverId)
                .max(Comparator.comparing(Integer::valueOf)).get() + 1;
    }

    public static boolean isEmptyDriver() {
        return drivers.isEmpty();
    }

    public static List<Driver> getDrivers() {
        return drivers;
    }
}
