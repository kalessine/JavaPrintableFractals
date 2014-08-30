package fractal.producer.calc;

import java.awt.geom.Point2D;
/*
 This ComplexNumber Number Class was shamelessly ripped from
 http://www.jamesh.id.au/fractals/
 */
public interface ComplexNumber {
    public Number getReal();
    public Number getImag();
    public Number getAngle();
    public Number getMagnitude();
    public Number getModSquared();
    public ComplexNumber add(ComplexNumber p);
    public ComplexNumber add(double p);
    public ComplexNumber add(double rl,double im);
    public ComplexNumber add(int p);
    public ComplexNumber subtr(ComplexNumber p);
    public ComplexNumber subtr(double p);
    public ComplexNumber subtr(int p);
    public ComplexNumber mult(double p);
    public ComplexNumber mult(int p);
    public ComplexNumber mult(ComplexNumber p);
    public ComplexNumber divid(ComplexNumber p);
    public ComplexNumber pow(ComplexNumber p);
    public ComplexNumber conjugate();
    public ComplexNumber sqr();
    public ComplexNumber cube();
    public ComplexNumber flip();
    public Point2D getPoint();
    public int getPrecision();
}
