package com.melons.game.skills;

import com.badlogic.gdx.graphics.Texture;
import com.melons.game.MelonCycle;

public class Fireball extends Skill {

    public Fireball(MelonCycle g) {
        super(g);
        img = new Texture("Runes/Fireball.png");
        this.name = "Fireball";
        this.damage = 10;
    }
}
