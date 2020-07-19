package view.impl;

import view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminMainMenu implements Menu {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final String TRY_AGAIN = "Incorrect input. Please, try again";
    private final String [] adminMainMenu = {"1. Admin products menu", "2. Admin user Menu",
                                                "3. Admin order menu", "0. Exit"};
    private AdminOrderMenu adminOrderMenu = new AdminOrderMenu();
    private LoginMenu loginMenu = new LoginMenu();


    @Override
    public void show() {
        int choice = 0;
        showItems(adminMainMenu);
        while (true) {
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println(TRY_AGAIN);
                show();
            }
            switch (choice) {
                case 1:
                    //link to products menu
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
}
