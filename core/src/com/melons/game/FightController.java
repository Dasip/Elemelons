package com.melons.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.melons.game.skills.Skill;

import org.omg.CORBA.UserException;

import java.util.ArrayList;

public class FightController {


    private boolean pickable = true;
    ArrayList<MelonMage> rivals;
    MelonMage player;
    MelonMage current_melon;
    Skill picked_skill = null;

    Stage field;

    public FightController(ArrayList<MelonMage> melons, MelonMage player, Stage g){
        rivals = melons;
        this.player = player;
        player.setSeedDraw(true);
        current_melon = rivals.get(0);
        field = g;
        for (MelonMage m: rivals){
            m.setFightController(this);
        }
    }

    public void pick(Skill skill){
        if (picked_skill == skill){
            unpick();
        }
        else if (pickable) {
            for (MelonMage i: rivals){
                if (i != player){
                    i.setMark();
                }
                else{
                    i.setShade();
                    i.showSeedsToUse(skill.getSeeds());
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
        if (picked_skill != null && m != player && pickable) {
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
    }

    public boolean getPickable(){
        return pickable;
    }

}
