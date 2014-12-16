package com.person;

import static javax.swing.BorderFactory.createTitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import com.login.Session;
import com.entity.*;
import com.dao.*; 

import java.awt.Insets;

/**
 * 人员基本信息模块 
 */
public class BaseInfoPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private JComboBox manageComboBox = null; 
    private JTextField userNameField = null;   
    private JLabel jLabel2 = null; 
    private JLabel jLabel1;
    private JTextField nameField;
    private Person person; 
    private JLabel jLabel = null;
    private JPasswordField passwordField = null;
    
    /**
     * 构造方法
     */
    public BaseInfoPanel() {
        super();
        this.setName("基本资料");
        initialize();// 初始化界面
        initDatas();// 初始化界面数据
        // 添加事件监听器
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // 在显示界面时，重新初始化界面数据
                initDatas();
            }
        });
    }
    
    /**
     * 初始化界面数据
     */
    private void initDatas() {
        // 获取登录用户
        Person user = Session.getUser();  
        setPerson(user);
    }
    
    /**
     * 初始化程序界面
     * 
     * @return void
     */
    private void initialize() {
        GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
        gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints21.gridy = 0;
        gridBagConstraints21.weightx = 1.0;
        gridBagConstraints21.anchor = GridBagConstraints.CENTER;
        gridBagConstraints21.insets = new Insets(0, 0, 0, 5);
        gridBagConstraints21.gridwidth = 3;
        gridBagConstraints21.gridx = 3;
        GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
        gridBagConstraints13.gridx = 2;
        gridBagConstraints13.gridy = 0;
        GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
        gridBagConstraints12.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints12.gridwidth = 5;
        gridBagConstraints12.gridx = 1;
        gridBagConstraints12.gridy = 5;
        gridBagConstraints12.ipadx = 0;
        gridBagConstraints12.ipady = 0;
        gridBagConstraints12.weightx = 0.0;
        gridBagConstraints12.anchor = GridBagConstraints.NORTH;
        gridBagConstraints12.weighty = 0.0;
        gridBagConstraints12.insets = new Insets(0, 0, 0, 5);
        GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
        gridBagConstraints11.insets = new Insets(0, 5, 0, 5);
        gridBagConstraints11.gridy = 5;
        gridBagConstraints11.ipadx = 1;
        gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints11.weighty = 1.0;
        gridBagConstraints11.anchor = GridBagConstraints.NORTH;
        gridBagConstraints11.weightx = 0.0;
        gridBagConstraints11.ipady = 10;
        gridBagConstraints11.gridx = 0;
        GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
        gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints10.gridwidth = 5;
        gridBagConstraints10.gridx = 1;
        gridBagConstraints10.gridy = 4;
        gridBagConstraints10.ipadx = 0;
        gridBagConstraints10.weightx = 1.0;
        gridBagConstraints10.weighty = 0.0;
        gridBagConstraints10.insets = new Insets(0, 0, 0, 5);
        GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
        gridBagConstraints9.insets = new Insets(0, 5, 0, 5);
        gridBagConstraints9.gridy = 4;
        gridBagConstraints9.ipadx = 1;
        gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints9.gridx = 0;
        GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.insets = new Insets(0, 5, 0, 5);
        gridBagConstraints7.gridy = 1;
        gridBagConstraints7.ipadx = 1;
        gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints7.gridheight = 1;
        gridBagConstraints7.gridx = 2;
        GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.ipadx = 0;
        gridBagConstraints6.weightx = 1.0;
        gridBagConstraints6.anchor = GridBagConstraints.CENTER;
        gridBagConstraints6.gridwidth = 1;
        gridBagConstraints6.insets = new Insets(0, 0, 0, 5);
        GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.insets = new Insets(0, 5, 0, 5);
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.ipadx = 0;
        gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints5.anchor = GridBagConstraints.CENTER;
        gridBagConstraints5.gridx = 0;
        GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.ipadx = 0;
        gridBagConstraints4.weightx = 1.0;
        gridBagConstraints4.anchor = GridBagConstraints.NORTH;
        gridBagConstraints4.insets = new Insets(0, 0, 0, 5);
        GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.gridx = 5;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.ipadx = 0;
        gridBagConstraints3.weightx = 0.0;
        gridBagConstraints3.insets = new Insets(0, 3, 0, 5);
        GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.ipadx = 0;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
        GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.insets = new Insets(0, 5, 0, 5);
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.ipadx = 1;
        gridBagConstraints1.gridx = 4;
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        jLabel = new JLabel();// 密码标签
        jLabel.setText("密码："); 
        jLabel2 = new JLabel();// 性别标签
        jLabel2.setText("性别：");
        jLabel1 = new JLabel();// 姓名标签
        jLabel1.setText("姓名："); 
        // 设置布局管理器
        this.setLayout(new GridBagLayout());
        // 设置边框
        this.setBorder(createTitledBorder(null, "基本资料",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
                        "sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
         
        // 添加是否为管理员下拉选择框
        this.add(getManageComboBox(), gridBagConstraints10);
        // 添加姓名文本框
        this.add(getNameField(), gridBagConstraints6);
        // 添加密码框
        this.add(getPasswordField(), gridBagConstraints4);
        this.add(jLabel1, gridBagConstraints5); 
        this.add(jLabel2, gridBagConstraints9); 
        this.add(jLabel, gridBagConstraints11); 
        
    }
    
    /**
     * This method initializes nameField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getNameField() {
        if (nameField == null) {
            nameField = new JTextField();
            nameField.setColumns(10);
            nameField.setMinimumSize(new Dimension(50, 30));
        }
        return nameField;
    }
    
    /**
     * This method initializes sexComboBox
     * 
     * @return javax.swing.JTextField
     */
    private JComboBox getManageComboBox() {
        if (manageComboBox == null) {
            manageComboBox = new JComboBox(new String[] { "yes", "no" });
            manageComboBox.setSelectedIndex(0);
            manageComboBox.setBackground(Color.white);
        }
        return manageComboBox;
    } 
    /**
     * This method initializes userNameField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getUserNameField() {
        if (userNameField == null) {
            userNameField = new JTextField();
            userNameField.setMinimumSize(new Dimension(50, 30));
            userNameField.setEditable(false);
        }
        Person user = Session.getUser();
        if (user == null) {
            userNameField.setEditable(false);
        } else if (Session.getUser().getisManage() == "yes")//只有管理员才有修改的权限
            userNameField.setEditable(true);
        else
            userNameField.setEditable(false);
        return userNameField;
    }  
    /**
     * 获取修改后的人员对象
     * 
     * @return
     */
    public Person getPerson() {
        if (person == null)
            return null;
        // 更新人员对象的姓名
        person.setuserName(getNameField().getText()); 
        // 更新人员对象的性别
        person.setisManage((String) getManageComboBox().getSelectedItem());
        // 更新人员对象的密码
        person.setpassWord(getPasswordField().getText());
        return person;
    }
    
    /**
     * 为界面设置指定人员对象的信息
     * 
     * @param Person
     */
    public void setPerson(Person Person) {
        this.person = Person;
        if (Person == null)
            return;
        // 更新界面的名称文本框
        getNameField().setText(Person.getuserName());
        // 更新界面的账号文本框
        getUserNameField().setText(Person.getuserName()); 
        // 更新界面的是否为管理员下拉选择框
        getManageComboBox().setSelectedItem(Person.getisManage()); 
        // 更新界面的登录密码框
        getPasswordField().setText(Person.getpassWord());
    }
    
    /**
     * This method initializes passwordField
     * 
     * @return javax.swing.JPasswordField
     */
    private JPasswordField getPasswordField() {
        if (passwordField == null) {
            passwordField = new JPasswordField();
        }
        return passwordField;
    }
}
