package me.hasenzahn1.breakout.game.balls;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.display.IDrawable;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.game.ScoreManager;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.input.click.IMouseClickable;
import me.hasenzahn1.breakout.math.BoundingBox;
import me.hasenzahn1.breakout.math.CombinedValues;
import me.hasenzahn1.breakout.math.Vec2d;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ball implements IDrawable, ICollidable {

    public static BufferedImage DEFAULT = ImageLoader.loadImage("game/balls/ball.png");
    public static float SPEED = 4;

    protected float x, y;
    protected Vec2d direction;
    protected double speed;
    protected int width;

    protected BufferedImage image;
    private ScoreManager scoreManager;

    //Debug
    private ArrayList<Vec2d> hitPositions;


    public Ball(float newX, float newY, IngameState ingameState){
        x = newX;
        y = newY;
        direction = new Vec2d(Math.random()-0.5,-Math.random());
        direction.normalize();
        speed = SPEED;
        image = DEFAULT;
        width = 12;
        hitPositions = new ArrayList<>();
        scoreManager = new ScoreManager(ingameState);
    }

    @Override
    public void tick(double deltaTime) {
        x += direction.getX() * deltaTime * speed;
        y += direction.getY() * deltaTime * speed;

        if(x < 0 || x > Breakout.getInstance().getWidth() - width){
            direction.multiply( -1, 1);
            if(x < 0) x=0;
            else x = Breakout.getInstance().getWidth() - width;
        }
        if(y < 0){
            direction.multiply(1,-1);
            if(y < 0) y=0;
            else y = Breakout.getInstance().getHeight() - width;
        }

        if(y > Breakout.getInstance().getHeight()){
            ((IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE)).removeBall(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, width, null);

        /*g.setColor(Color.RED);
        g.drawOval((int) (x + width / 2), (int) (y + width / 2), 2, 2);
        Vec2d endPoint = new Vec2d(x + width / 2f, y + width / 2f).add(direction.clone().multiply(100));
        g.drawLine((int) (x + width / 2), (int) (y + width / 2), (int) endPoint.getX(), (int) endPoint.getY());
        //System.out.println(image + "   y:" + y + "   x:" + x);

         */
    }

    @Override
    public BoundingBox getCollider() {
        return new BoundingBox((int) x, (int) y, width, width);
    }

    @Override
    public void onCollide(ICollidable object){
        BoundingBox objectBoundingBox = object.getCollider();
        CombinedValues<Integer, Vec2d> collisionSidePoint = objectBoundingBox.getCollideIndex(x + width / 2.0f, y + width / 2.0f, direction);
        //System.out.println(collisionSidePoint);
        if (collisionSidePoint == null) return;

        switch (collisionSidePoint.getT()) {
            case 0 -> direction.setY(-Math.abs(direction.getY()));
            case 1 -> direction.setX(-Math.abs(direction.getX()));
            case 2 -> direction.setX(Math.abs(direction.getX()));
            default -> direction.setY(Math.abs(direction.getY()));
        }

    }

    public double getSpeed() {
        return speed;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Vec2d getDirection() {
        return direction;
    }


    public void setDirection(Vec2d direction) {
        this.direction = direction;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }
}
