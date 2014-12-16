package com.Frame;
 
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.*;
import com.dao.*;
import com.login.Session;  
import com.entity.*; 

/**
 * ��д��Ա���ؼ� 
 */
public class PersonTree extends JTree {
    
    private static final long serialVersionUID = 1L;
    private DefaultMutableTreeNode rootNode; // �����ڵ� 
    /**
     * ���췽��
     */
    public PersonTree() {
        super();
        initialize();
    }
    /**
     * ��ʼ���������
     * @return void
     */
    private void initialize() {
        this.setSize(300, 300);// ��ʼ��С
        this.setRootVisible(false);// ���ظ��ڵ�
        this.setShowsRootHandles(false);// ���ؾ��
        loadPerson();// ������Ա�ڵ�
    }
    
    /**
     * ������Ա�ڵ�
     */
    public void loadPerson() {
        // ��ʼ�����ڵ�
        rootNode = new DefaultMutableTreeNode("");
        // ������ģ�Ͷ���
        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        setModel(model);// ����ģ�� 
        // ��ȡ��¼�û���Ա����
        Person user = Session.getUser(); 
        // �Ѵ����ݿ��ȡ����ϢӦ�õ����ؼ���
        loadPersonTreeNode(rootNode);
        // չ�����ڵ�
        this.setExpandedState(new TreePath(rootNode), true);
        this.expandRow(0);
    }
    
    /**
     * �Ѵ����ݿ��ȡ����ϢӦ�õ����ؼ���
     * @param parent
     * @param allDept
     */
    private void loadPersonTreeNode(final DefaultMutableTreeNode parent) {
    	PersonDAO dao = new PersonDAO();
    	List<Person> allPerson = dao.listPerson();
        for (final Person person : allPerson) {// �������ź���Ա�б�
            final DefaultMutableTreeNode personNode;
            personNode = new DefaultMutableTreeNode();
            personNode.setUserObject(person);// ������Ա�ڵ�
            parent.add(personNode);// ���ڵ���ӵ����ڵ�
            revalidate();  
        }
    }
} 
