package me.hasenzahn1.breakout.math;

public class Vec2d{

    private double n1;
    private double n2;

    public Vec2d(double n1, double n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public Vec2d normalize(){
        double length = Math.sqrt((n1*n1)+(n2*n2));
        n1 = n1/length;
        n2 = n2/length;
        return this;
    }
    public Vec2d multiply(double x){
        n1 *= x;
        n2 *= x;
        return this;
    }
    
    public Vec2d add(double a, double b){
        n1 += a;
        n2 += b;
        return this;
    }
    
    public void setN1(double n1) {
        this.n1 = n1;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }
}
