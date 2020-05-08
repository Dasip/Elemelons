package com.melons.game.buffs;

import com.melons.game.Constants;
import com.melons.game.Damage;

public class ImmuneBuff extends SpellBuff {

    protected String element = Constants.FIRE;
    protected int durability = 0;  // сколько заклинаний стихии element выдержит бафф
    protected boolean durable = false;  // Заклинание имеет порог защиты по количеству поглощенных спеллов
    protected boolean limited = true;  // Заклинание перестает действовать со временем

    public ImmuneBuff(){
        type = Constants.IMMUNE;
    }

    public boolean checkImmune(Damage d){
        if (d.getElement() == element && durable && durability > 0){
            durability -= 1;
            if (durability == 0){
                owner.removeBuff(this);
            }
            return true;
        }
        return false;
    }

    @Override
    public void use() {
        if (limited){
            duration -= 1;
            if (duration == 0){
                owner.addToRemove(this);
            }
        }
        if (durable && durability == 0){
            owner.addToRemove(this);
        }
    }
}
