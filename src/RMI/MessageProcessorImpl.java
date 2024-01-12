package RMI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageProcessorImpl extends UnicastRemoteObject implements MessageProcessor {
    public MessageProcessorImpl() throws RemoteException {
    }

    @Override
    public String processMessage(String message) throws RemoteException {
        if ("time".equalsIgnoreCase(message)) {
            // If input is "time", send current date and time to the client
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return "Server Response: Current Date and Time - " + dateFormat.format(new Date());
        } else if ("testfile.txt".equalsIgnoreCase(message)) {
            // If input is "testfile.txt", check if the file exists
            File file = new File("testfile.txt");
            if (file.exists()) {
                try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = fileReader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    return "Server Response: Content of testfile.txt: \n" + content.toString().trim();
                } catch (IOException e) {
                    return "Server Response: Error reading file";
                }
            } else {
                return "Server Response: File does not exist";
            }
        } else {
            return "Server Response: Invalid input. Discarding...";
        }
    }
}

