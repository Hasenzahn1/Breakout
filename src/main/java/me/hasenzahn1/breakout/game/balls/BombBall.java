package me.hasenzahn1.breakout.game.balls;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;
import me.hasenzahn1.breakout.image.ImageLoader;
import me.hasenzahn1.breakout.map.Map;
import me.hasenzahn1.breakout.map.bricks.Brick;
import me.hasenzahn1.breakout.math.Vec2d;

import java.awt.image.BufferedImage;

public class BombBall extends Ball{

    public static BufferedImage IMAGE = ImageLoader.loadImage("game/balls/ball_bomb.png");

    public BombBall(float newX, float newY, IngameState state) {
        super(newX, newY, state);
        direction = new Vec2d(0, -1).normalize();
        image = IMAGE;
    }

    @Override
    public void tick(double deltaTime) {
        if(y > 1) super.tick(deltaTime);
        else if(y < -width) ((IngameState)Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE)).removeBall(this);
        else y += direction.getY() * deltaTime * speed;
    }

    @Override
    public void onCollide(ICollidable object) {
        IngameState state = (IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE);
        state.removeBall(this);
        if(!(object instanceof Brick)) return;
        Brick hitBrick = (Brick) object;
        Map map = state.getMap();
        for(int ox = -1; ox < 2; ox++){
            for(int oy = -1; oy < 2; oy++){
                int bx = ox + hitBrick.getTx();
                int by = oy + hitBrick.getTy();
                if(bx < 0 || by < 0) continue;
                if(bx >= map.getBricks().length || by >= map.getBricks()[0].length) continue;
                if(map.getBricks()[bx][by] == null) continue;
                map.getBricks()[bx][by].onCollide(this);
            }
        }
    }
}
