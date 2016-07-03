package secretmap;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Secretmap extends JPanel implements KeyListener{
	int time;//控制画板的绘画方式
	int row;//行
	int column;//列
	int [][]a;//存储状态的节点
	int [][]visit;
	int []direction=new int[4];
	int Time;
	int personrow;//人当前所在的行
	int personcolumn;//人当前所在的列
	ImageIcon imagecon;
	public Secretmap()
	{   
		time=0;//默认展示背景图片
		Time=0;//默认没有找到路径
		row=column=0;//初始化为0(如果不为0说明可以绘制)
        imagecon=new ImageIcon("./picture/bg.png");//记录完整图片
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
		if ((row!=0)&&(column!=0))//当行数和列数均已经设定
		{   
			this.removeKeyListener(this);
			Time=0;
			System.out.println(row+"  "+column);
			time=1;
			a=new int[row+1][column+1];
			visit=new int[row+1][column+1];
			initsecretmap();//随机生成迷宫的一条线路
			for (int i=0;i<row;i++)
				for (int j=0;j<column;j++)
				{
					if (a[i][j]==0)//如果不在这条线路上
					{
						int m=(int)(Math.random()*100);
						if (m<=20)//除了线路上的点都是空白，肯定还要有一些空白，这个就认为剩下的20%是空白
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
				 a[i][j]=0;//一开始都标记为不可通行
		 for (int i=0;i<row;i++)
			 for (int j=0;j<column;j++)
				 visit[i][j]=0;
		 a[0][0]=1;//起点标记为可通行（这里默认左上角为起点，可自行修改)
		 visit[0][0]=1;//标记起点已经被访问过
		 DFS(0,0);//开始深搜
		 
	}
	public void DFS(int x,int y)
	{
		int []c={0,0,-1,1};
		int []d={1,-1,0,0};
		int dx,dy,i;
		if ((x==row-1)&&(y==column-1))//如果到达终点
		{
			Time=1;
			return;
		}
		initdirection();//随机生成四个方向（其实就是全排列)
		for (i=0;i<=3;i++)
		{
			dx=x+c[direction[i]];
			dy=y+d[direction[i]];
			if ((dx>=0)&&(dx<row)&&(dy>=0)&&(dy<column)&&(visit[dx][dy]==0)&&(Time==0))
			{
				a[dx][dy]=1;//认为它是通路
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
	        j=(int)(Math.random()*4);//注意括号的位置!要先乘再取int
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
		case 'w'://如果是向上
			if ((personrow>0)&&(a[personrow-1][personcolumn]==1))//如果上方可以到达
			personrow--;
			if (isreach())
			{
			new alert3();
			Time=0;
			this.removeKeyListener(this);
			}
			break;
		case 's'://如果是向下
			if ((personrow<row-1)&&(a[personrow+1][personcolumn]==1))//如果下方可以到达
			personrow++;
			if (isreach())
			{
			new alert3();
			Time=0;
			this.removeKeyListener(this);
			}
			break;
		case 'a'://如果是向左
			if ((personcolumn>0)&&(a[personrow][personcolumn-1]==1))//如果左方可以到达
			personcolumn--;
			if (isreach())
			{
			new alert3();
			Time=0;
			this.removeKeyListener(this);
			}
			break;
		case 'd'://如果是向右
			if ((personcolumn<column-1)&&(a[personrow][personcolumn+1]==1))//如果右方可以到达
			personcolumn++;
			if (isreach())
			{
				new alert3();
				Time=0;//标记为没有找到
				this.removeKeyListener(this);
			}
			break;
		 }
		repaint();
	}
	public void paint(Graphics g)
	{
		if (time==0)//如果是一开始没有开始游戏时，随便放一张背景图片
		{
			 Image r=imagecon.getImage();
			 g.drawImage(r,0,0,600,600,this);//画出原来的完整图案
		}
		else
		if (time==1)//否则就是在游戏中重绘
		{
			g.clearRect(0,0,600,600);
			for (int i=0;i<row;i++)
				for (int j=0;j<column;j++)
				{
					if ((i==personrow)&&(j==personcolumn))
					{
						Color c=g.getColor();
						g.setColor(Color.RED);//人是红色
						g.fillRect(600/column*j,600/row*i,600/column,600/row);
						g.setColor(c);
					}
					else
					if (a[i][j]==1)
					{
						Color c=g.getColor();
						g.setColor(Color.WHITE);//白色是路
						g.fillRect(600/column*j,600/row*i,600/column,600/row);
						g.setColor(c);
					}
					else
						if (a[i][j]==0)
						{
							Color c=g.getColor();
							g.setColor(Color.GRAY);//灰色是墙壁
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
