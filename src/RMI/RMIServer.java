package RMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            MessageProcessor server = new MessageProcessorImpl();
            LocateRegistry.createRegistry(8888);
            Naming.rebind("//localhost:8888/messageprocessor", server);
            System.out.println("RMI Server is running on port 8888...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

