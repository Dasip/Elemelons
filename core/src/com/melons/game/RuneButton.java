package com.melons.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.melons.game.interfaces.SizeChangable;
import com.melons.game.skills.Skill;

public class RuneButton extends Actor implements SizeChangable {

    private Texture Image;
    private float x;
    private float y;
    private float default_x;
    private float default_y;

    private float default_width;
    private float default_height;

    RuneButton(float x, float y, Skill s){

        this.x = x;
        this.y = y;
        this.default_x = x;
        this.default_y = y;
        Image = new Texture("Runes/"+s.getTextureName()+".png");
        default_width = Image.getWidth();
        default_height = Image.getHeight();

        setBounds(x, y, default_width, default_height);
    }

    @Override
    public void resize(int new_width, int new_height) {
        if (new_height != Constants.START_SCREEN_HEIGHT && new_width != Constants.START_SCREEN_WIDTH) {
            float bx = default_x / Constants.START_SCREEN_WIDTH * new_width;
            float by = default_y / Constants.START_SCREEN_HEIGHT * new_height;
            float width = default_width / Constants.START_SCREEN_WIDTH * new_width;
            float height = default_height / Constants.START_SCREEN_HEIGHT * new_height;
            System.out.println("Melon " + width + " " + height);
            setBounds(bx, by, width, height);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Image, getX(), getY());
    }
}
