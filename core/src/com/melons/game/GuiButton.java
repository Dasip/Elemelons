package com.melons.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GuiButton extends Actor {

        private float x;
        private float y;
        private Rectangle Collision;
        private static Texture Image = new Texture("Tile1.png");
        private Game g;
        private MainScreen ms;
        private LibraryScreen ls;

        GuiButton(float x, float y, Game g){
            ms = null;
            ls = null;
            this.x = x;
            this.y = y;
            Collision = new Rectangle();
            Collision.x = x;
            Collision.y = y;
            Collision.height = Image.getHeight();
            Collision.width = Image.getWidth();
            this.g = g;
        }

        public void setScreen(MainScreen s) {
            this.ms = s;
        }

        public void setScreen(LibraryScreen s){
            this.ls = s;
        }

        public float getX(){
            return x;
        }

        public float getY(){
            return y;
        }

        public Texture getImage(){
            return Image;
        }

        public static int getButtonWidth(){
            return Image.getWidth();
        }

        public static int getButtonHeight(){
            return Image.getHeight();
        }

        public boolean areCoordsRight(float x, float y) {
            return this.x <= x && x <= getButtonWidth() + this.x &&  this.y <= y && y <= getButtonHeight() + this.y;
        }

        public void execute() {
            if (ms != null){
                g.setScreen(ms);
            }
            else{
                g.setScreen(ls);
            }
        }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Image, this.x, this.y);
    }
}

