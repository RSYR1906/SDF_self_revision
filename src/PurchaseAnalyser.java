import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class PurchaseAnalyzer {
    public static void main(String[] args) {
        String filePath = "purchases.csv";
        
        Map<Integer, List<Double>> customerSpending = new HashMap<>();
        Map<String, Integer> productSales = new HashMap<>();
        Map<String, Double> monthlyRevenue = new HashMap<>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Read and parse the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                // Extract data
                int customerId = Integer.parseInt(values[1]);
                String productName = values[3];
                int quantity = Integer.parseInt(values[4]);
                double pricePerUnit = Double.parseDouble(values[5]);
                LocalDate transactionDate = LocalDate.parse(values[6], formatter);
                
                // Calculate spending for this transaction
                double transactionAmount = quantity * pricePerUnit;
                
                // Track spending by customer
                customerSpending.computeIfAbsent(customerId, k -> new ArrayList<>()).add(transactionAmount);
                
                // Track quantity sold per product
                productSales.put(productName, productSales.getOrDefault(productName, 0) + quantity);
                
                // Track revenue by month
                String month = transactionDate.getMonth().name();
                monthlyRevenue.put(month, monthlyRevenue.getOrDefault(month, 0.0) + transactionAmount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1. Calculate total and average spending per customer
        System.out.println("Customer Summary:");
        for (Map.Entry<Integer, List<Double>> entry : customerSpending.entrySet()) {
            int customerId = entry.getKey();
            List<Double> transactions = entry.getValue();
            double totalSpending = transactions.stream().mapToDouble(Double::doubleValue).sum();
            double averageSpending = totalSpending / transactions.size();
            System.out.printf("Customer %d: Total Spending = $%.2f | Average Spending = $%.2f%n",
                              customerId, totalSpending, averageSpending);
        }
        System.out.println();

        // 2. Identify the most popular product by quantity sold
        String mostPopularProduct = productSales.entrySet().stream()
                                                .max(Map.Entry.comparingByValue())
                                                .map(Map.Entry::getKey)
                                                .orElse("No data");
        int mostPopularQuantity = productSales.getOrDefault(mostPopularProduct, 0);
        System.out.printf("Product Summary:\nMost Popular Product: %s (Quantity Sold: %d)%n%n",
                          mostPopularProduct, mostPopularQuantity);

        // 3. Find the month with the highest total revenue
        String highestRevenueMonth = monthlyRevenue.entrySet().stream()
                                                   .max(Map.Entry.comparingByValue())
                                                   .map(Map.Entry::getKey)
                                                   .orElse("No data");
        double highestRevenue = monthlyRevenue.getOrDefault(highestRevenueMonth, 0.0);
        System.out.printf("Monthly Sales Summary:\nMonth with Highest Sales: %s | Total Revenue = $%.2f%n",
                          highestRevenueMonth, highestRevenue);
    }
}
