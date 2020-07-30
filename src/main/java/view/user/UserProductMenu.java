package view.user;

import model.Order;
import model.OrderStatus;
import model.Product;
import service.*;
import view.Menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserProductMenu implements Menu {
    private final Scanner scanner = new Scanner(System.in);
    private UserService userService = UserServiceImpl.getInstance();
    private static final UserProductMenu userProductMenu = new UserProductMenu();
    private static final UserMainMenu userMainMenu = UserMainMenu.getInstance();
    private final ProductService productService = ProductServiceImpl.getInstance();
    private final OrderService orderService = OrderServiceImpl.getInstance();
    private static final String TRY_AGAIN = "We dont have such product. Please, try again";
    private final String[] items = {"1.ShowAllProducts", "2.SearchingProduct",
            "3.addProduct", "4.checkout Order", "0. PreviousMenu"};
    private List<Product> productList = null;


    public static UserProductMenu getInstance() {
        return userProductMenu;
    }

    @Override
    public void show() {
        showItems(items);
        switch (scanner.nextInt()) {
            case 1:
                showAllProduct();
                break;
            case 2:
                searchingProduct();
                break;
            case 3:
                makeOrder();
                break;
            case 4:
                checkoutOrder();
            case 0:
                exit();
                break;
            default:
                incorrectInput();
        }
    }

    private void checkoutOrder() {
        int orderId = scanner.nextInt();
        Order order;
        System.out.println("Enter id of order you want to check");
        order = orderService.findById(orderId);
        System.out.println("You order is: " + order.getOrderStatus());
    }

    public void showAllProduct() {
        int products = 5;
        productService.findAll().stream().limit(products).
                forEach(product -> System.out.println("Product: " + product));
        show();
    }

    private void searchingProduct() {
        System.out.println("Enter name of product you want to find:");
        String name = scanner.next();
        productList = productService.findByName(name);
        if (productList.size() < 1) {
            System.out.println(TRY_AGAIN);
            show();
        } else {
            System.out.println("Position: " + " Product: " + productList);
        }
    }

    public void makeOrder() {
        System.out.println("Enter id of product you want to add in basket:");
        long id = scanner.nextLong();
        int amount;
        try {
            Product product = productService.findById(id);
            if (product == null) {
                throw new NullPointerException();
            }
            System.out.println(product);
            System.out.println("Please, Enter amount");
            amount = scanner.nextInt();
            Order order = new Order(id, userService.getActiveUser(), OrderStatus.PRE_CHECKOUT,
                    Map.of(productService.findById(id), amount));
            orderService.save(order);
            System.out.println("You have add product in order");
        } catch (NullPointerException exception) {
            System.out.println(TRY_AGAIN);
            makeOrder();
        } catch (InputMismatchException exception) {
            System.out.println("Wrong format, please enter id again");
        }
        show();
    }

    @Override
    public void exit() {
        userMainMenu.show();
    }
}
