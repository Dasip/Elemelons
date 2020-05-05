package com.melons.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.melons.game.skills.Skill;

import java.util.ArrayList;

public class FightController {

    ArrayList<MelonMage> rivals;
    MelonMage player;
    MelonMage current_melon;
    Skill picked_skill = null;

    Stage field;

    public FightController(ArrayList<MelonMage> melons, MelonMage player, Stage g){
        rivals = melons;
        this.player = player;
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
        else {
            for (MelonMage i: rivals){
                if (i != player){
                    i.setMark();
                }
                else{
                    i.setShade();
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
        picked_skill = null;
    }

    public void pickMelon(MelonMage m){
        if (picked_skill != null && m != player) {
            picked_skill.setTarget(current_melon, m);
            unpick();
        }
    }

    public void addActor(Actor a){
        field.addActor(a);
    }


}
