package com.Frame;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.swing.tree.*;
import com.login.Session;
import com.entity.*;
import com.Listener.ActionAdapter; 
import com.person.PersonInfoPanel;
import java.awt.Insets;

public class PersonPanel extends JPanel implements Serializable,
        TreeSelectionListener {
    private final class ControlCommandListener extends ActionAdapter {
        public void actionPerformed(ActionEvent e) {
            CardLayout layout = (CardLayout) getWorkPanel().getLayout();
            String command = e.getActionCommand();
            layout.show(getWorkPanel(), command);
        }
    }
    
    private static final long serialVersionUID = 1L;
    private JPanel controlPanel = null;
    private JLabel jLabel = null;
    private JTextField topDeptField = null;
    private JLabel jLabel25 = null;
    private JTextField topLevelField = null;
    private JLabel jLabel27 = null;
    private JTextField topNameField = null;
    private PersonInfoPanel PersonInfoPanel = null;
    private JPanel workPanel = null;
    private Person Person; // @jve:decl-index=0:
    
    /**
     * This is the default constructor
     */
    public PersonPanel() {
        super();
        setName("人员管理");
        initialize();
    }
    
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(594, 500);
        this.setLayout(new BorderLayout());
        this.add(getControlPanel(), BorderLayout.NORTH);
        this.add(getWorkPanel(), BorderLayout.CENTER);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                setPerson(getPerson());
            }
        });
    }
    
    /**
     * This method initializes controlPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getControlPanel() {
        if (controlPanel == null) {
            GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
            gridBagConstraints51.gridx = 4;
            gridBagConstraints51.fill = GridBagConstraints.NONE;
            gridBagConstraints51.weightx = 0.0;
            gridBagConstraints51.anchor = GridBagConstraints.WEST;
            gridBagConstraints51.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints51.gridwidth = 2;
            gridBagConstraints51.gridy = 1;
            GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
            gridBagConstraints50.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints50.gridy = 1;
            gridBagConstraints50.weightx = 1.0;
            gridBagConstraints50.insets = new Insets(0, 0, 0, 5);
            gridBagConstraints50.gridwidth = 3;
            gridBagConstraints50.gridx = 1;
            GridBagConstraints gridBagConstraints49 = new GridBagConstraints();
            gridBagConstraints49.gridx = 0;
            gridBagConstraints49.insets = new Insets(0, 5, 0, 0);
            gridBagConstraints49.gridy = 1;
            GridBagConstraints gridBagConstraints46 = new GridBagConstraints();
            gridBagConstraints46.gridx = 1;
            gridBagConstraints46.fill = GridBagConstraints.BOTH;
            gridBagConstraints46.gridwidth = 7;
            gridBagConstraints46.insets = new Insets(0, 0, 0, 5);
            gridBagConstraints46.gridy = 2;
            GridBagConstraints gridBagConstraints48 = new GridBagConstraints();
            gridBagConstraints48.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints48.gridy = 0;
            gridBagConstraints48.weightx = 1.0;
            gridBagConstraints48.insets = new Insets(0, 0, 0, 5);
            gridBagConstraints48.gridx = 3;
            GridBagConstraints gridBagConstraints47 = new GridBagConstraints();
            gridBagConstraints47.gridx = 2;
            gridBagConstraints47.gridy = 0;
            jLabel27 = new JLabel();
            jLabel27.setText("用户名：");
            GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
            gridBagConstraints40.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints40.gridy = 0;
            gridBagConstraints40.weightx = 0.0;
            gridBagConstraints40.insets = new Insets(0, 0, 0, 5);
            gridBagConstraints40.ipadx = 0;
            gridBagConstraints40.gridx = 7;
            GridBagConstraints gridBagConstraints38 = new GridBagConstraints();
            gridBagConstraints38.gridx = 6;
            gridBagConstraints38.gridy = 0;
            GridBagConstraints gridBagConstraints37 = new GridBagConstraints();
            gridBagConstraints37.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints37.gridy = 0;
            gridBagConstraints37.weightx = 1.0;
            gridBagConstraints37.insets = new Insets(0, 0, 0, 5);
            gridBagConstraints37.gridx = 5;
            GridBagConstraints gridBagConstraints36 = new GridBagConstraints();
            gridBagConstraints36.gridx = 4;
            gridBagConstraints36.gridy = 0;
            jLabel25 = new JLabel();
            jLabel25.setText("密   码：");
            GridBagConstraints gridBagConstraints39 = new GridBagConstraints();
            gridBagConstraints39.gridx = 0;
            gridBagConstraints39.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints39.insets = new Insets(5, 5, 0, 0);
            gridBagConstraints39.gridheight = 1;
            gridBagConstraints39.weightx = 0.0;
            gridBagConstraints39.anchor = GridBagConstraints.NORTH;
            gridBagConstraints39.gridy = 2;
            GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
            gridBagConstraints35.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints35.gridx = 1;
            gridBagConstraints35.gridy = 0;
            gridBagConstraints35.weightx = 1.0;
            gridBagConstraints35.gridwidth = 1;
            gridBagConstraints35.ipadx = 0;
            gridBagConstraints35.insets = new Insets(0, 0, 0, 5);
            GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
            gridBagConstraints23.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints23.gridy = 0;
            gridBagConstraints23.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints23.weightx = 0.0;
            gridBagConstraints23.weighty = 0.0;
            gridBagConstraints23.gridx = 0;
            jLabel = new JLabel();
            jLabel.setText("是否是管理员：");
            jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            controlPanel = new JPanel();
            controlPanel.setLayout(new GridBagLayout());
            controlPanel.setBorder(BorderFactory.createTitledBorder(null,
                    "员工信息", TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION, new Font("Dialog",
                            Font.BOLD, 12), new Color(51, 51, 51)));
            controlPanel.setOpaque(false);
            controlPanel.add(jLabel, gridBagConstraints23);
            controlPanel.add(getTopDeptField(), gridBagConstraints35);
            controlPanel.add(jLabel25, gridBagConstraints36);
            controlPanel.add(getTopLevelField(), gridBagConstraints37);
            controlPanel.add(jLabel27, gridBagConstraints47);
            controlPanel.add(getTopNameField(), gridBagConstraints48);
        }
        return controlPanel;
    }
    
    /**
     * This method initializes topDeptField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getTopDeptField() {
        if (topDeptField == null) {
            topDeptField = new JTextField();
            topDeptField.setColumns(10);
            topDeptField.setEditable(false);
        }
        return topDeptField;
    }
    
    /**
     * This method initializes topLevelField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getTopLevelField() {
        if (topLevelField == null) {
            topLevelField = new JTextField();
            topLevelField.setEditable(false);
        }
        return topLevelField;
    }
    
    /**
     * This method initializes topNameField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getTopNameField() {
        if (topNameField == null) {
            topNameField = new JTextField();
            topNameField.setEditable(false);
        }
        return topNameField;
    }
    
    /**
     * This method initializes PersonInfoPanel
     * 
     * @return com.lzw.manage.subpanel.PersonInfoPanel
     */
    private PersonInfoPanel getPersonInfoPanel() {
        if (PersonInfoPanel == null) {
            PersonInfoPanel = new PersonInfoPanel();
            PersonInfoPanel.setOpaque(false);
        }
        return PersonInfoPanel;
    }
    
    /**
     * This method initializes workPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getWorkPanel() {
        if (workPanel == null) {
            workPanel = new JPanel();
            workPanel.setLayout(new CardLayout());
            workPanel.setOpaque(false);
            workPanel.add(getPersonInfoPanel(), getPersonInfoPanel()
                    .getName());
        }
        return workPanel;
    }
    
    public Person getPerson() {
        if (Person == null)
            Person = Session.getUser();
        return Person;
    }
    
    public void setPerson(Person Person) {
        this.Person = Person;
        if (Person == null)
            return;
        getTopDeptField().setText(Person.getuserName());
        getTopNameField().setText(Person.getpassWord());
        getTopLevelField().setText(Person.getisManage());
        getPersonInfoPanel().setPerson(Person);
    }
    
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        if (path == null) {
            setPerson(Session.getUser());
        }
        Object object = path.getLastPathComponent();
        if (object instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) object;
            Object value = node.getUserObject();
            if (value instanceof Person) {
                Person per = (Person) value;
                setPerson(per);
            } else {
                setPerson(Session.getUser());
            }
        }
    }
} 