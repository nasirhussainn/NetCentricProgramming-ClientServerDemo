package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("10.141.210.186", 8888);
             BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.print("Enter 'time' or 'testfile.txt': ");
            String input = userInput.readLine();
            writer.println(input);

            // Receive and display server response
            String response;
            while ((response = serverInput.readLine()) != null) {
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

