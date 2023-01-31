package ProfChat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ProfIF extends Remote{
    public void afficherMessage(String message) throws RemoteException;    
    public void sendMessage() throws RemoteException;
	void sendMessageFile(ArrayList<Integer> inc, String fileName) throws RemoteException;
    public void afficherMessage(ArrayList<Integer> inc,String filename) throws RemoteException;
    public void CloseSession() throws RemoteException;
    

}

