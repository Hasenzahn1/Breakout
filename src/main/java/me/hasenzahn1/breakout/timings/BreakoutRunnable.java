package me.hasenzahn1.breakout.timings;

import me.hasenzahn1.breakout.Breakout;

public abstract class BreakoutRunnable implements java.lang.Runnable {

    public abstract void run();

    private long delay;
    private long startTime;

    public BreakoutRunnable runTaskLater(Breakout main, int delay){
        main.registerRunnable(this);
        this.delay = delay;
        this.startTime = System.currentTimeMillis();
        return this;
    }

    public boolean tick(){
        long currentTime = System.currentTimeMillis();
        if(startTime + delay * 1000 < currentTime){
            run();
            return true;
        }
        return false;
    }

}
