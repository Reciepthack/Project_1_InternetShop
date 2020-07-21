package view.impl;


import view.Menu;
import view.user.UserOrderMenu;
import view.user.UserProductMenu;

import java.util.Scanner;

public class UserMainMenu implements Menu {

    private final String[] items = {"1.ProductMenu", "2.OrderMenu", "0. PreviousMenu"};
    private final Scanner scanner = new Scanner(System.in);
    private final UserProductMenu userProductMenu = new UserProductMenu();
    private final UserOrderMenu userOrderMenu = new UserOrderMenu();
    private final LoginMenu loginMenu = new LoginMenu();


    @Override
    public void show() {
        showItems(items);

        while (true) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    userProductMenu.show();
                    break;
                case 2:
                    userOrderMenu.show();
                    break;
                case 0:
                    exit();
                    break;
            }
        }
    }

    @Override
    public void exit() {
        loginMenu.show();
    }
}
