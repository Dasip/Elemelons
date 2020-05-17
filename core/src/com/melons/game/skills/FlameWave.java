package com.melons.game.skills;

import com.badlogic.gdx.graphics.Texture;
import com.melons.game.Constants;
import com.melons.game.Damage;
import com.melons.game.MelonCycle;
import com.melons.game.MelonMage;
import com.melons.game.buffs.FlameWaveBuff;
import com.melons.game.buffs.SpellBuff;

import java.util.ArrayList;

public class FlameWave extends Skill {

    public FlameWave(MelonCycle g) {
        super(g);
    }

    public FlameWave(){
        super();
    }

    @Override
    public void setDef() {
        this.name = "FlameWave";
        this.damage = 5;
        this.seedToUse = 2;
        this.img = new Texture("Runes/FlameWave.png");
        this.description = "Flamewave hits \nan enemy with \nfire every \nturn for 2 \nturns";
    }

    @Override
    public void use() {
        super.use();
        ArrayList<SpellBuff> buffs = new ArrayList<>();
        buffs.add(new FlameWaveBuff());
        target.receiveSpell(new Damage(damage, Constants.FIRE, buffs));
    }

    @Override
    public void useOnTarget(MelonMage target) {
        target.receiveDamage(damage);
        target.addBuff(new FlameWaveBuff());
    }

    @Override
    public void setTarget(MelonMage speller, MelonMage target) {
        super.setTarget(speller, target);
        use();
    }
}
