package Chatrmi.Model;

import Chatrmi.Client;
import Chatrmi.Entities.User;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class dataUser {
    Connection con = null;

    public void DeleteUserFromSession(int IdUser) throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/chatreseau", "root","");

        String sql= "delete  from  `Session` where `id_userConnecter`='"+IdUser+"'";

        PreparedStatement preparedStatement = con.prepareStatement(sql);
        boolean ex=preparedStatement.execute();


    }

   
    public User getEtudiantGuiByLoginAndPasswd(String login, String mdp) throws ClassNotFoundException, SQLException, RemoteException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/e_learning_rmi", "root","");
        Statement statement = con.createStatement();
        String requete = "SELECT * FROM utilisateur where login='"+login+"' and passwd='"+mdp+"'";
        ResultSet result = statement.executeQuery(requete);
        User obj=null;
        
        while (result.next()){
            System.out.println(result.getInt("id_user"));
             obj = new User( result.getString("username"),result.getInt("id_user"),result.getString("login"),result.getString("passwd"));
        }
        return obj;
    }
	public User getProfGuiByLoginAndPasswd(String login, String mdp)throws ClassNotFoundException, SQLException, RemoteException  {
		   Class.forName("com.mysql.cj.jdbc.Driver");
	        con= DriverManager.getConnection("jdbc:mysql://localhost/e_learning_rmi", "root","");
	        Statement statement = con.createStatement();
	        String requete = "SELECT * FROM Prof where login='"+login+"' and passwd='"+mdp+"'";
	        ResultSet result = statement.executeQuery(requete);
	        User obj=null;
	        while (result.next()){

	             obj = new User( result.getString("Name_Prof"),result.getInt("id_Prof"),result.getString("login"),result.getString("passwd"));
	        }
	        return obj;
	}


	
		public User getUser(int id) throws ClassNotFoundException, SQLException {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con= DriverManager.getConnection("jdbc:mysql://localhost/e_learning_rmi", "root","");
	        Statement statement = con.createStatement();
	        String requete = "SELECT * FROM `utilisateur` where id_user='"+id+"';";
	        ResultSet result = statement.executeQuery(requete);

	            User obj = new User();
	            while (result.next()){
	                obj.id =  result.getInt("id_user");
	                obj.name = result.getString("username");
	                obj.login = result.getString("login");
	                obj.passwd = result.getString("passwd");
	            }


	        return obj;
	    }
	
	
}