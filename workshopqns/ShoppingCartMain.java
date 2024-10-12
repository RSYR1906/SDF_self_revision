package workshopqns;

import java.io.IOException;
import java.util.Scanner;

public class ShoppingCartMain {

    public static void main(String[] args) throws IOException {

        String cartDirectory;

        if (args.length > 0) {
            cartDirectory = args[0];
        } else {
            cartDirectory = "db";
        }
        
        ShoppingCart sc = new ShoppingCart(cartDirectory);
        sc.start();
    }
}
