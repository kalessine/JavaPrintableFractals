/*
 * NumberAffineTransform.java
 *
 * Created on 11 February 2007, 15:02
 *
 * This class is derived from the Gnu Classpath 0.93 AffineTransform Class
 *
 */

package fractal.producer.transform;
import fractal.producer.calc.BigMath;
import fractal.producer.calc.ComplexNumber;
import fractal.producer.calc.ComplexNumberFactory;
import fractal.producer.calc.M;
import fractal.producer.calc.NumberMath;
import java.math.MathContext;
/**
 *
 * @author Owner
 */
public class NumberAffineTransform {
    private Number m00;
    private Number m10;
    private Number m01;
    private Number m11;
    private Number m02;
    private Number m12;
    private MathContext context = MathContext.DECIMAL64;
    
    public NumberAffineTransform(MathContext context) {
        if( context.getPrecision() <= BigMath.DOUBLE_DECIMAL_PLACES) {
            m00 = m11 = 1;
        } else m00 = m11 = BigMath.ONE;
        this.context=context;
    }
    
    public Number getScaleX() {
        return m00;
    }
    public Number getScaleY() {
        return m11;
    }
    public Number getShearX() {
        return m01;
    }
    public Number getShearY() {
        return m10;
    }
    public Number getTranslateX() {
        return m02;
    }
    public Number getTranslateY() {
        return m12;
    }
    public void translate(Number tx, Number ty) {
        m02=M.add(m02,M.add(M.multiply(tx,m00,context),M.multiply(ty,m01,context),context),context);
        m12=M.add(m12,M.add(M.multiply(tx,m10,context),M.multiply(ty,m11,context),context),context);
    }
    public void rotate(Number theta) {
        Number c = M.cos(theta,context);
        Number s = M.sin(theta,context);
        Number n00 = M.add(M.multiply(m00,c,context),M.multiply(m01,s,context),context);
        Number n01 = M.add(M.multiply(m00,M.subtract(BigMath.ZERO,s,context),context),M.multiply(m01,c,context),context);
        Number n10 = M.add(M.multiply(m10,c,context),M.multiply(m11,s,context),context);
        Number n11 = M.add(M.multiply(m10,M.subtract(BigMath.ZERO,s,context),context),M.multiply(m11,c,context),context);
        m00 = n00;
        m01 = n01;
        m10 = n10;
        m11 = n11;
    }
    public void rotate(double theta, double x, double y) {
        translate(x, y);
        rotate(theta);
        translate(-x, -y);
    }
    public void scale(double sx, double sy) {
        m00 = M.multiply(m00,sx,context);
        m01 = M.multiply(m01,sy,context);
        m10 = M.multiply(m10,sx,context);
        m11 = M.multiply(m11,sy,context);
    }
    public void shear(double shx, double shy) {
        Number n00 = M.add(m00,M.multiply(shy,m01,context),context);
        Number n01 = M.add(m01,M.multiply(shx,m00,context),context);
        Number n10 = M.add(m10,M.multiply(shy,m11,context),context);
        Number n11 = M.add(m11,M.multiply(shx,m10,context),context);
        m00 = n00;
        m01 = n01;
        m10 = n10;
        m11 = n11;
    }
    public void setToIdentity() {
        if( context.getPrecision() < BigMath.DOUBLE_DECIMAL_PLACES ) {
            m00 = m11 = 1d;
            m01 = m02 = m10 = m12 = 0d;
        } else{
            m00 = m11 = BigMath.ONE;
            m01 = m02 = m10 = m12 = BigMath.ZERO;
        }
    }
    public void setToTranslation(Number tx, Number ty) {
        if( context.getPrecision() < BigMath.DOUBLE_DECIMAL_PLACES ) {
            m00 = m11 = 1d;
            m01 = m10 = 0d;
        } else{
            m00 = m11 = BigMath.ONE;
            m01 = m10 = BigMath.ZERO;
        }
        m02 = tx;
        m12 = ty;
    }
    public void setToRotation(Number theta) {
        Number c = M.cos(theta,context);
        Number s = M.sin(theta,context);
        m00 = c;
        if( context.getPrecision() < BigMath.DOUBLE_DECIMAL_PLACES ) {
            m01 = M.subtract(BigMath.ZERO,s,context);
            m02 = BigMath.ZERO;
            m10 = s;
            m11 = c;
            m12 = BigMath.ZERO;
        } else {
            m01 = -(s.doubleValue());
            m02 = 0d;
            m10 = s;
            m11 = c;
            m12 = 0d;
        }
    }
    public void setToRotation(Number theta, Number x, Number y) {
        setToIdentity();
        translate(x,y);
        rotate(theta);
        translate(M.subtract(BigMath.ZERO,x,context),M.subtract(BigMath.ZERO,y,context));
    }
    
    public void setToScale(double sx, double sy) {
        setToIdentity();
        scale(sx,sy);
    }
    public void setToShear(Number shx, Number shy) {
        if( context.getPrecision() <= BigMath.DOUBLE_DECIMAL_PLACES ) {
            m00 = m11 = 1d;
            m01 = shx;
            m10 = shy;
            m02 = m12 = 0d;
        }
    }
    
    public ComplexNumber transform(ComplexNumber src,ComplexNumberFactory factory) {
        Number x = src.getReal();
        Number y = src.getImag();
        Number nx =
                NumberMath.add(
                NumberMath.add(NumberMath.multiply(m00,x,context),NumberMath.multiply(m01,y,context),context),m02,context);
        Number ny = NumberMath.add(NumberMath.add(NumberMath.multiply(m10,x,context),NumberMath.multiply(m11,y,context),context),m12,context);
        return factory.createComplexNumber(nx,ny);
    }
    public String toString() {
        return "AffineTransform[[" + m00 + ", " + m01 + ", " + m02 + "], ["
                + m10 + ", " + m11 + ", " + m12 + "]]";
    }
    
    
}
