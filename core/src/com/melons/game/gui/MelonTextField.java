package com.melons.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.melons.game.Constants;
import com.melons.game.MelonCycle;
import com.melons.game.interfaces.SizeChangable;

public class MelonTextField extends TextField implements SizeChangable {

    protected MelonCycle game;
    protected float default_x;
    protected float default_y;

    protected float label_x;
    protected float label_y;

    protected float default_width;
    protected float default_height;

    protected Label label;

    public MelonTextField(String text, Skin skin, String label, Stage owner, MelonCycle g) {
        super(text, skin);
        this.label = new Label(label, skin);
        label_x = 0;
        label_y = 0;
        owner.addActor(this.label);
        game = g;
        g.addResizable(this);
    }

    public void setMelonSize(float x, float y, float w, float h){
        default_x = x;
        default_y = y;
        default_width = w;
        default_height = h;

        label_x = x;
        label_y = y + default_height + 10;
        setBounds(default_x, default_y, default_width, default_height);
    }

    @Override
    public void resize(int new_width, int new_height) {
        float x = default_x / Constants.START_SCREEN_WIDTH * new_width;
        float y = default_y / Constants.START_SCREEN_HEIGHT * new_height;
        float width = default_width / Constants.START_SCREEN_WIDTH * new_width;
        float height = default_height / Constants.START_SCREEN_HEIGHT * new_height;
        System.out.println("Coords " + x + " " + y);
        System.out.println("Defs " + default_x + " " + default_y);
        setX(x);
        setY(y);
        setBounds(default_x, default_y, default_width, default_height);
        label.setX(label_x);
        label.setY(label_y);
    }

}
