package com.melons.game.gui;

import com.melons.game.Constants;
import com.melons.game.MelonCycle;

public class EquipButton extends GuiButton {

    protected String skillName;
    protected DescContainer owner;

    public EquipButton(float x, float y, MelonCycle g, String skillName, DescContainer owner) {
        super(x, y, g, "GUI/Buttons/equip_button.png");
        this.skillName = skillName;
        this.owner = owner;
    }

    @Override
    public void execute() {
        boolean added = game.getPlayer().addSkill(Constants.GET_SKILL_BY_NAME(skillName));
        if (added){
            owner.showUnset();
            game.getMinerva().updateCurrent();
        }
    }
}
