import java.io.*;

public class FileReaderMain {

    public static void main(String[] args) throws IOException {

        // String pathName = "/Users/ryan/Documents/VISA VTTP/SDF/SDF_week2_self_revision/files/austen.txt";

        // FileReader fr = new FileReader(pathName);
        // BufferedReader br = new BufferedReader(fr);
        // while (br.readLine() != null) {
        //     String sentence = br.readLine();
        //     System.out.println(sentence.trim());
        // }
        // fr.close();

        File file = new File("/Users/ryan/Documents/VISA VTTP/SDF/SDF_week2_self_revision/files");

        if (file.exists()){
            System.out.println("This file/directory exists.");
        }else{
            System.out.println("There is no such file/directory.");
        }
        for (String name: file.list()){
            System.out.printf("filename: %s \n" , name);
        }

    }
}
