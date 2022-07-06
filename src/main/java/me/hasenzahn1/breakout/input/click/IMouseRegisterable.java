package me.hasenzahn1.breakout.input.click;

public interface IMouseRegisterable {

    void register(IMouseClickable clickable);
    void unregister(IMouseClickable clickable);

}
