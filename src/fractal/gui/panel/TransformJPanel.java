/*
 * TransformJPanel.java
 *
 * Created on 25 February 2007, 21:24
 */

package fractal.gui.panel;

import fractal.gui.util.UpdatableListModel;
import fractal.producer.Fractal;
import fractal.producer.transform.Transform;
import fractal.producer.transform.TransformStore;

/**
 *
 * @author  Owner
 */
public class TransformJPanel extends javax.swing.JPanel {
       
    private UpdatableListModel availableTransformsModel = new UpdatableListModel()  {
     public int getSize() { return TransformStore.getSize(); }
     public Object getElementAt(int index) { return TransformStore.getTransforms().get(index); }
    };
    private final UpdatableListModel fractalTransformsModel = new UpdatableListModel()  {
     public int getSize() { return fractal==null?0:fractal.getTransformsSize(); }
     public Object getElementAt(int index) { return fractal.getTransform(index); }
    };
    Fractal fractal = null;
    /** Creates new form TransformJPanel */
    public TransformJPanel() {
        initComponents();
        jlAvailableTransforms.setModel(availableTransformsModel);
        jlTransforms.setModel(fractalTransformsModel);
        jlTransformsValueChanged(null);
    }
 
    public void setFractal(Fractal f) {
       this.fractal=f;
    }

    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jbCancelChanges = new javax.swing.JButton();
        jlTransformName = new javax.swing.JLabel();
        jbSaveChanges = new javax.swing.JButton();
        propertyEditorJPanel1 = new fractal.gui.panel.util.PropertyEditorJPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlTransforms = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlAvailableTransforms = new javax.swing.JList();
        jbAdd = new javax.swing.JButton();
        jbDelete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jbMoveDown = new javax.swing.JButton();
        jbMoveUp = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Transform Properties"));
        jbCancelChanges.setText("Cancel Changes");
        jbCancelChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelChangesActionPerformed(evt);
            }
        });

        jlTransformName.setFont(new java.awt.Font("Tahoma", 1, 14));
        jlTransformName.setText("Transform");

        jbSaveChanges.setText("Save Changes");
        jbSaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSaveChangesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlTransformName, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(propertyEditorJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jbSaveChanges)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCancelChanges)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTransformName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(propertyEditorJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSaveChanges)
                    .addComponent(jbCancelChanges))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Remove Transforms"));
        jlTransforms.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlTransformsValueChanged(evt);
            }
        });

        jScrollPane2.setViewportView(jlTransforms);

        jlAvailableTransforms.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Inside Out" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlAvailableTransforms.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jlAvailableTransforms);

        jbAdd.setText("Add->");
        jbAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddActionPerformed(evt);
            }
        });

        jbDelete.setText("<-Del");
        jbDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDeleteActionPerformed(evt);
            }
        });

        jLabel3.setText("Current Fractal");

        jLabel1.setText("All Available");

        jbMoveDown.setText("Move Down");

        jbMoveUp.setText("Move Up");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbAdd)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbMoveDown, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbMoveUp, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbDelete, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jbDelete, jbMoveDown, jbMoveUp});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jbMoveUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbMoveDown)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbCancelChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelChangesActionPerformed
        if( jlTransforms.getSelectedIndex()==-1) return;
        Transform xform = (Transform)fractalTransformsModel.getElementAt(jlTransforms.getSelectedIndex());
        propertyEditorJPanel1.load(xform);
    }//GEN-LAST:event_jbCancelChangesActionPerformed

    private void jbSaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveChangesActionPerformed
        if( jlTransforms.getSelectedIndex()==-1) return;
        Transform xform = (Transform)fractalTransformsModel.getElementAt(jlTransforms.getSelectedIndex());
        propertyEditorJPanel1.save(xform);
    }//GEN-LAST:event_jbSaveChangesActionPerformed

    private void jlTransformsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlTransformsValueChanged
        System.out.println("value Changed!");   
        if( jlTransforms.getSelectedIndex()==-1) {
            System.out.println("No Transform");
          propertyEditorJPanel1.setBean(null);
          propertyEditorJPanel1.invalidate();
          propertyEditorJPanel1.validate();
          propertyEditorJPanel1.repaint();
          this.jlTransformName.setText("No Transform Selected");
          return;
        }
           Transform xform = (Transform)fractalTransformsModel.getElementAt(jlTransforms.getSelectedIndex());
           propertyEditorJPanel1.setBean(xform);
           propertyEditorJPanel1.invalidate();
           propertyEditorJPanel1.validate();
           propertyEditorJPanel1.repaint();
           this.jlTransformName.setText(xform.toString());
    }//GEN-LAST:event_jlTransformsValueChanged

    private void jbDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDeleteActionPerformed
        if( jlTransforms.getSelectedIndex()==-1) return;   
        Transform xform = (Transform)fractalTransformsModel.getElementAt(jlTransforms.getSelectedIndex());
        fractal.removeTransform(xform);
        fractalTransformsModel.fireUpdate();
        this.jlTransforms.getSelectionModel().clearSelection();
    }//GEN-LAST:event_jbDeleteActionPerformed

    private void jbAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddActionPerformed
        if( jlAvailableTransforms.getSelectedIndex()==-1) return;   
        Transform xform = (Transform)availableTransformsModel.getElementAt(jlAvailableTransforms.getSelectedIndex());
           fractal.addTransform(xform.clone());
           fractalTransformsModel.fireUpdate();
    }//GEN-LAST:event_jbAddActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbAdd;
    private javax.swing.JButton jbCancelChanges;
    private javax.swing.JButton jbDelete;
    private javax.swing.JButton jbMoveDown;
    private javax.swing.JButton jbMoveUp;
    private javax.swing.JButton jbSaveChanges;
    private javax.swing.JList jlAvailableTransforms;
    private javax.swing.JLabel jlTransformName;
    private javax.swing.JList jlTransforms;
    private fractal.gui.panel.util.PropertyEditorJPanel propertyEditorJPanel1;
    // End of variables declaration//GEN-END:variables
    
}