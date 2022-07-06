package me.hasenzahn1.breakout.input.keyboard;

public interface IKeyRegisterable {

    void register(IKeyClickable clickable);
    void unregister(IKeyClickable clickable);

}
