package com.melons.game.gui.buttons;

import com.melons.game.Constants;
import com.melons.game.MelonCycle;
import com.melons.game.gui.containers.DescContainer;

public class EquipButton extends GuiButton {

    protected String skillName;
    protected com.melons.game.gui.containers.DescContainer owner;

    public EquipButton(float x, float y, MelonCycle g, String skillName, DescContainer owner) {
        super(x, y, g, "Взять", g.getLib());
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
