package Chatrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientIf extends Remote{
    public void afficherMessage(String message) throws RemoteException;    
    public void sendMessage() throws RemoteException;
	void sendMessageFile(ArrayList<Integer> inc, String fileName) throws RemoteException;
    public void afficherMessage(ArrayList<Integer> inc,String filename) throws RemoteException;
    public void CloseSession() throws RemoteException;
	public void setDimForPaint(int x, int y, int x1, int y1) throws RemoteException;

}

