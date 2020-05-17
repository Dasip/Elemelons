package com.melons.game.controllers;

import com.melons.game.MelonMage;
import com.melons.game.skills.Skill;

public class SimpleAI {

    private FightController Mars;
    private MelonMage player;
    private MelonMage owner;

    public void takeCommand(FightController M, MelonMage P){
        Mars = M;
        player = P;
        if (owner.getHP() > 0 && P.getHP() > 0){
        for (Skill s: owner.getSkills()) {
            if (s.getTextureName() == "Fireball" || s.getTextureName() == "Lightning") {
                boolean canFight = s.pick();
                System.out.println("WTF GOES " + owner.getSeeds());
                if (canFight) {

                    Mars.pickMelon(player);
                    Mars.setAIContinue(this);
                    break;
                }
            }
        }
        }
    }

    public void setOwner(MelonMage o){
        owner = o;
    }
}
