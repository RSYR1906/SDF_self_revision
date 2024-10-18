package workshopqns;

import java.io.IOException;
import java.util.*;

public class ShoppingCart {

    private List<String> cart;
    private ShoppingCartDB shoppingCartDB;
    private String currentUser;

    public ShoppingCart(String cartDirectory) {
        this.cart = new ArrayList<>();
        this.shoppingCartDB = new ShoppingCartDB(cartDirectory);
        this.currentUser = null;
    }

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

    public void login(String username) {
        try {
            cart = shoppingCartDB.loadCart(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentUser = username;
        System.out.println("Logged in as " + username);
    }

    public void save() {
        if (currentUser == null) {
            System.out.println("Please login before saving.");
        } else {
            try {
                shoppingCartDB.saveCart(currentUser, cart);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void users() {
        List<String> users = shoppingCartDB.listUsers();

        if (users.isEmpty()) {
            System.out.println("No users found");
        } else {
            for (String user : users) {
                System.out.println(user);
            }
        }

    }

    public void start() throws IOException {
        Scanner scan = new Scanner(System.in);
        String command;

        System.out.println("Welcome to your shopping cart");

        while (true) {
            System.out.print("Enter your command: (login, save, users, list, add, delete, quit) ");
            command = scan.nextLine().trim().toLowerCase();

            switch (command) {

                case "login":
                    System.out.println("Enter username: ");
                    String username = scan.nextLine();
                    login(username);
                    break;

                case "save":
                    System.out.println("Saving to cart");
                    save();
                    break;

                case "users":
                    System.out.println("These are the registered users: ");
                    users();
                    break;

                case "list":
                    System.out.println("These are the items in your cart");
                    listItems();
                    break;

                case "add":
                    System.out.println("Enter the name of the item to add");
                    String itemsAdded = scan.nextLine();
                    addItems(itemsAdded);
                    break;

                case "delete":
                    System.out.println("Enter the index of the item to delete");
                    int index = Integer.parseInt(scan.nextLine()); // converting string input into integer type
                    deleteItems(index);
                    break;

                case "quit":
                    System.out.println("Goodbye!");
                    scan.close();
                    return; // exit program

                default:
                    System.out.println("Invalid input! Please type in one of the commands");
            }
        }
    }
}
