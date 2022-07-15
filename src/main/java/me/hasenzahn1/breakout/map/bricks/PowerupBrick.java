package me.hasenzahn1.breakout.map.bricks;

import me.hasenzahn1.breakout.Breakout;
import me.hasenzahn1.breakout.game.ICollidable;
import me.hasenzahn1.breakout.game.orbs.*;
import me.hasenzahn1.breakout.gamestate.GameState;
import me.hasenzahn1.breakout.gamestate.IngameState;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PowerupBrick extends Brick{

    public static List<Class<? extends Orb>> ORBS = Arrays.asList(TimesThreeOrb.class, PlusThreeOrb.class, SlowOrb.class, SpeedOrb.class, NoCollisionOrb.class);
    public PowerupBrick(int nHealth, int imageData) {
        super(nHealth, imageData);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        //g.setColor(Color.RED);
        //g.fillRect(x, y, getCollider().getWidth(), getCollider().getHeight());
    }

    @Override
    public void tick(double deltaTime) {
        super.tick(deltaTime);
    }

    @Override
    public void onCollide(ICollidable ball) {
        super.onCollide(ball);
        if(isBroken()){
            spawnOrb(x + getCollider().getWidth() / 2, y + getCollider().getHeight() / 2);
        }
    }

    private void spawnOrb(int x, int y) {
        Random random = new Random();
        Class<? extends Orb> spawnerOrb = ORBS.get(random.nextInt(ORBS.size()));
        try {
            Orb orb = (Orb) spawnerOrb.getConstructors()[0].newInstance(x, y);
            ((IngameState) Breakout.getInstance().getGameStateManager().getGameState(GameState.INGAME_STATE)).addOrb(orb);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
