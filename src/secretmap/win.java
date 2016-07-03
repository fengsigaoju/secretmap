package secretmap;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

 public class win extends JPanel{
		  	 ImageIcon li;
		  	 public win(){
		  		 li=new ImageIcon("./picture/you win.png");
		  		 repaint();
		  	 }
		  	 public void paint(Graphics g)
		  	 {
		  		 Image r=li.getImage();
		  	     g.drawImage(r,0,0,400,400,this);
		  	 }
 }

