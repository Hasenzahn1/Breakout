package me.hasenzahn1.breakout.math;

public class Vec2d{

    private double x;
    private double y;

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vec2d normalize(){
        double length = getDistance();
        x = x /length;
        y = y /length;
        return this;
    }

    public double getDistance(){
        return Math.sqrt((x * x)+(y * y));
    }
    public Vec2d multiply(double mult){
        this.x *= mult;
        y *= mult;
        return this;
    }

    @Override
    public Vec2d clone(){
        return new Vec2d(x, y);
    }

    public Vec2d multiply(double a, double b){
        x *= a;
        y *= b;
        return this;
    }

    public Vec2d add(double a, double b){
        x += a;
        y += b;
        return this;
    }

    public Vec2d add(Vec2d vec){
        x += vec.getX();
        y += vec.getY();
        return this;
    }

    public Vec2d subtract(Vec2d vec){
        x -= vec.getX();
        y -= vec.getY();
        return this;
    }

    public Vec2d subtract(double a, double b){
        x -= a;
        y -= b;
        return this;
    }
    
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vec2d{" +
                "n1=" + x +
                ", n2=" + y +
                '}';
    }
}
