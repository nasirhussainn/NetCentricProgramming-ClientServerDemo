package UDP;


import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPClientHandler extends Thread {
    private final DatagramSocket socket;
    private final DatagramPacket receivePacket;

    public UDPClientHandler(DatagramSocket socket, DatagramPacket receivePacket) {
        this.socket = socket;
        this.receivePacket = receivePacket;
    }

    @Override
    public void run() {
        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();
        String clientRequest = new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("Received from client " + clientAddress + ":" + clientPort + ": " + clientRequest);

        // Handle client request and send response to client
        String response;
        if ("time".equalsIgnoreCase(clientRequest)) {
            // If input is "time", send current date and time to the client
            response = "Server Response: Current Date and Time - " + new java.util.Date().toString();
        } else if ("testfile.txt".equalsIgnoreCase(clientRequest)) {
            // If input is "testfile.txt", check if the file exists
            File file = new File("testfile.txt");
            if (file.exists()) {
                try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = fileReader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    response = "Server Response: Content of testfile.txt: \n" + content.toString().trim();
                } catch (IOException e) {
                    response = "Server Response: Error reading file";
                }
            } else {
                response = "Server Response: File does not exist";
            }
        } else {
            response = "Server Response: Invalid input. Discarding...";
        }

        // Send response to client
        byte[] sendData = response.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
        try {
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
