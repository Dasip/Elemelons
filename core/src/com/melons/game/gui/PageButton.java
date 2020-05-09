package com.melons.game.gui;

import com.melons.game.MelonCycle;

public class PageButton extends GuiButton {

    protected RuneContainer pager;
    protected int pageValue;


    public PageButton(float x, float y, MelonCycle g, String path, RuneContainer pager, int val) {
        super(x, y, g, path);
        this.pager = pager;
        pageValue = val;
    }

    @Override
    public void execute() {
        pager.showRunes(pager.getPage()+pageValue);
    }
}
