package workshopqns;

import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {

    ArrayList<String> cart = new ArrayList<>();

    public void listItems() {

        if (cart.isEmpty()) {
            System.out.println("Cart is empty");
        }
        for (int i = 0; i < cart.size(); i++) {
            System.out.println((i + 1) + ". " + cart.get(i));
        }
    }

    public void addItems(String itemsAdded) {
        String[] itemList = itemsAdded.split(","); // for inputs: banana,apple,pear

        for (String items : itemList) { // iterating thru the itemList to find duplicates
            if (cart.contains(items.trim().toLowerCase())) { // remove whitespaces and convert all to lowercase
                System.out.println("Item already in cart");
            } else {
                cart.add(items);
                System.out.println(items + " added to cart");
            }
        }
    }

    public void deleteItems(int index) {
        if (index > 0 && index <= cart.size()) {
            System.out.println("Removing item: " + cart.remove(index - 1));
        } else {
            System.out.println("Index out of range");
        }
    }

    public static void main(String[] args) {

        ShoppingCart sc = new ShoppingCart();
        Scanner scan = new Scanner(System.in);
        String command;

        System.out.println("Welcome to your shopping cart");

        while (true) {
            System.out.print("Enter your command: (list, add, delete, quit) ");
            command = scan.nextLine().trim().toLowerCase();

            switch (command) {

                case "list":
                    System.out.println("These are the items in your cart");
                    sc.listItems();
                    break;

                case "add":
                    System.out.println("Enter the name of the item to add");
                    String itemsAdded = scan.nextLine();
                    sc.addItems(itemsAdded);
                    break;

                case "delete":
                    System.out.println("Enter the index of the item to delete");
                    int index = Integer.parseInt(scan.nextLine()); // converting string input into integer type
                    sc.deleteItems(index);
                    break;

                case "quit":
                    System.out.println("Goodbye!");
                    return; // exit program

                default:
                    System.out.println("Invalid input! Please type in one of the commands");
            }
            scan.close();
        }
    }

}
