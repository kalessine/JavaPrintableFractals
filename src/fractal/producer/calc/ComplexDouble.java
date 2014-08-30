package fractal.producer.calc;

import java.awt.geom.Point2D;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
/*
 This Complex Number Class was shamelessly ripped from
 http://www.jamesh.id.au/fractals/
 */
public final class ComplexDouble implements ComplexNumber,Externalizable {
    public static void main(String args[]) {
       ComplexNumber zeroone = new ComplexDouble(0.0d,1.0d);
       ComplexNumber oneone = new ComplexDouble(1.0d,1.0d);
       System.out.println("1/1^1/1="+oneone.pow(oneone));
       System.out.println("0/1^0/1="+zeroone.pow(zeroone));
       ComplexNumber two = new ComplexDouble(2.0d,0.0d);
       System.out.println("2/0^2/0="+two.pow(two));
    }
    double r=0d,i=0d;
    public ComplexDouble(){}
    public ComplexDouble(double r, double i) {
        if( Double.isNaN(r) ) throw new RuntimeException("NaN real "+r);
        if( Double.isNaN(i) ) throw new RuntimeException("NaN imag "+i);
        this.r=r;
        this.i=i;
    }
    
    
    public Number getReal(){return r;}
    public Number getImag(){return i;}
    public Number getModSquared(){ return r*r+i*i;}
    
    public double getI() {
        return i;
    }
    public ComplexNumber add(ComplexNumber p){
        return new ComplexDouble(r+p.getReal().doubleValue(),i+p.getImag().doubleValue());
    }
    public ComplexNumber add(double p){
        return new ComplexDouble(r+p,i);
    }
    public ComplexNumber add(double rl,double im){
        return new ComplexDouble(r+rl,i+im);
    }
    public ComplexNumber add(int p){
        return new ComplexDouble(r+p,i);
    }
    public ComplexNumber subtr(ComplexNumber p){
        double r1 = p.getReal().doubleValue();
        double i1 = p.getImag().doubleValue();
        return new ComplexDouble(r-r1,i-i1);
    }
    public ComplexNumber subtr(double p){
        return new ComplexDouble(r-p,i);
    }
    public ComplexNumber subtr(int p){
        return new ComplexDouble(r-p,i);
    }
    public ComplexNumber mult(double p){
        return new ComplexDouble(r*p,i*p);
    }
    public ComplexNumber mult(int p){
        return new ComplexDouble(r*p,i*p);
    }
    public ComplexNumber mult(ComplexNumber p){
        return new ComplexDouble(r * p.getReal().doubleValue() - i * p.getImag().doubleValue(),
                i * p.getReal().doubleValue() + r * p.getImag().doubleValue());
    }
    public ComplexNumber divid(ComplexNumber p){
        double tmp = p.getModSquared().doubleValue();
        return new ComplexDouble((r * p.getReal().doubleValue() + i * p.getImag().doubleValue()) / tmp,
                (i * p.getReal().doubleValue() - r * p.getImag().doubleValue()) / tmp);
    }
    public ComplexNumber sqr(){
        return new ComplexDouble(r * r - i * i,2 * r * i);
    }
    public ComplexNumber cube(){
        double r2 = r*r, i2 = i*i;
        return new ComplexDouble(r * (r2 - 3 * i2),i * (3 * r2 - i2));
    }
    public Number getAngle() {
        return Math.atan2(i,r);
    }
    public Number getMagnitude() {
        double m = Math.sqrt(r*r+i*i);
        if( Double.isNaN(m)) {
            System.out.println("Magnitude Is NaN r="+r+" i="+i);
        }
        return Math.sqrt(r*r+i*i);
    }
    public ComplexNumber flip(){
        return new ComplexDouble(i,r);
    }
    public int getPrecision() { return 16; }
    public String toString() { return "("+r+","+i+")";}
    public void readExternal(ObjectInput in)throws IOException, ClassNotFoundException{
        r = in.readDouble();
        i = in.readDouble();
    }
    public void writeExternal(ObjectOutput out)throws IOException{
        out.writeDouble(r);
        out.writeDouble(i);
    }
    /* From http://home.att.net/~srschmitt/complexnumbers.html
     *    
    var r : real := cabs(x)
    var t : real := carg(x)
    var c : real := y.re
    var d : real := y.im
    
    pow.re := (r^c)*exp(-d*t)*cos(c*t + d*ln(r))
    pow.im := (r^c)*exp(-d*t)*sin(c*t + d*ln(r))
     */
    public ComplexNumber pow(ComplexNumber p){
       double _r = this.getMagnitude().doubleValue();
//       _r=test(_r);
       double t = Math.atan2(i,r);
//       t=test(t);
       double c = p.getReal().doubleValue();
//       c=test(c);
       double d = p.getImag().doubleValue();
//       d=test(d);
       double temp = Math.pow(_r,c);
//       temp=test(temp);
       temp*=Math.exp(-d*t);
//       temp=test(temp);
       double log = _r==0d?Double.MIN_VALUE:Math.log(_r);
       double temp2 = c*t+d*log;
//       temp2=test(temp2);
       double nr = temp*Math.cos(temp2);
//       nr=test(nr);
       double ni = temp*Math.sin(temp2);
//       nr=test(ni);
       return new ComplexDouble(nr,ni);
    }
    // From http://www.clarku.edu/~djoyce/complex/div.html
    public ComplexNumber conjugate(){
        double mag = getMagnitude().doubleValue();
        if( mag == 0d ) return new ComplexDouble(0d,0d);
        double nr = r/mag;
        double ni = -i/mag;
        return new ComplexDouble(nr,ni);
    }
    
    public Point2D getPoint() { return new Point2D.Double(r,i); }
}
