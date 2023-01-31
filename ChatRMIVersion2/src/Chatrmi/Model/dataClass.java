package Chatrmi.Model;

import Chatrmi.Entities.ClassRoom;
import Chatrmi.Entities.User;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;



import com.mysql.cj.xdevapi.Statement;

public class dataClass {

	 Connection con = null;

	    public List<ClassRoom> getListClasses() throws ClassNotFoundException, SQLException {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        con= DriverManager.getConnection("jdbc:mysql://localhost/e_learning_rmi", "root","");
	        java.sql.Statement statement =  con.createStatement();
	        
	        String requete = "SELECT * FROM classroom";
	        ResultSet result = statement.executeQuery(requete);
	        List<ClassRoom> objs = new LinkedList<>();
	        while (result.next()){

	        	ClassRoom obj = new ClassRoom();
	            obj.setNumClass(result.getInt("id_class")); 
	            obj.setNomClass(result.getString("name_class")); 
	            obj.setClassmate(result.getInt("classmate"));
	            objs.add(obj);
	        }
	        ClassRoom[] tab = new ClassRoom[objs.size()];
	        objs.toArray(tab);
	        System.out.print(objs.size()+" nbr de classes");
	        return objs;
	    }

		public List<Integer> getListUserInClassroom(ClassRoom c) throws SQLException, ClassNotFoundException {
			// TODO Auto-generated method stub
			 Class.forName("com.mysql.cj.jdbc.Driver");
		        con= DriverManager.getConnection("jdbc:mysql://localhost/e_learning_rmi", "root","");
		        java.sql.Statement statement = con.createStatement();
		             // JOptionPane.showMessageDialog(null, "l'id "+id+" le nom "+nom);
		        System.out.println("je suis dans dataclass :ma classroom est: "+c.getNomClass());

		        String requete = "SELECT * FROM `classroom` where `name_class`='" + c.getNomClass() + "';";
		        ResultSet result =  statement.executeQuery(requete);
		      //  ResultSetMetaData resultat = result.getMetaData();
		        List<Integer> objs = new LinkedList<>();
		        while (result.next()){
	
		            objs.add(result.getInt("classmate"));
		        }
		        System.out.println("je suis dans dataclass :le nbr des etudient "+objs.size());
		    
		        return objs;
		}
	    
//	    public User getUser(int id) throws ClassNotFoundException, SQLException {
//	        Class.forName("com.mysql.cj.jdbc.Driver");
//	        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
//	        Statement statement = con.createStatement();
//	        String requete = "SELECT * FROM `utilisateur` where id_user='"+id+"';";
//	        ResultSet result = statement.executeQuery(requete);
//
//	            User obj = new User();
//	            while (result.next()){
//	                obj.id =  result.getInt("id_user");
//	                obj.name = result.getString("username");
//	                obj.login = result.getString("login");
//	                obj.passwd = result.getString("passwd");
//	            }
//
//
//	        return obj;
//	    }
//	    public int getIdUser(String login,String mdp) throws ClassNotFoundException, SQLException {
//	        Class.forName("com.mysql.cj.jdbc.Driver");
//	        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
//	        Statement statement = con.createStatement();
//	        String requete = "SELECT * FROM utilisateur where login='"+login+"' and passwd='"+mdp+"'";
//	        ResultSet result = statement.executeQuery(requete);
//	        int i=0;
//	        while (result.next()){
//	            System.out.println(result.getInt("id_user"));
//	             i= result.getInt("id_user");
//	        }
//	        return i;
//	    }
//
//
//
//
	    public void CreeClass(String NameClass,String ListIdClassmate) throws ClassNotFoundException, SQLException {

	    	String[] IdClassmate=ListIdClassmate.split(",");
	    	System.out.print(IdClassmate[0]);
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con= DriverManager.getConnection("jdbc:mysql://localhost/e_learning_rmi", "root","");
	        boolean ex=false;
	        	for(int i=0;i<IdClassmate.length;i++) {
	        		
	        
	            String sql= "INSERT INTO `classroom`( `name_class`, `classmate`) VALUES ('"+ NameClass+"','"+ Integer.parseInt(IdClassmate[i])+"');";
	            PreparedStatement preparedStatement = con.prepareStatement(sql);
	             ex=preparedStatement.execute();
	            	}
	            if (ex){
	                JOptionPane.showMessageDialog(null, "la Classe est cree avec succsses!");
	            }
	            else
	                JOptionPane.showMessageDialog(null, "la Classe est cree avec succsses!");

	        }
	    public void SupprimerClass(String NameClass) throws ClassNotFoundException, SQLException {


	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con= DriverManager.getConnection("jdbc:mysql://localhost/e_learning_rmi", "root","");

	        String sql= "delete  from  `classroom` where  `name_class`='"+NameClass+"'";

	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        boolean ex=preparedStatement.execute();
	        if (ex){
	            JOptionPane.showMessageDialog(null, "la classe a etait supprimer");
	        }
	        else
	            JOptionPane.showMessageDialog(null, "la classe a etait supprimer");

	    }

	

//	    public List<User> getListAmisInGroup(int id,String nom) throws ClassNotFoundException, SQLException {
//	        Class.forName("com.mysql.cj.jdbc.Driver");
//	        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
//	        Statement statement = con.createStatement();
//	        System.out.println("ana f opperation id_user_creator"+id+" le nom "+nom);
//	             // JOptionPane.showMessageDialog(null, "l'id "+id+" le nom "+nom);
//
//	        String requete = "SELECT * FROM `group` where `id_user_creator`='" + id + "' and `groupe_name`='" + nom + "';";
//	        ResultSet result = statement.executeQuery(requete);
//	      //  ResultSetMetaData resultat = result.getMetaData();
//	        List<User> objs = new LinkedList<>();
//	        while (result.next()){
//
//	            int id_amis=result.getInt("id_user_amis");
//	            objs.add(getUser(id_amis));
//	        }
//	        User[] tab = new User[objs.size()];
//	        objs.toArray(tab);
//	        return objs;
//	    }
//
//	    public ArrayList<ClassRoom> getUsersGroup(int id) throws ClassNotFoundException, SQLException {
//	        Class.forName("com.mysql.cj.jdbc.Driver");
//	        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
//	        Statement statement = con.createStatement();
//	        // JOptionPane.showMessageDialog(null, "l'id "+id+" le nom "+nom);
//
//	        String requete = "SELECT * FROM `group` where `id_user_creator`='" + id + "' ;";
//	        ResultSet result = statement.executeQuery(requete);
//	        ArrayList<ClassRoom> objs = new ArrayList<>();
//	        while (result.next()){
//	        	ClassRoom obj = new ClassRoom();
//
//	            obj.setIdGroup(result.getInt("id_group"));
//	            obj.setIdCreator(result.getInt("id_user_creator"));
//	            obj.setGroupName(result.getString("groupe_name"));
//	            obj.setIdAmis(result.getInt("id_user_amis"));
//
//	            objs.add(obj);
//	        }
//	        ClassRoom[] tab = new ClassRoom[objs.size()];
//	        objs.toArray(tab);
//	        return objs;
//
//	    }
//
//	    public int getCreatorGroup(String nameG) throws ClassNotFoundException, SQLException {
//	        Class.forName("com.mysql.cj.jdbc.Driver");
//	        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");
//	        Statement statement = con.createStatement();
//	        // JOptionPane.showMessageDialog(null, "l'id "+id+" le nom "+nom);
//
//	        String requete = "SELECT * FROM `group` where `groupe_name`='" + nameG + "';";
//	        ResultSet result = statement.executeQuery(requete);
//	        //  ResultSetMetaData resultat = result.getMetaData();
//	        int id_creator=0;
//	        while (result.next()){
//
//	            id_creator=result.getInt("id_user_creator");
//	        }
//	        return id_creator;
//	    }
}
