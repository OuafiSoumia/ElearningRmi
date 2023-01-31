package tableau;

import java.applet.Applet;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
//import java.awt.Graphics;
//import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;



public class TableEtudient extends JFrame implements MouseMotionListener ,MouseListener{
    private double x;
    private double y;

    private int xmousePressed =0, ymousePressed;
    private int xmouseReleased,ymouseReleased;

    private int xmouseMoved,ymouseMoved;

    public TableEtudient() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void setArg(int xmousePressed,int ymousePressed,int xmouseReleased,int ymouseReleased) {
        this.xmousePressed=xmousePressed;
        this.xmouseReleased=xmouseReleased;
        this.ymousePressed=ymousePressed;
        this.ymouseReleased=ymouseReleased;
        repaint ();
    }
    public void mouseDragged(java.awt.event.MouseEvent e) {

    }

    public void mouseMoved(java.awt.event.MouseEvent e) {

    }

    public void paint(Graphics g) {

        if(xmousePressed != 0)
            g.drawLine(xmousePressed, ymousePressed, xmouseReleased, ymouseReleased);
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {


    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {


    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {


    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }
}
