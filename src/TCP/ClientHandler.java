package TCP;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
class ClientHandler extends Thread {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String clientInput = reader.readLine();
            System.out.println("Received from client: " + clientInput);

            if ("time".equalsIgnoreCase(clientInput)) {
                // If input is "time", send current date and time to the client
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = dateFormat.format(new Date());
                writer.println("Server Response: Current Date and Time - " + currentTime);
            } else if ("testfile.txt".equalsIgnoreCase(clientInput)) {
                // If input is "testfile.txt", check if the file exists
                File file = new File("testfile.txt");
                if (file.exists()) {
                    try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = fileReader.readLine()) != null) {
                            writer.println(line);
                        }
                    }
                } else {
                    writer.println("Server Response: File does not exist");
                }
            } else {
                writer.println("Server Response: Invalid input. Discarding...");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
