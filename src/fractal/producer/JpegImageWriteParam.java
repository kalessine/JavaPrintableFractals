package fractal.producer;

import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.util.Locale;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class JpegImageWriteParam extends JPEGImageWriteParam {
               public JpegImageWriteParam() {
                   super(Locale.getDefault());
               }

               // This method accepts quality levels between 0 (lowest) and 1 (highest) and simply converts
               // it to a range between 0 and 256; this is not a correct conversion algorithm.
               // However, a proper alternative is a lot more complicated.
               // This should do until the bug is fixed.
               public void setCompressionQuality(float quality) {
                   if (quality < 0.0F || quality > 1.0F) {
                       throw new IllegalArgumentException("Quality out-of-bounds!");
                   }
                   this.compressionQuality = 256 - (quality * 256);
               }
	}
