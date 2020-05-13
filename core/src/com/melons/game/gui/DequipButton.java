package com.melons.game.gui;

import com.melons.game.Constants;
import com.melons.game.MelonCycle;

public class DequipButton extends GuiButton {

    protected String skillName;
    protected DescContainer owner;

    public DequipButton(float x, float y, MelonCycle g, String skillName, DescContainer owner) {
        super(x, y, g, "Убрать", g.getLib());
        this.skillName = skillName;
        this.owner = owner;
    }

    @Override
    public void execute() {
        boolean added = game.getPlayer().removeSkill(skillName);
        if (added){
            owner.showSet();
            game.getMinerva().updateCurrent();

        }
    }

}
