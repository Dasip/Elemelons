package com.melons.game.skills;
import com.badlogic.gdx.graphics.Texture;
import com.melons.game.MelonCycle;
import com.melons.game.buffs.ElectricFieldBuff;


public class ElectricField extends Skill {

    public ElectricField(MelonCycle g) {
        super(g);
    }

    public ElectricField(){
        super();
    }

    @Override
    public void setDef() {
        this.name = "ElectricField";
        this.damage = 0;
        this.seedToUse = 3;
        this.img = new Texture("Runes/ElectricField.png");
        this.description = "Electric field. \nProtects from \nlightnings";
    }

    @Override
    public boolean pick() {
        if (owner.getSeeds() >= seedToUse){
            owner.decreaseSeeds(seedToUse);
            use();
            return true;
        }
        return false;
    }

    @Override
    public void use() {
        super.use();
        owner.addBuff(new ElectricFieldBuff());
    }
}
