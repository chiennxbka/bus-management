package entities;

import constant.Level;
import service.DriverService;

import java.util.Scanner;

public class Driver implements InputInfo {

    private int driverId;

    private String driverName;

    private String address;

    private String phone;

    private Level level;

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public void enterInfo() {
        this.setDriverId(DriverService.generateId());
        System.out.println("Nhập họ tên lái xe: ");
        this.setDriverName(new Scanner(System.in).nextLine());
        System.out.print("Nhập địa chỉ người lái xe: ");
        this.setAddress(new Scanner(System.in).nextLine());
        System.out.print("Nhập số điện thoại người lái xe: ");
        this.setPhone(new Scanner(System.in).nextLine());
        System.out.println("Nhập trình độ lái xe: ");
        System.out.println("1. Bằng A1");
        System.out.println("2. Bằng A2");
        System.out.println("3. Bằng B1");
        System.out.println("4. Bằng B2");
        System.out.println("5. Bằng C");
        System.out.println("6. Bằng D");
        System.out.println("7. Bằng E");
        System.out.println("8. Bằng F");
        int bookSpecializationInt;
        do {
            bookSpecializationInt = new Scanner(System.in).nextInt();
            if (bookSpecializationInt < 1 || bookSpecializationInt > 8)
                System.out.print("Lựa chọn không hợp lệ, vui lòng chọn lại: ");
        } while (bookSpecializationInt < 1 || bookSpecializationInt > 8);
        switch (bookSpecializationInt) {
            case 1:
                this.setLevel(Level.A1);
                break;
            case 2:
                this.setLevel(Level.A2);
                break;
            case 3:
                this.setLevel(Level.B1);
                break;
            case 4:
                this.setLevel(Level.B2);
                break;
            case 5:
                this.setLevel(Level.C);
                break;
            case 6:
                this.setLevel(Level.D);
                break;
            case 7:
                this.setLevel(Level.E);
                break;
            case 8:
                this.setLevel(Level.F);
                break;
        }
    }
}
