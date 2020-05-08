package com.melons.game.buffs;

import com.badlogic.gdx.graphics.Texture;
import com.melons.game.Constants;

public class FlameWaveBuff extends CurseBuff {

    public FlameWaveBuff(){
        damage = 5;
        element = Constants.FIRE;
        duration = 2;
        texture = new Texture("Buffs/FlameWave.png");
    }
}
