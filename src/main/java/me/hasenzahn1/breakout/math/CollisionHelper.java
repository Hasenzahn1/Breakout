package me.hasenzahn1.breakout.math;

public class CollisionHelper {

    public static Vec2d linePointCollision(float xs, float ys, Vec2d dir, int x1, int y1, int x2, float y2){
        double alpha = (xs * dir.getY() - ys * dir.getX() - x1 * dir.getY() + y1 * dir.getX())
                       /
                       (x2 * dir.getY() - x1 * dir.getY() - y2 * dir.getX() + y1 * dir.getX());
        if(alpha < 0 || alpha > 1) return null;
        return new Vec2d(x1, y1).add(new Vec2d(x2 - x1, y2 - y1).multiply(alpha));
    }

    public static void main(String[] args) {
        Vec2d vec2d = linePointCollision(2, 2, new Vec2d(1, 0.2).normalize(), 5, 0, 5, 10);

        System.out.println(vec2d);
    }

}
