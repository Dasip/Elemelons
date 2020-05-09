package com.melons.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.melons.game.Constants;
import com.melons.game.MelonCycle;
import com.melons.game.RuneButton;
import com.melons.game.skills.Skill;

import java.util.ArrayList;


public class RuneContainer extends Panel {

    protected ArrayList<RuneButton> runes = new ArrayList<>();
    protected int runeIndex = 0;
    protected int runeVolume = 16;
    protected int page = 1;
    protected Stage owner;

    protected float width;
    protected float height;

    protected MelonCycle game;

    public RuneContainer(float x, float y, float w, float h, Stage owner, MelonCycle g){
        super(x, y, "null");
        width = w;
        height = h;
        this.owner = owner;
        game = g;
    }


    public int getPage(){
        return page;
    }


    public void setRunes(ArrayList<Skill> s){
        for (Skill i: s){
            RuneButton r = new RuneButton(0, 0, game, i);
            runes.add(r);
        }
        showRunes(page);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (Image != null){
            batch.draw(Image, getX(), getY());
        }

    }

    public void clearRunes(){
        for (RuneButton i: runes){
            i.remove();
        }
    }

    public void showRunes(int page){
        System.out.println(page);
        if (page > 0 && !(runeVolume*(page-1) > runes.size())) {
            clearRunes();

            int xStep = 120;
            int yStep = 110;
            int ySep = 110;
            int xSep = 45;
            int curIndex = runeVolume * (page - 1);

            int max_index = runes.size() > runeVolume + curIndex ? runeVolume + curIndex : runes.size();
            for (int i = curIndex; i < max_index; i++) {
                int col = i % 4;
                int row = (i - runeVolume*(page-1)) / 4;
                RuneButton cur = runes.get(i);
                owner.addActor(cur);
                cur.setX(x + (col * xStep) + xSep);
                cur.setY(height - (row * yStep) - ySep);
            }

            runeIndex = curIndex;
            this.page = page;

        }
    }
}
