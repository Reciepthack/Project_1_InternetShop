package view.impl;

import model.Product;
import service.ProductService;
import service.ProductServiceImpl;
import view.Menu;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Scanner;

public class AdminProductsMenu implements Menu {
    private static final String Return = "0 Return to main menu";
    private static final String Incorrect = "Please try again";
    private Scanner scanner = new Scanner(System.in);
    ProductService productService = ProductServiceImpl.getInstance();
    LoginMenu loginMenu = new LoginMenu();

    private final String[] mainAdminProductMenu = {"1. Update Product",
            "2. Add Product", "0. Exit"};

    @Override
    public void show() {
        int choice = 0;
        showItems(mainAdminProductMenu);
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Incorrect);
            }
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    exit();
                    break;
            }
        }
    }

    @Override
    public void exit() {
        loginMenu.show();
    }

    private void addNewProduct() {

    }

    private void findProductById() {
        String[] subMenuById = {"Enter ID of Product", Return};
        showItems(subMenuById);
        String productId = scanner.nextLine();
        int productIntId = Integer.parseInt(productId);
        try {
            if (productId.equals("0")) {
                showItems(mainAdminProductMenu);
            } else {
                productService.findById(productIntId);
            }
        } catch (NullPointerException e) {
            System.out.println(Incorrect);
        }
    }
}
