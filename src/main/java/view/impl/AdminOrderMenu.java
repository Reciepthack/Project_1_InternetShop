package view.impl;

import model.Order;
import model.User;
import service.OrderService;
import service.OrderServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AdminOrderMenu implements Menu {
    private static final String RETURN = "0. Return to main menu";
    private static final String TRY_AGAIN = "Incorrect input. Please, try again";
    private static final String [] SAVE_UPDATE_DELETE_MENU = {"1. Save", "2. Update", "3. Delete", RETURN};
    private OrderService orderServise = OrderServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private List<Order> orderList = null;
    private User user;
    LoginMenu loginMenu = new LoginMenu(); //change to adminMenu when create
    private final String[] mainAdminOrderMenu = {"1. Find order by ID",
            "2. Find order by User", "3. Find all", " 0. Exit"};

    @Override
    public void show() {
        int choice = 0;
        showItems(mainAdminOrderMenu);
        while (true) {
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println(TRY_AGAIN);
            }
            switch (choice) {
                case 1:
                    findOrderById();
                    break;
                case 2:
                    findOrderByUserMenu();
                    break;
                case 3:
                    break;
                case 0:
                    exit();
                    break;
            }
        }
    }

    @Override
    public void exit() {
        loginMenu.show(); //change to adminMenu.show() when create;
    }

    private void findOrderByUserMenu() {
        String[] subMenuByName = {"Please, enter this User", RETURN};
        showItems(subMenuByName);
        String userName = null;
        try {
            userName = reader.readLine();
        } catch (NumberFormatException | NullPointerException | IOException e) {
            System.out.println(TRY_AGAIN);
        }
        if (userName.equals("0")) {
            show();
        } else {
            user = userService.findByName(userName);
            orderList = orderServise.findOrdersByUser(user);
            showItems(SAVE_UPDATE_DELETE_MENU);
        }
        saveUpdateDelete();
    }

    private void findOrderById() {
        String[] subMenuByID = {"Please, enter ID number", RETURN};

        showItems(subMenuByID);
        String userId;
        int userIntId;
        try {
            userId = reader.readLine();
            userIntId = Integer.parseInt(userId);
            if (userId.equals("0")) {
                showItems(mainAdminOrderMenu);
            } else {
                orderServise.findById(userIntId);
                showItems(SAVE_UPDATE_DELETE_MENU);
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println(TRY_AGAIN);
        }
        saveUpdateDelete();
    }

    private void findAll() {

    }

    private void showAndDelete(List<Order> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ": " + list.get(i));
        }
        System.out.println("What must be remove? Write the number");
        try {
            int remuveNumber = Integer.parseInt(reader.readLine());
            orderServise.delete(list.get(remuveNumber - 1));
        } catch (IOException | NumberFormatException e) {
            System.out.println("Invalid input");
        }


    }

    private void createNewOrder() {
        //create some logic
    }

    private void updateSomething() {
        //create some logic
    }

    private void saveUpdateDelete() {
        while (true) {
            int choice = 0;
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println(TRY_AGAIN);
            }

            switch (choice) {
                case 1:
                    createNewOrder();
                    break;
                case 2:
                    updateSomething();
                    break;
                case 3:
                    showAndDelete(orderList);
                    break;
                case 0:
                    show();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new AdminOrderMenu().show();
    }
}
