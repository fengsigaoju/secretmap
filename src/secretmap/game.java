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
    JMenuBar menuBar;//�˵�������
    JMenu menu,menu2;//�˵�����
    JMenuItem menuitem,menuitem2,menuitem3;
    Secretmap g;
	public  game(){
		setTitle("�ҵ��Թ�");
		setLayout(null);
		setBounds(100,100,700,700);
		g= new Secretmap();//�������һ����Ϸ��̳л���
		g.setBounds(0,0,600,600);
		Container container=getContentPane();
		menuBar=new JMenuBar();//�����˵�������
		setJMenuBar(menuBar);//��Ӳ˵�������
		menu=new JMenu("��ʼ");//�����˵�����
		menuBar.add(menu);//���˵�������ӵ��˵���������
		menuitem=new JMenuItem("��ʼ��Ϸ");
		menu.add(menuitem);
		menuitem.addActionListener(this);//��Ӽ����¼�
		menu2=new JMenu("����");
		menuBar.add(menu2);
	    menuitem2=new JMenuItem("��������");
		menu2.add(menuitem2);
		menuitem2.addActionListener(this);//��Ӽ����¼�
		menuitem3=new JMenuItem("��������");
		menu2.add(menuitem3);
		menuitem3.addActionListener(this);//��Ӽ����¼�
		container.add(g);
		setVisible(true);
		g.setFocusable(true);//�ؼ�֮�ؼ���jpanel��Ӽ��̼����¼�Ҫ�۽�������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
     public void actionPerformed(ActionEvent e){
		 if (e.getSource()==menuitem) {
			g.startgame();//��ʼ��Ϸ
			System.out.println("1");
		}
		if (e.getSource()==menuitem2){//��������
			System.out.println("2");
			alert a=new alert(g);
		}
		if (e.getSource()==menuitem3){//��������
			System.out.println("3");
			alert2 b=new alert2(g);//����漰�����ڵĴ�ֵ�����ǰ�һ�����󴫽�ȥ
		}
	 }
	
	  public static void main(String [] args){
		 new game();
	}

}
