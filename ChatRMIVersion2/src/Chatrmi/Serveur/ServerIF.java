package Chatrmi.Serveur;
import Chatrmi.Client;

import java.awt.List;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import Chatrmi.ClientIf;

public interface ServerIF extends Remote {
    void broadcastMessage(String message) throws RemoteException;
    public void disconnectClient(int id) throws RemoteException, SQLException, ClassNotFoundException;
    void addClient(ClientIf client,int id) throws RemoteException;
    ArrayList<String> getClientConnecter() throws RemoteException;
    public void broadcastMessageFile(ArrayList<Integer> inc,String filename) throws RemoteException;
    //pour prof
	void supprimerClass(String text)throws RemoteException;
	public void sendXYtoServer(int x,int y,int x1,int x2) throws RemoteException ;
			

}