package com.melons.game;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
    private int row;
    private int col;
    private float startX;
    private float startY;
    private static Texture Image = new Texture("Tile1.png");

    Tile(float x, float y, int row, int col){
        this.row = row;
        this.col = col;
        startX = x;
        startY = y;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public float getX(){
        return col * Image.getWidth() + startX;
    }

    public float getY(){
        return row * Image.getHeight() + startY;
    }

    public Texture getImage(){
        return Image;
    }

    public static int getWidth(){
        return Image.getWidth();
    }

    public static int getHeight(){
        return Image.getHeight();
    }
}
