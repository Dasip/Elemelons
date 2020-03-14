package com.melons.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class RuneButton {

    private Texture Image;
    private Rectangle Collision;

    RuneButton(float x, float y, String name){

        Image = new Texture("Runes/Fireball.png");
        Collision = new Rectangle();
        Collision.x = x;
        Collision.y = y;
        Collision.width = Image.getWidth();
        Collision.height = Image.getHeight();
    }

    public Texture getImage(){ return Image; }
    public float getX(){ return Collision.x; }
    public float getY(){ return Collision.y; }
    public void dispose() { Image.dispose();}

}
