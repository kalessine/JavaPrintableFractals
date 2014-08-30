/*
 * FractalJFrame.java
 *
 * Created on 9 February 2007, 15:35
 */

package fractal.gui;

import fractal.gui.frames.StatisticsInspectorJFrame;
import fractal.gui.panel.ColourPanel;
import fractal.gui.panel.FormulaJPanel;
import fractal.gui.panel.FractalJPanel;
import fractal.gui.panel.PhysicalJPanel;
import fractal.gui.panel.RenderJPanel;
import fractal.gui.panel.RenderPdfJPanel;
import fractal.gui.panel.TransformJPanel;
import fractal.gui.panel.util.SelectPointJDialog;
import fractal.producer.Fractal;
import fractal.producer.exception.RenderException;
import fractal.producer.render.RenderListener;
import fractal.producer.render.chunkrenderer.CreatePdfMaster;
import fractal.producer.result.PixelValue;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author  Owner
 */
public class FractalJFrame extends javax.swing.JFrame implements RenderListener {
    
    Fractal fractal = new Fractal();
    
    StatisticsInspectorJFrame statistics = new StatisticsInspectorJFrame();
    FractalJPanel fractalPanel = new FractalJPanel();
    PhysicalJPanel physicalJPanel1 = new PhysicalJPanel();
    TransformJPanel transformJPanel1 = new TransformJPanel();
    FormulaJPanel formulaJPanel1 = new FormulaJPanel();
    ColourPanel colourPanel1 = new ColourPanel();
    RenderJPanel renderJPanel1 = new RenderJPanel();
    RenderPdfJPanel renderPdfJPanel1 = new RenderPdfJPanel();
    
    
    /** Creates new form FractalJFrame */
    public FractalJFrame() {
        initComponents();
        jtab.add(physicalJPanel1,"Physical");
        jtab.add(transformJPanel1,"Transform");
        jtab.add(formulaJPanel1,"Formula");
        jtab.add(colourPanel1,"Colour");
        jtab.add(renderJPanel1,"Render");
        jtab.add(renderPdfJPanel1,"Pdf");
        jscrollPane.getViewport().setView(fractalPanel);
        this.renderJPanel1.setFractal(fractal);
        this.renderPdfJPanel1.setFractal(fractal);
        this.fractalPanel.setFractal(fractal);
        this.fractalPanel.setStats(statistics);
        fractal.getRenderer().addRenderListener(this);
        this.physicalJPanel1.setFractal(fractal);
        this.transformJPanel1.setFractal(fractal);
        this.colourPanel1.setFractal(fractal);
        this.formulaJPanel1.setFractal(fractal);
        this.renderPdfJPanel1.setFractalJFrame(this);
        this.renderPdfJPanel1.setRenderJPanel(this.renderJPanel1);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jtab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jscrollPane = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jbRender = new javax.swing.JButton();
        jlStatus = new javax.swing.JLabel();
        jbZoomIn = new javax.swing.JButton();
        jbZoomOut = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmenuLoad = new javax.swing.JMenuItem();
        jmenuSave = new javax.swing.JMenuItem();
        jmenuSaveJpeg = new javax.swing.JMenuItem();
        jmenuExportAnimated = new javax.swing.JMenuItem();
        jmenuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmenuInspector = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.add(jscrollPane, java.awt.BorderLayout.CENTER);

        jbRender.setText("Render");
        jbRender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRenderActionPerformed(evt);
            }
        });

        jlStatus.setText("Status: READY");

        jbZoomIn.setText("Zoom In");
        jbZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbZoomInActionPerformed(evt);
            }
        });

        jbZoomOut.setText("Zoom Out");
        jbZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbZoomOutActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jbZoomIn)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jbZoomOut)
                .add(23, 23, 23)
                .add(jbRender)
                .add(12, 12, 12)
                .add(jlStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 210, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jbZoomIn)
                    .add(jlStatus)
                    .add(jbRender)
                    .add(jbZoomOut)
                    .add(jButton1))
                .addContainerGap())
        );
        jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jtab.addTab("Fractal", jPanel1);

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jmenuLoad.setText("Load");
        jmenuLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuLoadActionPerformed(evt);
            }
        });

        jMenu1.add(jmenuLoad);

        jmenuSave.setText("Save");
        jmenuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuSaveActionPerformed(evt);
            }
        });

        jMenu1.add(jmenuSave);

        jmenuSaveJpeg.setText("Save As Jpeg");
        jMenu1.add(jmenuSaveJpeg);

        jmenuExportAnimated.setText("Export Animated Gif");
        jMenu1.add(jmenuExportAnimated);

        jmenuExit.setText("Exit");
        jmenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuExitActionPerformed(evt);
            }
        });

        jMenu1.add(jmenuExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("View");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jmenuInspector.setText("Statistics");
        jmenuInspector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuInspectorActionPerformed(evt);
            }
        });

        jMenu2.add(jmenuInspector);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jtab, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jtab, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuExitActionPerformed
      System.exit(0);
    }//GEN-LAST:event_jmenuExitActionPerformed
    
    private void jmenuLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuLoadActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        if( file == null ) return;
        try{
            load(file);
        }catch(IOException io) {
            System.out.println("IOException");
            io.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            System.out.println("ClassNotFoundException");
        }
    }//GEN-LAST:event_jmenuLoadActionPerformed
    public void load(File f)throws IOException,ClassNotFoundException {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        if( o instanceof Fractal )
            load((Fractal)o);
        else if( o instanceof CreatePdfMaster) {
            load( ((CreatePdfMaster)o).getFractal());
        }
        ois.close();
        fis.close();
    }
    public void load(Fractal f)throws IOException,ClassNotFoundException {
        fractal = f;
        this.renderJPanel1.setFractal(fractal);
        this.renderPdfJPanel1.setFractal(fractal);
        this.fractalPanel.setFractal(fractal);
        this.fractalPanel.setStats(statistics);
        fractal.getRenderer().addRenderListener(this);
        this.physicalJPanel1.setFractal(fractal);
        this.transformJPanel1.setFractal(fractal);
        this.colourPanel1.setFractal(fractal);
        this.formulaJPanel1.setFractal(fractal);
        
    }
    
    private void jmenuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuSaveActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showSaveDialog(this);
        File file = chooser.getSelectedFile();
        if( file == null ) return;
        try{
            save(file);
        }catch(IOException io) {}
    }//GEN-LAST:event_jmenuSaveActionPerformed
    public void save(File f)throws IOException {
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(fractal);
        oos.flush();
        oos.close();
        fos.flush();
        fos.close();
    }
    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed
    
    private void jmenuInspectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuInspectorActionPerformed
        System.out.println("Stats!");
        if(!statistics.isVisible())
            statistics.setVisible(true);
        else statistics.setVisible(false);
    }//GEN-LAST:event_jmenuInspectorActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SelectPointJDialog spjd = new SelectPointJDialog(this,fractal);
        System.out.println("P1="+spjd.getComplexNumber());
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void jbZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbZoomOutActionPerformed
        fractal.getCoordinates().zoom(fractal.getCoordinates().getCenterX(),fractal.getCoordinates().getCenterY(),0.5d);
        fractal.firePhysicalChanged(this);
    }//GEN-LAST:event_jbZoomOutActionPerformed
    
    private void jbZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbZoomInActionPerformed
        fractal.getCoordinates().zoom(fractal.getCoordinates().getCenterX(),fractal.getCoordinates().getCenterY(),2d);
        fractal.firePhysicalChanged(this);
    }//GEN-LAST:event_jbZoomInActionPerformed
    
    private void jbRenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRenderActionPerformed
        try{
            if( fractal.isRendering())fractal.interruptRender();
            fractal.startRender();
        }catch(RenderException re) {
            re.printStackTrace();
        }
    }//GEN-LAST:event_jbRenderActionPerformed
    
    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenu1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FractalJFrame().setVisible(true);
            }
        });
    }
    
    public void renderFinished() {
        rendering=false;
        jlStatus.setText("Status: Ready");
    }
    public void renderStarted() {
        jlStatus.setText("Status: Initialising");
        rendering=false;
    }
    boolean rendering = false;
    public void updatePixel(int x, int y, PixelValue val){
        if(!rendering)jlStatus.setText("Status: Rendering");
        rendering=true;
    }
    public void updateWholeView(BufferedImage bi) {}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbRender;
    private javax.swing.JButton jbZoomIn;
    private javax.swing.JButton jbZoomOut;
    private javax.swing.JLabel jlStatus;
    private javax.swing.JMenuItem jmenuExit;
    private javax.swing.JMenuItem jmenuExportAnimated;
    private javax.swing.JMenuItem jmenuInspector;
    private javax.swing.JMenuItem jmenuLoad;
    private javax.swing.JMenuItem jmenuSave;
    private javax.swing.JMenuItem jmenuSaveJpeg;
    private javax.swing.JScrollPane jscrollPane;
    private javax.swing.JTabbedPane jtab;
    // End of variables declaration//GEN-END:variables
    public Fractal getFractal() { return fractal; }
}