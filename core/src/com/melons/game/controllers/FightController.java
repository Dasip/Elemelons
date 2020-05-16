package com.melons.game.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.melons.game.Constants;
import com.melons.game.MelonCycle;
import com.melons.game.MelonMage;
import com.melons.game.gui.buttons.GuiButton;
import com.melons.game.gui.containers.Panel;
import com.melons.game.skills.Skill;

import java.util.ArrayList;

public class FightController {

    private boolean mustChange = false;
    private boolean pickable = true;
    private boolean mustContinue = false;

    private SimpleAI ai;

    ArrayList<MelonMage> rivals;
    MelonMage player;
    MelonMage current_melon;
    int current_index = 0;
    Skill picked_skill = null;

    private MelonCycle game;

    Stage field;

    public FightController(ArrayList<MelonMage> melons, MelonMage player, MelonCycle gam, Stage g){
        for (MelonMage m: melons){
            m.setMars(this);
        }
        rivals = melons;
        this.player = player;
        player.setSeedDraw(true);
        current_melon = rivals.get(0);
        field = g;
        game = gam;
        for (MelonMage m: rivals){
            m.setFightController(this);
        }
    }

    public void changeTurn(){
        current_index = current_index >= rivals.size() - 1 ? 0 : current_index + 1;
        current_melon = rivals.get(current_index);
        current_melon.refreshSeeds();
        current_melon.runOverBuffs();
        mustChange = false;
        if (current_melon.getGuide() != null){
            current_melon.getGuide().takeCommand(this, player);
        }
    }

    public void pick(Skill skill){
        if (picked_skill == skill){
            unpick();
        }
        else if (pickable) {
            if (current_melon.getGuide() == null) {
                for (MelonMage i : rivals) {
                    if (i != player) {
                        i.setMark();
                    } else {
                        i.setShade();
                        i.showSeedsToUse(skill.getSeeds());
                    }
                }
            }
            picked_skill = skill;
            System.out.println("picked");
        }
    }

    public void unpick(){
        for (MelonMage i: rivals){
            i.unsetMark();
            i.unsetShade();
        }
        current_melon.showSeedsToUse(0);
        picked_skill = null;
    }

    public void pickMelon(MelonMage m){
        if (picked_skill != null && m != current_melon && pickable) {
            picked_skill.setTarget(current_melon, m);
            current_melon.decreaseSeeds(picked_skill.getSeeds());
            unpick();

        }
    }

    public void addActor(Actor a){
        field.addActor(a);
    }

    public void setPickable(boolean v){
        pickable = v;
        if (pickable && mustChange){
            changeTurn();
        }
        if (pickable && mustContinue){
            mustContinue = false;
            ai.takeCommand(this, player);
        }
    }

    public boolean getPickable(){
        return pickable;
    }

    public void setMustChange(boolean v){
        mustChange = v;
    }

    public void defeated(MelonMage m){
        /*
        for (MelonMage i: rivals){
            if (i != m){
                i.refreshAll();
            }
        }
*/
        pickable = false;
        System.out.println("Defeated!");

        Texture win = new Texture("GUI/Panels/message_panel.png");
        Panel message_window = new Panel(Constants.START_SCREEN_WIDTH / 2 - win.getWidth() / 2, Constants.START_SCREEN_HEIGHT / 2 - win.getHeight() / 2, "GUI/Panels/message_panel.png");

        field.addActor(message_window);

        GuiButton ok = new GuiButton(Constants.START_SCREEN_WIDTH / 2 - win.getWidth() / 3, Constants.START_SCREEN_HEIGHT / 2 - win.getHeight() / 3, game, "Ок", field);

        field.addActor(ok);
        ok.setStage(game.getMain());
    }

    public void setAIContinue(SimpleAI ai){
        this.ai = ai;
        mustContinue = true;
    }

}
