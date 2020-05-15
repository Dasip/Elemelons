package com.melons.game.gui.buttons;

import com.melons.game.MelonCycle;
import com.melons.game.gui.containers.DescContainer;

public class DequipButton extends GuiButton {

    protected String skillName;
    protected com.melons.game.gui.containers.DescContainer owner;

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
