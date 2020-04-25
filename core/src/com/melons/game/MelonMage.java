package com.melons.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.melons.game.gui.HealthBar;
import com.melons.game.interfaces.SizeChangable;

import java.util.ArrayList;


public class MelonMage extends Actor implements SizeChangable {

    private Texture Image;

    ArrayList<Actor> skills = new ArrayList<Actor>();

    float default_x;
    float default_y;
    float default_width ;
    float default_height;

    float x;
    float y;
    float start_screen_width = 0;
    float start_screen_height = 0;

    int hp = 100;
    HealthBar hpBar;

    MelonMage(float x, float y, HealthBar hp, MelonCycle g){

        Image = new Texture("Watermelon.png");
        this.x = x;
        this.y = y;
        setX(x);
        setY(y);
        default_x = x;
        default_y = y;
        setBounds(x, y, Image.getWidth(), Image.getHeight());
        hpBar = hp;
        // С самого начала у героя хп равно максимальному значению
        hpBar.setVal(this.hp, this.hp);

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

    public Texture getImage(){ return Image; }

    public void addSkill(Actor s){
        skills.add(s);
    }

    public ArrayList<Actor> getSkills(){
        return skills;
    }

    public void setCoords(float x, float y){
        this.x = x;
        this.y = y;
        setX(x);
        setY(y);
        default_x = x;
        default_y = y;
    }

    public void execute(){
        hp -= 10;
        hpBar.updateHealthBar(hp);
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
            System.out.println(width + " " + height);
            setBounds(bx, by, width, height);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Image, this.x ,this.y);
    }
}
