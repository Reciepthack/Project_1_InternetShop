package view.impl;

import model.User;
import model.UserRole;
import service.UserService;
import view.Menu;

import java.util.List;
import java.util.Scanner;

public class LoginMenu implements Menu {

    private UserService userService;
    private String[] items = {"1.Login", "2.Register"};
    private Scanner scanner;

    @Override
    public void show() {
        showItems(items);
        System.out.println("0. Exit");

        scanner = new Scanner(System.in);


        while(true)
        {
          int choice =  scanner.nextInt();

          switch (choice)
          {
              case 1 :
                  loginSubMenu(scanner); break;
              case 2 :
                  registerSubMenu(scanner); break;
              case 0 : exit(); break;
          }
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    private void loginSubMenu(Scanner scanner)
    {
        System.out.println("input login:");
        String login =  scanner.nextLine();

        System.out.println("input password:");
        String password =  scanner.nextLine();

        if(userService.login(login, password)) {
            User users = userService.findByName(login);
                if (UserRole.ADMIN.equals(users.getRole())) {
                    AdminMainMenu.show();
                } else if (users.getRole().equals(UserRole.CUSTOMER){
                    UserMainMenu.show();
                }
    }
        else {
            System.out.println("Wrong username/pasword");
            show();
        }
    }

    private void registerSubMenu(Scanner scanner)
    {
        System.out.println("Create a username:");
        String username = scanner.nextLine();


        System.out.println("Create a password:");
        String password = scanner.nextLine();

        System.out.println("Check your new Login and Password");
        System.out.println("Login "+username);
        System.out.println("Password "+password);

        userService.register(username, password);
    }
}