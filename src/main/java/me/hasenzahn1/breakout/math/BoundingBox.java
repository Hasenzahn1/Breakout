package me.hasenzahn1.breakout.math;

public class BoundingBox {

    private int x, y, width, height;

    public BoundingBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(BoundingBox boundingBox){
        if (x > boundingBox.x + boundingBox.width || x + width < boundingBox.x) return false;
        if (y > boundingBox.y + boundingBox.height || y + height < boundingBox.y) return false;
        return true;
    }

    public boolean contains(int x, int y){
        if(x < this.x || x > this.x + width) return false;
        if(y < this.y || y > this.y + height) return false;
        return true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxX(){
        return x + width;
    }

    public int getMaxY(){
        return y + height;
    }
}
