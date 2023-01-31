package Chatrmi.Entities;

import Chatrmi.Model.dataMsg;

import java.sql.SQLException;
import java.util.List;

public class Message {
    private int numMsg;
    private int emetteurMsg;
    private String contenuMsg;
    private dataMsg dMsg=new dataMsg();

    public void addMsg(String msg) throws SQLException, ClassNotFoundException {
         dMsg.setMsg(msg,emetteurMsg);
    }


    public String getMssg() throws SQLException, ClassNotFoundException {
        return dMsg.getMsg();
    }

    public List<String> getAllMssg() throws SQLException, ClassNotFoundException {
        return dMsg.getAllMsg();
    }

    public int getNumMsg() {
        return numMsg;
    }

    public void setNumMsg(int numMsg) {
        this.numMsg = numMsg;
    }

    public int getEmetteurMsg() {
        return emetteurMsg;
    }

    public void setEmetteurMsg(int emetteurMsg) {
        this.emetteurMsg = emetteurMsg;
    }

    public dataMsg getdMsg() {
        return dMsg;
    }

    public void setdMsg(dataMsg dMsg) {
        this.dMsg = dMsg;
    }

    public String getContenuMsg() {
        return contenuMsg;
    }

    public void setContenuMsg(String contenuMsg) {
        this.contenuMsg = contenuMsg;
    }
}
