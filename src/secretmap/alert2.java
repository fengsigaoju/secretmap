package secretmap;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class alert2 extends JDialog {
	 int column;
	 public alert2(Secretmap g){
		setTitle("ע��!");
		setLayout(null);
		setBounds(300,300,400,400);
		Container container=getContentPane();
		container.setBackground(Color.white);
		JLabel jl= new JLabel("�������µ�����");
	    jl.setBounds(50,50,300,100);
	    JTextField jt=new JTextField("");
	    jt.setBounds(50,150,300,50);
	    JButton jb=new JButton("ȷ��");
	    jb.setBounds(100,250,150,75);
       jb.addActionListener(new ActionListener() {//����ȷ����ť�ĵ���¼�
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stu9b
			  String s=jt.getText();
			  if (s.matches("\\d+"))//���������
			  column=Integer.valueOf(s);
			  System.out.println(column);
			  g.SetColumn(column);
			  setVisible(false);
			 }
		});
	    container.add(jl);
	    container.add(jt);
	    container.add(jb);
	    setVisible(true);
	    }
	
	 
	
}
