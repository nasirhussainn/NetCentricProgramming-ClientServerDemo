package RMI;

import java.rmi.Naming;
import java.util.Scanner;

public class RMIClient {
    public static void main(String[] args) {
        try {
            MessageProcessor server = (MessageProcessor) Naming.lookup("rmi://10.141.210.186:8888/messageprocessor");
            Scanner scanner = new Scanner(System.in);


                System.out.print("Enter 'time' or 'testfile.txt': ");
                String input = scanner.nextLine();

                // Send user input to the server and receive response
                String response = server.processMessage(input);

                // Display server response
                System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
