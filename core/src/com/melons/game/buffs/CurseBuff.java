package com.melons.game.buffs;

public class CurseBuff extends SpellBuff {

    protected int damage;

    public CurseBuff(){
        type = "Curse";
    }

    @Override
    public void use() {
        super.use();
        owner.receiveDamage(damage);
    }
}
