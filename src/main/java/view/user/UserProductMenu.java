package view.user;

import model.Product;
import service.ProductService;
import service.ProductServiceImpl;
import view.Menu;

import java.util.Scanner;

public class UserProductMenu implements Menu {
    private Scanner scanner = new Scanner(System.in);
    public ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public void show() {
        try {
            String userProduct = scanner.nextLine();
            for (Product p : productService.findByName(userProduct)) {
                System.out.println(p);
            }
        } catch (Exception e) {
            System.out.println("Fruit is not found");
        }
        scanner.close();
    }


    @Override
    public void exit() {
        System.exit(0);
    }
}
