package com.melons.game;

import com.melons.game.buffs.SpellBuff;

import java.util.ArrayList;

public class Damage {

    private int damage;  // Количество урона
    private String element;  // Стихия урона - электричество, огонь, земля, вода, воздух
    private ArrayList<SpellBuff> buffs;  // Бафы, которые нанесет этот урон

    public Damage(int damage, String element){
        this.damage = damage;
        this.element = element;
        buffs = new ArrayList<SpellBuff>();
    }

    public Damage(int damage, String element, ArrayList<SpellBuff> buffs){
        this.damage = damage;
        this.element = element;
        this.buffs = buffs;
    }

    public int getDamage() {
        return damage;
    }

    public ArrayList<SpellBuff> getBuffs() {
        return buffs;
    }

    public String getElement() {
        return element;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
