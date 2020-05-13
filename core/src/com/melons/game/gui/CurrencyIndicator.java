package com.melons.game.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.melons.game.Constants;
import com.melons.game.MelonMage;

public class CurrencyIndicator extends Actor {

    private float x;
    private float y;

    private int value;
    private MelonMage owner;
    private Texture texture = new Texture("GUI/Seeds/seedFull.png");
    private Label amount;

    CurrencyIndicator(float x, float y, MelonMage owner, Stage stage){
        this.x = x;
        this.y = y;
        setX(x);
        setY(y);
        this.owner = owner;
        setValue(owner.getSeedCurrency());
        amount = new Label(Integer.toString(value), Constants.getSkin());
        stage.addActor(amount);
        amount.setX(this.x+texture.getWidth()+10);
        amount.setY(this.y);
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setValue(owner.getSeedCurrency());
        amount.setText(Integer.toString(value));
        batch.draw(texture, x, y);
    }
}
