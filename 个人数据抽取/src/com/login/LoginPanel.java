/**
 * 创建登陆界面的面板
 */

package com.login;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LoginPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private ImageIcon bg;	//图片对象 
	public LoginPanel() {
		super();
		URL url = getClass().getResource("login3.png");
		bg = new ImageIcon(url);
		setSize(bg.getIconWidth(),bg.getIconHeight()); 
		System.out.println(bg.getIconWidth()+"  "+bg.getIconHeight());
	}
	protected void paintComponent(Graphics g) { 
		Graphics2D g2 = (Graphics2D)g.create();
		super.paintComponent(g2);
		if(bg != null) {
			g2.drawImage(bg.getImage(),0,0,this);
		}
	}
}
