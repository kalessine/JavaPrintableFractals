package fractal.gui.util;
import javax.swing.ListModel;
import javax.swing.*;
public class UpdatableListModel extends AbstractListModel implements ListModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Object getElementAt(int i){
		return null;
		}
	public int getSize(){return 0; }
    public void fireUpdate() {
        super.fireContentsChanged(this,0,0);
    }
}
