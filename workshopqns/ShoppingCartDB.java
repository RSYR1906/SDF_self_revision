package workshopqns;

import java.util.*;
import java.io.*;

public class ShoppingCartDB {

    private File cartDirectory;
    private File userFile;

    public ShoppingCartDB(String cartDirectoryPath) {
        this.cartDirectory = new File(cartDirectoryPath);

        if (!(this.cartDirectory.exists())) { // check if cart directory exists, if it doesn't exist then create a new
                                              // directory
            this.cartDirectory.mkdir();
        }
    }

    public List<String> loadCart(String username) throws IOException { // login method: load a user database file, if no
                                                                       // such user then create the
        // database file for new user (FILE READER)
        userFile = new File(cartDirectory, username + ".db");

        if (!(userFile.exists())) {
            userFile.createNewFile();
        }

        ArrayList<String> cartList = new ArrayList<>();

        FileReader fr = new FileReader(userFile);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            cartList.add(line.trim());
        }
        br.close();
        return cartList;
    }

    public void saveCart(String username, List<String> cartList) throws IOException {

        userFile = new File(cartDirectory, username + ".db");// save method: save the contents in the cart into the user
                                                             // database file. If
        // save without login first, prompt user to login first (FILE WRITER)

        BufferedWriter bw = new BufferedWriter(new FileWriter(userFile));

        for (String item : cartList) {
            bw.write(item);
            bw.newLine();
        }

        System.out.println("Cart saved to " + userFile.getAbsolutePath());
        bw.flush();
        bw.close();
    }

    public List<String> listUsers() { // listUsers method: list all the existing users (iterate the files in the
        // database directory)
        ArrayList<String> users = new ArrayList<>();

        File[] files = cartDirectory.listFiles(); // NEED UNDERSTAND THIS!!!
        if (files != null) {
            for (File file : files) {
                users.add(file.getName().replace(".db", ""));
            }
        }
        return users;
    }

}
