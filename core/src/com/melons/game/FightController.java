package com.melons.game;

import com.melons.game.skills.Skill;

import java.util.ArrayList;

public class FightController {

    ArrayList<MelonMage> rivals = new ArrayList<MelonMage>();
    MelonMage current_melon;
    Skill picked_skill = null;

    public FightController(ArrayList<MelonMage> melons){
        rivals = melons;
        current_melon = rivals.get(0);
        for (MelonMage m: rivals){
            m.setFightController(this);
        }
    }

    public void pick(Skill skill){
        picked_skill = skill;
        System.out.println("picked");
    }

    public void unpick(){
        picked_skill = null;
    }

    public void pickMelon(MelonMage m){
        if (picked_skill != null) {
            picked_skill.useOnTarget(m);
        }
    }


}
