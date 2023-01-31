package Chatrmi;

import java.awt.Cursor;
import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import Chatrmi.ClientIf;
import Chatrmi.Entities.User;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import Chatrmi.Serveur.ServerIF;
import tableau.TableEtudient;

public class Client extends UnicastRemoteObject implements ClientIf{
	private ServerIF server;
    private String username;
    private JTextField textField;
    private JTextArea textArea;
    private String blabla="";
    private String clientconnecter="";
    private JTextArea ConnectedList;
    private User user;
    private JPanel PanelForAfficheFil;
    public Client(ServerIF server,String username,JTextField textField,JTextArea textArea,JTextArea ConnectedList,User user,JPanel panel) throws RemoteException{
        this.username = username;
        this.server = server;
        this.textField = textField;
        this.textArea = textArea;       
        this.ConnectedList=ConnectedList;
        this.user=user;
        this.PanelForAfficheFil=panel;
  	    server.addClient(this,user.id);
  	    
      refreach();
    }
    public void refreach() throws RemoteException {
		ConnectedList.setText(null);
		clientconnecter="";
    	for(String all:server.getClientConnecter()) {
    		System.out.print("afficher "+all);
    		clientconnecter+=all+" \n";
    	}
    	
    	ConnectedList.setText(clientconnecter);   

    }
   
    	

    public void afficherMessage(ArrayList<Integer> inc, String filename) throws RemoteException {
		System.out.println("<HTML><U><font size=\"4\" color=\"#365899\">" + filename + " : "+inc.get(0)+"</font></U></HTML>");
		
	
	        JLabel label = new JLabel("<HTML><U><font size=\"4\" color=\"#365899\">" + filename + "</font></U></HTML>");
	        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        label.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                try {
	                    FileOutputStream out;
	                    String separator;
	                    if(System.getProperty("os.name").startsWith("Linux") || System.getProperty("os.name").startsWith("MacOS")) separator = "/";
	                    else separator = "\\";
	                    out = new FileOutputStream(System.getProperty("user.home") + separator + filename);
	                    String[] extension = filename.split("\\.");
	                    for (int i = 0; i<inc.size(); i++) {
	                        int cc = inc.get(i);
	                        if(extension[extension.length - 1].equals("txt")||
	                                extension[extension.length - 1].equals("java")||
	                                extension[extension.length - 1].equals("php")||
	                                extension[extension.length - 1].equals("c")||
	                                extension[extension.length - 1].equals("cpp")||
	                                extension[extension.length - 1].equals("xml")
	                            //    extension[extension.length - 1].equals("mp4")
	                                
	                                )
	                        out.write((char)cc);
	                        else{
	                            out.write((byte)cc);
	                        }
	                    }
	                    out.flush();
	                    out.close();
	                    JOptionPane.showMessageDialog(new JFrame(),"your file saved at " + System.getProperty("user.home") + separator + filename,"File Saved",JOptionPane.INFORMATION_MESSAGE);
	                } catch (FileNotFoundException ex) {
	                    System.out.println("Error: " + ex.getMessage());
	                } catch (IOException ex) {
	                    System.out.println("Error: " + ex.getMessage());
	                }             
	            }

	            @Override
	            public void mousePressed(MouseEvent e) {}

	            @Override
	            public void mouseReleased(MouseEvent e) {}

	            @Override
	            public void mouseEntered(MouseEvent e) {}

	            @Override
	            public void mouseExited(MouseEvent e) {}
	        });
	        
	        PanelForAfficheFil.add(label);
	        PanelForAfficheFil.repaint();
	        PanelForAfficheFil.revalidate();
	    }
        
	


	@Override
	public void afficherMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
		blabla += message + "\n";
		System.out.print(message);
		textArea.setText(blabla);
	}

	@Override
	public void sendMessage() throws RemoteException {
		try {
			server.broadcastMessage(user.name + " : " + textField.getText());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
	
	@Override
	public void sendMessageFile(ArrayList<Integer> inc,String fileName) throws RemoteException {
		server.broadcastMessageFile(inc,fileName);
	}
	@Override
	public void CloseSession() throws RemoteException {
		// TODO Auto-generated method stub
		
		try {
			server.disconnectClient(user.id);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

   
	public void setDimForPaint(int xmouseClicked, int ymouseClicked, int xmouseDragged, int ymouseDragged) {
		// TODO Auto-generated method stub
		m.setArg(xmouseClicked, ymouseClicked, xmouseDragged, ymouseDragged);
		
	}
	TableEtudient m;
	public void SetMenux(TableEtudient m) {
		// TODO Auto-generated method stub
		
		this.m=m;
	}
	


}
