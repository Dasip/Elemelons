package com.melons.game.buffs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.melons.game.MelonMage;
import com.melons.game.skills.Skill;

public class SpellBuff {

    protected String type;
    protected Texture texture;
    protected MelonMage owner;
    protected int duration;


    public SpellBuff(){
        duration = 2;
    }

    // Функция проверяет, можно ли применить способность на персонажа при данном эффекте на нем
    public boolean canUseOnOwner(Skill s){
        return true;
    }

    // Применение баффа на персонажа (нанесение урона, лечение или просто уменьшение длительности эффекта)
    public void use(){
        System.out.println("Used a buff");
        duration -= 1;
        if (duration == 0){
            owner.addToRemove(this);
        }
    }

    public void draw(Batch batch){
        batch.draw(texture, owner.getX(), owner.getY());
    }

    public void setOwner(MelonMage owner) {
        this.owner = owner;
    }

    public String getType(){ return type; }

}
