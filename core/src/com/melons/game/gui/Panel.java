package com.melons.game.gui;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.melons.game.RuneButton;

import java.util.ArrayList;

public class Panel extends Actor {

    protected Sprite Image;
    protected Texture Text;
    protected float x;
    protected float y;


    public Panel(float x, float y, String path){
        this.x = x;
        this.y = y;
        if (path != "null") {
            Text = new Texture(path);
            Image = new Sprite(Text);
            Image.setX(x);
            Image.setY(y);
        }

    }


    public float getHeight() { return Image.getHeight(); }

    public float getWidth() { return Image.getWidth(); }

    public void setX(float x){
        this.x = x;
    }

    public void disposeImage(){
        Text.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawAll(batch);
    }

    public void drawAll(Batch b){
        Image.draw(b);

    }


}
