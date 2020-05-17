package com.melons.game.gui.containers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.melons.game.Constants;
import com.melons.game.MelonCycle;
import com.melons.game.interfaces.SizeChangable;

public class MelonContainer extends Panel implements SizeChangable {
    protected Stage owner;

    protected float width;
    protected float height;

    protected float default_x;
    protected float default_y;

    protected float default_width;
    protected float default_height;

    protected MelonCycle game;

    public MelonContainer(float x, float y, float w, float h, Stage owner, MelonCycle g) {
        super(x, y, "null");
        width = w;
        height = h;
        default_height = h;
        default_width = w;
        default_x = x;
        default_y = y;
        this.owner = owner;
        game = g;
        g.addResizable(this);
    }

    @Override
    public void resize(int new_width, int new_height) {

        float bx = default_x / Constants.START_SCREEN_WIDTH * new_width;
        float by = default_y / Constants.START_SCREEN_HEIGHT * new_height;
        float width = default_width / Constants.START_SCREEN_WIDTH * new_width;
        float height = default_height / Constants.START_SCREEN_HEIGHT * new_height;
        setX(default_x);
        setY(default_y);
    }
}
