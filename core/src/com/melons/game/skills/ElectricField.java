package com.melons.game.skills;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.melons.game.MelonCycle;
import com.melons.game.buffs.ElectricFieldBuff;


public class ElectricField extends Skill {

    private boolean timerStarted = false;
    private float timer = 0;

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
        this.description = "Электрополе. \nДает иммунитет \nк 2 электрическим \nзаклинаниям \nна 2 хода.";
    }

    @Override
    public boolean pick() {
        if (owner.getSeeds() >= seedToUse){
            owner.decreaseSeeds(seedToUse);
            picked = true;
            timerStarted = true;
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (timerStarted){
            batch.setColor(0.5f, 0.5f, 0.5f, 1);
            timer += Gdx.graphics.getDeltaTime();
            if (timer > Gdx.graphics.getDeltaTime() * 10){
                timerStarted = false;
                timer = 0;
            }
        }
        super.draw(batch, parentAlpha);
        batch.setColor(1, 1, 1, 1);
    }
}
