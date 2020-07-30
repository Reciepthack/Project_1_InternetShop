package view;

public interface Menu {
    void show();

    void exit();

    default void incorrectInput() {
        System.out.println("Incorrect input please try again");
        show();
    }

    default void showItems(String[] items) {
        for (String item : items) {
            System.out.println("-------------");
            System.out.println(item);
        }
    }
}
