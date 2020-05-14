package com.melons.game.controllers;

import com.melons.game.Constants;
import com.melons.game.MelonCycle;
import com.melons.game.RuneButton;
import com.melons.game.gui.CurrentContainer;
import com.melons.game.gui.DescContainer;
import com.melons.game.skills.Skill;

import java.util.ArrayList;

public class LibController {

    private DescContainer desc;
    private CurrentContainer curr;
    private MelonCycle game;

    public LibController(MelonCycle g){
        game = g;
    }

    public void addDesc(DescContainer d){
        desc = d;
    }

    public void addCurr(CurrentContainer c) { curr = c; }

    public void setRune(RuneButton r){
        desc.setRune(r);
    }

    public void updateCurrent(){
        ArrayList<RuneButton> runes = game.getPlayerRunes();
        if (runes.size() < Constants.MAX_SKILLS){
            int sizeN = Constants.MAX_SKILLS - runes.size();
            for (int i=0; i < sizeN; i++){
                RuneButton r = new RuneButton(0, 0, game, new Skill(game));
                runes.add(r);
            }
        }

        curr.setRunes(runes);
    }

}
