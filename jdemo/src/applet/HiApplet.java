package applet;

import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;

//Applet继承java.applet.Applet类
public class HiApplet extends Applet{
	//重写paint方法，该方法将在Applet上绘图
	public void paint(Graphics g) {
		//先绘出字符串，字符串通过getParameter方法获取
		g.drawString (getParameter ("Hi") , 20, 30) ;
		//设置颜色
		g.setColor(new Color(255 , 200 , 200));
		//画出一个矩形
		g.fillRect(50 , 60, 200 , 150);
	}
}
