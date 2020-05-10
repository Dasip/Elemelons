package com.melons.game.gui;

import com.melons.game.MelonCycle;

public class EnterButton extends GuiButton {

    protected String mode;

    public EnterButton(float x, float y, MelonCycle g, String text, String mode) {
        super(x, y, g, text);
        this.mode = mode;
    }

    @Override
    public void execute() {
        super.execute();
    }
}
