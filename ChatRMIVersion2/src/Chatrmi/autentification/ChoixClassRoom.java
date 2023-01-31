package Chatrmi.autentification;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Chatrmi.Chat;
import Chatrmi.Entities.ClassRoom;
import Chatrmi.Entities.User;

import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Choice;
import java.awt.Checkbox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Panel;
import java.awt.Font;

public class ChoixClassRoom extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//	ChoixClassRoom frame = new ChoixClassRoom();
				//	frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws RemoteException 
	 * @throws ClassNotFoundException 
	 */
	public ChoixClassRoom(String name, User user) throws ClassNotFoundException, RemoteException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("classroom subject");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(137, 21, 134, 13);
		contentPane.add(lblNewLabel);
		
		Panel panel = new Panel();
		panel.setBounds(85, 56, 243, 129);
		contentPane.add(panel);
		ArrayList<String> listClasses=new ArrayList<String>();
		JLabel label = new JLabel();
//pour afficher la listes des Classes		
		for(ClassRoom all:new ClassRoom().getListClasses()) {
			
//pour éliminer les Classes occurents 
			if(!listClasses.contains(all.getNomClass())) {
					listClasses.add(all.getNomClass());

			
			 label = new JLabel("<HTML><U><font size=\"4\" color=\"#365899\">" + all.getNomClass()+ "</font></U></HTML>");

	        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        label.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            boolean	exist=false;
	            	Chat c;
	            	try {

						for(Integer clasmates:new ClassRoom().getListUserInClass(all)) {
						if(clasmates==user.id) {
         
						try {
							exist=true;
							c = new Chat(name,user);
							c.show();
							dispose();
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NotBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						}
						
						else {
							exist=false;

						}
						}
						if(!exist) 
						    JOptionPane.showMessageDialog(null, "Acces interdit");


					} catch (HeadlessException | ClassNotFoundException | RemoteException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
	        });	}
	        
	        panel.add(label);
	        panel.repaint();
	        panel.revalidate();
	    }
         
		
	}
}
