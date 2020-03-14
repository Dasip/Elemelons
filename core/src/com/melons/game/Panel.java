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

    public void setRunePanel(ArrayList<String> r){
        int counter = 0;
        for (String i: r) {
            runes.add(new RuneButton(50, 65 + 50 * counter + 200 * counter, i));
            counter += 1;
        }
    }

    public void flip(float originX, float originY){
        Image.setOrigin(originX, originY);
        Image.rotate(270);
        Image.setX(0);
        Image.setY(-540);

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
        if (runes.size() > 0) {
            for (RuneButton i : runes) {
                b.draw(i.getImage(), i.getX(), i.getY());
            }
        }
    }
/*
    public void addButton(float x, float y, GuiButton gb, Screen s){

    }
*/
}
