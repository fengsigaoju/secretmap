package secretmap;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class alert extends JDialog {
	 int row;
	 public alert(Secretmap g){
		setTitle("注意!");
		setLayout(null);
		setBounds(300,300,400,400);
		Container container=getContentPane();
		container.setBackground(Color.white);
		JLabel jl= new JLabel("请输入新的行数");
		jl.setBounds(50,50,300,100);
	    JTextField jt=new JTextField("");
	    jt.setBounds(50,150,300,50);
	    JButton jb=new JButton("确定");
	    jb.setBounds(100,250,150,75);
        jb.addActionListener(new ActionListener() {//设立确定按钮的点击事件
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stu9b
			  String s=jt.getText();
			  if (s.matches("\\d+"))//如果是数字
			  row=Integer.valueOf(s);
			  System.out.println(row);
			  g.SetRow(row);
			  setVisible(false);
			}
		});
	    container.add(jl);
	    container.add(jt);
	    container.add(jb);
	    setVisible(true);
	    }
	
	 
	
}
