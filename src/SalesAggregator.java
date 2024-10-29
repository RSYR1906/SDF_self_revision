import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class SalesAggregator {
    public static void main(String[] args) {
        String filePath = "sales_data.csv";
        Map<String, List<Double>> salesData = new HashMap<>();

        // Read the file and parse data
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String productName = values[1];
                double salesAmount = Double.parseDouble(values[2]);

                // Store sales amount in the list for each product
                salesData.computeIfAbsent(productName, k -> new ArrayList<>()).add(salesAmount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calculate and display total and average sales
        for (Map.Entry<String, List<Double>> entry : salesData.entrySet()) {
            String productName = entry.getKey();
            List<Double> salesAmounts = entry.getValue();

            double totalSales = salesAmounts.stream().mapToDouble(Double::doubleValue).sum();
            double averageSales = totalSales / salesAmounts.size();

            System.out.printf("Product: %s | Total Sales: %.2f | Average Sales: %.2f%n", 
                              productName, totalSales, averageSales);
        }
    }
}
