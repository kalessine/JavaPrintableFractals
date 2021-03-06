/*
 * OrbitPanel.java
 *
 * Created on 21 March 2007, 09:23
 */

package fractal.gui.panel;

import fractal.producer.calc.ComplexNumber;
import fractal.producer.colour.ColourMap;
import fractal.producer.colour.Colouring;
import fractal.producer.formula.Formula;
import fractal.producer.orbittrap.OrbitTrap;
import fractal.producer.result.Result;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author  Owner
 */
public class OrbitPanel extends javax.swing.JPanel implements OrbitTrap {
    
    /** Creates new form OrbitPanel */
    public OrbitPanel() {
        initComponents();
    }
    
    public void paint(Graphics g) {
         Iterator it = values.iterator();
         while(it.hasNext()) {
              ComplexNumber z = (ComplexNumber)it.next();
              int iterations = values.indexOf(z);
              
         }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    List values = new ArrayList(0);
    public void init(HashMap map){
        values=new ArrayList(0);
    }
    public void hit(HashMap map,Number iterations){
        values.add(map.get(Formula.Z));
    }
    public Object getTrapValue(){
        return null;
    }
    public Color getColor(Result res,ColourMap cmap,Color oldColor,HashMap map){
        return oldColor;
    }
    public Colouring clone() { return this; }
}
