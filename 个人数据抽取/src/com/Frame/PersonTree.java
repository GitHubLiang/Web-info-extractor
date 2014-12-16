package com.Frame;
 
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.*;
import com.dao.*;
import com.login.Session;  
import com.entity.*; 

/**
 * 编写人员树控件 
 */
public class PersonTree extends JTree {
    
    private static final long serialVersionUID = 1L;
    private DefaultMutableTreeNode rootNode; // 树根节点 
    /**
     * 构造方法
     */
    public PersonTree() {
        super();
        initialize();
    }
    /**
     * 初始化程序界面
     * @return void
     */
    private void initialize() {
        this.setSize(300, 300);// 初始大小
        this.setRootVisible(false);// 隐藏根节点
        this.setShowsRootHandles(false);// 隐藏句柄
        loadPerson();// 加载人员节点
    }
    
    /**
     * 加载人员节点
     */
    public void loadPerson() {
        // 初始化根节点
        rootNode = new DefaultMutableTreeNode("");
        // 创建树模型对象
        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        setModel(model);// 设置模型 
        // 获取登录用户人员对象
        Person user = Session.getUser(); 
        // 把从数据库读取的信息应用到树控件中
        loadPersonTreeNode(rootNode);
        // 展开根节点
        this.setExpandedState(new TreePath(rootNode), true);
        this.expandRow(0);
    }
    
    /**
     * 把从数据库读取的信息应用到树控件中
     * @param parent
     * @param allDept
     */
    private void loadPersonTreeNode(final DefaultMutableTreeNode parent) {
    	PersonDAO dao = new PersonDAO();
    	List<Person> allPerson = dao.listPerson();
        for (final Person person : allPerson) {// 遍历部门和人员列表
            final DefaultMutableTreeNode personNode;
            personNode = new DefaultMutableTreeNode();
            personNode.setUserObject(person);// 创建人员节点
            parent.add(personNode);// 将节点添加到父节点
            revalidate();  
        }
    }
} 
