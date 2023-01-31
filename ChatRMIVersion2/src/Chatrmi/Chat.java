package Chatrmi;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import Chatrmi.Entities.User;
import Chatrmi.Serveur.ServerIF;
import tableau.TableEtudient;

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
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Chat extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMessage;
	private Client client;
	private String username;
	private String url = "rmi://localhost/irisi";
	private ServerIF server;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JButton btnTablePartager;
	private JButton btnQuitter;
	private User user;
	private JButton btnRefreach;
	private JButton btnPDF;
	private JTextArea ConnectedList;
	private JPanel PanelForAfficheFil;
	private JLabel lblNewLabel;
	
	/**
	 * Create the frame.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Chat(String username,User user) throws MalformedURLException, RemoteException, NotBoundException, ClassNotFoundException, SQLException {
		server = (ServerIF) Naming.lookup(url);
		this.username=username;
		this.user=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		textFieldMessage = new JTextField(10);
		textFieldMessage.setBounds(152, 475, 252, 19);
		contentPane.add(textFieldMessage);
		textFieldMessage.setColumns(10);
		contentPane.setLayout(null);
		
		
		textArea = new JTextArea(20,50);
		textArea.setBounds(1, 1, 381, 364);
		contentPane.add(textArea);
		JScrollPane sp = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setBounds(87, 82, 423, 383);
		getContentPane().add(sp);
		
		JButton envoyer = new JButton("envoyer");
		envoyer.setForeground(new Color(255, 255, 255));
		envoyer.setBackground(new Color(0, 0, 205));
		envoyer.setBounds(443, 474, 116, 21);
		envoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//pour envoyer un message on fait appel la fonction sendMessage() qui existe dans la class Client 
					client.sendMessage();
	                textFieldMessage.setText("");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(envoyer);
		
		btnTablePartager = new JButton("tableau Blanc");
		btnTablePartager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableEtudient m= new TableEtudient();
				client.SetMenux(m);
				
			}
		});
		btnTablePartager.setBounds(605, 475, 122, 19);
		contentPane.add(btnTablePartager);
		
		btnQuitter = new JButton("X");
		btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnQuitter.setBackground(new Color(220, 20, 60));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//pour terminer la session du client
					client.CloseSession();
					setVisible(false);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnQuitter.setBounds(689, 2, 48, 29);
		contentPane.add(btnQuitter);
		
		JLabel LabelForMsg = new JLabel("message");
		LabelForMsg.setBounds(87, 478, 85, 13);
		contentPane.add(LabelForMsg);
		
		JLabel lblEtudiantsConnecter = new JLabel("En ligne");
		lblEtudiantsConnecter.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEtudiantsConnecter.setBounds(536, 60, 109, 13);
		contentPane.add(lblEtudiantsConnecter);
		
		btnRefreach = new JButton("refresh");
		btnRefreach.setBackground(new Color(60, 179, 113));
		btnRefreach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//pour reafficher la liste des client connecter
					client.refreach();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefreach.setBounds(635, 56, 92, 21);
		contentPane.add(btnRefreach);
		
		btnPDF = new JButton("New button");
		btnPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{//apparaitre le fenetre des fichiers
			        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			        int returnValue = jfc.showOpenDialog(null);
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			        	//file:le fichier selectionner
			            File file = jfc.getSelectedFile();
			            String[] extension = file.getName().split("\\.");
			            System.out.println(extension.length);
			            if(extension[extension.length - 1].equals("txt")||
			                extension[extension.length - 1].equals("java")||
			                extension[extension.length - 1].equals("php")||
			                extension[extension.length - 1].equals("c")||
			                extension[extension.length - 1].equals("cpp")||
			                extension[extension.length - 1].equals("xml")||
			                extension[extension.length - 1].equals("exe")||
			                extension[extension.length - 1].equals("png")||
			                extension[extension.length - 1].equals("jpg")||
			                extension[extension.length - 1].equals("jpeg")||
			                extension[extension.length - 1].equals("pdf")||
			                extension[extension.length - 1].equals("jar")||
			                extension[extension.length - 1].equals("rar")||
			                extension[extension.length - 1].equals("zip")
			              //  extension[extension.length - 1].equals("mp4")
			            ){
			                try {
			                    ArrayList<Integer> inc;
			                    try (FileInputStream in = new FileInputStream(file)) {
			                        inc = new ArrayList<Integer>();
			                        int c=0;
			                        while((c=in.read()) != -1) {
			                            inc.add(c);
			                        }
			                        in.close();
			                    }

								client.sendMessageFile(inc,file.getName());

			                } catch (Exception ex) {
			                    System.out.println("Error: " + ex.getMessage());
			                } 
			                            
			            }else{
			                JOptionPane.showInputDialog(this);
			            }
			        }
			    }
			}
		});
		btnPDF.setIcon(new ImageIcon("C:\\Users\\soumi\\Desktop\\file-upload.png"));
		btnPDF.setBounds(414, 475, 19, 19);
		contentPane.add(btnPDF);
		
		ConnectedList = new JTextArea();
		ConnectedList.setBounds(536, 102, 191, 246);
		contentPane.add(ConnectedList);
		//pour afficher les anciens messages 
		
		PanelForAfficheFil = new JPanel();
		PanelForAfficheFil.setBackground(Color.LIGHT_GRAY);
		PanelForAfficheFil.setBounds(536, 372, 191, 93);
		contentPane.add(PanelForAfficheFil);
		
		
		client = new Client(server,username,textFieldMessage,textArea,ConnectedList,user,PanelForAfficheFil);
		
		lblNewLabel = new JLabel("Fils:");
		PanelForAfficheFil.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(255, 240, 245));
		lblNewLabel_1.setBounds(-14, -13, 801, 565);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Welcome To Your ClassRoom");
		lblNewLabel_2.setFont(new Font("MingLiU-ExtB", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(new Color(255, 0, 0));
		lblNewLabel_2.setBounds(189, 33, 244, 29);
		contentPane.add(lblNewLabel_2);
	
			String[] s = historique().split("/");

		for(int i=0;i<s.length;i++) {
			client.afficherMessage(new User().getUserById(Integer.parseInt(e[i])).name+" : "+s[i]);
		}

		
	}
	String[] e=null;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	public String historique() throws SQLException, ClassNotFoundException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_learning_rmi", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet res =stmt.executeQuery("select * from message");
       
        String message="";
        String emetteur="";
        while(res.next()) {
             message+=res.getString("contenu_msg")+"/";
             emetteur+=res.getInt("emetteur_msg")+"/";

        }
        e=emetteur.split("/");
        System.out.print("le msg est "+message);
        conn.close();
        return message;
   	}
	
}