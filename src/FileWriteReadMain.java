import java.io.*;
import java.util.*;

public class FileWriteReadMain {

    public static void main(String[] args) throws IOException {

        String fileName = "/Users/ryan/Documents/VISA VTTP/SDF/SDF_week2_self_revision/files/testing.txt";

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true)); // writing a new text file that adds new
                                                                                // information after each input

        Scanner scan = new Scanner(System.in);
        System.out.printf("Name of freshmen: ");
        String name = scan.nextLine();

        System.out.printf("Major: ");
        String major = scan.nextLine();

        System.out.printf("Club: ");
        String club = scan.nextLine();

        System.out.printf("cGPA: ");
        String cGPA = scan.nextLine();

        scan.close();

        bw.write("\n Details of freshmen \n");
        bw.write("==================== \n");
        bw.write("Name: " + name + "\n");
        bw.write("Major: " + major + "\n");
        bw.write("Club: " + club + "\n");
        bw.write("cGPA: " + cGPA + "\n");

        bw.close();

        BufferedReader br = new BufferedReader(new FileReader(fileName)); // reading the text file
        LineNumberReader lnr = new LineNumberReader(br); // layering with LineNumberReader which let us know the line
                                                         // number

        String line;
        while ((line = lnr.readLine()) != null) {
            System.out.println(lnr.getLineNumber() + ". " + line);  //printing out the line number and the line
        }

        lnr.close();

    }

}
