package com.melons.game.skills;

import com.badlogic.gdx.graphics.Texture;
import com.melons.game.MelonCycle;

public class Lightning extends Skill {

    public Lightning(MelonCycle g) {
        super(g);
        img = new Texture("Runes/Lightning.png");
        this.name = "Lightning";
        this.damage = 18;
    }

}
