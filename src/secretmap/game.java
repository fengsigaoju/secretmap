package secretmap;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class game extends JFrame implements ActionListener {
    JMenuBar menuBar;//菜单栏对象
    JMenu menu,menu2;//菜单对象
    JMenuItem menuitem,menuitem2,menuitem3;
    Secretmap g;
	public  game(){
		setTitle("我的迷宫");
		setLayout(null);
		setBounds(100,100,700,700);
		g= new Secretmap();//里面包含一个游戏类继承画板
		g.setBounds(0,0,600,600);
		Container container=getContentPane();
		menuBar=new JMenuBar();//创建菜单栏对象
		setJMenuBar(menuBar);//添加菜单栏对象
		menu=new JMenu("开始");//创建菜单对象
		menuBar.add(menu);//将菜单对象添加到菜单栏对象中
		menuitem=new JMenuItem("开始游戏");
		menu.add(menuitem);
		menuitem.addActionListener(this);//添加监听事件
		menu2=new JMenu("设置");
		menuBar.add(menu2);
	    menuitem2=new JMenuItem("设置行数");
		menu2.add(menuitem2);
		menuitem2.addActionListener(this);//添加监听事件
		menuitem3=new JMenuItem("设置列数");
		menu2.add(menuitem3);
		menuitem3.addActionListener(this);//添加监听事件
		container.add(g);
		setVisible(true);
		g.setFocusable(true);//关键之关键，jpanel添加键盘监听事件要聚焦到画板
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
     public void actionPerformed(ActionEvent e){
		 if (e.getSource()==menuitem) {
			g.startgame();//开始游戏
			System.out.println("1");
		}
		if (e.getSource()==menuitem2){//设置行数
			System.out.println("2");
			alert a=new alert(g);
		}
		if (e.getSource()==menuitem3){//设置列数
			System.out.println("3");
			alert2 b=new alert2(g);//这边涉及到窗口的传值，就是把一个对象传进去
		}
	 }
	
	  public static void main(String [] args){
		 new game();
	}

}
