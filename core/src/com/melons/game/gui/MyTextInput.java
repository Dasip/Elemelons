package com.melons.game.gui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

public class MyTextInput implements Input.TextInputListener {

    private String text;
    @Override
    public void input(String text) {
        this.text = text;
    }

    @Override
    public void canceled() {
        text = "cancelled";
    }


    public String getText() {
        return text;
    }
}
