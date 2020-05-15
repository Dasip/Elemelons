package com.melons.game.gui.buttons;

import com.melons.game.controllers.FightController;
import com.melons.game.MelonCycle;

public class EndTurnButton extends GuiButton{

    private FightController Mars;

    public EndTurnButton(float x, float y, MelonCycle g, FightController M) {
        super(x, y, g, "Ход", g.getFight());
        Mars = M;
    }

    @Override
    public void execute() {

        if (Mars.getPickable()) {
            Mars.changeTurn();

        }
    }

}
