package com.melons.game.interfaces;

import com.melons.game.FightController;
import com.melons.game.MelonMage;

public interface SkillButton {
    public void use();
    public void setCoords(float x, float y);
    public void setController(FightController m);
    public void useOnTarget(MelonMage target);
}
