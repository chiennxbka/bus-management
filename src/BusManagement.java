import service.AssignmentService;
import service.DriverService;
import service.RouteService;

import java.util.Scanner;

public class BusManagement {
    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        boolean isExit = false;
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1 -> DriverService.inputNewDriver();
                case 2 -> RouteService.inputNewRoute();
                case 3 -> AssignmentService.assignment();
                case 4 -> AssignmentService.sort();
                case 5 -> AssignmentService.statistic();
                case 6 -> isExit = true;
                default -> isExit = true;
            }
        } while (!isExit);
    }

    private static int functionChoice() {
        showMenu();
        System.out.print("Xin mời chọn chức năng: ");
        int choice;
        do {
            choice = new Scanner(System.in).nextInt();
            if (choice >= 1 && choice <= 8) {
                break;
            }
            System.out.print("Lựa chọn không hợp lệ, vui lòng chọn lại: ");
        } while (true);
        return choice;
    }

    private static void showMenu() {
        System.out.println("----------CHƯƠNG TRÌNH QUẢN LÝ PHÂN CÔNG XE BUÝT------------");
        System.out.println("1. Nhập & in danh sách lái xe.");
        System.out.println("2. Nhập & in danh sách tuyến xe.");
        System.out.println("3. Phân công lái xe.");
        System.out.println("4. Sắp xếp danh sách phân công.");
        System.out.println("5. Thống kê khoảng cách chạy xe của mỗi xe trong ngày.");
        System.out.println("6. Thoát chương trình.");
    }
}
