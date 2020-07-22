package view.impl;

import dao.ProductDaoImpl;
import model.Product;
import service.ProductService;
import service.ProductServiceImpl;
import view.Menu;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdminProductsMenu implements Menu {
    private static final String Return = "0 Return to main menu";
    private static final String Incorrect = "Please try again";
    private static final String[] Changes = {"Delete", "Update", "Save", Return};
    private Scanner scanner = new Scanner(System.in);
    private ProductService productService = ProductServiceImpl.getInstance();
    LoginMenu loginMenu = new LoginMenu();

    private final String[] mainAdminProductMenu = {"1. Show all Products",
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
                    showAllProducts(ProductDaoImpl);
                    break;
                case 2:
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

    private void addNewProduct() {
    }

    private void updateProduct() {
    }

    private void saveProduct(){
    }

    private void deleteProduct(){
    }

    private void showAllProducts(Map<Long,Product> productMap){
        for (Map.Entry entry: productMap.entrySet()) {
            System.out.println("ID: " + entry.getKey() + "Product: " + entry.getValue());
        }
        System.out.println(Changes);
        subMenuDeleteUpdateSave();

    }

    private void subMenuDeleteUpdateSave(){
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
                    saveProduct();
                    break;
                case 0:
                    exit();
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
                productService.findById(productIntId);
            }
        } catch (NullPointerException e) {
            System.out.println(Incorrect);
        }
    }
}
