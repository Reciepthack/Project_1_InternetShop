package view.impl;

import model.Product;
import service.ProductService;
import service.ProductServiceImpl;
import view.Menu;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AdminProductsMenu implements Menu {
    public static void main(String[] args) {
        new AdminProductsMenu().show();
    }

    private static final String Return = "0 Return to main menu";
    private static final String Incorrect = "Please try again";
    private static final String[] Changes = {"Delete", "Update", "Save", Return};
    private Scanner scanner = new Scanner(System.in);
    private ProductService productService = ProductServiceImpl.getInstance();
    private List<Product> productList;
    LoginMenu loginMenu = new LoginMenu();
    private int currentProductId;

    private final String[] mainAdminProductMenu = {"1. Find Product by Number",
            "2. Find Product by Name", "3. Show all Products", "0. Exit"};

    @Override
    public void show() {

        int choice = 0;
        showItems(mainAdminProductMenu);
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Incorrect);
                show();
            }
            switch (choice) {
                case 1:
                    findProductByNumber();
                    break;
                case 2:
                    findProductByName();
                    break;
                case 3:
                    showAllProducts();
                    break;
                case 0:
                    exit();
                    break;
            }
        }
    }

    @Override
    public void exit() {
        new AdminMainMenu().show();
    }

    private void addNewProduct() {
        long id = 0;
        String name;
        float price;
        int amount;
        productList = productService.findAll();
        try {
            System.out.println("Please, Enter Name of new product");
            name = scanner.nextLine();
            System.out.println("Please, Enter price");
            price = scanner.nextFloat();
            System.out.println("Please, Enter amount");
            amount = scanner.nextInt();
            Product product = new Product(id, name, price, amount);
            productService.save(product);
            showAllProducts();
        } catch (NumberFormatException | NullPointerException exception) {
            System.out.println(Incorrect);
        }
    }

    private void updateProduct() {

    }

    private void deleteProduct() {
        productList = productService.findAll();
        System.out.println("Enter product NUMBER, what you want delete");
        try {
            currentProductId = Integer.parseInt(scanner.nextLine());
            if (currentProductId == 0) {
                show();
            } else {
                productService.delete(productList.get(currentProductId - 1));
                System.out.println("This product deleted");

            }
        } catch (NumberFormatException e) {
            System.out.println(Incorrect);
        }
    }

    private void showAllProducts() {
        productList = productService.findAll();
        for (int i = 0; i < productList.size(); i++) {
            System.out.println("Number: " + (i + 1) + " Product: " + productList.get(i));
        }
        showItems(Changes);
        subMenuDeleteUpdateSave();
    }

    private void subMenuDeleteUpdateSave() {
        int choice = 0;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Incorrect);
            }
            switch (choice) {
                case 1:
                    deleteProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    addNewProduct();
                    break;
                case 0:
                    show();
                    break;
            }
        }
    }

    private void findProductByName() {
        String[] subMenuByName = {"Enter Name of Product", Return};
        showItems(subMenuByName);
        String productStringName = scanner.nextLine();
        try {
            if (productStringName.equals("0")) {
                showItems(mainAdminProductMenu);
            } else {
                System.out.println(productService.findByName(productStringName));
                showItems(Changes);
                subMenuDeleteUpdateSave();
            }

        } catch (NullPointerException | NumberFormatException exception) {
            System.out.println(Incorrect);
        }
    }

    private void findProductByNumber() {
        String[] subMenuById = {"Enter ID of Product", Return};
        showItems(subMenuById);
        String productId = scanner.nextLine();
        int productIntId = Integer.parseInt(productId);
        try {
            if (productId.equals("0")) {
                showItems(mainAdminProductMenu);
            } else {
                System.out.println(productService.findById(productIntId));
                showItems(Changes);
                subMenuDeleteUpdateSave();
            }
        } catch (NullPointerException e) {
            System.out.println(Incorrect);
        }
    }
}
