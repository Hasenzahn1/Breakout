package me.hasenzahn1.breakout;

public class Launcher {

    public static void main(String[] args) {
        Breakout breakout = new Breakout("Breakout!", 640, 800);
        breakout.start();
    }

}
