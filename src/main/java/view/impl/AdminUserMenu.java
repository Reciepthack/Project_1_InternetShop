package view.impl;

import service.UserService;
import view.Menu;

import java.util.Scanner;

public class AdminUserMenu implements Menu {

    private UserService userService;
    private String[] items = {"1.List users", "2.Block user"};
    private Scanner scanner;

    @Override
    public void show() {
        showItems(items);
        System.out.println("0. Exit");
        userService.findAll();
        userService.blockUser(1);
        userService.unBlockUser(1);
        scanner = new Scanner(System.in);
    }

    @Override
    public void exit() {

    }
}
