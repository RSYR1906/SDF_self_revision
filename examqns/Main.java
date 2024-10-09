package examqns;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String pathName = "/Users/ryan/Documents/VISA VTTP/SDF/SDF_week2_self_revision/files/googleplaystore.csv";
        String line = "";
        int count = 0;
        HashMap<String, HashMap<String, Double>> Map = new HashMap<>();

        FileReader fr = new FileReader(pathName);
        BufferedReader br = new BufferedReader(fr);

        line = br.readLine(); // Read the first line (header)

        while ((line = br.readLine()) != null) {
            String[] values = line.split(","); // Create an array for the values in the csv file

            String names = values[0]; // First column (names)
            String categories = values[1]; // Second column (categories)
            String ratings = values[2]; // Third column (ratings)
            if (ratings.equalsIgnoreCase("NaN") || names.equalsIgnoreCase("NaN")
                    || categories.equalsIgnoreCase("NaN")) {
                count++;
                continue;
            }

            ratings = ratings.replaceAll("[^0-9.]", "0").trim(); // Keep only digits and decimal point

            // Check if the cleaned rating string is not empty and has at most one decimal
            // point
            if (ratings.isEmpty() || ratings.indexOf('.') != ratings.lastIndexOf('.')) {
                count++; // Increment NaN counter for empty rating or multiple decimal points
                continue; // Skip this row
            }

            Double rating = Double.parseDouble(ratings);
            System.out.println(rating);

            Map.putIfAbsent(categories.toLowerCase(), new HashMap<>());
            Map.get(categories.toLowerCase()).put(names, rating);
        }
        System.out.println("Total number of NaN values removed: " + count);
        fr.close();
        br.close();
    }
}
