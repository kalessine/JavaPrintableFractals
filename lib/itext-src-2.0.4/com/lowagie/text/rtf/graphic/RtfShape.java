package com.lowagie.text.rtf.graphic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;

import com.lowagie.text.rtf.RtfAddableElement;

/**
 * The RtfShape provides the interface for adding shapes to
 * the RTF document. This will only work for Word 97+, older
 * Word versions are not supported by this class.<br /><br />
 * 
 * Only very simple shapes are directly supported by the RtfShape.
 * For more complex shapes you will have to read the RTF
 * specification (iText follows the 1.6 specification) and add
 * the desired properties via the RtfShapeProperty.<br /><br />
 * 
 * One thing to keep in mind is that distances are not expressed
 * in the standard iText point, but in EMU where 1 inch = 914400 EMU
 * or 1 cm = 360000 EMU. 
 * 
 * @version $Id: RtfShape.java 2776 2007-05-23 20:01:40Z hallm $
 * @author Mark Hall (mhall@edu.uni-klu.ac.at)
 * @author Thomas Bickel (tmb99@inode.at)
 */
public class RtfShape extends RtfAddableElement {
    /**
     * Constant for a free form shape. The shape verticies must
     * be specified with an array of Point objects in a
     * RtfShapeProperty with the name PROPERTY_VERTICIES.
     */
	public static final int SHAPE_FREEFORM = 0;
    /**
     * Constant for a rectangle.
     */
	public static final int SHAPE_RECTANGLE = 1;
    /**
     * Constant for a rounded rectangle. The roundness is
     * set via a RtfShapeProperty with the name PROPERTY_ADJUST_VALUE.
     */
	public static final int SHAPE_ROUND_RECTANGLE = 2;
    /**
     * Constant for an ellipse. Use this to create circles.
     */
	public static final int SHAPE_ELLIPSE = 3;
    /**
     * Constant for a diamond.
     */
	public static final int SHAPE_DIAMOND = 4;
    /**
     * Constant for a isoscelle triangle.
     */
	public static final int SHAPE_TRIANGLE_ISOSCELES = 5;
    /**
     * Constant for a right triangle.
     */
	public static final int SHAPE_TRIANGLE_RIGHT = 6;
    /**
     * Constant for a parallelogram.
     */
	public static final int SHAPE_PARALLELOGRAM = 7;
    /**
     * Constant for a trapezoid.
     */
	public static final int SHAPE_TRAPEZOID = 8;
    /**
     * Constant for a hexagon.
     */
	public static final int SHAPE_HEXAGON = 9;
    /**
     * Constant for an ocatagon.
     */
	public static final int SHAPE_OCTAGON = 10;
    /**
     * Constant for a star.
     */
	public static final int SHAPE_STAR = 12;
    /**
     * Constant for an arrow.
     */
	public static final int SHAPE_ARROW = 13;
    /**
     * Constant for a thick arrow.
     */
	public static final int SHAPE_ARROR_THICK = 14;
    /**
     * Constant for a home plate style shape.
     */
	public static final int SHAPE_HOME_PLATE = 15;
    /**
     * Constant for a cube shape.
     */
	public static final int SHAPE_CUBE = 16;
    /**
     * Constant for a balloon shape.
     */
	public static final int SHAPE_BALLOON = 17;
    /**
     * Constant for a seal shape.
     */
	public static final int SHAPE_SEAL = 18;
    /**
     * Constant for an arc shape.
     */
	public static final int SHAPE_ARC = 19;
    /**
     * Constant for a line shape.
     */
	public static final int SHAPE_LINE = 20;
    /**
     * Constant for a can shape.
     */
	public static final int SHAPE_CAN = 22;
    /**
     * Constant for a donut shape.
     */
	public static final int SHAPE_DONUT = 23;
	
    /**
     * Text is not wrapped around the shape.
     */
	public static final int SHAPE_WRAP_NONE = 0;
    /**
     * Text is wrapped to the top and bottom.
     */
	public static final int SHAPE_WRAP_TOP_BOTTOM = 1;
    /**
     * Text is wrapped on the left and right side.
     */
	public static final int SHAPE_WRAP_BOTH = 2;
    /**
     * Text is wrapped on the left side.
     */
	public static final int SHAPE_WRAP_LEFT = 3;
    /**
     * Text is wrapped on the right side.
     */
	public static final int SHAPE_WRAP_RIGHT = 4;
    /**
     * Text is wrapped on the largest side.
     */
	public static final int SHAPE_WRAP_LARGEST = 5;
    /**
     * Text is tightly wrapped on the left and right side.
     */
	public static final int SHAPE_WRAP_TIGHT_BOTH = 6;
    /**
     * Text is tightly wrapped on the left side.
     */
	public static final int SHAPE_WRAP_TIGHT_LEFT = 7;
    /**
     * Text is tightly wrapped on the right side.
     */
	public static final int SHAPE_WRAP_TIGHT_RIGHT = 8;
    /**
     * Text is tightly wrapped on the largest side.
     */
	public static final int SHAPE_WRAP_TIGHT_LARGEST = 9;
    /**
     * Text is wrapped through the shape.
     */
	public static final int SHAPE_WRAP_THROUGH = 10;
	
    /**
     * The shape nr is a random unique id.
     */
	private int shapeNr = 0;
    /**
     * The shape type.
     */
	private int type = 0;
    /**
     * The RtfShapePosition that defines position settings for this RtfShape.
     */
	private RtfShapePosition position = null;
    /**
     * A HashMap with RtfShapePropertys that define further shape properties.
     */
	private HashMap properties = null;
    /**
     * The wrapping mode. Defaults to SHAPE_WRAP_NONE;
     */
	private int wrapping = SHAPE_WRAP_NONE;
    /**
     * Text that is contained in the shape.
     */
	private String shapeText = "";
	
	/**
     * Constructs a new RtfShape of a given shape at the given RtfShapePosition.
     * 
     * @param type The type of shape to create.
     * @param position The RtfShapePosition to create this RtfShape at.
	 */
	public RtfShape(int type, RtfShapePosition position) {
		this.type = type;
		this.position = position;
		this.properties = new HashMap();
	}

    /**
     * Sets a property.
     * 
     * @param property The property to set for this RtfShape.
     */
	public void setProperty(RtfShapeProperty property) {
        this.properties.put(property.getName(), property);
    }
    
    /**
     * Sets the text to display in this RtfShape.
     * 
     * @param shapeText The text to display.
     */
	public void setShapeText(String shapeText) {
		this.shapeText = shapeText;
	}

	/**
     * Set the wrapping mode.
     * 
     * @param wrapping The wrapping mode to use for this RtfShape.
	 */
	public void setWrapping(int wrapping) {
		this.wrapping = wrapping;
	}

    /**
     * Writes the RtfShape. Some settings are automatically translated into
     * or require other properties and these are set first.
     * 
     * @deprecated replaced by {@link #writeContent(OutputStream)}
     */
	public byte[] write() {
		
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try {
        	writeContent(result);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return result.toByteArray();
	}
	
    /**
     * Writes the RtfShape. Some settings are automatically translated into
     * or require other properties and these are set first.
     */    
    public void writeContent(final OutputStream result) throws IOException
    {
		this.shapeNr = this.doc.getRandomInt();
		
		this.properties.put("ShapeType", new RtfShapeProperty("ShapeType", this.type));
		if(this.position.isShapeBelowText()) {
			this.properties.put("fBehindDocument", new RtfShapeProperty("fBehindDocument", true));
		}
		if(this.inTable) {
			this.properties.put("fLayoutInCell", new RtfShapeProperty("fLayoutInCell", true));
		}
		if(this.properties.containsKey("posh")) {
			this.position.setIgnoreXRelative(true);
		}
		if(this.properties.containsKey("posv")) {
			this.position.setIgnoreYRelative(true);
		}
    	
    	result.write(OPEN_GROUP);
    	result.write("\\shp".getBytes());
    	result.write("\\shplid".getBytes());
    	result.write(intToByteArray(this.shapeNr));
    	//.result.write(this.position.write());
    	this.position.writeContent(result);
    	switch(this.wrapping) {
    	case SHAPE_WRAP_NONE:
    		result.write("\\shpwr3".getBytes());
    		break;
    	case SHAPE_WRAP_TOP_BOTTOM:
    		result.write("\\shpwr1".getBytes());
    		break;
    	case SHAPE_WRAP_BOTH:
    		result.write("\\shpwr2".getBytes());
    		result.write("\\shpwrk0".getBytes());
    		break;
    	case SHAPE_WRAP_LEFT:
    		result.write("\\shpwr2".getBytes());
    		result.write("\\shpwrk1".getBytes());
    		break;
    	case SHAPE_WRAP_RIGHT:
    		result.write("\\shpwr2".getBytes());
    		result.write("\\shpwrk2".getBytes());
    		break;
    	case SHAPE_WRAP_LARGEST:
    		result.write("\\shpwr2".getBytes());
    		result.write("\\shpwrk3".getBytes());
    		break;
    	case SHAPE_WRAP_TIGHT_BOTH:
    		result.write("\\shpwr4".getBytes());
    		result.write("\\shpwrk0".getBytes());
    		break;
    	case SHAPE_WRAP_TIGHT_LEFT:
    		result.write("\\shpwr4".getBytes());
    		result.write("\\shpwrk1".getBytes());
    		break;
    	case SHAPE_WRAP_TIGHT_RIGHT:
    		result.write("\\shpwr4".getBytes());
    		result.write("\\shpwrk2".getBytes());
    		break;
    	case SHAPE_WRAP_TIGHT_LARGEST:
    		result.write("\\shpwr4".getBytes());
    		result.write("\\shpwrk3".getBytes());
    		break;
    	case SHAPE_WRAP_THROUGH:
    		result.write("\\shpwr5".getBytes());
    		break;
    	default:
    		result.write("\\shpwr3".getBytes());
    	}
    	if(this.inHeader) {
    		result.write("\\shpfhdr1".getBytes());
    	} 
    	if(this.doc.getDocumentSettings().isOutputDebugLineBreaks()) {
    		result.write('\n');
    	}
    	result.write(OPEN_GROUP);
    	result.write("\\*\\shpinst".getBytes());
    	Iterator it = this.properties.values().iterator();
    	while(it.hasNext()) {
    		RtfShapeProperty rsp = (RtfShapeProperty) it.next();
    		//.result.write(rsp.write());
    		rsp.writeContent(result);
    	}
    	if(!this.shapeText.equals("")) {
    		result.write(OPEN_GROUP);
    		result.write("\\shptxt".getBytes());
    		result.write(DELIMITER);
    		result.write(this.shapeText.getBytes());
    		result.write(CLOSE_GROUP);
    	}
    	result.write(CLOSE_GROUP);
    	if(this.doc.getDocumentSettings().isOutputDebugLineBreaks()) {
    		result.write('\n');
    	}
    	result.write(CLOSE_GROUP);
		
    }        
	
}
