package examqns;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static boolean isValidString(String str) {

        if (str.isEmpty() || str == null || str.equals("NaN")) {
            return false;
        }
        try {
            double value = Double.parseDouble(str);
            return !Double.isNaN(value);
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String pathName = "";

        if (args.length > 0) {
            pathName = args[0];
        } else {
            System.out.println("Error: Invalid file.");
            return;
        }

        String line = "";
        int numberOfLines = 0;

        HashMap<String, CategoryInfo> Map = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(pathName));
        line = br.readLine(); // Skip the first line (header)

        String csvPattern = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

        while ((line = br.readLine()) != null) {
            numberOfLines++;
            // Split by commas outside of quoted fields
            String[] row = line.split(csvPattern);

            // Remove surrounding quotes from each field (optional)
            for (int i = 0; i < row.length; i++) {
                row[i] = row[i].replaceAll("^\"|\"$", "").trim();
            }
            String appName = row[0].trim(); // First column (names)
            String category = row[1].trim(); // Second column (categories)
            String ratingStr = row[2].trim(); // Third column (ratings)

            // System.out.println("AppName: " + appName + ", Category: " + category + ",
            // Rating: " + ratingStr); // error
            // checking

            if (isValidString(ratingStr)) {
                double rating = Double.parseDouble(ratingStr);

                // If the category does not exist, create a new CategoryInfo object
                Map.putIfAbsent(category, new CategoryInfo(category));

                // Add the app to the corresponding category's CategoryInfo
                Map.get(category).addApp(appName, rating);
            } else {
                // If the rating is invalid, increment discarded records for the category
                Map.putIfAbsent(category, new CategoryInfo(category));
                Map.get(category).incrementDiscardedRecords();
            }
        }
        // // Print out the entire structure (outer HashMap)
        // System.out.println("Category to AppName-Rating Mapping:");
        // for (String category : categoryMap.keySet()) {
        // System.out.println("Category: " + category);
        // for (HashMap.Entry<String, Double> appEntry :
        // categoryMap.get(category).entrySet()) {
        // System.out.println(
        // "Category: " + category + ", AppName: " + appEntry.getKey() + ", Rating: "
        // + appEntry.getValue());
        // }
        // }

        // Print the report for each category
        for (Map.Entry<String, CategoryInfo> entry : Map.entrySet()) {
            String category = entry.getKey();
            CategoryInfo info = entry.getValue();

            System.out.println("Category: " + category);
            System.out.println("Average Rating: " + info.calculateAverageRating());
            System.out.println(
                    "Highest Rated App: " + info.getNameOfHighestRatedApp() + " (" + info.getHighestRating() + ")");
            System.out.println(
                    "Lowest Rated App: " + info.getNameOfLowestRatedApp() + " (" + info.getLowestRating() + ")");
            System.out.println("Total Apps: " + info.getTotalNumberOfApps());
            System.out.println("Discarded Records: " + info.getNumberOfDiscardedRecords());
            System.out.println();
        }

        System.out.println("Total number of lines read: " + numberOfLines);

        int categorycount = 0;

        for (Map.Entry<String, CategoryInfo> entry : Map.entrySet()) {
            String category = entry.getKey(); // check unique categories
            categorycount += 1;
        }

        System.out.println(categorycount);

        br.close();
    }
}

class CategoryInfo {
    String categoryName;
    private double totalRating;
    private int totalNumberOfApps;
    private int numberOfDiscardedRecords;
    private double highestRating;
    private String nameOfHighestRatedApp;
    private double lowestRating;
    private String nameOfLowestRatedApp;

    // Constructor
    public CategoryInfo(String categoryName) {
        this.categoryName = categoryName;
        this.totalRating = 0.0;
        this.totalNumberOfApps = 0;
        this.highestRating = Double.MIN_VALUE;
        this.lowestRating = Double.MAX_VALUE;
        this.numberOfDiscardedRecords = 0;
        this.nameOfHighestRatedApp = "";
        this.nameOfLowestRatedApp = "";

    }

    public void addApp(String appName, double rating) {
        totalRating += rating;
        totalNumberOfApps++;

        if (rating > highestRating) {
            highestRating = rating;
            nameOfHighestRatedApp = appName;
        }

        if (rating < lowestRating) {
            lowestRating = rating;
            nameOfLowestRatedApp = appName;
        }
    }

    public double calculateAverageRating() {
        return totalNumberOfApps != 0 ? totalRating / totalNumberOfApps : 0.0;
    }

    public void incrementDiscardedRecords() {
        numberOfDiscardedRecords++;
    }

    // Getters for the required fields
    public String getNameOfHighestRatedApp() {
        return nameOfHighestRatedApp;
    }

    public double getHighestRating() {
        return highestRating;
    }

    public String getNameOfLowestRatedApp() {
        return nameOfLowestRatedApp;
    }

    public double getLowestRating() {
        return lowestRating;
    }

    public int getTotalNumberOfApps() {
        return totalNumberOfApps;
    }

    public int getNumberOfDiscardedRecords() {
        return numberOfDiscardedRecords;
    }
}
