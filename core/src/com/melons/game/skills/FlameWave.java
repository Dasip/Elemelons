package com.melons.game.skills;

import com.badlogic.gdx.graphics.Texture;
import com.melons.game.MelonCycle;
import com.melons.game.MelonMage;
import com.melons.game.buffs.FlameWaveBuff;

public class FlameWave extends Skill {

    public FlameWave(MelonCycle g) {
        super(g);
        this.name = "FlameWave";
        this.damage = 0;
        this.seedToUse = 2;
        this.img = new Texture("Runes/FlameWave.png");
    }

    @Override
    public void use() {
        super.use();
        target.addBuff(new FlameWaveBuff());
    }

    @Override
    public void setTarget(MelonMage speller, MelonMage target) {
        super.setTarget(speller, target);
        use();
    }
}
