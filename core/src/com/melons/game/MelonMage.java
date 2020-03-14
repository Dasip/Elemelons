package com.melons.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class MelonMage {

    private Rectangle Collision;
    private Texture Image;

    MelonMage(float x, float y){
        Image = new Texture("Watermelon.png");

        Collision = new Rectangle();
        Collision.x = x;
        Collision.y = y;
        Collision.height = Image.getHeight();
        Collision.width = Image.getWidth();
    }

    public Texture getImage(){ return Image; }

    public float getX(){
        return Collision.x;
    }

    public float getY(){
        return Collision.y;
    }

    public void disposeImage(){
        Image.dispose();
    }

    public void setCoords(float x, float y){
        Collision.x = x + (Tile.getWidth() - Image.getWidth()) / 2;
        Collision.y = y + (Tile.getHeight() - Image.getHeight()) / 2;
    }


}
