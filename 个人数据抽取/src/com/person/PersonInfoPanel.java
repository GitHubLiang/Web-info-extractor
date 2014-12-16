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
     * ��Ա��Ϣ�����б��ѡ���¼������� 
     */
    private class PersonListSelectListener implements ListSelectionListener,
            Serializable {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                JList list = (JList) e.getSource();// ��ȡ�¼�Դ
                // ��ȡ�б�ѡ�ת��Ϊ�ַ���
                String value = (String) list.getSelectedValue();
                // ��ȡ��һ���������
                CardLayout layout = (CardLayout) getInfoPanel().getLayout();
                layout.show(getInfoPanel(), value);
                doLayout();
            }
        }
    }
    
    /**
     * ���췽��
     */
    public PersonInfoPanel() {
        super();
        setName("��Ա����");
        initialize();// ��ʼ���������
        // �õ�¼�û��������ý���ؼ�ֵ
        setPerson(Session.getUser());
        // ����¼�������
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // ��ʾ����ʱ�������ý�����ϢΪ��¼�û�����Ϣ
                setPerson(Session.getUser());
                
            }
        });
    }
    
    /**
     * ��ʼ���������
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(525, 387);// ��ʼ��С
        this.setLayout(new BorderLayout());// ���ò��ֹ�����
        // ���ñ߿�
        this.setBorder(BorderFactory.createTitledBorder(null, "�û���Ϣ",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP,
                new Font("sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
        // �����Ϣ���
        this.add(getInfoPanel(), BorderLayout.CENTER); 
        // ��ӿ������
        this.add(getContrlPanel(), BorderLayout.SOUTH);
    }
     
    
    /**
     * ��Ա��Ϣ�����б�
     * 
     * @return javax.swing.JList
     */
    private JList getJList() {
        if (jList == null) {
            // ������ѡ����б�ؼ�
            jList = new JList(new String[] { "��������", "��ϵ��ʽ", "��ϸ����" });
            // ����ʹ�õ�ѡģʽ
            jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jList.setSelectedIndex(0);// Ĭ��ѡ���һ��
            // ���ѡ���¼�������
            jList.addListSelectionListener(new PersonListSelectListener());
        }
        return jList;
    }
    
    /**
     * ��Ϣ���
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getInfoPanel() {
        if (infoPanel == null) {
            // �������
            infoPanel = new JPanel();
            // ���ÿ�Ƭ���ֹ�����
            infoPanel.setLayout(new CardLayout());
            infoPanel.setOpaque(false);// ���͸��
            // ��ӻ�����Ϣ���
            infoPanel.add(getBaseInfoPanel(), getBaseInfoPanel().getName());  
        }
        return infoPanel;
    } 
    /**
     * ��ť�������
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getContrlPanel() {
        if (contrlPanel == null) {
            // �������ֹ�����
            FlowLayout flowLayout1 = new FlowLayout();
            flowLayout1.setHgap(35);// ����ˮƽ���ּ��
            contrlPanel = new JPanel();// �����������
            contrlPanel.setOpaque(false);// ���͸��
            // ���ò��ֹ�����
            contrlPanel.setLayout(flowLayout1);
            contrlPanel.setBorder(null);// ȡ���߿�
            // ����½���ť
            contrlPanel.add(getNewButton(), null);
            // ���ȷ����ť
            contrlPanel.add(getOkButton(), null);
            // ���ɾ����ť
            contrlPanel.add(getDelButton(), null);
        }
        return contrlPanel;
    }
    
    /**
     * ȷ����ť
     * 
     * @return javax.swing.JButton
     */
    private JButton getOkButton() {
        if (okButton == null) {
            okButton = new JButton();
            okButton.setText("ȷ��");// ���ð�ť�ı�
            // ����¼�������
            okButton.addActionListener(new ActionAdapter() {
                public void actionPerformed(ActionEvent e) {
                    // ��ȡ���������Ϣ����Ա����
                    Person per = getBaseInfoPanel().getPerson(); 
                    if (per == null) {// ��֤¼�������Ϣ
                        JOptionPane.showMessageDialog(null, "��Ϣ��ȫ");
                        return;
                    }
                    // ������Ա���ݿ��������
                    PersonDAO dao = new PersonDAO();
                    if (per.getuserName() == null) {// �������Ա
                        try {
                            String re = dao.addPerson(per);
                            if (re == "success")
                                JOptionPane.showMessageDialog(null, "������");
                            else
                            	JOptionPane.showMessageDialog(null, "���ʧ��");
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        try {// ������Ա��Ϣ
                            String re = dao.updatePerson(per);
                            if (re == "success")
                                JOptionPane.showMessageDialog(null, "�޸����");
                            else
                            	JOptionPane.showMessageDialog(null, "�޸�ʧ��");
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
     * ɾ����ť
     * 
     * @return javax.swing.JButton
     */
    private JButton getDelButton() {
        if (delButton == null) {
            delButton = new JButton();
            delButton.setText("ɾ��");
            delButton.addActionListener(new ActionAdapter() {
                public void actionPerformed(ActionEvent e) {
                    // ��ȡ��Ա����
                    Person per = getBaseInfoPanel().getPerson();
                    if (per == null || per.getuserName() == null)
                        return;
                    // ��ʾȷ�϶Ի���
                    int option = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ��"
                            + per.getuserName() + "����Ϣ��");
                    if (option != JOptionPane.YES_OPTION)
                        return;
                    // ������Ա���ݿ��������
                    PersonDAO dao = new PersonDAO();
                    try {
                        // �����ݿ�ɾ��ָ����Ա����
                        String re = dao.deletePerson(per.getuserName());
                        if(re == "success")
                        	JOptionPane.showMessageDialog(null, "�������");
                        else
                        	JOptionPane.showMessageDialog(null, "����ʧ��");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        // ��ȡ��¼�û�
        Person user = Session.getUser();
        // �����û��Ƿ��ǹ���Ա��ȷ�ϰ�ť�Ƿ����
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
     * �½���ť
     * 
     * @return javax.swing.JButton
     */
    private JButton getNewButton() {
        if (newButton == null) {
            newButton = new JButton();
            newButton.setText("�½�");// ���ð�ť�ı�
            // ����¼�������
            newButton.addActionListener(new ActionAdapter() {
                public void actionPerformed(ActionEvent e) {
                    // �����µ���Ա����
                    Person person= new Person();
                    person.setisManage("no");// ��ʼ�������Ա�
                    // ��ʼ����������
                    getBaseInfoPanel().setPerson(person); 
                }
            });
        }
        // ��ȡ��¼�û�����
        Person user = Session.getUser();
        // �����Ƿ��ǹ���Ա�ж���ť�Ƿ����
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
     * ������Ա���󣬲�ͬʱ���½�������
     * 
     * @param personnel
     */
    public void setPerson(Person person) {
        this.person = person;
        if (person == null)
            return; 
        // ���½�������
        getBaseInfoPanel().setPerson(person); 
    }
}  
