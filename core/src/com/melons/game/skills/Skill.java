package com.melons.game.skills;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.melons.game.controllers.FightController;
import com.melons.game.MelonCycle;
import com.melons.game.MelonMage;
import com.melons.game.interfaces.SizeChangable;
import com.melons.game.interfaces.SkillButton;

public class Skill extends Actor implements SizeChangable, SkillButton {

    Texture img = new Texture("Runes/Empty.png");

    protected boolean picked = false;
    protected MelonMage owner;
    MelonMage speller;
    MelonMage target;

    float default_x;
    float default_y;

    float x;
    float y;

    float default_width = img.getWidth();
    float default_height = img.getHeight();

    float start_screen_width = 0;
    float start_screen_height = 0;

    int seedToUse = 1;

    FightController Mars;

    protected String name = "Empty";
    protected String description = "Empty slot.\nYou can equip\nsome spells\nfrom the list";
    int damage = 10;

    public Skill(MelonCycle g){
        x = 0;
        y = 0;
        setX(0);
        setY(0);
        setBounds(0, 0, img.getWidth(), img.getHeight());
        setTouchable(Touchable.enabled);
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pick();
                return true;
            }
        });
        g.addResizable(this);
        setDef();
    }

    public Skill(){
        x = 0;
        y = 0;
        setX(0);
        setY(0);
        setBounds(0, 0, img.getWidth(), img.getHeight());
        setTouchable(Touchable.enabled);
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pick();
                return true;
            }
        });
        setDef();
    }

    public String getTextureName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public void setController(FightController m){
        Mars = m;
    }

    @Override
    public void setX(float x) {
        default_x = x;
        this.x = x;
        super.setX(x);
    }

    @Override
    public void setY(float y) {
        default_y = y;
        this.y = y;
        super.setY(y);

    }

    @Override
    public void setCoords(float x, float y){
        setX(x);
        setY(y);
        default_x = x;
        default_y = y;
    }

    @Override
    public void resize(int new_width, int new_height) {
        if (start_screen_height == 0 && start_screen_width == 0){
            start_screen_height = new_height;
            start_screen_width = new_width;
        }
        else{
            float bx = default_x / start_screen_width * new_width;
            float by = default_y / start_screen_height * new_height;
            float width = default_width / start_screen_width * new_width;
            float height = default_height / start_screen_height * new_height;
            setBounds(bx, by, width, height);
        }
    }

    @Override
    public boolean pick() {
        if (owner.getSeeds() >= seedToUse) {
            picked = true;
            Mars.pick(this);
            System.out.println(this.name);
            return true;
        }
        return false;
    }

    @Override
    public void use() {
        Mars.setPickable(true);
        unpick();
    }

    @Override
    public void useOnTarget(MelonMage target) {
        target.receiveDamage(damage);
    }

    @Override
    public void setTarget(MelonMage speller, MelonMage target) {
        Mars.setPickable(false);
        this.speller = speller;
        this.target = target;
        /*
        System.out.println("SPELLER " + speller.getX() + " " + speller.getY());
        System.out.println("TARGET " + target.getX() + " " + target.getY());*/
    }

    @Override
    public int getSeeds() {
        return seedToUse;
    }

    @Override
    public void setOwner(MelonMage a) {
        owner = a;
    }

    @Override
    public void setDef() {

    }

    public void unpick(){
        picked = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (owner.getSeeds() < seedToUse){
            batch.setColor(0.2f, 0.2f, 0.2f, 0.2f);
        }
        if (picked){
            batch.setColor(0.5f, 0.5f, 0.5f, 1);
        }
        batch.draw(img, this.x, this.y);
        if (owner.getSeeds() < seedToUse || picked){
            batch.setColor(1, 1, 1, 1);
        }
    }

}
