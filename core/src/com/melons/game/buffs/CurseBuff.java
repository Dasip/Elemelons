package com.melons.game.buffs;

import com.melons.game.Constants;
import com.melons.game.Damage;

public class CurseBuff extends SpellBuff {

    protected int damage;
    protected String element;

    public CurseBuff(){
        type = Constants.CURSE;
    }

    @Override
    public void use() {
        super.use();
        owner.receiveSpell(new Damage(damage, element));
    }
}
