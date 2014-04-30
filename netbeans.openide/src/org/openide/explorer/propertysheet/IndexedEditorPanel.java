/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2003 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.openide.explorer.propertysheet;

import java.beans.*;
import java.awt.Component;
import javax.swing.Scrollable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
                
import org.openide.explorer.ExplorerManager;
import org.openide.nodes.Node;
import org.openide.util.actions.NodeAction;
import org.openide.util.actions.SystemAction;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

/**
 * Panel displaying indexed properties.
 * @author  dstrupl@netbeans.org
 */
class IndexedEditorPanel extends javax.swing.JPanel implements ExplorerManager.Provider, PropertyChangeListener {

    private ExplorerManager em;

    private NodeAction moveUp;
    private NodeAction moveDown;
    private NodeAction newAction;
    private boolean showingDetails = false;
    private Node rootNode;
    private Node.Property prop;
    
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JPanel detailsPanel = new JPanel();

    /** Creates new form IndexedEditorPanel */
    public IndexedEditorPanel(Node node, Node.Property[] props) {
        initComponents();
        
        detailsPanel.setLayout(new java.awt.BorderLayout());
        getExplorerManager().setRootContext(node);
        
        rootNode = node;
        prop = props[0];
        getExplorerManager().addPropertyChangeListener(this);
        treeTableView1.setProperties(props);
        treeTableView1.setRootVisible(false);
        treeTableView1.setDefaultActionAllowed(false);
        treeTableView1.setTreePreferredWidth(200);
        
        node.addPropertyChangeListener(this);
        try {
            moveUp = (NodeAction)SystemAction.get(Class.forName("org.openide.actions.MoveUpAction")); // NOI18N
            moveDown = (NodeAction)SystemAction.get(Class.forName("org.openide.actions.MoveDownAction")); // NOI18N
            newAction = (NodeAction)SystemAction.get(Class.forName("org.openide.actions.NewAction")); // NOI18N
        } catch (ClassNotFoundException cnfe) {
        }
        
        java.util.ResourceBundle bundle = NbBundle.getBundle(IndexedEditorPanel.class);
        propertiesLabel.setDisplayedMnemonic(bundle.getString("CTL_Properties_Mnemonic").charAt(0));
        newButton.setMnemonic(bundle.getString("CTL_New_Mnemonic").charAt(0));
        deleteButton.setMnemonic(bundle.getString("CTL_Delete_Mnemonic").charAt(0));
        upButton.setMnemonic(bundle.getString("CTL_MoveUp_Mnemonic").charAt(0));
        downButton.setMnemonic(bundle.getString("CTL_MoveDown_Mnemonic").charAt(0));

        treeTableView1.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_Properties"));
        newButton.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_New"));
        deleteButton.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_Delete"));
        upButton.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_MoveUp"));
        downButton.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_MoveDown"));
        getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_IndexedEditorPanel"));
    }

    public void addNotify() {
        super.addNotify();
        updateButtonState();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        treeTableView1 = new org.openide.explorer.view.TreeTableView();
        jPanel1 = new javax.swing.JPanel();
        newButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();
        detailsButton = new javax.swing.JButton();
        propertiesLabel = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(12, 12, 0, 12)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 11);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        add(treeTableView1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridLayout(5, 1, 0, 5));

        newButton.setText(org.openide.util.NbBundle.getBundle(IndexedEditorPanel.class).getString("CTL_New"));
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        jPanel1.add(newButton);

        deleteButton.setText(org.openide.util.NbBundle.getBundle(IndexedEditorPanel.class).getString("CTL_Delete"));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jPanel1.add(deleteButton);

        upButton.setText(org.openide.util.NbBundle.getBundle(IndexedEditorPanel.class).getString("CTL_MoveUp"));
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });

        jPanel1.add(upButton);

        downButton.setText(org.openide.util.NbBundle.getBundle(IndexedEditorPanel.class).getString("CTL_MoveDown"));
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });

        jPanel1.add(downButton);

        detailsButton.setText(org.openide.util.NbBundle.getBundle(IndexedEditorPanel.class).getString("CTL_HideDetails"));
        detailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsButtonActionPerformed(evt);
            }
        });

        jPanel1.add(detailsButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 0);
        add(jPanel1, gridBagConstraints);

        propertiesLabel.setText(org.openide.util.NbBundle.getBundle(IndexedEditorPanel.class).getString("CTL_Properties"));
        propertiesLabel.setLabelFor(treeTableView1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 11);
        add(propertiesLabel, gridBagConstraints);

    }//GEN-END:initComponents

private void detailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsButtonActionPerformed
        showingDetails = !showingDetails;
        
        if ( showingDetails && !this.equals( detailsPanel.getParent() ) ) {
            initDetails();
        }
        
        updateButtonState();
        updateDetailsPanel();
}//GEN-LAST:event_detailsButtonActionPerformed

private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        try {
            getExplorerManager().setSelectedNodes(new Node[] { rootNode });
        } catch (PropertyVetoException pve) {
            // this should be always possible --> if not, notify problem
            PropertyDialogManager.notify(pve);
        }
        if ((newAction != null) && (newAction.isEnabled())) {
            newAction.performAction();
        } 
}//GEN-LAST:event_newButtonActionPerformed

private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
    Node[] sn = getExplorerManager().getSelectedNodes();
    if ((sn == null) || (sn.length != 1) || (sn[0] == rootNode)) {
        return;
    }
    try {
        sn[0].destroy();        
    } catch (java.io.IOException ioe) {
        PropertyDialogManager.notify(ioe);
    }
    rootNode = getExplorerManager().getRootContext();    
}//GEN-LAST:event_deleteButtonActionPerformed

private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        Node[] sn = getExplorerManager().getSelectedNodes();    
        if ((moveDown != null) && (moveDown.isEnabled())) {
            moveDown.performAction();
        } 
        
        if ((sn == null) || (sn.length != 1) || (sn[0] == rootNode)) {
            return;
        }
        try {
            getExplorerManager().setSelectedNodes( sn );
        } catch ( PropertyVetoException pve ) {
        }
        
}//GEN-LAST:event_downButtonActionPerformed

private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        Node[] sn = getExplorerManager().getSelectedNodes();    
        if ((moveUp != null) && (moveUp.isEnabled())) {
            moveUp.performAction();
        }
        
        if ((sn == null) || (sn.length != 1) || (sn[0] == rootNode)) {
            return;
        }
        try {
            getExplorerManager().setSelectedNodes( sn );
        } catch ( PropertyVetoException pve ) {
        }
}//GEN-LAST:event_upButtonActionPerformed

    public synchronized ExplorerManager getExplorerManager() {
        if (em == null) {
            em = new ExplorerManager();
        }
        return em;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openide.explorer.view.TreeTableView treeTableView1;
    private javax.swing.JButton detailsButton;
    private javax.swing.JLabel propertiesLabel;
    private javax.swing.JButton newButton;
    private javax.swing.JButton downButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables

    private void updateButtonState() {
        if (showingDetails) {
            detailsButton.setText(NbBundle.getBundle(IndexedEditorPanel.class).getString("CTL_HideDetails"));
        } else {
            detailsButton.setText(NbBundle.getBundle(IndexedEditorPanel.class).getString("CTL_ShowDetails"));
        }
        upButton.setEnabled((moveUp != null) && (moveUp.isEnabled()));
        downButton.setEnabled((moveDown != null) && (moveDown.isEnabled()));
        Node[] sn = getExplorerManager().getSelectedNodes();
        deleteButton.setEnabled((sn != null) && (sn.length == 1) && (sn[0] != rootNode));
        detailsButton.setVisible(
            (prop != null) && 
            (prop.getPropertyEditor() != null) &&
            (prop.getPropertyEditor().supportsCustomEditor())
        );
        if (detailsButton.isVisible()) {
            if (showingDetails) {
                detailsButton.setText(NbBundle.getBundle(IndexedEditorPanel.class).getString("CTL_HideDetails"));
                detailsButton.setMnemonic(NbBundle.getBundle(IndexedEditorPanel.class).getString("CTL_HideDetails_Mnemonic").charAt(0));
                detailsButton.getAccessibleContext().setAccessibleDescription(NbBundle.getBundle(IndexedEditorPanel.class).getString("ACSD_HideDetails"));
            } else {
                detailsButton.setText(NbBundle.getBundle(IndexedEditorPanel.class).getString("CTL_ShowDetails"));
                detailsButton.setMnemonic(NbBundle.getBundle(IndexedEditorPanel.class).getString("CTL_ShowDetails_Mnemonic").charAt(0));
                detailsButton.getAccessibleContext().setAccessibleDescription(NbBundle.getBundle(IndexedEditorPanel.class).getString("ACSD_ShowDetails"));
            }
            detailsButton.setEnabled((sn != null) && (sn.length == 1) && (sn[0] != rootNode));
        }
    }

    private void updateDetailsPanel() {
        detailsPanel.removeAll();
        if (!showingDetails) {
            remove( detailsPanel );        
            revalidateDetailsPanel();
            return;
        }
        Node []selN = getExplorerManager().getSelectedNodes();
        if ((selN == null) || (selN.length == 0)) {
            revalidateDetailsPanel();
            return;
        }
        Node n = selN[0];

        if (n == rootNode) {
            revalidateDetailsPanel();
            return;
        }

        if (selN.length > 1) {
            n = new ProxyNode(selN);
        }
        // beware - this will function only if the DisplayIndexedNode has
        // one property on the first sheet and the property is of type
        // ValueProp
        Node.Property prop = n.getPropertySets()[0].getProperties()[0];
        PropertyPanel p = new PropertyPanel(prop, selN);
        p.setPreferences(PropertyPanel.PREF_CUSTOM_EDITOR);
        
        if ( isEditorScrollable( p ) )
            detailsPanel.add(p, java.awt.BorderLayout.CENTER);
        else {
            jScrollPane1.setViewportView(p);
            detailsPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        }
        
        revalidateDetailsPanel();
    }
    
    private void revalidateDetailsPanel() {
        detailsPanel.invalidate();
        repaint();
        if (detailsPanel.getParent() != null) {
            detailsPanel.getParent().validate();
        } else {
            detailsPanel.validate();
        }
    }
    
    /**
     * This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source 
     *  	and the property that has changed.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if (ExplorerManager.PROP_SELECTED_NODES.equals(evt.getPropertyName())) {
            updateButtonState();
            updateDetailsPanel();
        }
    }
    
    private void initDetails() {
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        add(detailsPanel, gridBagConstraints);
    }
    
    private boolean isEditorScrollable(PropertyPanel p) {
        Component[] comps = p.getComponents();
        for (int i=0; i< comps.length; i++) {
            if ( comps[i] instanceof Scrollable || comps[i] instanceof TopComponent )
                return true;
        }
        
        return false;
    }
}
