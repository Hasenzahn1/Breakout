package me.hasenzahn1.breakout.input.motion;

public interface IMouseMotionRegisterable {

    void register(IMouseMovable movable);
    void unregister(IMouseMovable movable);

}
