package view.impl;

import model.User;
import service.UserService;
import service.UserServiceImpl;
import view.Menu;
import java.util.List;
import java.util.Scanner;

public class AdminUserMenu implements Menu {

    private String[] items = {"1.Unblock/Block user"};
    private Scanner scanner = new Scanner(System.in);

    private UserService userService = UserServiceImpl.getInstance();


    @Override
    public void show() {
        showItems(items);
        System.out.println("0. Exit");

        String input;
        int num = 0;
        input = scanner.next();
        while (true) {
            try {
                num = Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("Error, please enter");
                show();
            }
            switch (num) {
                case 1:
                    SubBlockUnblockUser();
                    break;
                case 0:
                    exit();
                    break;
            }
        }
    }

    @Override
    public void exit() {

    }

    public void SubBlockUnblockUser() {
        List<User> allUsers = userService.findAll();
        System.out.println("List users");
        for (User u : allUsers) {
            System.out.println(u.getId() + " " + u.getUsername() + " " + u.isActive());
        }
        System.out.println("Enter ID user block/unblock");
        int a = scanner.nextInt();
        User user = userService.findById(a);

        if (user == null) {
            System.out.println("Press re-enter ID:");
        }
        if (user != null && user.isActive() == true) {
            user.setActive(false);
            userService.update(user);
            System.out.println("Block active user");
        } else if (user != null && user.isActive() == false) ;
        user.setActive(true);
        System.out.println("Unblock inactive user");
        userService.update(user);
        switch (a){
            case 1:
                user.getId();
                break;
            case 2:
                user.setActive(true);
                break;
            case 3:
                user.setActive(false);
                break;
            case 0:
                show();
                break;
        }
    }
}

