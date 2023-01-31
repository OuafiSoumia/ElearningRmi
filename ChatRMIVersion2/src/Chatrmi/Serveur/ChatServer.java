package Chatrmi.Serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import Chatrmi.Client;
import Chatrmi.ClientIf;
import Chatrmi.Entities.User;
import Chatrmi.Model.dataClass;

public class ChatServer extends UnicastRemoteObject implements ServerIF{
	protected ChatServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	private ArrayList<ClientIf> clients=new ArrayList<ClientIf>();
	public  ArrayList<Integer> listConnecter=new ArrayList<>();


	
	@Override
	public void broadcastMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
		for(int i=0;i<clients.size();i++){
			clients.get(i).afficherMessage(message);
        }
	}


	
	@Override
	public void disconnectClient(int id) throws RemoteException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0;i<clients.size();i++) {
			if(listConnecter.get(i)==id) {
					clients.remove(i);
					//ne pas envoyer le msg au client deconnecter
					listConnecter.remove(i);
					

			}
		}
		for(int i=0;i<clients.size();i++) {
			System.out.println("les clients connecter "+listConnecter.get(i));
			System.out.println("les id connecter "+clients.get(i));

			}
		System.out.println("session de  "+id+"est fermer");

		}
		
		
	

	public void broadcastMessageFile(ArrayList<Integer> inc, String filename) throws RemoteException {
		for(int i=0;i<clients.size();i++){
			clients.get(i).afficherMessage(inc,filename);
        }
	}
	

	@Override
	public void addClient(ClientIf client,int id) throws RemoteException {
		// TODO Auto-generated method stub
		clients.add(client);
		listConnecter.add(id);
		
	}


	@Override
	public ArrayList<String> getClientConnecter() throws RemoteException {
		// TODO Auto-generated method stub
		  ArrayList<String> all=new ArrayList<String>();

		for(Integer ids:listConnecter) {
			try {
				all.add(new User().getUserById(ids).name);
				System.out.print("helloooo"+all.size());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return all;
	}


	@Override
	public void supprimerClass(String text) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			new dataClass().SupprimerClass(text);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public void sendXYtoServer(int x, int y, int x1, int y1) throws RemoteException {
		// TODO Auto-generated method stub
		for(int i=0;i<clients.size();i++){
			clients.get(i).setDimForPaint(x, y,x1,y1);
        }
		
	}


        

	}


