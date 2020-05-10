package com.melons.game.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.melons.game.Constants;
import com.melons.game.MelonCycle;
import com.melons.game.interfaces.SizeChangable;

public class PageButton extends ImageButton implements SizeChangable {

    protected RuneContainer pager;
    protected int pageValue;

    protected float default_x;
    protected float default_y;

    protected float default_width;
    protected float default_height;

    protected float x;
    protected float y;


    public PageButton(float x, float y, MelonCycle g, String path, RuneContainer pager, int val) {
        super(new TextureRegionDrawable(new TextureRegion(new Texture(path))));
        this.pager = pager;
        pageValue = val;
        this.x = x;
        this.default_x = x;
        this.y = y;
        this.default_y = y;

        default_width = 64;
        default_height = 64;

        setTouchable(Touchable.enabled);
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                execute();
                return true;
            }
        });
        g.addResizable(this);
    }

    public void execute() {
        pager.showRunes(pager.getPage()+pageValue);
    }

    @Override
    public void resize(int new_width, int new_height) {
        float bx = default_x / Constants.START_SCREEN_WIDTH * new_width;
        float by = default_y / Constants.START_SCREEN_HEIGHT * new_height;
        float width = default_width / Constants.START_SCREEN_WIDTH * new_width;
        float height = default_height / Constants.START_SCREEN_HEIGHT * new_height;
        setBounds(bx, by, width, height);
    }
}
