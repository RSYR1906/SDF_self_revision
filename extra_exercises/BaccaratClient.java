package extra_exercises;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class BaccaratClient {

    public static void main(String[] args) throws UnknownHostException, IOException {

        // java-cp classes sg.edu.nus.iss.baccarat.client.ClientApp localhost:12345

        String[] hostNport = args[0].split(":");
        String host = hostNport[0];
        int portNumber = Integer.parseInt(hostNport[1]);
        boolean isLogin = false;
        boolean placeBet = false;

        Socket socket = new Socket(host, portNumber);
        socket.setKeepAlive(true);

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        while (true) {

            if (isLogin == false) {
                System.out.println(
                        "Welcome to the game of Baccarat! \n Example of commands \n [Login Kenneth 100] \n [Bet 50] \n [Deal B or Deal P]");
                System.out.println("Please enter your login details");
            } else if (placeBet == true) {
                System.out.println("Deal player or banker? ");
            } else {
                System.out.println("Would you like to bet or logout?");
            }

            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();
            String[] commandList = input.split(" ");

            if (commandList.length < 2) {
                System.out.println("Invalid command format.");
                continue;
            }

            String command = commandList[0].toLowerCase();
            switch (command) {
                case "login":
                    if (commandList.length == 3) {
                        dos.writeUTF("login" + "|" + commandList[1] + "|" + commandList[2]);
                        dos.flush();
                        System.out.println("Login Successful");
                        isLogin = true;
                    } else {
                        System.out.println("Invalid login command. Correct format: Login <username> <capital>");
                    }
                    break;

                case "bet":
                    if (commandList.length == 2) {
                        dos.writeUTF("bet" + "|" + commandList[1]);
                        dos.flush();
                        placeBet = true;
                    } else {
                        System.out.println("Invalid bet command. Correct format: Bet <amount>");
                    }
                    break;

                case "deal":
                    if (commandList.length == 2
                            && (commandList[1].equalsIgnoreCase("P") || commandList[1].equalsIgnoreCase("B"))) {
                        dos.writeUTF("deal" + "|" + commandList[1].toUpperCase());
                        dos.flush();
                    } else {
                        System.out.println("Invalid deal command. Correct format: Deal <P/B>");
                    }
                    break;

                case "logout":
                    dos.writeUTF("LOGOUT");
                    dos.flush();
                    socket.close();
                    return;

                default:
                    System.out.println("Invalid command.");
            }
        }
    }

}
