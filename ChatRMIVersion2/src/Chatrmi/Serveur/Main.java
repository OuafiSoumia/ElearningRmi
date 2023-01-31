package Chatrmi.Serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		// TODO Auto-generated method stub
		ChatServer s = new ChatServer();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("irisi",s);
        System.out.println("Serveur Pret");
	}

}
