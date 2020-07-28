package view.impl;

import view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminMainMenu implements Menu {
    private static final AdminMainMenu adminMainMenu = new AdminMainMenu();
    private static final AdminOrderMenu adminOrderMenu = AdminOrderMenu.getInstance();
  //  private static final AdminProductsMenu adminProductsMenu = AdminProductsMenu.getInstance();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final String TRY_AGAIN = "Incorrect input. Please, try again";
    private final String [] adminMainMenuList = {"1. Admin products menu", "2. Admin user menu",
                                                "3. Admin order menu", "0. Exit"};
    private LoginMenu loginMenu = new LoginMenu();

    public static AdminMainMenu getInstance() {
        return adminMainMenu;
    }

    @Override
    public void show() {
        int choice = 0;
        showItems(adminMainMenuList);
        while (true) {
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println(TRY_AGAIN);
                show();
            }
            switch (choice) {
                case 1:
                  //  adminProductsMenu.show();
                    break;
                case 2:
                    //link to users menu
                    break;
                case 3:
                    adminOrderMenu.show();
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

    public static void main(String[] args) {
        new AdminMainMenu().show();
    }
}