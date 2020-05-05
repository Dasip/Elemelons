package com.melons.game.interfaces;

public interface SpellEffect {
    public void move();
    public void onCollide();
    public void setXVector(int v);
    public void setYVector(int y);
    public void setTarget(float x, float y);
    public boolean checkAchieved();
}
