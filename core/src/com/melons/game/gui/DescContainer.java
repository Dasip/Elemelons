package com.melons.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.melons.game.Constants;
import com.melons.game.RuneButton;

public class DescContainer extends Panel {

    protected RuneButton rune;
    protected float width;
    protected float height;

    public DescContainer(float x, float y, float width, float height) {
        super(x, y, "null");
        this.height = height;
        this.width = width;
    }

    public void setRune(RuneButton r){
        rune = r;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (Image != null){
            batch.draw(Image, x, y);
        }

        if (rune != null){
            batch.draw(rune.getImage(), x+75, height-110);
            String text = rune.getDesc();
            Constants.MELON_FONT.draw(batch, text, x, height-140);
        }

    }
}
