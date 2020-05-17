package com.melons.game.gui.containers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.melons.game.Constants;
import com.melons.game.MelonCycle;
import com.melons.game.gui.buttons.RuneButton;

import java.util.ArrayList;

public class CurrentContainer extends MelonContainer {

    protected ArrayList<RuneButton> runes;
    protected Stage owner;

    public CurrentContainer(float x, float y, float width, float height, Stage owner, MelonCycle g) {
        super(x, y, width, height, owner, g);
        this.owner = owner;
    }

    public void setRunes(ArrayList<RuneButton> r){
        runes = r;
        float start_y = height - 130;
        float y_step = -120;

        for (int i=0; i<r.size(); i++){
            owner.addActor(r.get(i));
            r.get(i).setCoords(25, start_y + y_step * i);
        }
    }

}
