/*
 * ColorRangeJDialog.java
 *
 * Created on December 10, 2006, 10:35 PM
 */

package fractal.gui.frames;

//import fractal.gui.old.FractalViewJPanel;
import fractal.producer.Fractal;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import fractal.producer.colour.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.swing.event.MouseInputAdapter;
/**
 *
 * @author  james
 */
import java.text.DecimalFormat;
import java.util.Iterator;
import javax.swing.JFileChooser;
public class ColorRangeJDialog extends javax.swing.JDialog {
    public final DecimalFormat format = new DecimalFormat("00.00");
    
    ItemListener itemListener = new ItemListener() {
        public void itemStateChanged(ItemEvent ie) {
            if( selectedColourItem==null) return;
            selectedColourItem.setBlend(jComboBoxBlending.getSelectedIndex());
            fractal.getColourMap().buildColourMap();
            colorPanel.repaint();
            fractal.fireColourChanged(this);
        }
    };
    
    Fractal fractal;
    /** Creates new form ColorRangeJDialog */
    public ColorRangeJDialog(JFrame parent,Fractal fractal) {
        super(parent,false);
        this.fractal=fractal;
        initComponents();
        jScrollPane1.getViewport().add(colorPanel);
        MouseInputAdapter adapter = new MouseInputAdapter(){
            public void mouseClicked(MouseEvent e) {colourPanelClicked(e);}
            public void mousePressed(MouseEvent e) { colourPanelPressed(e); }
            public void mouseReleased(MouseEvent e) {colourPanelReleased(e);}
            public void mouseEntered(MouseEvent e) { colourPanelEntered(e);}
            public void mouseExited(MouseEvent e) { colourPanelExited(e);}
            public void mouseDragged(MouseEvent e) {colourPanelDragged(e);}
            public void mouseMoved(MouseEvent e) {colourPanelMoved(e);}
        };
        colorPanel.addMouseListener(adapter);
        colorPanel.addMouseMotionListener(adapter);
        double offset = fractal.getColourMap().getOffset();
        jSliderOffset.setValue((int)(jSliderOffset.getMaximum()*offset/fractal.getColourMap().getTotalSize()));
        double repeat = fractal.getColourMap().getRepeatColourMap();
        jSliderRepeatColourMap.setValue((int)repeat*10);
        jSliderMapSize.setMinimum(100);
        jSliderMapSize.setMaximum(1500);
        jSliderMapSize.setValue(fractal.getColourMap().getMapSize());
        jlOffset.setText(format.format(fractal.getColourMap().getOffset()));
        jlRepeat.setText(format.format(fractal.getColourMap().getRepeatColourMap()));
        jCheckBoxStriped.setEnabled(false);
        jbStriped.setEnabled(false);
        jComboBoxBlending.setEnabled(false);
    }
    ColourItem selectedColourItem = null;
    ColourItem leftOfSelected = null;
    int xPointClicked = 0;
    double startPlace = 0d;
    private JPanel colorPanel = new JPanel() {
        public void paint(Graphics g) {
            ColourMap list = fractal.getColourMap();
            g.setColor(getBackground());
            g.fillRect(0,0,getWidth(),getHeight());
            for(int i=0;i<list.getMapSize();i++){
                Color c = list.getColour(i);
                g.setColor(c);
                g.fillRect(i,0,1,getHeight());
            }
            //list.dumpMap();
            for(int i=0; i<list.getColourItems().size();i++){
                ColourItem ci = list.getColourItem(i);
                int msize = list.getMapSize();
                double place = ((ci.getColourCenter())/list.getTotalSize())/list.getRepeatColourMap();
                int x = (int)(place*list.getMapSize());
                int y = colorPanel.getHeight()/2;
                if( ci!=selectedColourItem)paintColourIndicator(g,x,y,ci);
            }
            if( selectedColourItem!=null ) {
                ColourItem ci = selectedColourItem;
                int msize = list.getMapSize();
                double place = ((ci.getColourCenter())/list.getTotalSize())/list.getRepeatColourMap();
                int x = (int)(place*list.getMapSize());
                int y = colorPanel.getHeight()/2;
                paintSelectedColourIndicator(g,x,y);
            }
            if( getWidth()!=list.getMapSize() ){
                setPreferredSize(new Dimension(list.getMapSize(),53));
                //Let the scroll pane know to update itself
                //and its scroll bars.
                revalidate();
            }
            
        }
    };
    private void paintColourIndicator(Graphics g, int x, int y,ColourItem ci) {
        g.setColor(Color.BLACK);
        g.drawRect(x-1,y-1,3,3);
        g.drawLine(x,y-3,x,y+3);
        g.setColor(ColorUtil.inverse(ci.getColor()));
        g.fillRect(x,y,1,1);
    }
    private void paintSelectedColourIndicator(Graphics g, int x, int y) {
        ColourItem ci = selectedColourItem;
        g.setColor(Color.WHITE);
        g.drawRect(x-1,y-1,3,3);
        g.drawLine(x,y-3,x,y+3);
    }
    private void colourPanelClicked(MouseEvent evt){
        colourPanelPressed(evt);
/*    ColourMap list = fpanel.getColourMap();
    double place = (evt.getX()/list.getMapSize())*list.getTotalSize();
    for(int i=0; i<list.getColourItems().size();i++){
        ColourItem ci = list.getColourItem(i);
        int msize = list.getMapSize();
        double ciplace = ((ci.getColourCenter())/list.getTotalSize())/list.getRepeatColourMap();
        int x = (int)(ciplace*list.getMapSize());
        if( evt.getX()==x ) {
            selectedColourItem = ci;
            jbCurrentColour.setBackground(ci.getColor());
        }
    }
    colorPanel.repaint();
 */
    }
    private void colourPanelPressed(MouseEvent evt){
        //System.out.println("ColourPanelPressed");
        ColourMap list = fractal.getColourMap();
        double place = (evt.getX()/list.getMapSize())*list.getTotalSize();
        ColourItem left = list.getColourItem(list.getColourItems().size()-1);
        for(int i=0; i<list.getColourItems().size();i++){
            ColourItem ci = list.getColourItem(i);
            int msize = list.getMapSize();
            double ciplace = ((ci.getColourCenter())/list.getTotalSize())/list.getRepeatColourMap();
            int x = (int)(ciplace*list.getMapSize());
            if( evt.getX()==x ||  evt.getX()==x-1|| evt.getX()==x+1) {
                leftOfSelected=left;
                startPlace = place;
                xPointClicked = evt.getX();
                setSelectedColourItem(ci);
            }
            left=ci;
        }
        colorPanel.repaint();
    }
    private void colourPanelReleased(MouseEvent evt){
        leftOfSelected=null;
        fractal.fireColourChanged(this);
    }
    private void colourPanelEntered(MouseEvent evt){
    }
    private void colourPanelExited(MouseEvent evt){
    }
    private void colourPanelDragged(MouseEvent evt){
        if( selectedColourItem == null ) return;
        ColourMap list = fractal.getColourMap();
        double amt = (((double)evt.getX()-(double)xPointClicked)/(double)list.getMapSize())*list.getTotalSize();
        xPointClicked = evt.getX();
        if( leftOfSelected!=null) {
            double newLength = leftOfSelected.getLength()+amt;
            if( newLength < 0.2 ) newLength=0.2d;
            if( newLength > 10d ) newLength = 10d;
            leftOfSelected.setLength(newLength);
            newLength = selectedColourItem.getLength()-amt;
            if( newLength < 0.2 ) newLength=0.2d;
            if( newLength > 10d ) newLength = 10d;
            selectedColourItem.setLength(newLength);
            list.recalculateLength();
            list.buildColourMap();
        }
        colorPanel.repaint();
    }
    private void colourPanelMoved(MouseEvent evt){
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel3 = new javax.swing.JPanel();
        jbStriped = new javax.swing.JButton();
        jCheckBoxStriped = new javax.swing.JCheckBox();
        jbCurrentColour = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSliderOffset = new javax.swing.JSlider();
        jSliderRepeatColourMap = new javax.swing.JSlider();
        jlOffset = new javax.swing.JLabel();
        jlRepeat = new javax.swing.JLabel();
        jComboBoxBlending = new javax.swing.JComboBox();
        jSliderMapSize = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jlMapSize = new javax.swing.JLabel();
        jbOK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        colourPanelContainer = new javax.swing.JPanel();
        jbAddColour = new javax.swing.JButton();
        jbDeleteColour = new javax.swing.JButton();
        jbColourReset = new javax.swing.JButton();
        jbLoadColours = new javax.swing.JButton();
        jbSaveColours = new javax.swing.JButton();
        jbSaveCmap = new javax.swing.JButton();

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbStriped.setText(new String("StripeColour"));
        jbStriped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbStripedActionPerformed(evt);
            }
        });

        jCheckBoxStriped.setText(new String("Striped"));
        jCheckBoxStriped.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxStriped.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBoxStriped.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxStripedStateChanged(evt);
            }
        });

        jbCurrentColour.setText(new String("Colour"));
        jbCurrentColour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeCurrentColour(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14));
        jLabel1.setText(new String("Offset"));

        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14));
        jLabel3.setText(new String("Repeat"));

        jSliderOffset.setValue(new Integer(0));
        jSliderOffset.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderOffsetStateChanged(evt);
            }
        });

        jSliderRepeatColourMap.setMaximum(new Integer(320));
        jSliderRepeatColourMap.setMinimum(new Integer(10));
        jSliderRepeatColourMap.setValue(new Integer(10));
        jSliderRepeatColourMap.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderRepeatColourMapStateChanged(evt);
            }
        });

        jlOffset.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14));
        jlOffset.setText(new String("Offset"));

        jlRepeat.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14));
        jlRepeat.setText(new String("Repeat"));

        jComboBoxBlending.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No Blend", "Left Blend", "Right Blend", "Double Blend", "0.25", "0.50", "0.75", "CrissCross" }));

        jSliderMapSize.setMaximum(new Integer(1500));
        jSliderMapSize.setMinimum(new Integer(100));
        jSliderMapSize.setMaximumSize(null);
        jSliderMapSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mapSizeStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14));
        jLabel2.setText(new String("Map Size"));

        jlMapSize.setText(new String("Map Size"));

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(jbCurrentColour))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jSliderMapSize, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jSliderOffset, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jSliderRepeatColourMap, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jlMapSize)
                            .add(jlOffset)
                            .add(jlRepeat)))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jCheckBoxStriped)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jbStriped)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBoxBlending, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel1)
                    .add(jSliderOffset, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlOffset))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jSliderRepeatColourMap, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jlRepeat))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel2)
                    .add(jSliderMapSize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlMapSize))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jbCurrentColour)
                    .add(jCheckBoxStriped)
                    .add(jbStriped)
                    .add(jComboBoxBlending, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jbOK.setText(new String("OK"));
        jbOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOKActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(new String("Colour Scheme")));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        colourPanelContainer.setMinimumSize(new java.awt.Dimension(2000, 0));
        org.jdesktop.layout.GroupLayout colourPanelContainerLayout = new org.jdesktop.layout.GroupLayout(colourPanelContainer);
        colourPanelContainer.setLayout(colourPanelContainerLayout);
        colourPanelContainerLayout.setHorizontalGroup(
            colourPanelContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1223, Short.MAX_VALUE)
        );
        colourPanelContainerLayout.setVerticalGroup(
            colourPanelContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 65, Short.MAX_VALUE)
        );
        jScrollPane1.setViewportView(colourPanelContainer);

        jbAddColour.setText(new String("Add"));
        jbAddColour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddColourActionPerformed(evt);
            }
        });

        jbDeleteColour.setText(new String("Delete"));
        jbDeleteColour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDeleteColourActionPerformed(evt);
            }
        });

        jbColourReset.setText(new String("Reset"));
        jbColourReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbColourResetActionPerformed(evt);
            }
        });

        jbLoadColours.setText(new String("Load Colours"));
        jbLoadColours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLoadColoursActionPerformed(evt);
            }
        });

        jbSaveColours.setText(new String("Save Colours"));
        jbSaveColours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSaveColoursActionPerformed(evt);
            }
        });

        jbSaveCmap.setText(new String("Save .MAP"));
        jbSaveCmap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSaveCmapActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(114, 114, 114)
                        .add(jbOK)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jbLoadColours)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jbSaveColours)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jbSaveCmap))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 379, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 18, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jbAddColour, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jbColourReset, 0, 0, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jbDeleteColour, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(8, 8, 8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(22, 22, 22)
                        .add(jbAddColour)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jbDeleteColour)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jbColourReset)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jbOK)
                    .add(jbLoadColours)
                    .add(jbSaveColours)
                    .add(jbSaveCmap))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jbSaveCmapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveCmapActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        if( file == null ) return;
        FileOutputStream fos =null;
        PrintWriter pw =  null;
        try{
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            ColourMap map = fractal.getColourMap();
            map.buildColourMap();
            for(int i=0;i<map.getMapSize();i++) {
                Color c = map.getColour(i);
                pw.println(c.getRed()+" "+c.getGreen()+" "+c.getBlue());
            }
        }catch(IOException io) {
            io.printStackTrace();
        }finally{
            try{
                if(pw!=null)pw.close();
                if(fos!=null)fos.close();
            }catch(IOException io) {io.printStackTrace();}
        }
    }//GEN-LAST:event_jbSaveCmapActionPerformed
    
    private void jbSaveColoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveColoursActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showSaveDialog(this);
        File file = chooser.getSelectedFile();
        if( file == null ) return;
        FileOutputStream fos =null;
        ObjectOutputStream oos =null;
        try{
            fos= new FileOutputStream(file);
            oos= new ObjectOutputStream(fos);
            oos.writeObject(fractal.getColourMap());
        }catch(IOException io) {
            io.printStackTrace();
            
        }finally{
            try{
                if(oos!=null)oos.close();
                if(fos!=null)fos.close();
            }catch(IOException io) {io.printStackTrace();}
        }
    }//GEN-LAST:event_jbSaveColoursActionPerformed
    
    private void jbLoadColoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLoadColoursActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        if( file == null ) return;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ColourMap map = fractal.getColourMap();
        try{
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            map = (ColourMap)ois.readObject();
        }catch(IOException io) {
            io.printStackTrace();
        }catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }finally{
            try{
            if(ois!=null)ois.close();
            if(fis!=null)fis.close();
            }catch(IOException io) {io.printStackTrace();}
        }
        map.buildColourMap();
        fractal.setColourMap(map);
        setSelectedColourItem(null);
        colorPanel.repaint();
    }//GEN-LAST:event_jbLoadColoursActionPerformed
    
    private void jbColourResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbColourResetActionPerformed
        Iterator it = fractal.getColourMap().getColourItems().iterator();
        while(it.hasNext()) {
            ColourItem ci = (ColourItem)it.next();
            ci.setLength(1.0d);
        }
        fractal.getColourMap().recalculateLength();
        fractal.getColourMap().buildColourMap();
        colorPanel.repaint();
        fractal.fireColourChanged(this);
    }//GEN-LAST:event_jbColourResetActionPerformed
    
    private void jbDeleteColourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDeleteColourActionPerformed
        if(this.selectedColourItem!=null){
            fractal.getColourMap().removeColourItem(this.selectedColourItem);
            this.selectedColourItem=null;
        }
        fractal.getColourMap().buildColourMap();
        colorPanel.repaint();
        this.fractal.fireColourChanged(this);
    }//GEN-LAST:event_jbDeleteColourActionPerformed
    
    private void jbAddColourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddColourActionPerformed
        fractal.getColourMap().addColourItem(new ColourItem());
        fractal.getColourMap().buildColourMap();
        colorPanel.repaint();
        fractal.fireColourChanged(this);
    }//GEN-LAST:event_jbAddColourActionPerformed
    
    private void mapSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mapSizeStateChanged
        int val = jSliderMapSize.getValue();
        fractal.getColourMap().setMapSize(val);
        jlMapSize.setText(format.format(val));
        fractal.getColourMap().buildColourMap();
        colorPanel.repaint();
        fractal.fireColourChanged(this);
    }//GEN-LAST:event_mapSizeStateChanged
    boolean checkBoxFlag = false;
    private void jCheckBoxStripedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxStripedStateChanged
        if( selectedColourItem==null||checkBoxFlag) return;
        checkBoxFlag=true;
        if( jCheckBoxStriped.isSelected() ){
            jbStriped.setEnabled(true);
            jComboBoxBlending.setEnabled(true);
            selectedColourItem.setStripeColor(Color.BLACK);
            colorPanel.repaint();
            fractal.getColourMap().buildColourMap();
            fractal.fireColourChanged(this);
        } else {
            jbStriped.setEnabled(false);
            jComboBoxBlending.setEnabled(false);
            selectedColourItem.setStripeColor(null);
            colorPanel.repaint();
            fractal.getColourMap().buildColourMap();
            fractal.fireColourChanged(this);
        }
        checkBoxFlag=false;
    }//GEN-LAST:event_jCheckBoxStripedStateChanged
    
    private void changeCurrentColour(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeCurrentColour
        if( selectedColourItem == null ) return;
        Color colour = JColorChooser.showDialog(this,"Select Different Colour",selectedColourItem.getColor());
        selectedColourItem.setColor(colour);
        jbCurrentColour.setBackground(colour);
        repaint();
        fractal.fireColourChanged(this);
    }//GEN-LAST:event_changeCurrentColour
    
    private void jSliderRepeatColourMapStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderRepeatColourMapStateChanged
        int val = jSliderRepeatColourMap.getValue();
        double repeat = (double)val/10d;
        fractal.getColourMap().setRepeatColourMap(repeat);
        jlRepeat.setText(format.format(repeat));
        colorPanel.repaint();
        fractal.fireColourChanged(this);
    }//GEN-LAST:event_jSliderRepeatColourMapStateChanged
    
    private void jSliderOffsetStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderOffsetStateChanged
        int val = jSliderOffset.getValue();
        double offset = (double)val/(double)jSliderOffset.getMaximum();
        offset = offset*fractal.getColourMap().getTotalSize();
        fractal.getColourMap().setOffset(offset);
        jlOffset.setText(format.format(offset));
        colorPanel.repaint();
        fractal.fireColourChanged(this);
    }//GEN-LAST:event_jSliderOffsetStateChanged
    
    private void jbStripedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbStripedActionPerformed
        if( selectedColourItem == null ) return;
        Color colour = JColorChooser.showDialog(this,"Select Stripe Colour",selectedColourItem.getStripeColor());
        jbStriped.setBackground(colour);
        selectedColourItem.setStripeColor(colour);
        fractal.getColourMap().buildColourMap();
        colorPanel.repaint();
        fractal.fireColourChanged(this);
    }//GEN-LAST:event_jbStripedActionPerformed
    
    public JPanel getColorPanel() {
        return colorPanel;
    }
    public void setSelectedColourItem(ColourItem ci) {
        selectedColourItem = ci;
        checkBoxFlag=true;
        jCheckBoxStriped.setEnabled(true);
        checkBoxFlag=false;
        if( ci!=null)
        jbCurrentColour.setBackground(ci.getColor());
        checkBoxFlag=true;
        if( ci!=null)
        jCheckBoxStriped.setSelected(ci.isStriped());
        checkBoxFlag=false;
        if( ci!=null)
        if( ci.isStriped() ){
            jbStriped.setEnabled(true);
            jComboBoxBlending.setEnabled(true);
            jbStriped.setBackground(ci.getStripeColor());
        } else {
            jbStriped.setBackground(getBackground());
            jComboBoxBlending.setEnabled(false);
            jbStriped.setEnabled(false);
        }
        jComboBoxBlending.removeItemListener(itemListener);
        if( ci!=null)
        jComboBoxBlending.setSelectedIndex(ci.getBlend());
        jComboBoxBlending.addItemListener(itemListener);
    }
    private void jbOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOKActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jbOKActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel colourPanelContainer;
    private javax.swing.JCheckBox jCheckBoxStriped;
    private javax.swing.JComboBox jComboBoxBlending;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSliderMapSize;
    private javax.swing.JSlider jSliderOffset;
    private javax.swing.JSlider jSliderRepeatColourMap;
    private javax.swing.JButton jbAddColour;
    private javax.swing.JButton jbColourReset;
    private javax.swing.JButton jbCurrentColour;
    private javax.swing.JButton jbDeleteColour;
    private javax.swing.JButton jbLoadColours;
    private javax.swing.JButton jbOK;
    private javax.swing.JButton jbSaveCmap;
    private javax.swing.JButton jbSaveColours;
    private javax.swing.JButton jbStriped;
    private javax.swing.JLabel jlMapSize;
    private javax.swing.JLabel jlOffset;
    private javax.swing.JLabel jlRepeat;
    // End of variables declaration//GEN-END:variables
    
}
