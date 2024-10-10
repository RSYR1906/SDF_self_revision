import java.util.Scanner;

public class Main {

    public static void main(String[] args) { // entry point

        Scanner scan = new Scanner(System.in);

        System.out.println("What is your name? ");
        String name = scan.next();

        System.out.println("What is your age? ");
        int age = scan.nextInt();

        System.out.println("What is your quote? ");
        String quote = scan.next();
        quote += scan.nextLine();

        System.out.println("\n Details of senior: \n Name: " + name + "\n Age: " + age + "\n quote: " + quote);

        scan.close();
    }
}
