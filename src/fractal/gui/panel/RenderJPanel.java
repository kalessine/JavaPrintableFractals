/*
 * RenderPanel.java
 *
 * Created on 12 July 2007, 00:46
 */

package fractal.gui.panel;

import fractal.gui.worker.DistributedWorkerJFrame;
import fractal.producer.Fractal;
import fractal.producer.render.chunkrenderer.ChunkRenderer;
import fractal.producer.render.local.LocalRenderer;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author  Owner
 */
public class RenderJPanel extends javax.swing.JPanel {
    
    /** Creates new form RenderPanel */
    public RenderJPanel() {
        initComponents();
    }
    LocalRenderer localRenderer = new LocalRenderer();
    ChunkRenderer distRenderer = new ChunkRenderer();
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        bgRenderer = new javax.swing.ButtonGroup();
        jrLocal = new javax.swing.JRadioButton();
        jrDistributed = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jbChooseDistDirectory = new javax.swing.JButton();
        jtDistributionDirectory = new javax.swing.JTextField();
        jbSpawnWorker = new javax.swing.JButton();
        jcbDeleteChunks = new javax.swing.JCheckBox();

        bgRenderer.add(jrLocal);
        jrLocal.setSelected(true);
        jrLocal.setText("Local Renderer");
        jrLocal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jrLocal.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrLocal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrLocalItemStateChanged(evt);
            }
        });

        bgRenderer.add(jrDistributed);
        jrDistributed.setText("Distributed Renderer");
        jrDistributed.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jrDistributed.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrDistributed.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrDistributedItemStateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Distribution Directory"));
        jbChooseDistDirectory.setText("Browse");
        jbChooseDistDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbChooseDistDirectoryActionPerformed(evt);
            }
        });

        jtDistributionDirectory.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtDistributionDirectory, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addComponent(jbChooseDistDirectory))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jtDistributionDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jbChooseDistDirectory))
        );

        jbSpawnWorker.setText("Spawn Worker");
        jbSpawnWorker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSpawnWorkerActionPerformed(evt);
            }
        });

        jcbDeleteChunks.setSelected(true);
        jcbDeleteChunks.setText("Delete Chunks When Loaded");
        jcbDeleteChunks.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jcbDeleteChunks.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jcbDeleteChunks.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbDeleteChunksItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrLocal)
                    .addComponent(jrDistributed)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbSpawnWorker)
                        .addGap(110, 110, 110)
                        .addComponent(jcbDeleteChunks)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrLocal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrDistributed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbDeleteChunks)
                    .addComponent(jbSpawnWorker))
                .addContainerGap(140, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbDeleteChunksItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbDeleteChunksItemStateChanged
        distRenderer.setDeleteChunks(jcbDeleteChunks.isSelected());
    }//GEN-LAST:event_jcbDeleteChunksItemStateChanged

    private void jbSpawnWorkerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSpawnWorkerActionPerformed
        DistributedWorkerJFrame dwjf = new DistributedWorkerJFrame();
        dwjf.setVisible(true);
    }//GEN-LAST:event_jbSpawnWorkerActionPerformed

    private void jbChooseDistDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChooseDistDirectoryActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        if( file == null ) return;
        distRenderer.setDirectory(file.toString());
    }//GEN-LAST:event_jbChooseDistDirectoryActionPerformed

    private void jrDistributedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrDistributedItemStateChanged
        updateRenderer();
    }//GEN-LAST:event_jrDistributedItemStateChanged

    private void jrLocalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrLocalItemStateChanged
        updateRenderer();
    }//GEN-LAST:event_jrLocalItemStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgRenderer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbChooseDistDirectory;
    private javax.swing.JButton jbSpawnWorker;
    private javax.swing.JCheckBox jcbDeleteChunks;
    private javax.swing.JRadioButton jrDistributed;
    private javax.swing.JRadioButton jrLocal;
    private javax.swing.JTextField jtDistributionDirectory;
    // End of variables declaration//GEN-END:variables
    Fractal fractal = null;
    public Fractal getFractal(){ return fractal; }
    public void setFractal(Fractal f) {
        fractal=f;
        updateRenderer();
    }
    public void updateRenderer() { 
       if( jrLocal.isSelected() ) {
            fractal.setRenderer(localRenderer);
        }
        if( jrDistributed.isSelected()) {
            fractal.setRenderer(distRenderer);
            jtDistributionDirectory.setText(distRenderer.getDirectory().toString());
            
        }  
    }
    public ChunkRenderer getDistRenderer() { return distRenderer; }
}
