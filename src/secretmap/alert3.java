package secretmap;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;


public class alert3 extends JDialog {
			ImageIcon li;
			public alert3(){
				win g=new win();
				g.setBounds(0,0,400,400);
				setTitle("you win!");
				setLayout(null);
				setBounds(400,300,450,400);
				Container container=getContentPane();
				container.add(g);
				container.setBackground(Color.white);
			    setVisible(true);
			    }
			
	}
 
	

