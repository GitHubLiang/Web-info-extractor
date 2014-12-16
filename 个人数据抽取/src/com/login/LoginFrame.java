/**
 * ������½����
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
     * ��¼��ť���¼������� 
     */
    private final class loginActionListener implements ActionListener {
        /**
         * ģ���˾� 
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
            createBackImage();	//����ģ���Ĵ������
            String userNameStr = getUserName().getText();  //�õ���¼�û��������Ϣ
            char[] passChars = getPassword().getPassword();
            String passwordStr = new String(passChars);
            try {
                doLogin(userNameStr, passwordStr);// ִ�е�¼
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        
        /**
         * ����ģ���Ĵ������
         */
        private void createBackImage() {
            Container pane = getContentPane();// ��ȡ��������
            int width = pane.getWidth();// ��ȡ������С
            int height = pane.getHeight();
            System.out.println(width+"---"+height);
            // ����ͼƬ����
            BufferedImage bimage = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_ARGB);
            // ��ȡͼƬ����Ļ�ͼ������
            Graphics2D g2 = bimage.createGraphics();
            pane.paint(g2);// ������������Ƶ�ͼƬ����
            bimage.flush();
            // ΪͼƬ����Ӧ��ģ���˾�
            bimage = getFilter(2).filter(bimage, null);
            // ��ģ�����ͼƬ��Ϊ����
            panel.setBackImage(bimage);
        }
        
        /**
         * ִ�е�¼�ķ���
         * 
         * @param userNameStr
         *            �û���
         * @param passwordStr
         *            ��¼����
         * @throws Exception
         * @throws InvocationTargetException
         * @throws InterruptedException
         */
        private void doLogin(final String userNameStr, final String passwordStr) {
            new Thread() {// �������߳�
                public void run() {
                    // ��ʾ����ĵ�¼�������
                    getGlassPane().setVisible(true);
                    // ������Ա���ݱ��������
                    PersonDAO dao = new PersonDAO();
                    // ��ȡָ���˻�����Ա����
                    Person user = dao.getPerson(userNameStr, passwordStr);
                    if (user != null) {// �ж��Ƿ�ɹ���ȡ��Ա����
                        Session.setUser(user);// ��Ա���󱣴�Ự������
                        // �������������
                        ProjectFrame frame = new ProjectFrame();
                        frame.setVisible(true);// ��ʾ������
                        LoginFrame.this.dispose();// ���ٵ�¼����
                    } else {
                        showMessageDialog(null, "�ṩ���û����������޷���¼");
                    }
                    // ��ɵ�¼�����ص�¼�������
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
     * ��ʼ����¼���
     * 
     * @return com.lzw.LoginPanel
     */
    private LoginPanel getLoginPanel() {
        if (loginPanel == null) {
            loginPanel = new LoginPanel();// ������¼������
            loginPanel.setLayout(null);
            loginPanel.add(getUserName(), null);// ����ı���
            loginPanel.add(getPassword(), null);// ��������
            loginPanel.add(getLoginButton(), null);// ��ӵ�¼��ť
            loginPanel.add(getCloseButton(), null);// ��ӹرհ�ť
            loginPanel.add(getClockPanel(), null);// ���ʱ�ӿؼ�
            // �������¼�������
            loginPanel.addMouseListener(new TitleMouseAdapter());
            // �����궯��������
            loginPanel.addMouseMotionListener(new TitleMouseMotionadapter());
        }
        return loginPanel;
    }
    
    /**
     * �����û����ı���
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getUserName() {
        if (userName == null) {
            userName = new JTextField();
            // �����ı���λ�����С
            userName.setBounds(new Rectangle(230, 180, 162, 21));
            userName.setBorder(null);// ȡ���߿� 
            userName.setOpaque(false);// ���ÿؼ�͸��
        }
        return userName;
    }
    
    /**
     * ���������
     * 
     * @return javax.swing.JPasswordField
     */
    private JPasswordField getPassword() {
        if (password == null) {
            password = new JPasswordField();
            // ���������λ�úʹ�С
            password.setBounds(new Rectangle(230, 225, 159, 22));
            password.setOpaque(false);// ����͸��
            password.setBorder(null);// ȡ���߿� 
            password.setEchoChar('*');// ����������ַ�
        }
        return password;
    }
    
    /**
     * ������¼��ť
     * 
     * @return javax.swing.JButton
     */
    private JButton getLoginButton() {
        if (loginButton == null) {
            loginButton = new JButton();
            // ���ð�ťλ�����С
            loginButton.setBounds(new Rectangle(380,190, 68, 68));
            // ���ð�ťͼ��
            loginButton.setIcon(new ImageIcon(getClass().getResource(
                    "/com/login/logBut1.png")));
            loginButton.setContentAreaFilled(false);
            // ���ð�ť���¶�����ͼ��
            loginButton.setPressedIcon(new ImageIcon(getClass().getResource(
                    "/com/login/logBut2.png")));
            // ������꾭����ť��ͼ��
            loginButton.setRolloverIcon(new ImageIcon(getClass().getResource(
                    "/com/login/logBut3.png")));
            // ��Ӱ�ť�¼�������
            loginButton.addActionListener(new loginActionListener());
        }
        return loginButton;
    }
    
    /**
     * �����رհ�ť
     * 
     * @return javax.swing.JButton
     */
    private GlassButton getCloseButton() {
        if (closeButton == null) {
            closeButton = new GlassButton();
            // ���ð�ť��λ�����С
            closeButton.setBounds(new Rectangle(550,5, 24, 25));
            // ���ð�ťͼ��
            closeButton.setIcon(new ImageIcon(getClass().getResource(
                    "/com/login/close1.png")));
            // ���ð�ť���¶�����ͼ��
            closeButton.setPressedIcon(new ImageIcon(getClass().getResource(
                    "/com/login/close2.png")));
            closeButton.setContentAreaFilled(false);
            // ��Ӱ�ť�Ķ���������
            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();// ���ٵ�ǰ����
                    System.exit(0);// �˳�Java����
                }
            });
        }
        return closeButton;
    }
    
    /**
     * ����ʱ�ӿؼ�
     * 
     * @return com.lzw.ClockPanel
     */
    private ClockPanel getClockPanel() {
        if (clockPanel == null) {
            clockPanel = new ClockPanel();
            // ����ʱ�ӿؼ�λ�����С
            clockPanel.setBounds(new Rectangle(10, 33, 132, 132));
            // ���ÿؼ���С���
            clockPanel.setMinimumSize(new Dimension(207, 207));
        }
        return clockPanel;
    }
    
    /**
     * �������ڷ���
     * 
     * @param args
     * @throws UnsupportedLookAndFeelException
     *             ��֧�ֵ����
     */
    public static void main(String[] args)
            throws UnsupportedLookAndFeelException { 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // ������¼����
                LoginFrame loginFrame = new LoginFrame(); 
                // ��ʾ��¼����
                loginFrame.setVisible(true);
            }
        });
    }
    
    /**
     * ��¼����Ĺ��췽��
     */
    public LoginFrame() {
        super();
        // ������¼������� 
        panel = new ProgressPanel();
        // �ѵ�¼�����������Ϊ���嶥��
        setGlassPane(panel);
        System.out.println(panel.getHeight()+"---"+panel.getWidth());
        initialize();// ���ó�ʼ������ķ���
        setLocationRelativeTo(null);// �������
    }
    
    /**
     * ��ʼ���������ķ���
     * 
     * @return void
     */
    private void initialize() {
        setUndecorated(true);// ȡ���������� 
        // ���ô����������
        this.setContentPane(getJContentPane());
        // ���ô����С
        setSize(new Dimension(574, 358));
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                getUserName().requestFocus();
            }
        });
    }
    
    /**
     * ���������������
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            // ���ò��ֹ�����
            jContentPane.setLayout(new BorderLayout());
            // ��ӵ�¼��嵽�������
            jContentPane.add(getLoginPanel(), BorderLayout.CENTER);
        }
        return jContentPane;
    }
}  
