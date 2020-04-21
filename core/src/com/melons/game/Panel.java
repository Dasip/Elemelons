package com.melons.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

public class Panel extends Actor {

    private Sprite Image;
    private Texture Text;
    private float x;
    private float y;
    private ArrayList<RuneButton> runes;

    Panel(float x, float y, String path){
        this.x = x;
        this.y = y;
        Text = new Texture(path);
        Image = new Sprite(Text);
        runes = new ArrayList<RuneButton>();
        Image.setX(x);
        Image.setY(y);

    }


    public float getHeight() { return Image.getHeight(); }

    public float getWidth() { return Image.getWidth(); }

    public void setX(float x){
        this.x = x;
    }

    public void disposeImage(){
        Text.dispose();
        for (RuneButton i: runes){
            i.dispose();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawAll(batch);
    }

    public void drawAll(Batch b){
        Image.draw(b);

    }


}
