package com.person;

import static javax.swing.BorderFactory.createTitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import com.login.Session;
import com.Listener.ActionAdapter;
import com.dao.*; 
import com.exception.*; 
import com.entity.*;
import com.widget.AlphaScrollPane;

public class PersonInfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel contrlPanel = null;
    private JButton okButton = null;
    private AlphaScrollPane jScrollPane2 = null;
    private JList jList = null;
    private JButton delButton = null;
    private JPanel infoPanel;  
    private BaseInfoPanel baseInfoPanel = null; 
    private JButton newButton = null;
    private Person person; 
    /**
     * 人员信息操作列表的选择事件监听器 
     */
    private class PersonListSelectListener implements ListSelectionListener,
            Serializable {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                JList list = (JList) e.getSource();// 获取事件源
                // 获取列表选项并转换为字符串
                String value = (String) list.getSelectedValue();
                // 获取上一级容器面板
                CardLayout layout = (CardLayout) getInfoPanel().getLayout();
                layout.show(getInfoPanel(), value);
                doLayout();
            }
        }
    }
    
    /**
     * 构造方法
     */
    public PersonInfoPanel() {
        super();
        setName("人员管理");
        initialize();// 初始化程序界面
        // 用登录用户对象设置界面控件值
        setPerson(Session.getUser());
        // 添加事件监听器
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // 显示窗体时重新设置界面信息为登录用户的信息
                setPerson(Session.getUser());
                
            }
        });
    }
    
    /**
     * 初始化程序界面
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(525, 387);// 初始大小
        this.setLayout(new BorderLayout());// 设置布局管理器
        // 设置边框
        this.setBorder(BorderFactory.createTitledBorder(null, "用户信息",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP,
                new Font("sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
        // 添加信息面板
        this.add(getInfoPanel(), BorderLayout.CENTER); 
        // 添加控制面板
        this.add(getContrlPanel(), BorderLayout.SOUTH);
    }
     
    
    /**
     * 人员信息操作列表
     * 
     * @return javax.swing.JList
     */
    private JList getJList() {
        if (jList == null) {
            // 创建带选项的列表控件
            jList = new JList(new String[] { "基本资料", "联系方式", "详细资料" });
            // 设置使用单选模式
            jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jList.setSelectedIndex(0);// 默认选择第一项
            // 添加选择事件监听器
            jList.addListSelectionListener(new PersonListSelectListener());
        }
        return jList;
    }
    
    /**
     * 信息面板
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getInfoPanel() {
        if (infoPanel == null) {
            // 创建面板
            infoPanel = new JPanel();
            // 设置卡片布局管理器
            infoPanel.setLayout(new CardLayout());
            infoPanel.setOpaque(false);// 面板透明
            // 添加基本信息面板
            infoPanel.add(getBaseInfoPanel(), getBaseInfoPanel().getName());  
        }
        return infoPanel;
    } 
    /**
     * 按钮控制面板
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getContrlPanel() {
        if (contrlPanel == null) {
            // 创建布局管理器
            FlowLayout flowLayout1 = new FlowLayout();
            flowLayout1.setHgap(35);// 设置水平布局间距
            contrlPanel = new JPanel();// 创建控制面板
            contrlPanel.setOpaque(false);// 面板透明
            // 设置布局管理器
            contrlPanel.setLayout(flowLayout1);
            contrlPanel.setBorder(null);// 取消边框
            // 添加新建按钮
            contrlPanel.add(getNewButton(), null);
            // 添加确定按钮
            contrlPanel.add(getOkButton(), null);
            // 添加删除按钮
            contrlPanel.add(getDelButton(), null);
        }
        return contrlPanel;
    }
    
    /**
     * 确定按钮
     * 
     * @return javax.swing.JButton
     */
    private JButton getOkButton() {
        if (okButton == null) {
            okButton = new JButton();
            okButton.setText("确定");// 设置按钮文本
            // 添加事件监听器
            okButton.addActionListener(new ActionAdapter() {
                public void actionPerformed(ActionEvent e) {
                    // 获取保存基本信息的人员对象
                    Person per = getBaseInfoPanel().getPerson(); 
                    if (per == null) {// 保证录入基本信息
                        JOptionPane.showMessageDialog(null, "信息不全");
                        return;
                    }
                    // 创建人员数据库操作对象
                    PersonDAO dao = new PersonDAO();
                    if (per.getuserName() == null) {// 添加新人员
                        try {
                            String re = dao.addPerson(per);
                            if (re == "success")
                                JOptionPane.showMessageDialog(null, "添加完成");
                            else
                            	JOptionPane.showMessageDialog(null, "添加失败");
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        try {// 更新人员信息
                            String re = dao.updatePerson(per);
                            if (re == "success")
                                JOptionPane.showMessageDialog(null, "修改完成");
                            else
                            	JOptionPane.showMessageDialog(null, "修改失败");
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
        }
        return okButton;
    }
    
    /**
     * 删除按钮
     * 
     * @return javax.swing.JButton
     */
    private JButton getDelButton() {
        if (delButton == null) {
            delButton = new JButton();
            delButton.setText("删除");
            delButton.addActionListener(new ActionAdapter() {
                public void actionPerformed(ActionEvent e) {
                    // 获取人员对象
                    Person per = getBaseInfoPanel().getPerson();
                    if (per == null || per.getuserName() == null)
                        return;
                    // 显示确认对话框
                    int option = JOptionPane.showConfirmDialog(null, "确认要删除"
                            + per.getuserName() + "的信息吗？");
                    if (option != JOptionPane.YES_OPTION)
                        return;
                    // 创建人员数据库操作对象
                    PersonDAO dao = new PersonDAO();
                    try {
                        // 从数据库删除指定人员对象
                        String re = dao.deletePerson(per.getuserName());
                        if(re == "success")
                        	JOptionPane.showMessageDialog(null, "操作完成");
                        else
                        	JOptionPane.showMessageDialog(null, "操作失败");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        // 获取登录用户
        Person user = Session.getUser();
        // 根据用户是否是管理员，确认按钮是否可用
        if (user == null) {
            delButton.setEnabled(false);
        } else if (Session.getUser().getisManage() == "yes")
            delButton.setEnabled(true);
        else
            delButton.setEnabled(false);
        return delButton;
    }
    
    /**
     * This method initializes baseInfoPanel
     * 
     * @return com.lzw.personnel.BaseInfoPanel
     */
    private BaseInfoPanel getBaseInfoPanel() {
        if (baseInfoPanel == null) {
            baseInfoPanel = new BaseInfoPanel();
            baseInfoPanel.setOpaque(false);
        }
        return baseInfoPanel;
    }
    
    /**
     * 新建按钮
     * 
     * @return javax.swing.JButton
     */
    private JButton getNewButton() {
        if (newButton == null) {
            newButton = new JButton();
            newButton.setText("新建");// 设置按钮文本
            // 添加事件监听器
            newButton.addActionListener(new ActionAdapter() {
                public void actionPerformed(ActionEvent e) {
                    // 创建新的人员对象
                    Person person= new Person();
                    person.setisManage("no");// 初始化对象性别
                    // 初始化界面数据
                    getBaseInfoPanel().setPerson(person); 
                }
            });
        }
        // 获取登录用户对象
        Person user = Session.getUser();
        // 根据是否是管理员判定按钮是否可用
        if (user == null) {
            newButton.setEnabled(false);
        } else if (user.getisManage() == "yes")
            newButton.setEnabled(true);
        else
            newButton.setEnabled(false);
        return newButton;
    }
    
    public Person getPerson() {
        return person;
    }
    
    /**
     * 设置人员对象，并同时更新界面数据
     * 
     * @param personnel
     */
    public void setPerson(Person person) {
        this.person = person;
        if (person == null)
            return; 
        // 更新界面数据
        getBaseInfoPanel().setPerson(person); 
    }
}  
