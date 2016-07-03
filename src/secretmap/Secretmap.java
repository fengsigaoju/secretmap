package secretmap;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Secretmap extends JPanel implements KeyListener{
	int time;//���ƻ���Ļ滭��ʽ
	int row;//��
	int column;//��
	int [][]a;//�洢״̬�Ľڵ�
	int [][]visit;
	int []direction=new int[4];
	int Time;
	int personrow;//�˵�ǰ���ڵ���
	int personcolumn;//�˵�ǰ���ڵ���
	ImageIcon imagecon;
	public Secretmap()
	{   
		time=0;//Ĭ��չʾ����ͼƬ
		Time=0;//Ĭ��û���ҵ�·��
		row=column=0;//��ʼ��Ϊ0(�����Ϊ0˵�����Ի���)
        imagecon=new ImageIcon("./picture/bg.png");//��¼����ͼƬ
		repaint();
	}
	public void SetRow(int row)
	{
		this.row=row;
	}
	public void SetColumn(int column)
	{
		this.column=column;
	}
	public void startgame()
	{   
		if ((row!=0)&&(column!=0))//���������������Ѿ��趨
		{   
			this.removeKeyListener(this);
			Time=0;
			System.out.println(row+"  "+column);
			time=1;
			a=new int[row+1][column+1];
			visit=new int[row+1][column+1];
			initsecretmap();//��������Թ���һ����·
			for (int i=0;i<row;i++)
				for (int j=0;j<column;j++)
				{
					if (a[i][j]==0)//�������������·��
					{
						int m=(int)(Math.random()*100);
						if (m<=20)//������·�ϵĵ㶼�ǿհף��϶���Ҫ��һЩ�հף��������Ϊʣ�µ�20%�ǿհ�
						a[i][j]=1;
					}
				}
			this.addKeyListener(this);
			personrow=0;
			personcolumn=0;
			repaint();
		}
	}
	public void initsecretmap()
	{
		 for (int i=0;i<row;i++)
			 for (int j=0;j<column;j++)
				 a[i][j]=0;//һ��ʼ�����Ϊ����ͨ��
		 for (int i=0;i<row;i++)
			 for (int j=0;j<column;j++)
				 visit[i][j]=0;
		 a[0][0]=1;//�����Ϊ��ͨ�У�����Ĭ�����Ͻ�Ϊ��㣬�������޸�)
		 visit[0][0]=1;//�������Ѿ������ʹ�
		 DFS(0,0);//��ʼ����
		 
	}
	public void DFS(int x,int y)
	{
		int []c={0,0,-1,1};
		int []d={1,-1,0,0};
		int dx,dy,i;
		if ((x==row-1)&&(y==column-1))//��������յ�
		{
			Time=1;
			return;
		}
		initdirection();//��������ĸ�������ʵ����ȫ����)
		for (i=0;i<=3;i++)
		{
			dx=x+c[direction[i]];
			dy=y+d[direction[i]];
			if ((dx>=0)&&(dx<row)&&(dy>=0)&&(dy<column)&&(visit[dx][dy]==0)&&(Time==0))
			{
				a[dx][dy]=1;//��Ϊ����ͨ·
				visit[dx][dy]=1;
				DFS(dx,dy);
				if (Time==0)
				a[dx][dy]=0;
			}
		}
	}
	public void initdirection()
	{
	    int i,j,k,ok;
	    for (i=0;i<=3;i++)
	    {
	        j=(int)(Math.random()*4);//ע�����ŵ�λ��!Ҫ�ȳ���ȡint
	        ok=1;
	        for (k=0;k<i;k++)
	        if (j==direction[k])
	        ok=0;
	        if (ok==1)
	        direction[i]=j;
	        else
	        i--;
	     }
	}
	
	public boolean isreach()
	{
		if ((personrow==row-1)&&(personcolumn==column-1))
		return true;
		return false;
   }
	@Override
	public void keyPressed(KeyEvent e)
	{
		char c=e.getKeyChar();
		System.out.println(c);
		switch(c)
		{
		case 'w'://���������
			if ((personrow>0)&&(a[personrow-1][personcolumn]==1))//����Ϸ����Ե���
			personrow--;
			if (isreach())
			{
			new alert3();
			Time=0;
			this.removeKeyListener(this);
			}
			break;
		case 's'://���������
			if ((personrow<row-1)&&(a[personrow+1][personcolumn]==1))//����·����Ե���
			personrow++;
			if (isreach())
			{
			new alert3();
			Time=0;
			this.removeKeyListener(this);
			}
			break;
		case 'a'://���������
			if ((personcolumn>0)&&(a[personrow][personcolumn-1]==1))//����󷽿��Ե���
			personcolumn--;
			if (isreach())
			{
			new alert3();
			Time=0;
			this.removeKeyListener(this);
			}
			break;
		case 'd'://���������
			if ((personcolumn<column-1)&&(a[personrow][personcolumn+1]==1))//����ҷ����Ե���
			personcolumn++;
			if (isreach())
			{
				new alert3();
				Time=0;//���Ϊû���ҵ�
				this.removeKeyListener(this);
			}
			break;
		 }
		repaint();
	}
	public void paint(Graphics g)
	{
		if (time==0)//�����һ��ʼû�п�ʼ��Ϸʱ������һ�ű���ͼƬ
		{
			 Image r=imagecon.getImage();
			 g.drawImage(r,0,0,600,600,this);//����ԭ��������ͼ��
		}
		else
		if (time==1)//�����������Ϸ���ػ�
		{
			g.clearRect(0,0,600,600);
			for (int i=0;i<row;i++)
				for (int j=0;j<column;j++)
				{
					if ((i==personrow)&&(j==personcolumn))
					{
						Color c=g.getColor();
						g.setColor(Color.RED);//���Ǻ�ɫ
						g.fillRect(600/column*j,600/row*i,600/column,600/row);
						g.setColor(c);
					}
					else
					if (a[i][j]==1)
					{
						Color c=g.getColor();
						g.setColor(Color.WHITE);//��ɫ��·
						g.fillRect(600/column*j,600/row*i,600/column,600/row);
						g.setColor(c);
					}
					else
						if (a[i][j]==0)
						{
							Color c=g.getColor();
							g.setColor(Color.GRAY);//��ɫ��ǽ��
							g.fillRect(600/column*j,600/row*i,600/column,600/row);
							g.setColor(c);
						}
					
				}
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
