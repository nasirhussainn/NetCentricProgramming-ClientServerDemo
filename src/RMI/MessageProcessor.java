package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageProcessor extends Remote {
    String processMessage(String message) throws RemoteException;
}
