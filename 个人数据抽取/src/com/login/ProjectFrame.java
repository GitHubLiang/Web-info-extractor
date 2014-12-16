package com.login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.Listener.ActionAdapter; 
import com.Frame.*;
import com.entity.*; 
import com.widget.*;

import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;

public class ProjectFrame extends JFrame {
    /**
     * 工具按钮的事件监听处理器
     * 
     * @author Administrator
     */
    private final class ToolsButtonActionAdapter extends ActionAdapter {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            showPanel(command);
        }
    }
    
    private Point spoint;
    private static final long serialVersionUID = 1L;
    private BGPanel jContentPane = null;
    private BGPanel bgPanel = null;
    private GlassButton showMyTableButton = null; 
    private GlassButton extracTableButton = null;
    private BGPanel jPanel = null;  
    private BGPanel leftPanel2 = null;
    private BGPanel functionPanel = null;
    private BGPanel locationPanel = null;
    private JLabel jLabel3 = null;
    private JLabel currentLocationLabel = null;
    private BGPanel mainPanel = null;
    private ShowMyTable showMyTable = null;
    private ToolsButtonActionAdapter toolsButtonActionAdapter; // @jve:decl-index=0:
    private ExtracTablePanel extracTablePanel = null; 
    private BGPanel jPanel4 = null;
    private BGPanel bgPanel2 = null;
    private BGPanel bgPanel1 = null;
    private SmallScrollPanel moduleButtonGroup = null;
    private GlassButton personManageButton = null;
    private PersonPanel personPanel = null;  
    private AlphaScrollPane jScrollPane = null;
    private ButtonGroup buttonGroup = null; // @jve:decl-index=0:visual-constraint="505,680"
    private JPanel workPanel = null;
    private BGPanel personManagePanel = null;
    private BGPanel loginInfoPanel = null;
    private ButtonGroup faceButtonGroup = null; // @jve:decl-index=0:visual-constraint="587,680"
    private JLabel jLabel = null;
    private PersonTree personTree = null;
    private BGPanel treeToolsBar = null;
    private JLabel jLabel1 = null;
    private JButton refrenshButton = null;
    private ClockPanel clockPanel = null;
    private JLabel jLabel2 = null;
    private JLabel jLabel4 = null;
    private JLabel jLabel5 = null;
    private JPanel jPanel1 = null;
    
    /**
     * This is the default constructor
     */
    public ProjectFrame() {
        super();
        toolsButtonActionAdapter = new ToolsButtonActionAdapter();
        initialize();
    }
    
    /**
     * 创建顶层面板和功能按钮组
     * 
     */
    private BGPanel getBGPanel() {
        if (bgPanel == null) {
            bgPanel = new BGPanel();
            // 设置布局管理器
            bgPanel.setLayout(new BorderLayout());
            // 设置初始高度
            bgPanel.setPreferredSize(new Dimension(0, 97));
            bgPanel.setOpaque(false);// 面板透明
            // 背景图片横向填充
            bgPanel.setIconFill(BGPanel.HORIZONGTAL_FILL);
            bgPanel.setIcon(new ImageIcon(getClass().getResource(
                    "/com/Frame/top.png")));
            // 
            bgPanel.add(getJPanel4(), BorderLayout.CENTER);
            bgPanel.add(getBGPanel2(), BorderLayout.WEST);
            bgPanel.add(getBGPanel1(), BorderLayout.EAST);
        }
        return bgPanel;
    }
    
    /**
     * This method initializes showMyTableButton
     *  我的表格
     */
    private GlassButton getshowMyTableButton() {
        if (showMyTableButton == null) {
            showMyTableButton = new GlassButton();
            showMyTableButton.setActionCommand("我的表格");
            showMyTableButton.setIcon(new ImageIcon(getClass().getResource(
                    "/com/Frame/buttonIcons/myWorkSpace.png")));	//需要改图片
            ImageIcon icon = new ImageIcon(getClass().getResource(
                    "/com/Frame/buttonIcons/myWorkSpace2.png"));
            showMyTableButton.setRolloverIcon(icon);
            showMyTableButton.setSelectedIcon(icon);
            showMyTableButton.setSelected(true);
            // showMyTableButton.setText("我的工作台");
            showMyTableButton.addActionListener(toolsButtonActionAdapter);
        }
        return showMyTableButton;
    }
    /**
     * This method initializes bookProjectButton
     * 可以改为表格提取
     * @return com.lzw.client.widget.GlassButton
     */
    private GlassButton getExtracTableButton() {
        if (extracTableButton == null) {
        	extracTableButton = new GlassButton();
        	extracTableButton.setActionCommand("提取表格"); 
            ImageIcon icon = new ImageIcon(getClass().getResource(
                    "/com/frame/buttonIcons/bookProjectButton2.png"));
            extracTableButton.setSelectedIcon(icon);
            extracTableButton.setRolloverIcon(icon);
            extracTableButton.setIcon(new ImageIcon(getClass().getResource(
                    "/com/frame/buttonIcons/bookProjectButton.png")));
            extracTableButton.addActionListener(toolsButtonActionAdapter);
        }
        return extracTableButton;
    }
    
    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private BGPanel getJContentPane() {
        if (jContentPane == null) {
            BorderLayout borderLayout1 = new BorderLayout();
            borderLayout1.setHgap(5);
            borderLayout1.setVgap(5);
            jContentPane = new BGPanel();
            jContentPane.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            jContentPane.setLayout(borderLayout1);
            jContentPane.add(getBGPanel(), BorderLayout.NORTH);
            jContentPane.add(getLeftPanel2(), BorderLayout.WEST);
            jContentPane.add(getFunctionPanel(), BorderLayout.CENTER);
        }
        return jContentPane;
    }
    
    /**
     * 创建按钮组面板 需要3个就行
     * 
     * @return javax.swing.JPanel
     */
    private BGPanel getJPanel() {
        if (jPanel == null) {
            GridLayout gridLayout = new GridLayout();
            gridLayout.setRows(1);
            gridLayout.setHgap(0);
            gridLayout.setVgap(0);
            jPanel = new BGPanel();
            // 设置布局管理器
            jPanel.setLayout(gridLayout);
            // 设置初始大小
            jPanel.setPreferredSize(new Dimension(400, 50));
            jPanel.setOpaque(false);// 设置透明
            jPanel.setSize(new Dimension(381, 54));
            // 添加按钮
            jPanel.add(getshowMyTableButton(), null);// 我的表格按钮
            jPanel.add(getExtracTableButton(),null);//提取表格按钮
            jPanel.add(getPersonManageButton(), null);// 人员管理按钮 
            if (buttonGroup == null) {
                buttonGroup = new ButtonGroup();
            }
            // 把所有按钮添加到一个组控件中
            buttonGroup.add(getshowMyTableButton()); 
            buttonGroup.add(getExtracTableButton());
            buttonGroup.add(getPersonManageButton()); 
        }
        return jPanel;
    }
    
    /**
     * This method initializes leftPanel2
     * 左边的面板，上部显示时钟和登录人信息
     * 下部显示最新动态
     * @return javax.swing.JPanel
     */
    private BGPanel getLeftPanel2() {
        if (leftPanel2 == null) {
            BorderLayout borderLayout = new BorderLayout();
            borderLayout.setVgap(5);
            leftPanel2 = new BGPanel();
            leftPanel2.setLayout(borderLayout);
            leftPanel2.setIconFill(BGPanel.BOTH_FILL);
            leftPanel2.setPreferredSize(new Dimension(177, 0));
            leftPanel2.add(getPersonManagePanel(), BorderLayout.CENTER);
            leftPanel2.add(getLoginInfoPanel(), BorderLayout.NORTH);
        }
        return leftPanel2;
    }
    
    /**
     * 功能区面板
     * 
     * @return javax.swing.JPanel
     */
    private BGPanel getFunctionPanel() {
        if (functionPanel == null) {
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.gridx = 0;
            gridBagConstraints3.ipadx = 0;
            gridBagConstraints3.ipady = 0;
            gridBagConstraints3.fill = GridBagConstraints.BOTH;
            gridBagConstraints3.weightx = 1.0;
            gridBagConstraints3.weighty = 1.0;
            gridBagConstraints3.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints3.gridy = 1;
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.gridx = 0;
            gridBagConstraints2.ipadx = 0;
            gridBagConstraints2.fill = GridBagConstraints.BOTH;
            gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints2.ipady = 0;
            gridBagConstraints2.gridy = 0;
            functionPanel = new BGPanel();
            // 设置布局管理器
            functionPanel.setLayout(new GridBagLayout());
            functionPanel.setOpaque(false);// 面板透明
            functionPanel.setIconFill(BGPanel.BOTH_FILL);
            // 设置背景
            functionPanel.setIcon(new ImageIcon(getClass().getResource(
                    "/com/Frame/right.png")));
            // 添加位置标识面板
            functionPanel.add(getLocationPanel(), gridBagConstraints2);
            // 添加主面板
            functionPanel.add(getMainPanel(), gridBagConstraints3);
        }
        return functionPanel;
    }
    
    /**
     * 位置标识面板
     * 
     * @return javax.swing.JPanel
     */
    private BGPanel getLocationPanel() {
        if (locationPanel == null) {
            GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
            gridBagConstraints13.gridx = 1;
            gridBagConstraints13.ipady = 4;
            gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints13.gridy = 1;
            // 站位标签
            jLabel = new JLabel();
            jLabel.setText("");// 初始空文本
            GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
            gridBagConstraints10.gridx = 1;
            gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints10.weightx = 1.0;
            gridBagConstraints10.insets = new Insets(0, 3, 0, 2);
            gridBagConstraints10.anchor = GridBagConstraints.SOUTH;
            gridBagConstraints10.weighty = 0.0;
            gridBagConstraints10.ipady = 8;
            gridBagConstraints10.gridy = 0;
            // 初始化位置标识标签控件
            currentLocationLabel = new JLabel();
            // 设置标签文本
            currentLocationLabel.setText("我的表格");
            // 设置垂直对齐方式
            currentLocationLabel.setVerticalAlignment(SwingConstants.BOTTOM);
            Font font = currentLocationLabel.getFont().deriveFont(Font.BOLD);
            // 设置字体
            currentLocationLabel.setFont(font);
            GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
            gridBagConstraints9.gridy = 0;
            gridBagConstraints9.insets = new Insets(0, 20, 0, 0);
            gridBagConstraints9.anchor = GridBagConstraints.CENTER;
            gridBagConstraints9.fill = GridBagConstraints.NONE;
            gridBagConstraints9.ipady = 8;
            gridBagConstraints9.weighty = 0.0;
            gridBagConstraints9.ipadx = 0;
            gridBagConstraints9.gridx = 0;
            // 说明性标签控件
            jLabel3 = new JLabel();
            font = jLabel3.getFont().deriveFont(Font.BOLD);
            jLabel3.setFont(font);
            // 设置标签文本
            jLabel3.setText("您当前的位置：");
            // 设置标签对齐方式
            jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
            jLabel3.setVerticalAlignment(SwingConstants.BOTTOM);
            locationPanel = new BGPanel();// 创建位置面板
            // 设置位置面板的布局管理器
            locationPanel.setLayout(new GridBagLayout());
            locationPanel.setOpaque(false);// 面板透明
            locationPanel.add(jLabel3, gridBagConstraints9);
            // 添加位置标识标签控件
            locationPanel.add(currentLocationLabel, gridBagConstraints10);
            locationPanel.add(jLabel, gridBagConstraints13);
        }
        return locationPanel;
    }
    
    /**
     * This method initializes myshowMyTablePanel
     * 
     * @return MyshowMyTablePanel
     */
    private ShowMyTable getshowMyTablePanel() {
    	System.out.println("showMytable");
        if (showMyTable == null) {
            showMyTable = new ShowMyTable();
            showMyTable.setOpaque(false);
        }
        return showMyTable;
    }
    
    /**
     * 功能区的主面板
     * 
     * @return JPanel
     */
    private BGPanel getMainPanel() {
        if (mainPanel == null) {
            GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.gridx = 0;
            gridBagConstraints4.ipadx = 0;
            gridBagConstraints4.ipady = 0;
            gridBagConstraints4.fill = GridBagConstraints.BOTH;
            gridBagConstraints4.weightx = 1.0;
            gridBagConstraints4.weighty = 1.0;
            gridBagConstraints4.insets = new Insets(5, 12, 5, 20);
            gridBagConstraints4.gridy = 0;
            mainPanel = new BGPanel();// 创建主面板
            // 设置布局管理器
            mainPanel.setLayout(new GridBagLayout());
            mainPanel.setOpaque(false);// 面板透明
            // 添加工作面板
            mainPanel.add(getWorkPanel(), gridBagConstraints4);
        }
        return mainPanel;
    }
    
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setTitle("HIT表格小助手—————" + Session.getUser().getuserName() + "在线");
        this.setContentPane(getJContentPane());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(900, 650);
        this.setLocationRelativeTo(null);
    }
    
    private void showPanel(String paneName) {
        LayoutManager layout = getWorkPanel().getLayout();
        CardLayout cl = (CardLayout) layout;
        cl.show(getWorkPanel(), paneName);
        currentLocationLabel.setText(paneName);
    }
     
    /**
     * 提取表格
     * @return
     */
    private ExtracTablePanel getExtracTablePanel() {
        System.out.println("提取表格1");
        if (extracTablePanel == null) {
        	extracTablePanel = new ExtracTablePanel();
        	extracTablePanel.setOpaque(false);
        }
        return extracTablePanel;
    } 
    
    /**
     * This method initializes jPanel4
     * 
     * @return javax.swing.JPanel
     */
    private BGPanel getJPanel4() {
        if (jPanel4 == null) {
            GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
            gridBagConstraints6.gridx = 2;
            gridBagConstraints6.fill = GridBagConstraints.VERTICAL;
            gridBagConstraints6.insets = new Insets(18, 0, 0, 0);
            gridBagConstraints6.gridy = 0;
            GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.gridx = 0;
            gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
            gridBagConstraints5.insets = new Insets(18, 0, 0, 0);
            gridBagConstraints5.gridy = 0;
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.ipady = 0;
            gridBagConstraints.insets = new Insets(10, 15, 10, 15);
            jPanel4 = new BGPanel();
            jPanel4.setLayout(new GridBagLayout());
            jPanel4.setOpaque(false);
            jPanel4.setIconFill(BGPanel.HORIZONGTAL_FILL);
            jPanel4.add(getModuleButtonGroup(), gridBagConstraints);
        }
        return jPanel4;
    }
    
    /**
     * This method initializes BGPanel
     * 
     * @return com.lzw.client.widget.BGPanel
     */
    private BGPanel getBGPanel2() {
        if (bgPanel2 == null) {
            GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
            gridBagConstraints11.gridx = 0;
            gridBagConstraints11.anchor = GridBagConstraints.CENTER;
            gridBagConstraints11.insets = new Insets(7, 0, 0, 10);
            gridBagConstraints11.gridy = 0;
            jLabel4 = new JLabel();
//            jLabel4.setIcon(new ImageIcon(getClass().getResource(
//                    "/com/Frame/logo.png")));
            bgPanel2 = new BGPanel();
            bgPanel2.setPreferredSize(new Dimension(230, 109));
            bgPanel2.setOpaque(false);
            bgPanel2.add(jLabel4, gridBagConstraints11);
        }
        return bgPanel2;
    }
    
    /**
     * This method initializes BGPanel1
     * 
     * @return com.lzw.client.widget.BGPanel
     */
    private BGPanel getBGPanel1() {
        if (bgPanel1 == null) {
            jLabel5 = new JLabel();
            jLabel5.setBounds(new Rectangle(9, 7, 179, 88));
//            jLabel5.setIcon(new ImageIcon(getClass().getResource(
//                    "/com/Frame/pc01.png")));
            bgPanel1 = new BGPanel();
            bgPanel1.setLayout(null);
            bgPanel1.setPreferredSize(new Dimension(217, 109));
            bgPanel1.setOpaque(false);
            bgPanel1.add(jLabel5, null);
        }
        return bgPanel1;
    }
    
    /**
     * 模块按钮组
     * 
     * @return com.lzw.widget.AlphaScrollPane
     */
    private SmallScrollPanel getModuleButtonGroup() {
        if (moduleButtonGroup == null) {
            moduleButtonGroup = new SmallScrollPanel();// 创建移动面板
            moduleButtonGroup.setOpaque(false);
            // 将按钮组面板作为移动面板的视图
            moduleButtonGroup.setViewportView(getJPanel());
            // 添加鼠标事件监听器
        }
        return moduleButtonGroup;
    }
    
    /**
     * This method initializes personManageButton
     * 
     * @return javax.swing.JButton
     */
    private GlassButton getPersonManageButton() {
        if (personManageButton == null) {
            personManageButton = new GlassButton();
            // personManageButton.setText("人员管理");
            personManageButton
                    .setActionCommand("人员管理");
            personManageButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(
                    "/com/Frame/buttonIcons/personnelManagerButton.png"));
            personManageButton.setIcon(imageIcon);
            ImageIcon icon = new ImageIcon(getClass().getResource(
                    "/com/Frame/buttonIcons/personnelManagerButton2.png"));
            personManageButton.setRolloverIcon(icon);
            personManageButton.setSelectedIcon(icon);
            personManageButton.setFocusPainted(false);
            personManageButton.addActionListener(toolsButtonActionAdapter);
        }
        return personManageButton;
    }
    
    /**
     * 创建员工信息面板
     * 
     * @return
     */
    private PersonPanel getPersonPanel() {
        if (personPanel == null) {
            personPanel = new PersonPanel();
            personPanel.setOpaque(false);
        }
        return personPanel;
    } 
    /**
     * This method initializes jScrollPane
     * 
     * @return javax.swing.JScrollPane
     */
    private AlphaScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new AlphaScrollPane();
            jScrollPane.setMinimumSize(new Dimension(130, 25));
            jScrollPane.setViewportBorderPaint(true);
            jScrollPane.setViewportView(getPersonTree());
        }
        return jScrollPane;
    }
    
    /**
     * 工作面板
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getWorkPanel() {
        if (workPanel == null) {
            workPanel = new JPanel();
            // 设置布局管理器
            workPanel.setLayout(new CardLayout());
            workPanel.setOpaque(false);// 面板透明
            // 添加我的工作台面板
            workPanel.add(getshowMyTablePanel(), getshowMyTablePanel()
                    .getName());
            // 添加图书计划面板
            workPanel.add(getExtracTablePanel(), getExtracTablePanel()
                    .getName()); 
            // 添加人员管理面板
            workPanel.add(getPersonPanel(), getPersonPanel().getName());
        }
        return workPanel;
    }
    
    /**
     * 人员管理面板
     * 
     * @return com。widget.BGPanel
     */
    private BGPanel getPersonManagePanel() {
        if (personManagePanel == null) {
            personManagePanel = new BGPanel();// 创建面板
            // 设置布局管理器
            personManagePanel.setLayout(new BorderLayout());
            // 面板透明
            personManagePanel.setOpaque(false);
            // 设置背景
            personManagePanel.setIcon(new ImageIcon(getClass().getResource(
                    "/com/Frame/perBack.png")));
            // 背景双向填充
            personManagePanel.setIconFill(BGPanel.BOTH_FILL);
            // 设置初始大小
            personManagePanel.setPreferredSize(new Dimension(177, 900));
            // 添加工具面板
            personManagePanel.add(getTreeToolsBar(), BorderLayout.NORTH);
            // 添加人员信息的树组件
            personManagePanel.add(getJPanel1(), BorderLayout.CENTER);
        }
        return personManagePanel;
    }
    
    /**
     * 登录信息面板
     * 
     * @return com.widget.BGPanel
     */
    private BGPanel getLoginInfoPanel() {
        if (loginInfoPanel == null) {
            // 创建布局参数1
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.gridx = 0;
            gridBagConstraints1.weighty = 0.0;
            gridBagConstraints1.fill = GridBagConstraints.BOTH;
            gridBagConstraints1.insets = new Insets(0, 5, 0, 5);
            gridBagConstraints1.gridheight = 1;
            gridBagConstraints1.weightx = 1.0;
            gridBagConstraints1.anchor = GridBagConstraints.CENTER;
            gridBagConstraints1.ipady = 15;
            gridBagConstraints1.gridy = 80;
            jLabel2 = new JLabel();// 创建登录信息标签
            Person user = Session.getUser();// 获取登录用户对象
            if (user != null) {// 如果用户成功登录
                // 定义欢迎信息字符串
                String info = "<html><body>"
                        + "<font color=#FFFFFF>你 好：</font>"
                        + "<font color=yellow><b>" + user.getuserName() + "</b></font>"
                        + "<br><font color=#FFFFFF>欢 迎 登 录</font>"
                        + "</body></html>";
                jLabel2.setText(info);// 设置欢迎信息
            }
            // 设置信息字体
            jLabel2.setFont(new Font("宋体", Font.PLAIN, 12));
            // 布局参数2
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.gridx = 3;
            gridBagConstraints2.anchor = GridBagConstraints.CENTER;
            gridBagConstraints2.fill = GridBagConstraints.NONE;
            gridBagConstraints2.weighty = 0.0;
            gridBagConstraints2.weightx = 0.0;
            gridBagConstraints2.insets = new Insets(35, 0, 0, 0);
            gridBagConstraints2.gridy = 30;
            loginInfoPanel = new BGPanel();// 创建面板
            // 设置面板图标
            loginInfoPanel.setIcon(new ImageIcon(getClass().getResource(
                    "/com/Frame/login.png")));
            loginInfoPanel.setIconFill(BGPanel.NO_FILL);
            // 设置初始大小
            loginInfoPanel.setPreferredSize(new Dimension(180, 228));
            // 添加时钟控件到面板
            loginInfoPanel.add(getClockPanel(), gridBagConstraints2);
            // 添加欢迎信息标签组件到面板
            loginInfoPanel.add(jLabel2, gridBagConstraints1);
        }
        return loginInfoPanel;
    }
    
    /**
     * 初始化人员树控件--改为最近动态控件
     * 
     * @return com.personnel.personTree
     */
    private PersonTree getPersonTree() {
        if (personTree == null) {
            personTree = new PersonTree();
            // 设置背景色
            personTree.setBackground(Color.WHITE);
            // 添加人员管理事件监听器
            personTree.addTreeSelectionListener(getPersonPanel());
            // 添加提取表格事件监听器 
            System.out.println("提取表格");
            personTree.addTreeSelectionListener(getExtracTablePanel());
            // 添加我的工作台事件监听器
            personTree.addTreeSelectionListener(getshowMyTablePanel());
        }
        return personTree;
    }
    
    /**
     * This method initializes treeToolsBar
     * 
     * @return javax.swing.JPanel
     */
    private BGPanel getTreeToolsBar() {
        if (treeToolsBar == null) {
            GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
            gridBagConstraints8.gridx = 1;
            gridBagConstraints8.ipady = 0;
            gridBagConstraints8.anchor = GridBagConstraints.CENTER;
            gridBagConstraints8.insets = new Insets(0, 0, 0, 2);
            gridBagConstraints8.fill = GridBagConstraints.NONE;
            gridBagConstraints8.gridy = 0;
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.gridx = 0;
            gridBagConstraints1.ipadx = 16;
            gridBagConstraints1.ipady = 0;
            gridBagConstraints1.insets = new Insets(0, 5, 7, 0);
            gridBagConstraints1.gridy = 0;
            jLabel1 = new JLabel();
            jLabel1.setIcon(new ImageIcon(getClass().getResource(
                    "/com/Frame/perList.png")));
            jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel1.setVerticalAlignment(SwingConstants.TOP);
            treeToolsBar = new BGPanel();
            treeToolsBar.setLayout(new GridBagLayout());
            treeToolsBar.setPreferredSize(new Dimension(177, 57));
            treeToolsBar.setIcon(new ImageIcon(getClass().getResource(
                    "/com/Frame/perTop.png")));
            treeToolsBar.add(jLabel1, gridBagConstraints1);
            treeToolsBar.add(getRefrenshButton(), gridBagConstraints8);
        }
        return treeToolsBar;
    }
    
    /**
     * This method initializes refrenshButton
     * 
     * @return javax.swing.JButton刷新按钮可以不用
     */
    private JButton getRefrenshButton() {
        if (refrenshButton == null) {
            refrenshButton = new JButton();
            refrenshButton.setIcon(new ImageIcon(getClass().getResource(
                    "/com/Frame/buttonIcons/refresh.png")));
            refrenshButton.setContentAreaFilled(false);
            refrenshButton.setBorderPainted(false);
            refrenshButton.setPressedIcon(new ImageIcon(getClass().getResource(
                    "/com/Frame/buttonIcons/refresh3.png")));
            refrenshButton.setRolloverIcon(new ImageIcon(getClass()
                    .getResource("/com/Frame/buttonIcons/refresh2.png")));
            refrenshButton.addActionListener(new ActionAdapter() {
                public void actionPerformed(ActionEvent e) {
                    getPersonTree().loadPerson();
                }
            });
        }
        return refrenshButton;
    }
    
    /**
     * This method initializes clockPanel
     * 
     * @return com.lzw.ClockPanel
     */
    private ClockPanel getClockPanel() {
        if (clockPanel == null) {
            clockPanel = new ClockPanel();
            clockPanel.setMinimumSize(new Dimension(207, 207));
        }
        return clockPanel;
    }
    
    /**
     * This method initializes jPanel1
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel1() {
        if (jPanel1 == null) {
            jPanel1 = new JPanel();
            jPanel1.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            jPanel1.setLayout(new BorderLayout());
            jPanel1.setOpaque(false);
            jPanel1.add(getJScrollPane(), null);
        }
        return jPanel1;
    }
}
