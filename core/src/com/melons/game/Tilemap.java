package com.melons.game;

import java.util.ArrayList;

public class Tilemap {

    private ArrayList<Tile> Tiles;
    private int rows;
    private int cols;
    private float startX;
    private float startY;

    Tilemap(float x, float y, int rows, int cols){
        this.rows = rows;
        this.cols = cols;

        this.startX = x;
        this.startY = y;

        Tiles = new ArrayList<Tile>();

        for (int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                Tile tile = new Tile(startX, startY, i, j);
                Tiles.add(tile);
            }
        }
    }

    public ArrayList<Tile> getTiles(){
        return Tiles;
    }

    public void dispose(){
        for (Tile tile: Tiles){
            tile.getImage().dispose();
        }
    }

    public boolean areCoordsRight(float x, float y){
        return startX <= x && x <= cols * Tile.getWidth() + startX && startY < y && y <= rows * Tile.getHeight();
    }

    public Tile getTileByCoords(float x, float y){
        int newX = (int) (x - startX) / Tile.getWidth();
        int newY = (int) (y - startY) / Tile.getHeight();
        return Tiles.get(newY * cols + newX);
    }

}
