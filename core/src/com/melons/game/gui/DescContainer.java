package com.melons.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.melons.game.Constants;
import com.melons.game.MelonCycle;
import com.melons.game.RuneButton;

public class DescContainer extends Panel {

    protected RuneButton rune;
    protected float width;
    protected float height;
    protected MelonCycle game;
    protected Stage owner;

    protected EquipButton set;  // кнопка выучивания навыка
    protected DequipButton unset;  // кнопка снятия навыка

    public DescContainer(float x, float y, float width, float height, Stage owner, MelonCycle g) {
        super(x, y, "null");
        this.height = height;
        this.width = width;
        game = g;
        this.owner = owner;
        set = null;
        unset = null;
    }

    public void cleanButtons(){
        if (set != null){
            set.remove();
            set = null;
        }
        if (unset != null){
            unset.remove();
            unset = null;
        }
    }

    public void setRune(RuneButton r){
        rune = r;
        if (rune != null) {
            if (!game.getPlayer().isSkillLearnt(rune.getRuneName())) {
                showSet();
            }
            else{
                showUnset();
            }

        }
    }

    public void showSet(){
        cleanButtons();
        set = new EquipButton(x + 35, 110, game, rune.getRuneName(), this);
        owner.addActor(set);
    }

    public void showUnset(){
        cleanButtons();
        unset = new DequipButton(x + 35, 110, game, rune.getRuneName(), this);
        owner.addActor(unset);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (Image != null){
            batch.draw(Image, x, y);
        }

        if (rune != null){
            batch.draw(rune.getImage(), x+75, height-110);
            String text = rune.getDesc();
            Constants.MELON_FONT.draw(batch, text, x, height-140);
        }

    }
}
