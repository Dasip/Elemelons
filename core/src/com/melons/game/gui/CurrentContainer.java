package com.melons.game.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.melons.game.Constants;
import com.melons.game.RuneButton;
import com.melons.game.skills.Skill;

import java.util.ArrayList;

public class CurrentContainer extends Panel {

    protected ArrayList<RuneButton> runes;
    protected float width;
    protected float height;
    protected Stage owner;

    public CurrentContainer(float x, float y, float width, float height, Stage owner) {
        super(x, y, "null");
        this.owner = owner;
    }

    public void setRunes(ArrayList<RuneButton> r){
        runes = r;
        int start_y = Constants.START_SCREEN_HEIGHT - 150;
        for (RuneButton i: runes){
            owner.addActor(i);
            i.setX(25);
            i.setY(start_y);
            start_y -= 120;
        }
    }

}
