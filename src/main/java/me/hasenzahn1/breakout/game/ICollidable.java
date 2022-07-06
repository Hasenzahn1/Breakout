package me.hasenzahn1.breakout.game;

import me.hasenzahn1.breakout.math.BoundingBox;

public interface ICollidable {

    BoundingBox getCollider();
    void onCollide(ICollidable ball);

}
