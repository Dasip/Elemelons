package com.melons.game.spelleffects;

import com.badlogic.gdx.graphics.Texture;
import com.melons.game.interfaces.SkillButton;

public class LightningEffect extends Effect {
    public LightningEffect(float x, float y, SkillButton owner) {
        super(x, y, owner);
        effect = new Texture("Anims/Lightning.png");
    }
}
