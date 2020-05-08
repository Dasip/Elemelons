package com.melons.game.buffs;

import com.badlogic.gdx.graphics.Texture;
import com.melons.game.Constants;

public class ElectricFieldBuff extends ImmuneBuff {

    public ElectricFieldBuff(){
        this.durable = true;
        this.durability = 3;
        this.limited = false;
        texture = new Texture("Buffs/ElectricField.png");
        this.element = Constants.ELECTRIC;
    }

}
