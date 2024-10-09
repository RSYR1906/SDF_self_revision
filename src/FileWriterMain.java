import java.io.*;

public class FileWriterMain {
    
    public static void main(String[] args) throws IOException {
        
        String fileName = "test.txt";

        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        fw.write("This is a line written by filewriter");
        bw.newLine();
        bw.write("This is a line written by bufferedwriter");

        fw.flush();
        bw.flush();
        bw.close();
        fw.close();
    }
}
