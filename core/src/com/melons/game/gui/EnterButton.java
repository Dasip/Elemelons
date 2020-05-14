package com.melons.game.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.melons.game.Constants;
import com.melons.game.MelonCycle;

public class EnterButton extends GuiButton {

    protected String mode;
    protected TextField login;
    protected TextField password;

    public EnterButton(float x, float y, MelonCycle g, String text, String mode, Stage owner) {
        super(x, y, g, text, owner);
        this.mode = mode;
    }

    public void setFields(TextField l, TextField p){
        login = l;
        password = p;
    }

    @Override
    public void execute() {
        if (this.mode == "login"){
            Constants.GET_TOKEN();

        }
    }
}
