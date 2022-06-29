package me.hasenzahn1.breakout.input;

public interface IMouseRegisterable {

    void register(IMouseClickable clickable);
    void unregister(IMouseClickable clickable);

}
