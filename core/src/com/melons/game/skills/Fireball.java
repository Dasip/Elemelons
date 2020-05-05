package com.melons.game.skills;

import com.badlogic.gdx.graphics.Texture;
import com.melons.game.MelonCycle;
import com.melons.game.MelonMage;
import com.melons.game.spelleffects.FireballEffect;

public class Fireball extends Skill {

    public Fireball(MelonCycle g) {
        super(g);
        img = new Texture("Runes/Fireball.png");
        this.name = "Fireball";
        this.damage = 10;
    }

    @Override
    public void use() {
        useOnTarget(target);
    }

    @Override
    public void setTarget(MelonMage speller, MelonMage target) {
        super.setTarget(speller, target);
        FireballEffect ball = new FireballEffect(speller.getX(), speller.getY() + speller.getHeight() / 2, this);
        Mars.addActor(ball);
        ball.setTarget(target.getX(), target.getY());

    }
}
