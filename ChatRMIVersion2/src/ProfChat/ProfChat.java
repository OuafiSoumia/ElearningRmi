package ProfChat;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import Chatrmi.Chat;
import Chatrmi.Client;
import Chatrmi.Entities.ClassRoom;
import Chatrmi.Entities.User;
import Chatrmi.Serveur.ServerIF;
import tableau.TableProf;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ProfChat extends JFrame {

	private JPanel contentPane;
	private Client client;
	private Prof prof;

	private String username;
	private String url = "rmi://localhost/irisi";
	private ServerIF server;
	private JTextArea textArea;
	private JButton btnTablePartager;
	private User user;
	private JButton btnRefreach;
	private JTextArea ConnectedList;
	/**
	 * Create the frame.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ProfChat(String username,User user) throws MalformedURLException, RemoteException, NotBoundException, ClassNotFoundException, SQLException {
		server = (ServerIF) Naming.lookup(url);
		this.username=username;
		this.user=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 562);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton Chat = new JButton("Chat");
		Chat.setBounds(483, 390, 92, 21);
		Chat.setForeground(new Color(255, 255, 255));
		Chat.setBackground(new Color(0, 0, 205));
		Chat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chat c;
            	
     
					try {
						c = new Chat(username,user);
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
		});
		contentPane.setLayout(null);
		contentPane.add(Chat);
		
		btnTablePartager = new JButton("tableau Blanc");
		btnTablePartager.setBackground(new Color(255, 255, 240));
		btnTablePartager.setBounds(611, 391, 116, 19);
		btnTablePartager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        TableProf c= new TableProf(server);
		    
		     //setDimForPaint(c.getXmouseClicked(),c.getYmouseClicked(), c.getXmouseDragged(), c.getYmouseDragged());
				
			}
		});
		contentPane.add(btnTablePartager);
		
		JLabel lblEtudiantsConnecter = new JLabel("Les Etudiants En ligne");
		lblEtudiantsConnecter.setBounds(483, 60, 142, 13);
		lblEtudiantsConnecter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblEtudiantsConnecter);
		
		btnRefreach = new JButton("refresh");
		btnRefreach.setBounds(635, 56, 92, 21);
		btnRefreach.setBackground(new Color(255, 255, 255));
		btnRefreach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					prof.refreach();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnRefreach);
		
		ConnectedList = new JTextArea();
		ConnectedList.setFont(new Font("Tahoma", Font.BOLD, 13));
		ConnectedList.setBounds(483, 102, 244, 246);
		contentPane.add(ConnectedList);
		
		lblNewLabel_2 = new JLabel("Welcome To The Admin  Interface");
		lblNewLabel_2.setBounds(223, 10, 301, 29);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(new Color(255, 0, 0));
		contentPane.add(lblNewLabel_2);
		
		ListClass = new JTextArea();
		ListClass.setColumns(2);
		ListClass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ListClass.setBounds(69, 102, 311, 246);
		contentPane.add(ListClass);
		
		lblDetailsClass = new JLabel("Details Classes :");
		lblDetailsClass.setBounds(69, 60, 109, 17);
		lblDetailsClass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblDetailsClass);
		
		btnAjouterClass = new JButton("Ajouter Class");
		btnAjouterClass.setForeground(new Color(255, 255, 255));
		btnAjouterClass.setBackground(new Color(0, 0, 255));
		btnAjouterClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ajouterClasse frame = new ajouterClasse();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnAjouterClass.setBounds(69, 390, 142, 19);
		contentPane.add(btnAjouterClass);
		
		btnSupprimerClass = new JButton("Supprimer Class");
		btnSupprimerClass.setBackground(new Color(255, 69, 0));
		btnSupprimerClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtClassname.getText().equals("className")) {
	                JOptionPane.showMessageDialog(null, "veuillez saisir le nom du classe a supprimer");
				} else
					try {
						server.supprimerClass(txtClassname.getText());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		btnSupprimerClass.setBounds(236, 391, 142, 19);
		contentPane.add(btnSupprimerClass);
	
		prof = new Prof(server,username,textArea,ConnectedList,user,ListClass);
		
		lblClass = new JLabel("Nom de la classe :");
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClass.setBounds(69, 430, 116, 13);
		contentPane.add(lblClass);
		
		txtClassname = new JTextField();
		txtClassname.setForeground(Color.LIGHT_GRAY);
		txtClassname.setFont(new Font("Trebuchet MS", Font.ITALIC, 10));
		txtClassname.setToolTipText("");
		txtClassname.setText("className");
		txtClassname.setBounds(211, 427, 169, 19);
		contentPane.add(txtClassname);
		txtClassname.setColumns(10);
		
		btnRefreachclass = new JButton("refresh");
		btnRefreachclass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					prof.afficherClasse();
				} catch (ClassNotFoundException | RemoteException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		btnRefreachclass.setBackground(Color.WHITE);
		btnRefreachclass.setBounds(175, 57, 92, 21);
		contentPane.add(btnRefreachclass);

			prof.afficherClasse();
	

	}
	String[] e=null;
	private JLabel lblNewLabel_2;
	private JTextArea ListClass;
	private JLabel lblDetailsClass;
	private JButton btnAjouterClass;
	private JButton btnSupprimerClass;
	private JLabel lblClass;
	private JTextField txtClassname;
	private JButton btnRefreachclass;
}
