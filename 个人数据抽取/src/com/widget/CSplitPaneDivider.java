package com.widget;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.plaf.basic.*;

/**
 * @author ¿Ó÷”Œæ
 *
 */
public class CSplitPaneDivider extends BasicSplitPaneDivider implements
        Serializable {
    
    private ImageIcon image;
    private JButton leftButton;
    private JButton rightButton;
    
    public CSplitPaneDivider(BasicSplitPaneUI ui) {
        super(ui);
        // this.dividerSize = 100;
        // try {
        // image = new ImageIcon(new URL(
        // "http://lzw:8888/com/lzw/client/widget/fdsa.jpg"));
        // } catch (MalformedURLException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        setBackground(Color.DARK_GRAY);
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(151, 188, 229));
        g.fill3DRect(0, 0, getWidth(), getHeight(), true);
        if (image != null)
            g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
        super.paint(g);
    }
    
    @Override
    protected JButton createLeftOneTouchButton() {
        leftButton = super.createLeftOneTouchButton();
        leftButton.setBackground(new Color(0, 0, 0, 0));
        leftButton.setOpaque(false);
        return leftButton;
    }
    
    @Override
    protected JButton createRightOneTouchButton() {
        rightButton = super.createRightOneTouchButton();
        rightButton.setBackground(new Color(0, 0, 0, 0));
        rightButton.setOpaque(false);
        return rightButton;
    }
    
    @Override
    protected void oneTouchExpandableChanged() {
        // setLayout(new BorderLayout());
        // JPanel panel=new JPanel();
        // panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        // panel.add(leftButton);
        // panel.add(rightButton);
        // panel.add(new JButton("1"));
        // panel.add(new JButton("2"));
        // panel.add(new JButton("3"));
        // add(panel);
        // validate();
        super.oneTouchExpandableChanged();
    }
    
    //
    // @Override
    // public Border getBorder() {
    // Border border = super.getBorder();
    // return border;
    // }
}
