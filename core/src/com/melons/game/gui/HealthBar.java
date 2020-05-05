package com.melons.game.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.melons.game.Constants;

public class HealthBar extends Actor {

    int current_value;
    int max_value;
    int min_value = 0;

    Texture border = new Texture("Bars/HealthBarBorder.png");
    Texture bar = new Texture("Bars/HealthBarFill.png");

    float default_width = bar.getWidth();

    public HealthBar(float x, float y){
        setX(x);
        setY(y);
    }

    public void setVal(int cur, int max){
        max_value = max;
        current_value = cur;
        updateHealthBar(cur);
    }

    public void updateHealthBar(int val){
        if (val >= min_value && val <= max_value) {
            current_value = val;
            System.out.println("HealthBar - " + val);
            System.out.println();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float ratio = (float) current_value / max_value;
        batch.draw(bar, getX(), getY(), default_width * ratio, bar.getHeight());
        Constants.MELON_FONT.draw(batch, Integer.toString(current_value), getX() + 20, getY() + 48);
        batch.draw(border, getX(), getY());
    }
}
