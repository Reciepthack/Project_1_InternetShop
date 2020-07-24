package view.impl;

import model.Product;
import service.ProductService;
import service.ProductServiceImpl;
import view.Menu;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AdminProductsMenu implements Menu {
    private static final AdminProductsMenu adminProductsMenu = new AdminProductsMenu();
    private static final AdminMainMenu adminMainMenu = AdminMainMenu.getInstance();
    private static final String Return = "0 Return to main menu";
    private static final String Incorrect = "Please try again";
    private static final String[] Changes = {"Delete", "Update", "Save", Return};
    private Scanner scanner = new Scanner(System.in);
    private ProductService productService = ProductServiceImpl.getInstance();
    private List<Product> productList;
    private int currentProductId;

    private final String[] mainAdminProductMenu = {"1. Find Product by Number",
            "2. Find Product by Name", "3. Show all Products", "0. Exit"};

    public static AdminProductsMenu getInstance(){
        return adminProductsMenu;
    }

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
                    findProductById();
                    break;
                case 2:

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
        adminMainMenu.show();
    }

    private void addNewProduct() {
        long id = 0;
        String name;
        float price;
        int amount;
        productList = productService.findAll();
        try {
            System.out.println("Please, Enter new product");
            name = scanner.nextLine();
            System.out.println("Please, Enter price");
            price = scanner.nextFloat();
            System.out.println("Please, Enter amount");
            amount = scanner.nextInt();
            Product product = new Product(id, name, price, amount);
            productService.save(product);
            showAllProducts();
        } catch (NumberFormatException | NullPointerException exception) {
            addNewProduct();
            
        }
    }

    private void updateProduct() {

    }

    private void deleteProduct() {
        productList = productService.findAll();
        System.out.println("Enter product ID, what you want delete");
        try {
            currentProductId = Integer.parseInt(scanner.nextLine());
            if (currentProductId == 0) {
                show();
            } else {
                productService.delete(productList.get(currentProductId - 1));
                showAllProducts();
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

    private void findProductById() {
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
