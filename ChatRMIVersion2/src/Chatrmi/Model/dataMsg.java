package Chatrmi.Model;

import Chatrmi.Entities.Message;


import javax.swing.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class dataMsg {
    Connection con = null;

    public void setMsg(String msg,int emetteur) throws ClassNotFoundException, SQLException {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost/e_learning_rmi", "root","");

            String sql= "INSERT INTO `message`(`contenu_msg`, `emetteur_msg`) VALUES ('"+msg+"','"+ emetteur+"');";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            boolean ex=preparedStatement.execute();
            if (ex){
                JOptionPane.showMessageDialog(null, "le groupe est cree avec succsses!");
            }
            else
                JOptionPane.showMessageDialog(null, "le groupe n'est cree!");

        }


    public String getMsg() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/e_learning_rmi", "root","");
        Statement statement = con.createStatement();
//        String requete = "SELECT * FROM message where emetteur_msg='"+1+"';";
      String requete = "SELECT * FROM message where num_msg='"+1+"';";
        ResultSet result = statement.executeQuery(requete);
        String obj = new String();
        while (result.next()){
            obj=(result.getString("contenu_msg"));
        }


        return obj;

    }

    public List<String> getAllMsg() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/e_learning_rmi", "root","");
        Statement statement = con.createStatement();
        String requete = "SELECT * FROM message";
        ResultSet result = statement.executeQuery(requete);
        List<String> objs = new LinkedList<>();
        while (result.next()){
//
//            Message obj = new Message();
//            obj.setNumMsg(result.getInt("num_msg"));
//            obj.setContenuMsg(result.getString("contenu_msg"));
//            obj.setEmetteurMsg(result.getInt("emetteur_msg"));
//            objs.add(obj);
           Message obj = new Message();
            obj.setContenuMsg(result.getString("contenu_msg"));
            objs.add(obj.getContenuMsg());

        }
        return objs;
    }
    
    public List<String> getAllMsg1() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/e_learning_rmi", "root","");
        Statement statement = con.createStatement();
        String requete = "SELECT * FROM message";
        ResultSet result = statement.executeQuery(requete);
        List<String> objs = new LinkedList<>();
        while (result.next()){
//
//            Message obj = new Message();
//            obj.setNumMsg(result.getInt("num_msg"));
//            obj.setContenuMsg(result.getString("contenu_msg"));
//            obj.setEmetteurMsg(result.getInt("emetteur_msg"));
//            objs.add(obj);
           Message obj = new Message();
            obj.setContenuMsg(result.getString("contenu_msg"));
            objs.add(obj.getContenuMsg());

        }
        return objs;
    }
}
