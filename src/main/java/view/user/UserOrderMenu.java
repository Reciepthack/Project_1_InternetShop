package view.user;

import service.OrderService;
import service.OrderServiceImpl;
import view.Menu;

import java.util.Scanner;

public class UserOrderMenu implements Menu {

    private static final UserMainMenu userMainMenu = UserMainMenu.getInstance();
    private static final UserOrderMenu userOrderMenu = new UserOrderMenu();
    private static final String[] ORDER_MAIN_MENU = {"1. showMyOrders", "0. PreviousMenu"};
    private final Scanner scanner = new Scanner(System.in);
    private final OrderService orderService = OrderServiceImpl.getInstance();

    public static UserOrderMenu getInstance() {
        return userOrderMenu;
    }

    @Override
    public void show() {
        showItems(ORDER_MAIN_MENU);
        switch (scanner.nextInt()) {
            case 1:
                showOrders();
                break;
            default:
                incorrectInput();
            case 0:
                exit();
        }
    }

    private void showOrders() {
        orderService.findAll().stream()
                .forEach(order -> System.out.println("Order :" + order));
    }

    @Override
    public void exit() {
        userMainMenu.show();
    }

}