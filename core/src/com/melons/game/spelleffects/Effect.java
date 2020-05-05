package com.melons.game.spelleffects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.melons.game.interfaces.SizeChangable;
import com.melons.game.interfaces.SkillButton;
import com.melons.game.interfaces.SpellEffect;
import com.melons.game.skills.Skill;

public class Effect extends Actor implements SpellEffect, SizeChangable {


    Texture effect = new Texture("Anims/Fireball.png");
    private SkillButton owner;
    private float default_x;
    private float default_y;
    private float default_width = effect.getWidth();
    private float default_height = effect.getHeight();

    private float scaled_width = default_width;
    private float scaled_height = default_height;

    private float default_target_x;
    private float default_target_y;

    private float target_x;
    private float target_y;

    private int x_velocity;
    private int y_velocity;

    float x;
    float y;

    public Effect(float x, float y, SkillButton owner){
        this.x = x;
        this.y = y;
        this.default_x = x;
        this.default_y = y;
        this.owner = owner;
    }

    @Override
    public void move() {
        x += x_velocity;
        y += y_velocity;
        default_x += x_velocity;
        default_y += y_velocity;
        if (checkAchieved()){
            onCollide();
        }
    }

    @Override
    public void onCollide() {
        owner.use();
        remove();
    }

    @Override
    public void setXVector(int v) {
        x_velocity = v;
    }

    @Override
    public void setYVector(int y) {
        y_velocity = y;
    }

    @Override
    public void setTarget(float x, float y) {
        target_x = x;
        target_y = y;
        if (x > this.x){
            setXVector(5);
        }
        else{
            setXVector(-5);
        }
    }

    @Override
    public boolean checkAchieved() {
        if (target_x - x < scaled_width){
            return true;
        }
        return false;
    }

    @Override
    public void resize(int new_width, int new_height) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(effect, this.x, this.y);
        move();
    }
}
