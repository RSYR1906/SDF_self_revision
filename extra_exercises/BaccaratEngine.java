package extra_exercises;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaccaratEngine {

    private ArrayList<Double> cardDeck = new ArrayList<>();

    public BaccaratEngine(int numberOfDecks) {
        generateDeck(numberOfDecks);
        shuffleDeck();
    }

    // Generate deck of cards based on the number of decks
    // 1 is Ace 1.1 is hearts, 1.2 is diamonds, 1.3 is spades, and 1.4 is clubs.
    // 11 is Jack 11.1 is hearts, 11.2 is diamonds, 11.3 is spades, and 11.4 is
    // clubs.
    private void generateDeck(int numberOfDecks) {
        int count = 0;
        while (count != numberOfDecks) {
            for (int i = 1; i < 14; i++) {
                for (double j = 0.1; j < 0.5; j += 0.1) {
                    cardDeck.add(i + j);
                }
            }
            count++;
        }
    }

    // Shuffle the deck
    public void shuffleDeck() {
        Collections.shuffle(cardDeck);
    }

    // Function to read cards from the "cards.db" file
    public static ArrayList<Double> readCardsFromFile(String filename) throws IOException {
        ArrayList<Double> cardDeck = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("cards.db"));

        String line;
        while ((line = br.readLine()) != null) {
            cardDeck.add(Double.parseDouble(line));
        }
        br.close();

        return cardDeck;
    }

    // Function to draw cards from the deck
    public static List<Double> drawCards(ArrayList<Double> cardDeck, int numCards) {
        List<Double> drawnCards = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            drawnCards.add(cardDeck.remove(0)); // Remove the top card from the deck
        }
        return drawnCards;
    }

    // Function to calculate Baccarat card totals
    public static int calculateCardTotal(List<Double> cards) {
        int total = 0;
        for (Double card : cards) {
            int cardValue = (int) Math.floor(card); // Extract the integer part
            if (cardValue >= 10) {
                cardValue = 0; // In Baccarat, 10, Jack, Queen, King are worth 0 points
            }
            total += cardValue;
        }
        return total % 10; // Baccarat totals are mod 10 (0-9 range)
    }

    // Function to determine winner between player and banker
    public static String determineWinner(int playerTotal, int bankerTotal) {
        if (playerTotal > bankerTotal) {
            return "P";
        } else if (bankerTotal > playerTotal) {
            return "B";
        } else {
            return "T";
        }
    }

    private static int resultCount = 0;

    public static void saveResult(String result) throws IOException {
        FileWriter csvWriter = new FileWriter("results.csv", true); // 'true' for appending
        if (resultCount < 6) {
            csvWriter.write(result + ",");
            resultCount++;
        } else {
            csvWriter.write(result + "\n");
            resultCount = 0;
        }
        csvWriter.flush();
        csvWriter.close();
    }
}
