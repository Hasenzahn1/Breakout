package me.hasenzahn1.breakout.settings;

public class Settings {
    private boolean mouseActive, sfxOn, musicOn;

    public Settings(){
        mouseActive = false;
        sfxOn = true;
        musicOn = true;
    }

    public boolean isMouseActive() {
        return mouseActive;
    }

    public void setMouseActive(boolean mouseActive) {
        this.mouseActive = mouseActive;
    }

    public void toggleMouseActive(){
        mouseActive = !mouseActive;
    }


    public boolean isSfxOn() {
        return sfxOn;
    }

    public void setSfxOn(boolean sfxOn) {
        this.sfxOn = sfxOn;
    }

    public void toggleSfxOn(){
        sfxOn = !sfxOn;
    }


    public boolean isMusicOn() {
        return musicOn;
    }

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

    public void toggleMusicOn() {
        mouseActive = !mouseActive;
    }
}
