package com.melons.game.controllers;

import com.melons.game.RuneButton;
import com.melons.game.gui.DescContainer;

public class LibController {

    private DescContainer desc;

    public LibController(){

    }

    public void addDesc(DescContainer d){
        desc = d;
    }

    public void setRune(RuneButton r){
        desc.setRune(r);
    }

}
