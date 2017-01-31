package fractal.producer.colour;

/**
 *<author>
 * @author Aamir khan
 * @URL    aamir-4u.blogspot.com
 *</author>
 * <b>RGB to CMYK conversion formula</b>
 *
 * <p>The R,G,B values are divided by 255 to change the range from 0..255 to 0..1:
 *
 * R1 = R/255
 *
 * G1 = G/255
 *
 * B1 = B/255
 *
 * The black key (K) color is calculated from the red (R1,G1,B1)
 *
 * K = 1-max(R1, G1, B1)
 *
 * The cyan color (C) is calculated from the red (R1) and black (K) colors:
 *
 * C = (1-R1-K) / (1-K)
 *
 * The magenta color (M) is calculated from the green (G1) and black (K) colors:
 *
 * M = (1-G1-K) / (1-K)
 *
 * The yellow color (Y) is calculated from the blue (B1) and black (K) colors:
 *
 * Y = (1-B1-K) / (1-K)<p>
 */
public class RGBtoCMYK {
    

    public static void main(String argu[]) {
        double r = 2, g = 25, b = 255;
        double r1 = r / 255, g1 = g / 255, b1 = b / 255;
        double c, m, y, k;
        c = m = y = k = 0;//just initialize

        /*the black key Color (k) is Calculated from r1,g1,b1
         so k = 1- max(r1,g1,b1)
         */
        k = 1 - max(r1, g1, b1);
//The Cyan
        c = (1 - r1 - k) / (1 - k);
//The Magento
        m = (1 - g1 - k) / (1 - k);
//The Yellow
        y = (1 - b1 - k) / (1 - k);
//Format the Output
        java.text.DecimalFormat p = new java.text.DecimalFormat("#.###");
//Printing the Values
        System.out.println("C = "+p.format(c));
        System.out.println("M = "+p.format(m));
        System.out.println("Y = "+p.format(y));
        System.out.println("K = "+p.format(k));
//Check the Values
        System.out.println("Check______________________");
        toRGB(c, m, y, k);

    }//main end

    public static double max(double a, double b, double c) {

        double max = a;
        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }
        return max;

    }//max end

    public static float[] toRGB(double c, double m, double y, double k) {

        /*
         <b>CMYK to RGB conversion formula</b>

         <p>The R,G,B values are given in the range of 0..255.

         The red (R) color is calculated from the cyan (C) and black (K) colors:

         R = 255 × (1-C) × (1-K)

         The green color (G) is calculated from the magenta (M) and black (K) colors:

         G = 255 × (1-M) × (1-K)

         The blue color (B) is calculated from the yellow (Y) and black (K) colors:

         B = 255 × (1-Y) × (1-K)
        </p>
         */
        
        double r = 255 * (1-c) * (1-k);
        double g = 255 * (1-m) * (1-k);
        double b = 255 * (1-y) * (1-k);
        return new float[]{(float)r,(float)g,(float)b};
    }

}