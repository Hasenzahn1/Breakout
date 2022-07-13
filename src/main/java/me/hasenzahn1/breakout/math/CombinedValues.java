package me.hasenzahn1.breakout.math;

public class CombinedValues<T, U>{

    private T t;
    private U u;

    public CombinedValues(T t, U u){
        this.t = t;
        this.u = u;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public U getU() {
        return u;
    }

    public void setU(U u) {
        this.u = u;
    }

    @Override
    public String toString() {
        return "CombinedValues{" +
                "t=" + t +
                ", u=" + u +
                '}';
    }
}
