package extra_exercises;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.net.*;

public class BaccaratServer {

    public static void main(String[] args) throws IOException {

        // java -cp classes sg.edu.nus.iss.baccarat.server.ServerApp 12345 4
        // take in two inputs (portnumber and number of deck of cards)

        int portNumber = Integer.parseInt(args[0]);
        int numberOfDecks = Integer.parseInt(args[1]);

        ServerSocket server = new ServerSocket(portNumber);

        System.out.println("Waiting for connection with client");

        while (true) {
            Socket conn = server.accept();
            System.out.println("Connected!");

            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            int betAmount = 0;

            while (true) {
                String message = dis.readUTF();
                System.out.println(message);

                if (message.equals("LOGOUT")) {
                    System.out.println("Client disconnected.");
                    conn.close();
                }

                String[] clientInput = message.split("\\|");

                if (clientInput[0].equals("login")) {
                    File file = new File("db/" + clientInput[1] + ".db");

                    if (file.exists()) {
                        System.out.println("User already exists.");
                    } else {
                        BufferedWriter bw1 = new BufferedWriter(new FileWriter(file));
                        bw1.write(clientInput[2]);
                        bw1.flush();
                        bw1.close();

                        System.out.println(
                                "Created Filename: " + clientInput[1] + ".db" + "with data: " + clientInput[2]);
                    }
                }
                if (clientInput[0].equals("bet")) {
                    betAmount = Integer.parseInt(clientInput[1]);
                }

                if (clientInput[0].equals("deal")) {
                    // Read the "cards.db" file to load the shuffled deck of cards
                    ArrayList<Double> cardDeck = BaccaratEngine.readCardsFromFile("cards.db");

                    // Draw cards for player and banker
                    List<Double> playerCards = BaccaratEngine.drawCards(cardDeck, 2); // Draw 2 cards for Player
                    List<Double> bankerCards = BaccaratEngine.drawCards(cardDeck, 2); // Draw 2 cards for Banker

                    // Calculate totals for player and banker
                    int playerTotal = BaccaratEngine.calculateCardTotal(playerCards);
                    int bankerTotal = BaccaratEngine.calculateCardTotal(bankerCards);

                    // Determine the result
                    String result = BaccaratEngine.determineWinner(playerTotal, bankerTotal);
                    BaccaratEngine.saveResult(result);

                    // Print the cards and totals
                    System.out.println("Player's cards: " + playerCards);
                    System.out.println("Banker's cards: " + bankerCards);
                    System.out.println("Player's total: " + playerTotal);
                    System.out.println("Banker's total: " + bankerTotal);
                    System.out.println("Result: " + result);

                    // Handle bet
                    String betSide = clientInput[1]; // "P" for Player or "B" for Banker

                    if (betSide.equals("P") && result.equals("P")) {
                        System.out.println("You win: $" + betAmount);
                    } else if (betSide.equals("B") && result.equals("B")) {
                        System.out.println("You win: $" + betAmount);
                    } else {
                        System.out.println("You lose: $" + betAmount);
                    }
                }

            }
        }

    }
}