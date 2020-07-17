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

        scanner = new Scanner(System.in);
    }

    @Override
    public void exit() {

    }
}
