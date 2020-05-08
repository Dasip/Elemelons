package com.melons.game.skills;

import com.badlogic.gdx.graphics.Texture;
import com.melons.game.Constants;
import com.melons.game.Damage;
import com.melons.game.MelonCycle;
import com.melons.game.MelonMage;
import com.melons.game.spelleffects.Effect;
import com.melons.game.spelleffects.FireballEffect;

public class Fireball extends Skill {

    public Fireball(MelonCycle g) {
        super(g);
        img = new Texture("Runes/Fireball.png");
        this.name = "Fireball";
        this.damage = 5;
        seedToUse = 1;
    }

    @Override
    public void use() {
        super.use();
        target.receiveSpell(new Damage(damage, Constants.FIRE));
    }

    @Override
    public void setTarget(MelonMage speller, MelonMage target) {
        super.setTarget(speller, target);
        Effect ball = new FireballEffect(speller.getX(), speller.getY() + speller.getHeight() / 2, this);
        Mars.addActor(ball);
        ball.setTarget(target.getX(), target.getY());

    }
}
