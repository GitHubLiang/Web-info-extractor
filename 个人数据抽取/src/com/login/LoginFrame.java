/**
 * 创建登陆窗体
 */
package com.login;

import static javax.swing.JOptionPane.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.*;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;
import com.entity.*;
import com.dao.*;
import com.widget.ClockPanel;
import com.widget.GlassButton; 
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
 
public class LoginFrame extends JFrame {
    
    /**
     * 登录按钮的事件监听器 
     */
    private final class loginActionListener implements ActionListener {
        /**
         * 模糊滤镜 
         */
        private ConvolveOp getFilter(int radius) {
            int size = radius * 2 + 1;
            float width = 1.0f / (size * size);
            float data[] = new float[size * size];
            for (int i = 0; i < data.length; i++) {
                data[i] = width;
            }
            Kernel kernel = new Kernel(size, size, data);
            return new ConvolveOp(kernel, ConvolveOp.EDGE_ZERO_FILL, null);
        }
        
        public void actionPerformed(ActionEvent e) {
            createBackImage();	//创建模糊的窗体界面
            String userNameStr = getUserName().getText();  //得到登录用户的相关信息
            char[] passChars = getPassword().getPassword();
            String passwordStr = new String(passChars);
            try {
                doLogin(userNameStr, passwordStr);// 执行登录
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        
        /**
         * 创建模糊的窗体界面
         */
        private void createBackImage() {
            Container pane = getContentPane();// 获取窗体容器
            int width = pane.getWidth();// 获取容器大小
            int height = pane.getHeight();
            System.out.println(width+"---"+height);
            // 创建图片对象
            BufferedImage bimage = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_ARGB);
            // 获取图片对象的绘图上下文
            Graphics2D g2 = bimage.createGraphics();
            pane.paint(g2);// 将容器界面绘制到图片对象
            bimage.flush();
            // 为图片对象应用模糊滤镜
            bimage = getFilter(2).filter(bimage, null);
            // 将模糊后的图片作为背景
            panel.setBackImage(bimage);
        }
        
        /**
         * 执行登录的方法
         * 
         * @param userNameStr
         *            用户名
         * @param passwordStr
         *            登录密码
         * @throws Exception
         * @throws InvocationTargetException
         * @throws InterruptedException
         */
        private void doLogin(final String userNameStr, final String passwordStr) {
            new Thread() {// 开辟新线程
                public void run() {
                    // 显示窗体的登录进度面板
                    getGlassPane().setVisible(true);
                    // 创建人员数据表操作对象
                    PersonDAO dao = new PersonDAO();
                    // 获取指定账户的人员对象
                    Person user = dao.getPerson(userNameStr, passwordStr);
                    if (user != null) {// 判断是否成功获取人员对象
                        Session.setUser(user);// 人员对象保存会话对象中
                        // 创建主窗体对象
                        ProjectFrame frame = new ProjectFrame();
                        frame.setVisible(true);// 显示主窗体
                        LoginFrame.this.dispose();// 销毁登录窗体
                    } else {
                        showMessageDialog(null, "提供的用户名和密码无法登录");
                    }
                    // 完成登录后隐藏登录进度面板
                    getGlassPane().setVisible(false);
                }
            }.start();
        }
    }
    
    private final class TitleMouseMotionadapter extends MouseMotionAdapter
            implements Serializable {
        public void mouseDragged(java.awt.event.MouseEvent e) {
            Point dpoint = e.getPoint();
            Point location = LoginFrame.this.getLocation();
            int y = dpoint.y + location.y - spoint.y;
            int x = dpoint.x + location.x - spoint.x;
            LoginFrame.this.setLocation(x, y);
        }
    }
    
    private final class TitleMouseAdapter extends MouseAdapter implements
            Serializable {
        public void mousePressed(java.awt.event.MouseEvent e) {
            spoint = e.getPoint();
        }
    }
    
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private LoginPanel loginPanel = null;
    private Point spoint;
    private JTextField userName = null;
    private JPasswordField password = null;
    private JButton loginButton = null;
    private GlassButton closeButton = null;
    private ProgressPanel panel;
    private ClockPanel clockPanel = null;
    
    /**
     * 初始化登录面板
     * 
     * @return com.lzw.LoginPanel
     */
    private LoginPanel getLoginPanel() {
        if (loginPanel == null) {
            loginPanel = new LoginPanel();// 创建登录面板对象
            loginPanel.setLayout(null);
            loginPanel.add(getUserName(), null);// 添加文本框
            loginPanel.add(getPassword(), null);// 添加密码框
            loginPanel.add(getLoginButton(), null);// 添加登录按钮
            loginPanel.add(getCloseButton(), null);// 添加关闭按钮
            loginPanel.add(getClockPanel(), null);// 添加时钟控件
            // 添加鼠标事件监听器
            loginPanel.addMouseListener(new TitleMouseAdapter());
            // 添加鼠标动作监听器
            loginPanel.addMouseMotionListener(new TitleMouseMotionadapter());
        }
        return loginPanel;
    }
    
    /**
     * 创建用户名文本框
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getUserName() {
        if (userName == null) {
            userName = new JTextField();
            // 设置文本框位置与大小
            userName.setBounds(new Rectangle(230, 180, 162, 21));
            userName.setBorder(null);// 取消边框 
            userName.setOpaque(false);// 设置控件透明
        }
        return userName;
    }
    
    /**
     * 创建密码框
     * 
     * @return javax.swing.JPasswordField
     */
    private JPasswordField getPassword() {
        if (password == null) {
            password = new JPasswordField();
            // 设置密码框位置和大小
            password.setBounds(new Rectangle(230, 225, 159, 22));
            password.setOpaque(false);// 设置透明
            password.setBorder(null);// 取消边框 
            password.setEchoChar('*');// 设置密码框字符
        }
        return password;
    }
    
    /**
     * 创建登录按钮
     * 
     * @return javax.swing.JButton
     */
    private JButton getLoginButton() {
        if (loginButton == null) {
            loginButton = new JButton();
            // 设置按钮位置与大小
            loginButton.setBounds(new Rectangle(380,190, 68, 68));
            // 设置按钮图标
            loginButton.setIcon(new ImageIcon(getClass().getResource(
                    "/com/login/logBut1.png")));
            loginButton.setContentAreaFilled(false);
            // 设置按钮按下动作的图标
            loginButton.setPressedIcon(new ImageIcon(getClass().getResource(
                    "/com/login/logBut2.png")));
            // 设置鼠标经过按钮的图标
            loginButton.setRolloverIcon(new ImageIcon(getClass().getResource(
                    "/com/login/logBut3.png")));
            // 添加按钮事件监听器
            loginButton.addActionListener(new loginActionListener());
        }
        return loginButton;
    }
    
    /**
     * 创建关闭按钮
     * 
     * @return javax.swing.JButton
     */
    private GlassButton getCloseButton() {
        if (closeButton == null) {
            closeButton = new GlassButton();
            // 设置按钮的位置与大小
            closeButton.setBounds(new Rectangle(550,5, 24, 25));
            // 设置按钮图标
            closeButton.setIcon(new ImageIcon(getClass().getResource(
                    "/com/login/close1.png")));
            // 设置按钮按下动作的图标
            closeButton.setPressedIcon(new ImageIcon(getClass().getResource(
                    "/com/login/close2.png")));
            closeButton.setContentAreaFilled(false);
            // 添加按钮的动作监听器
            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();// 销毁当前容器
                    System.exit(0);// 退出Java环境
                }
            });
        }
        return closeButton;
    }
    
    /**
     * 创建时钟控件
     * 
     * @return com.lzw.ClockPanel
     */
    private ClockPanel getClockPanel() {
        if (clockPanel == null) {
            clockPanel = new ClockPanel();
            // 设置时钟控件位置与大小
            clockPanel.setBounds(new Rectangle(10, 33, 132, 132));
            // 设置控件最小面积
            clockPanel.setMinimumSize(new Dimension(207, 207));
        }
        return clockPanel;
    }
    
    /**
     * 主类的入口方法
     * 
     * @param args
     * @throws UnsupportedLookAndFeelException
     *             不支持的外观
     */
    public static void main(String[] args)
            throws UnsupportedLookAndFeelException { 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // 创建登录窗体
                LoginFrame loginFrame = new LoginFrame(); 
                // 显示登录窗体
                loginFrame.setVisible(true);
            }
        });
    }
    
    /**
     * 登录窗体的构造方法
     */
    public LoginFrame() {
        super();
        // 创建登录进度面板 
        panel = new ProgressPanel();
        // 把登录进度面板设置为窗体顶层
        setGlassPane(panel);
        System.out.println(panel.getHeight()+"---"+panel.getWidth());
        initialize();// 调用初始化界面的方法
        setLocationRelativeTo(null);// 窗体居中
    }
    
    /**
     * 初始化程序界面的方法
     * 
     * @return void
     */
    private void initialize() {
        setUndecorated(true);// 取消窗体修饰 
        // 设置窗体内容面板
        this.setContentPane(getJContentPane());
        // 设置窗体大小
        setSize(new Dimension(574, 358));
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                getUserName().requestFocus();
            }
        });
    }
    
    /**
     * 创建窗体内容面板
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            // 设置布局管理器
            jContentPane.setLayout(new BorderLayout());
            // 添加登录面板到内容面板
            jContentPane.add(getLoginPanel(), BorderLayout.CENTER);
        }
        return jContentPane;
    }
}  
