/*
 * RenderPdfJPanell.java
 *
 * Created on 12 July 2007, 21:19
 */

package fractal.gui.panel;

import fractal.producer.Fractal;

/**
 *
 * @author  Owner
 */
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import fractal.gui.FractalJFrame;
import fractal.producer.render.chunkrenderer.ChunkRenderer;
import fractal.producer.render.chunkrenderer.CreatePdfMaster;
import fractal.producer.render.chunkrenderer.FractalChunk;
import fractal.producer.result.PixelValue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class RenderPdfJPanel extends javax.swing.JPanel {
    CreatePdfMaster cpm = new CreatePdfMaster();
    FractalJFrame fjf = null;
    RenderJPanel rjp = null;
    /** Creates new form RenderPdfJPanell */
    public RenderPdfJPanel() {
        initComponents();
        jcbPageSizeItemStateChanged(null);
    }
    
    
    public void setRenderJPanel(RenderJPanel rj) {
        this.rjp=rj;
    }
    public void setFractalJFrame(FractalJFrame fjf) {
        this.fjf=fjf;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbPageSize = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jtPageWidth = new javax.swing.JTextField();
        jtPageHeight = new javax.swing.JTextField();
        jbCreatePdf = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtPixelWidth = new javax.swing.JTextField();
        jtPixelHeight = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jbCreateChunks = new javax.swing.JButton();
        jbCreateMissingChunks = new javax.swing.JButton();
        jcbTileWidth = new javax.swing.JComboBox();
        jcbTileHeight = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jcbDPI = new javax.swing.JComboBox();
        jcbRenderNo = new javax.swing.JComboBox();

        jLabel1.setText("Width mm");

        jLabel2.setText("Height mm");

        jcbPageSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A5", "A4", "A3", "A2", "A1", "A0" }));
        jcbPageSize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbPageSizeItemStateChanged(evt);
            }
        });

        jLabel3.setText("Page Size");

        jtPageWidth.setEditable(false);

        jtPageHeight.setEditable(false);

        jbCreatePdf.setText("Save Finished PDF(Open Master)");
        jbCreatePdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCreatePdfActionPerformed(evt);
            }
        });

        jLabel5.setText("Pixel Width");

        jLabel6.setText("Pixel Height");

        jtPixelWidth.setEditable(false);

        jtPixelHeight.setEditable(false);

        jLabel4.setText("Tile W");

        jLabel7.setText("Tile H");

        jbCreateChunks.setText("Create Chunks(Uses Distributed Render Directory)");
        jbCreateChunks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCreateChunksActionPerformed(evt);
            }
        });

        jbCreateMissingChunks.setText("Create Missing Chunks(Open Master)");
        jbCreateMissingChunks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCreateMissingChunksActionPerformed(evt);
            }
        });

        jcbTileWidth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "72", "144", "288", "576", "720", "1080", "1440", "2880" }));
        jcbTileWidth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbTileWidthItemStateChanged(evt);
            }
        });

        jcbTileHeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "72", "144", "288", "576", "720", "1080", "1440", "2880" }));
        jcbTileHeight.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbTileHeightItemStateChanged(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("DPI"));
        jcbDPI.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "72", "144", "288", "576", "1080", "1440", "2880" }));
        jcbDPI.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbDPIItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbDPI, 0, 74, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jcbDPI, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        jcbRenderNo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Render 0", "Render 1", "Render 2", "Render 3", "Render 4", "Render 5", "Render 6", "Render 7", "Render 8", "Render 9", "Render 10" }));
        jcbRenderNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbRenderNoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jcbTileWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbTileHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(386, 386, 386))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtPageHeight)
                            .addComponent(jtPageWidth)
                            .addComponent(jcbPageSize, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbRenderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jtPixelWidth, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtPixelHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(72, 72, 72))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbCreateChunks, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbCreatePdf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jbCreateMissingChunks, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                        .addGap(53, 53, 53))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jcbTileHeight, jcbTileWidth});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jtPixelHeight, jtPixelWidth});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbPageSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbRenderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtPageWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jtPixelWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtPageHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jtPixelHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbTileWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbTileHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbCreateChunks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbCreateMissingChunks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbCreatePdf)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, jLabel7});

    }// </editor-fold>//GEN-END:initComponents
    
    private void jbCreatePdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCreatePdfActionPerformed
        loadMasterFile();
        ChunkRenderer cr = rjp.getDistRenderer();
        cpm.update(cr);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showSaveDialog(this);
        File pdf = chooser.getSelectedFile();
        if( pdf== null) return;
        FileOutputStream fos = null;
        BufferedOutputStream pdfos = null;
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try{
            fos = new FileOutputStream(pdf);
            pdfos = new BufferedOutputStream(fos,30000);
            FontFactory.registerDirectories();
            PdfWriter writer = PdfWriter.getInstance(document, pdfos);
            Rectangle ps = cpm.getPagesize();
            System.out.println("PageSize="+ps);
            System.out.println("Fractal Aspect="+fractal.getAspect());
            document.setPageSize(ps);
            document.open();
            document.newPage();
            PdfContentByte cb = writer.getDirectContent();
            float width = document.getPageSize().getWidth();
            float height = document.getPageSize().getHeight();
            double tileWidth = (width/cpm.getPixelWidth())*cpm.getTileWidth();
            double tileHeight =(height/cpm.getPixelHeight())*cpm.getTileHeight();
            
            String dir = cr.getDirectory();
            int rows = cr.getRows();
            int cols = cr.getColumns();
            File out = new File(dir,"Out");
            File in = new File(dir,"In");
            BufferedImage bi = new BufferedImage(cpm.getTileWidth(),cpm.getTileHeight(),BufferedImage.TYPE_INT_RGB);
            System.out.println("Rows="+rows);
            System.out.println("Cols="+cols);
            System.out.println("TileWidth="+tileWidth+" TileHeight="+tileHeight);
            System.out.println("ColWidth="+cr.getColWidth()+" RowHeight="+cr.getRowHeight());
            System.out.println("PageSize w="+ps.getWidth()+" h="+ps.getHeight());
            for(int i=0; i<cols; i++) {
                for(int j=0;j<rows;j++) {
                    File f = new File(out,cpm.getRenderNo()+"-"+i+"-"+j+".fch");
                    if( !f.exists() ) {
                        System.out.println("Cant Find "+f.toString());
                    }else {
                        System.out.println("Loading "+f.toString());
                        FileInputStream fis = null;
                        BufferedInputStream bis = null;
                        ObjectInputStream ois = null;
                        try{
                            System.gc();
                            
                            fis = new FileInputStream(f);
                            bis = new BufferedInputStream(fis,(int)f.length());
                            ois = new ObjectInputStream(bis);
                            FractalChunk fc = (FractalChunk)ois.readObject();
                            
                            double xft = tileWidth*i;
                            double yft = tileHeight*j;
                            PixelValue[][] pixels = fc.getPixels();
                            double tw = pixels[0].length*width/cpm.getPixelWidth();
                            double th = pixels.length*height/cpm.getPixelHeight();
                            
                            bi = toBufferedImage(pixels,bi);
                            //ImageIO.write(bi,"JPEG",new File(out,cpm.getRenderNo()+"-"+i+"-"+j+".jpg"));
                            //System.out.println(""+new File(out,cpm.getRenderNo()+"-"+i+"-"+j+".jpg"));
                            Image image = Image.getInstance(bi,null);
                            int dpi = Integer.parseInt((String)this.jcbDPI.getSelectedItem());
                            image.setDpi(dpi,dpi);
                            System.out.println("i="+i+" j="+j+"x="+xft+" y="+(ps.getHeight()-yft-tileHeight));
                            
                            image.setAbsolutePosition((float)xft,(float)(ps.getHeight()-yft-th));
                            image.scaleAbsolute((float)tw,(float)th);
                            cb.addImage(image);
                        }catch(BadElementException bee) {
                            bee.printStackTrace();
                        } catch(DocumentException de) {
                            de.printStackTrace();
                        }catch(IOException io){
                            io.printStackTrace();
                        }catch(IllegalStateException ise) {
                            // Thrown by ObjectInputStream.readObject Sometimes
                            // when another process writing the file
                            ise.printStackTrace();
                        }catch(IllegalArgumentException iae) {
                            // Thrown By BufferedInputStream(fis,-1)
                            // when file size comes in wrong
                            iae.printStackTrace();
                        }catch(ClassNotFoundException cnfe) {
                            cnfe.printStackTrace();
                        }catch(OutOfMemoryError oome) {
                            oome.printStackTrace();
                        }finally{
                            try{
                                if(ois!=null)ois.close();
                                if(bis!=null)bis.close();
                                if(fis!=null)fis.close();
                            }catch(IOException io2) {
                                io2.printStackTrace();
                            }
                        }
                    }
                }
            }
        }catch(IOException io) {
            io.printStackTrace();
        }catch(DocumentException de) {
            de.printStackTrace();
        }finally{
            if(document!=null)document.close();
            try{
                if(pdfos!=null)pdfos.close();
                if(fos!=null)fos.close();
            }catch(IOException io) {io.printStackTrace();}
        }
       // cpm.restore(cr);
    }//GEN-LAST:event_jbCreatePdfActionPerformed
    
    public BufferedImage toBufferedImage(PixelValue[][] pv,BufferedImage bi) {
        if( pv == null ) return null;
        if( bi.getHeight()!= pv.length||bi.getWidth()!=pv[0].length){
            bi = new BufferedImage(pv[0].length,pv.length,BufferedImage.TYPE_INT_RGB);
        }
        Graphics g = bi.getGraphics();
        for(int i=0;i<pv.length;i++){
            for(int j=0; j<pv[0].length;j++) {
                g.setColor(pv[i][j].getColour());
                g.drawLine(j,i,j,i);
            }
        }
        return bi;
    }
    
    
    private void jbCreateMissingChunksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCreateMissingChunksActionPerformed
        loadMasterFile();
        ChunkRenderer cr = rjp.getDistRenderer();
        cpm.update(cr);
        String dir = cr.getDirectory();
        int rows = cr.getRows();
        int cols = cr.getColumns();
        File out = new File(dir,"Out");
        File in = new File(dir,"In");
        for(int i=0; i<cols; i++) {
            for(int j=0;j<rows;j++) {
                File f = new File(out,cpm.getRenderNo()+"-"+i+"-"+j+".fch");
                if( !f.exists() ) {
                    try{
                        cr.createChunk(i,j);
                    }catch(IOException io) { io.printStackTrace(); }
                }
            }
        }
    }//GEN-LAST:event_jbCreateMissingChunksActionPerformed
    public void loadMasterFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        if( file == null ) return;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);
            cpm = (CreatePdfMaster)ois.readObject();
            update();
        }catch(IOException io) {
            System.out.println("IOException");
            io.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            System.out.println("ClassNotFoundException");
        }
        FractalJFrame fjf = (FractalJFrame)SwingUtilities.getRoot(this);
        try{
            fjf.load(cpm.getFractal());
        }catch(IOException io) {
            io.printStackTrace();
        }catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
    private void jcbRenderNoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbRenderNoItemStateChanged
        if(flag)return;
        cpm.setRenderNo(jcbRenderNo.getSelectedIndex());
    }//GEN-LAST:event_jcbRenderNoItemStateChanged
    
    private void jbCreateChunksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCreateChunksActionPerformed
        try{
            cpm.createChunks(rjp.getDistRenderer());
            
        }catch(IOException io) {
            io.printStackTrace();
        }
    }//GEN-LAST:event_jbCreateChunksActionPerformed
    
    private void jcbTileHeightItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbTileHeightItemStateChanged
        if(flag)return;
        int i = Integer.parseInt((String)jcbTileHeight.getSelectedItem());
        cpm.setTileHeight(i);
    }//GEN-LAST:event_jcbTileHeightItemStateChanged
    
    private void jcbTileWidthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbTileWidthItemStateChanged
        if(flag)return;
        int i = Integer.parseInt((String)jcbTileWidth.getSelectedItem());
        cpm.setTileWidth(i);
    }//GEN-LAST:event_jcbTileWidthItemStateChanged
    
    private void jcbDPIItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbDPIItemStateChanged
        if(flag)return;
        int i = Integer.parseInt((String)jcbDPI.getSelectedItem());
        cpm.setDpi(i);
    }//GEN-LAST:event_jcbDPIItemStateChanged
    
    private void jcbPageSizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbPageSizeItemStateChanged
        if(flag)return;
        String s = (String)jcbPageSize.getSelectedItem();
        Rectangle size = PageSize.A5;
        if("A5".equals(s) )size=PageSize.A5;
        if("A4".equals(s) )size=PageSize.A4;
        if("A3".equals(s) )size=PageSize.A3;
        if("A2".equals(s) )size=PageSize.A2;
        if("A1".equals(s) )size=PageSize.A1;
        if("A0".equals(s) )size=PageSize.A0;
        cpm.setPagesize(size);
        update();
    }//GEN-LAST:event_jcbPageSizeItemStateChanged
    boolean flag = false;
    public void update() {
        if(fractal==null)return;
        flag=true;
        jtPageHeight.setText(cpm.getPageHeightMM());
        jtPageWidth.setText(cpm.getPageWidthMM());
        jtPixelWidth.setText(Integer.toString(cpm.getPixelWidth()));
        jtPixelHeight.setText(Integer.toString(cpm.getPixelHeight()));
        jcbDPI.setSelectedItem(Integer.toString(cpm.getDpi()));
        jcbTileWidth.setSelectedItem(Integer.toString(cpm.getTileWidth()));
        jcbTileHeight.setSelectedItem(Integer.toString(cpm.getTileHeight()));
        switch(cpm.getRenderNo()){
            case 0:jcbRenderNo.setSelectedItem("Render 0");
            case 1:jcbRenderNo.setSelectedItem("Render 1");
            case 2:jcbRenderNo.setSelectedItem("Render 2");
            case 3:jcbRenderNo.setSelectedItem("Render 3");
            case 4:jcbRenderNo.setSelectedItem("Render 4");
            case 5:jcbRenderNo.setSelectedItem("Render 5");
            case 6:jcbRenderNo.setSelectedItem("Render 6");
            case 7:jcbRenderNo.setSelectedItem("Render 7");
            case 8:jcbRenderNo.setSelectedItem("Render 8");
            case 9:jcbRenderNo.setSelectedItem("Render 9");
            case 10:jcbRenderNo.setSelectedItem("Render 10");
        }
        Rectangle ps = cpm.getPagesize();
        float w = ps.getWidth();
        float h = ps.getHeight();
        w = Math.min(w,h);
        h = Math.max(w,h);
        if(w == PageSize.A5.getWidth()) jcbPageSize.setSelectedItem("A5");
        if(w == PageSize.A4.getWidth()) jcbPageSize.setSelectedItem("A4");
        if(w == PageSize.A3.getWidth()) jcbPageSize.setSelectedItem("A3");
        if(w == PageSize.A2.getWidth()) jcbPageSize.setSelectedItem("A2");
        if(w == PageSize.A1.getWidth()) jcbPageSize.setSelectedItem("A1");
        if(w == PageSize.A0.getWidth()) jcbPageSize.setSelectedItem("A0");
        flag=false;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbCreateChunks;
    private javax.swing.JButton jbCreateMissingChunks;
    private javax.swing.JButton jbCreatePdf;
    private javax.swing.JComboBox jcbDPI;
    private javax.swing.JComboBox jcbPageSize;
    private javax.swing.JComboBox jcbRenderNo;
    private javax.swing.JComboBox jcbTileHeight;
    private javax.swing.JComboBox jcbTileWidth;
    private javax.swing.JTextField jtPageHeight;
    private javax.swing.JTextField jtPageWidth;
    private javax.swing.JTextField jtPixelHeight;
    private javax.swing.JTextField jtPixelWidth;
    // End of variables declaration//GEN-END:variables
    Fractal fractal = null;
    public Fractal getFractal(){ return fractal; }
    public void setFractal(Fractal f) {
        fractal=f;
        cpm.setFractal(f);
    }
    
}