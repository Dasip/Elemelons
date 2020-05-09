package com.melons.game.skills;

import com.badlogic.gdx.graphics.Texture;
import com.melons.game.Constants;
import com.melons.game.Damage;
import com.melons.game.MelonCycle;
import com.melons.game.MelonMage;
import com.melons.game.spelleffects.Effect;
import com.melons.game.spelleffects.LightningEffect;

public class Lightning extends Skill {

    public Lightning(MelonCycle g) {
        super(g);
    }

    public Lightning(){
        super();
    }

    @Override
    public void setDef() {
        img = new Texture("Runes/Lightning.png");
        this.name = "Lightning";
        this.damage = 15;
        seedToUse = 2;
        this.description = "Bolt Lightning - hits the enemy with elecrical shock strike";
    }

    @Override
    public void use() {
        super.use();
        target.receiveSpell(new Damage(damage, Constants.ELECTRIC));
    }

    @Override
    public void setTarget(MelonMage speller, MelonMage target) {
        super.setTarget(speller, target);
        Effect bolt = new LightningEffect(speller.getX(), speller.getY() + speller.getHeight() / 2, this);
        Mars.addActor(bolt);
        bolt.setTarget(target.getX(), target.getY());

    }

}
