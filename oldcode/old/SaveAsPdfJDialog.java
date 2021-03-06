/*
 * SaveAsPdfJDialog.java
 *
 * Created on November 18, 2006, 2:14 PM
 */

package fractal.gui.old;

import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import fractal.gui.*;
import fractal.producer.formula.Formula;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import com.lowagie.text.*;
import com.lowagie.*;
import fractal.producer.calc.*;
import fractal.producer.colour.*;
/**
 *
 * @author  james
 */
public class SaveAsPdfJDialog extends javax.swing.JDialog {
    MathContext PAGE_MATH_CONTEXT = MathContext.DECIMAL64;
    DecimalFormat format = new DecimalFormat("0.00");

    BigDecimal x;
    BigDecimal y;
    BigDecimal w;
    BigDecimal h;

    int aspectRatio = FractalViewJPanel.ASPECT_RATIO_SQUARE;

    ColourMap colorModel = null;
    Formula producer = null;
    Rectangle pageSize = PageSize.A4;
    double dpi = 72d;
    MathContext context = null;

    Thread worker = null;
    boolean stop = false;

    /** Creates new form SaveAsPdfJDialog */
    public SaveAsPdfJDialog(java.awt.Frame parent, boolean modal,ColourMap model,Formula prod,BigDecimal x, BigDecimal y, BigDecimal w, BigDecimal h,int aspectRatio,MathContext context) {
        super(parent, modal);
        initComponents();
        this.colorModel=model;
        this.producer = prod;
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.aspectRatio=aspectRatio;
        this.context=context;
        Dimension frameSize = parent.getSize();
        Point loc = parent.getLocation();
        Point center = new Point(loc.x+frameSize.width/2,loc.y+frameSize.height/2);
        // Move Left by half frame's width, move up by half frames height
        Point center2 = new Point(center.x-getWidth()/2,center.y-getHeight()/2);
        setLocation(center2);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jtWidth = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtHeight = new javax.swing.JTextField();
        jcbPaperSize = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jcbDotsPerInch = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jbSaveAsPdf = new javax.swing.JButton();
        jbCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jpbOverall = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jpbCurrentTile = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jtWidth.setEnabled(false);
        jtWidth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtWidthKeyTyped(evt);
            }
        });

        jLabel1.setText("Width");

        jLabel2.setText("Height");

        jtHeight.setEnabled(false);
        jtHeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtHeightKeyTyped(evt);
            }
        });

        jcbPaperSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Custom", "A0", "A1", "A2", "A3", "A4", "A5", "A6" }));
        jcbPaperSize.setSelectedIndex(5);
        jcbPaperSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPaperSizeActionPerformed(evt);
            }
        });

        jLabel3.setText("Paper Size");

        jcbDotsPerInch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "72", "144", "216", "288", "360", "432", "504", "576", "720", "1440" }));
        jcbDotsPerInch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDotsPerInchActionPerformed(evt);
            }
        });

        jLabel4.setText("Dots Per Inch");

        jbSaveAsPdf.setText("Save As Pdf");
        jbSaveAsPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSaveAsPdfActionPerformed(evt);
            }
        });

        jbCancel.setText("Cancel");
        jbCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        jLabel5.setText("Overall");

        jLabel6.setText("Current Tile");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jpbCurrentTile, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(12, 12, 12))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel6)
                        .addContainerGap(86, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jpbOverall, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(12, 12, 12))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel5)
                        .addContainerGap(116, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpbOverall, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpbCurrentTile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel2)
                            .add(jLabel1)
                            .add(jLabel3)))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel4)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jtWidth, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jtHeight, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .add(jcbPaperSize, 0, 132, Short.MAX_VALUE)
                    .add(jcbDotsPerInch, 0, 132, Short.MAX_VALUE))
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jbSaveAsPdf)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 25, Short.MAX_VALUE)
                .add(jbCancel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(24, 24, 24))
            .add(layout.createSequentialGroup()
                .add(35, 35, 35)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jcbPaperSize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jtWidth, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jtHeight, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jcbDotsPerInch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(17, 17, 17)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jbSaveAsPdf)
                    .add(jbCancel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtHeightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtHeightKeyTyped
        float width = (float)convertMillimetersToPoints(Double.parseDouble(jtWidth.getText()));
        float height = (float)convertMillimetersToPoints(Double.parseDouble(jtHeight.getText()));
        pageSize = new Rectangle(width,height);
    }//GEN-LAST:event_jtHeightKeyTyped

    private void jtWidthKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtWidthKeyTyped
        float width = (float)convertMillimetersToPoints(Double.parseDouble(jtWidth.getText()));
        float height = (float)convertMillimetersToPoints(Double.parseDouble(jtHeight.getText()));
        pageSize = new Rectangle(width,height);
    }//GEN-LAST:event_jtWidthKeyTyped

    private void jbSaveAsPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveAsPdfActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showSaveDialog(this);
        File file = chooser.getSelectedFile();
        if( file == null  ) return;

        try{
            final FileOutputStream out = new FileOutputStream(file);
            FontFactory.registerDirectories();
            DefaultFontMapper mapper = new DefaultFontMapper();
            //if( System.getProperties().getProperty("operating.system").equalsIgnoreCase("windows")) )
            mapper.insertDirectory("c:\\windows\\fonts");

            final com.lowagie.text.Document document = new com.lowagie.text.Document();
            final PdfWriter writer = PdfWriter.getInstance(document, out);
            if( aspectRatio == FractalViewJPanel.ASPECT_RATIO_SQUARE ){
                   document.setPageSize(pageSize);
            }
            if( aspectRatio == FractalViewJPanel.ASPECT_RATIO_A_PORTRAIT ){
                    document.setPageSize(pageSize);
            }
            if( aspectRatio == FractalViewJPanel.ASPECT_RATIO_A_LANDSCAPE ){
                    document.setPageSize(new Rectangle(pageSize.height(),pageSize.width()));
            }

            if (document.isOpen()) {
                document.newPage();
            } else {
                document.open();
            }
            worker = new Thread() {
                public void run() {
//                    try{
                    PdfContentByte cb = writer.getDirectContent();
                    float width = document.getPageSize().width();
                    float height = document.getPageSize().height();
                    System.out.println("Width="+width+" Height="+height);
//                    Graphics2D g = cb.createGraphics(width,height);
                    int horizTiles = calculateNoOfTiles(new BigDecimal(width),dpi);
                    int vertTiles = calculateNoOfTiles(new BigDecimal(height),dpi);
                    System.out.println("vertiles="+vertTiles+" horiztiles="+horizTiles);
                    //int horizTiles = 20;
                    //int vertTiles = 20;
                  
                    //if( dpi == 1440d ){
                    //    // Lots and Lots and Lots of Tiles!!!
                    //    vertTiles = 60;
                    //    horizTiles = 60;
                    //}
                   
                    //double dpi = 72d*4;
                    double multiplier = dpi/72d;
                    BigDecimal total_pixel_width = new BigDecimal(width).multiply(new BigDecimal(multiplier));
                    BigDecimal total_pixel_height = new BigDecimal(height).multiply(new BigDecimal(multiplier));
                    BigDecimal hSpace = new BigDecimal(width).divide(new BigDecimal(horizTiles),PAGE_MATH_CONTEXT);
                    BigDecimal vSpace = new BigDecimal(height).divide(new BigDecimal(vertTiles),PAGE_MATH_CONTEXT);
                    System.out.println("HSpace="+hSpace+" VSPace="+vSpace);
                    BigDecimal tileWidth = w.divide(new BigDecimal(horizTiles),PAGE_MATH_CONTEXT);
                    BigDecimal tileHeight = h.divide(new BigDecimal(vertTiles),PAGE_MATH_CONTEXT);
                    
                    BigDecimal pxwidth = total_pixel_width.divide(new BigDecimal(horizTiles),PAGE_MATH_CONTEXT);
                    BigDecimal pxheight = total_pixel_height.divide(new BigDecimal(vertTiles),PAGE_MATH_CONTEXT);
                    System.out.println("Total Pixels width:"+total_pixel_width+" Height:"+total_pixel_height);
                    System.out.println("PXwidth="+pxwidth+" PXHeight="+pxheight);
                    int pixelwidth = pxwidth.intValue();
                    int pixelheight = pxheight.intValue();
                    int noOfTiles = vertTiles * horizTiles;
                    int currentTile = 1;
                    jpbOverall.setMinimum(1);
                    jpbOverall.setMaximum(noOfTiles);
                    BufferedImage bi = new BufferedImage(pixelwidth,pixelheight,BufferedImage.TYPE_INT_RGB);
                    for(int i=0;i<vertTiles;i++)
                        for(int j=0;j<horizTiles;j++){
                        create(
                                x.add(tileWidth.multiply(new BigDecimal(j),context),context),
                                y.add(tileHeight.multiply(new BigDecimal(i),context),context),
                                tileWidth,tileHeight,
                                bi);
                        AffineTransform xf = new AffineTransform();
                        BigDecimal xft = hSpace.multiply(new BigDecimal(j),PAGE_MATH_CONTEXT);
                        BigDecimal yft = vSpace.multiply(new BigDecimal(i),PAGE_MATH_CONTEXT);
                        xf.translate(xft.doubleValue(),yft.doubleValue());
                        xf.scale(72d/dpi,72d/dpi);
                        try{
                        Image image = Image.getInstance(bi,null);
                        //image.setDpi((int)dpi,(int)dpi);
                        image.setAbsolutePosition(xft.floatValue(),height-yft.floatValue()-vSpace.floatValue());
                        image.scaleAbsolute(hSpace.floatValue(),vSpace.floatValue());
                        cb.addImage(image);
                        }catch(BadElementException bee) {
                            bee.printStackTrace();
                        }
                        catch(DocumentException de) {
                        de.printStackTrace();
                        }
                        catch(IOException io) {
                            io.printStackTrace();
                        }
                        
//                        g.drawImage(bi,xf,null);
                        jpbOverall.setValue(currentTile);
                        currentTile++;
                        if( stop ) {
                            i=vertTiles;
                            j=horizTiles;
                        }
                        }
                    jpbOverall.setValue(jpbOverall.getMaximum());
//                    g.dispose();
                    document.close();
                    cb = null;
                    try{
                    out.flush();
                    out.close();
                    }catch(IOException io) {
                        System.out.println("IOException closing file");
                    }
                    dispose();
                    System.gc();
                }
            };
        }catch(com.lowagie.text.DocumentException de) {
            // Since this is a new document
            // and we're not really doing anything overly taxing
            // this shouldn't really happen (i hope!!!)
            de.printStackTrace();
        }catch(IOException io) {
            JOptionPane.showMessageDialog(this,"IO Exception writing to file","IO Exception",JOptionPane.ERROR_MESSAGE);
            return;
        }
        worker.start();
        jbSaveAsPdf.setEnabled(false);

    }//GEN-LAST:event_jbSaveAsPdfActionPerformed
    private int calculateNoOfTiles(BigDecimal size,double dpi) {
        double multiplier = dpi/72d;
        BigDecimal tpixels = size.multiply(new BigDecimal(multiplier));
        for(int i=(int)(10*multiplier);i<1000;i++) {
            if( tpixels.remainder(new BigDecimal(i)).compareTo(BigMath.ZERO)==0 ) {
                System.out.println("found tiles="+i+" remainder="+tpixels.remainder(new BigDecimal(i)));
                return i;
            }
        }
        return 60;
    }
    private void jbCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jbCancelActionPerformed

    private void jcbDotsPerInchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDotsPerInchActionPerformed
        dpi = Double.parseDouble((String)jcbDotsPerInch.getSelectedItem());
    }//GEN-LAST:event_jcbDotsPerInchActionPerformed

    private void jcbPaperSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPaperSizeActionPerformed
        if( ((String)jcbPaperSize.getSelectedItem()).equalsIgnoreCase("Custom") ){
            jtWidth.setEnabled(true);
            jtHeight.setEnabled(true);
            float width = (float)convertMillimetersToPoints(Double.parseDouble(jtWidth.getText()));
            float height = (float)convertMillimetersToPoints(Double.parseDouble(jtHeight.getText()));
            pageSize = new Rectangle(width,height);
        } else if( ((String)jcbPaperSize.getSelectedItem()).equals("A0") ){
            jtWidth.setEnabled(false);
            jtHeight.setEnabled(false);
            pageSize=PageSize.A0;
        } else if( ((String)jcbPaperSize.getSelectedItem()).equals("A1") ){
            jtWidth.setEnabled(false);
            jtHeight.setEnabled(false);
            pageSize=PageSize.A1;
        } else if( ((String)jcbPaperSize.getSelectedItem()).equals("A2") ){
            jtWidth.setEnabled(false);
            jtHeight.setEnabled(false);
            pageSize=PageSize.A2;
        } else if( ((String)jcbPaperSize.getSelectedItem()).equals("A3") ){
            jtWidth.setEnabled(false);
            jtHeight.setEnabled(false);
            pageSize=PageSize.A3;
        } else if( ((String)jcbPaperSize.getSelectedItem()).equals("A4") ){
            jtWidth.setEnabled(false);
            jtHeight.setEnabled(false);
            pageSize=PageSize.A4;
        } else if( ((String)jcbPaperSize.getSelectedItem()).equals("A5") ){
            jtWidth.setEnabled(false);
            jtHeight.setEnabled(false);
            pageSize=PageSize.A5;
        } else if( ((String)jcbPaperSize.getSelectedItem()).equals("A6") ){
            jtWidth.setEnabled(false);
            jtHeight.setEnabled(false);
            pageSize=PageSize.A6;
        }
        jtWidth.setText(format.format(
                convertPointsToMillimeters(pageSize.width())));
        jtHeight.setText(format.format(
                convertPointsToMillimeters(pageSize.height())));
    }//GEN-LAST:event_jcbPaperSizeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static final double MILLIMETERS_PER_POINT = 72d/25.40d;

    public double convertPointsToMillimeters(double points) {
        return points/MILLIMETERS_PER_POINT;
    }
    public double convertMillimetersToPoints(double mm) {
        return mm*MILLIMETERS_PER_POINT;
    }
    public void updatePaperSize() {

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbSaveAsPdf;
    private javax.swing.JComboBox jcbDotsPerInch;
    private javax.swing.JComboBox jcbPaperSize;
    private javax.swing.JProgressBar jpbCurrentTile;
    private javax.swing.JProgressBar jpbOverall;
    private javax.swing.JTextField jtHeight;
    private javax.swing.JTextField jtWidth;
    // End of variables declaration//GEN-END:variables

    public void create(BigDecimal x, BigDecimal y, BigDecimal w, BigDecimal h,BufferedImage bi){
        Graphics g = bi.getGraphics();
        int width = bi.getWidth();
        int height= bi.getHeight();
        BigDecimal xstep = w.divide(new BigDecimal(bi.getWidth()),context);
        BigDecimal ystep = h.divide(new BigDecimal(bi.getHeight()),context);
        jpbCurrentTile.setMaximum(height);
        for(int i=0;i<height;i++) {
            BigDecimal py = y.add(ystep.multiply(new BigDecimal(i),context),context);
            for(int j=0;j<width;j++) {
                BigDecimal px = x.add(xstep.multiply(new BigDecimal(j),context),context);
                int id=producer.getColorAtPoint(px,py,context);
                if( stop ) return;
                Color c = colorModel.getColour(id);
                g.setColor(c);
                g.drawLine(j,i,j,i);
            }
            Thread.currentThread().yield();
            jpbCurrentTile.setValue(i);
        }
        return;
    }
}
