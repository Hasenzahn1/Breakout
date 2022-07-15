package me.hasenzahn1.breakout.math;

import me.hasenzahn1.breakout.game.balls.Ball;

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

    public Vec2d[] getCollidePoints(float px, float py, Vec2d direction){
        Vec2d[] vectors = new Vec2d[4];
        vectors[0] = CollisionHelper.linePointCollision(px, py, direction, x, y, x + width, y);
        vectors[1] = CollisionHelper.linePointCollision(px, py, direction, x, y, x, y + height);
        vectors[2] = CollisionHelper.linePointCollision(px, py, direction, x + width, y, x + width, y + height);
        vectors[3] = CollisionHelper.linePointCollision(px, py, direction, x, y + height, x + width, y + height);
        return vectors;
    }

    public boolean lineCollision(Ball ball){
        Vec2d[] vec2ds = getCollidePoints(ball.getCollider().x + ball.getCollider().width / 2, ball.getCollider().y + ball.getCollider().height / 2, ball.getDirection());
        for(Vec2d vec2d : vec2ds) {
            if (vec2d != null) return true;
        }
        return false;
    }

    public CombinedValues<Integer, Vec2d> getCollideIndex(float px, float py, Vec2d direction){
        Vec2d[] vec2ds = getCollidePoints(px, py, direction);
        //System.out.println(Arrays.toString(vec2ds));
        int index = -1;
        double minDist = 1000000000;
        for(int i = 0; i < vec2ds.length; i++){
            if(vec2ds[i] == null) continue;
            if(vec2ds[i].clone().subtract(new Vec2d(px, py)).getDistance() < minDist){
                index = i;
                minDist = vec2ds[i].clone().subtract(new Vec2d(px, py)).getDistance();
            }
        }
        if(index >= 0) return new CombinedValues<>(index, vec2ds[index]);
        return null;
    }

    public int getMiddleX(){
        return x + width / 2;
    }

    public int getMiddleY(){
        return y + height / 2;
    }

}
