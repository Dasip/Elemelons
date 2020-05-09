package com.melons.game.gui;

import com.melons.game.controllers.FightController;
import com.melons.game.MelonCycle;

public class EndTurnButton extends GuiButton{

    private FightController Mars;

    public EndTurnButton(float x, float y, MelonCycle g, String path, FightController M) {
        super(x, y, g, path);
        Mars = M;
    }

    @Override
    public void execute() {

        if (Mars.getPickable()) {
            Mars.changeTurn();

        }
    }

}
