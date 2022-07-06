package me.hasenzahn1.breakout.timings;

import me.hasenzahn1.breakout.Breakout;

public abstract class BreakoutRunnable implements java.lang.Runnable {

    public abstract void run();

    private long delay;
    private long startTime;
    private boolean repeat;
    private Breakout main;

    public BreakoutRunnable runTaskLater(Breakout main, int delay){
        main.registerRunnable(this);
        this.main = main;
        this.delay = delay;
        this.startTime = System.currentTimeMillis();
        return this;
    }

    public BreakoutRunnable runTaskTimer(Breakout main, int delay){
        main.registerRunnable(this);
        repeat = true;
        this.main = main;
        this.delay = delay;
        this.startTime = System.currentTimeMillis();
        return this;
    }

    public void cancel(){
        main.unregisterRunnable(this);
    }

    public boolean tick(){
        long currentTime = System.currentTimeMillis();
        if(startTime + delay * 1000 < currentTime){
            run();
            if(repeat) startTime = currentTime;
            return !repeat;
        }
        return false;
    }

}
