package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket();
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Enter 'time' or 'testfile.txt': ");
            String input = userInput.readLine();

            // Send user input to the server
            byte[] sendData = input.getBytes();
            InetAddress serverAddress = InetAddress.getByName("10.141.210.186");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 8888);
            socket.send(sendPacket);

            // Receive and display server response
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
