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
 * ��Ա������Ϣģ�� 
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
     * ���췽��
     */
    public BaseInfoPanel() {
        super();
        this.setName("��������");
        initialize();// ��ʼ������
        initDatas();// ��ʼ����������
        // ����¼�������
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // ����ʾ����ʱ�����³�ʼ����������
                initDatas();
            }
        });
    }
    
    /**
     * ��ʼ����������
     */
    private void initDatas() {
        // ��ȡ��¼�û�
        Person user = Session.getUser();  
        setPerson(user);
    }
    
    /**
     * ��ʼ���������
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
        jLabel = new JLabel();// �����ǩ
        jLabel.setText("���룺"); 
        jLabel2 = new JLabel();// �Ա��ǩ
        jLabel2.setText("�Ա�");
        jLabel1 = new JLabel();// ������ǩ
        jLabel1.setText("������"); 
        // ���ò��ֹ�����
        this.setLayout(new GridBagLayout());
        // ���ñ߿�
        this.setBorder(createTitledBorder(null, "��������",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
                        "sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
         
        // ����Ƿ�Ϊ����Ա����ѡ���
        this.add(getManageComboBox(), gridBagConstraints10);
        // ��������ı���
        this.add(getNameField(), gridBagConstraints6);
        // ��������
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
        } else if (Session.getUser().getisManage() == "yes")//ֻ�й���Ա�����޸ĵ�Ȩ��
            userNameField.setEditable(true);
        else
            userNameField.setEditable(false);
        return userNameField;
    }  
    /**
     * ��ȡ�޸ĺ����Ա����
     * 
     * @return
     */
    public Person getPerson() {
        if (person == null)
            return null;
        // ������Ա���������
        person.setuserName(getNameField().getText()); 
        // ������Ա������Ա�
        person.setisManage((String) getManageComboBox().getSelectedItem());
        // ������Ա���������
        person.setpassWord(getPasswordField().getText());
        return person;
    }
    
    /**
     * Ϊ��������ָ����Ա�������Ϣ
     * 
     * @param Person
     */
    public void setPerson(Person Person) {
        this.person = Person;
        if (Person == null)
            return;
        // ���½���������ı���
        getNameField().setText(Person.getuserName());
        // ���½�����˺��ı���
        getUserNameField().setText(Person.getuserName()); 
        // ���½�����Ƿ�Ϊ����Ա����ѡ���
        getManageComboBox().setSelectedItem(Person.getisManage()); 
        // ���½���ĵ�¼�����
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
