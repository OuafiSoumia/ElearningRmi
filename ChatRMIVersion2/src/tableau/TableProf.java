package tableau;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Chatrmi.Serveur.ServerIF;




public class TableProf extends JFrame implements MouseMotionListener ,MouseListener{
   
    private double x;
    private double y;
    ServerIF server;
    private int xmouseClicked, ymouseClicked;
    private int xmouseEntered, ymouseEntered;
    private int xmouseExited, ymouseExited;
    private int xmousePressed, ymousePressed;
    private int xmouseDragged,ymouseDragged;
    TableEtudient m;
    private int xmouseMoved,ymouseMoved;
    private boolean clear=false;
    public TableProf(ServerIF server) {
//	    	JFrame f = new JFrame("The Twilight Zone");
//	    	f.setSize(100, 100);
//	    	this.add(f);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	       this.setTitle("hello");
        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.server=server;



    }





    public void paint(Graphics g) {
       //graphique debut et fin
            g.drawLine(xmousePressed, ymousePressed, xmouseDragged, ymouseDragged);
            try {
				server.sendXYtoServer(xmousePressed, ymousePressed, xmouseDragged, ymouseDragged);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            xmousePressed=xmouseDragged;
            ymousePressed=ymouseDragged; 


    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
//premiere click
    @Override
    public void mousePressed(MouseEvent e) {
        xmousePressed = e.getX();
        ymousePressed = e.getY();
//	      repaint();
//	      showStatus("x = "+x+" ; y = "+y);

    }

//clicker et enlever la souris le point
    @Override
    public void mouseReleased(MouseEvent e) {

//	      showStatus("x = "+x+" ; y = "+y);

    }
    
//clicker sur la mouse et mouve
    @Override
    public void mouseDragged(MouseEvent e) {
        xmouseDragged = e.getX();
        ymouseDragged = e.getY();
        repaint();
    }
//mouve sans click
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

  
	public int getXmouseClicked() {
		return xmouseClicked;
	}

	public void setXmouseClicked(int xmouseClicked) {
		this.xmouseClicked = xmouseClicked;
	}

	public int getYmouseClicked() {
		return ymouseClicked;
	}

	public void setYmouseClicked(int ymouseClicked) {
		this.ymouseClicked = ymouseClicked;
	}

	public int getXmouseDragged() {
		return xmouseDragged;
	}

	public void setXmouseDragged(int xmouseDragged) {
		this.xmouseDragged = xmouseDragged;
	}

	public int getYmouseDragged() {
		return ymouseDragged;
	}

	public void setYmouseDragged(int ymouseDragged) {
		this.ymouseDragged = ymouseDragged;
	}
    
}