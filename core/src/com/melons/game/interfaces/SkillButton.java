package com.melons.game.interfaces;

import com.melons.game.controllers.FightController;
import com.melons.game.MelonMage;

public interface SkillButton {
    public void use();
    public void setCoords(float x, float y);
    public void setController(FightController m);
    public void useOnTarget(MelonMage target);
    public void pick();
    public void setTarget(MelonMage speller, MelonMage target);
    public int getSeeds();
    public void setOwner(MelonMage a);
    public void setDef();
}
