package com.melons.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.melons.game.gui.HealthBar;
import com.melons.game.gui.Mark;
import com.melons.game.interfaces.SizeChangable;
import com.melons.game.skills.Skill;

import java.util.ArrayList;


public class MelonMage extends Actor implements SizeChangable {

    private Texture Image;

    ArrayList<Skill> skills = new ArrayList<>();

    private float default_x;
    private float default_y;
    private float default_width;
    private float default_height;

    private float x;
    private float y;
    private float start_screen_width = 0;
    private float start_screen_height = 0;

    private boolean shaded = false;
    private boolean marked = false;

    FightController Mars;

    int hp = 100;
    HealthBar hpBar;

    Mark mark;

    MelonMage(float x, float y, HealthBar hp, MelonCycle g){

        Image = new Texture("Watermelon.png");
        this.x = x;
        this.y = y;
        setX(x);
        setY(y);
        default_x = x;
        default_y = y;
        default_width = Image.getWidth();
        default_height = Image.getHeight();
        setBounds(x, y, Image.getWidth(), Image.getHeight());
        hpBar = hp;
        // С самого начала у героя хп равно максимальному значению
        hpBar.setVal(this.hp, this.hp);

        mark = new Mark(x+10, y-100);

        setTouchable(Touchable.enabled);
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                giveMarsVictim();
                return true;
            }
        });
        g.addResizable(this);
    }

    public Texture getImage(){ return Image; }

    public void addSkill(Skill s){
        skills.add(s);
    }

    public ArrayList<Skill> getSkills(){
        return skills;
    }

    public void setCoords(float x, float y){
        this.x = x;
        this.y = y;
        setX(x);
        setY(y);
        default_x = x;
        default_y = y;
        mark.setX(x+24);
        mark.setY(y+150);
    }

    public void giveMarsVictim(){
        System.out.println("Executed Order 66");
        Mars.pickMelon(this);
    }

    @Override
    public void resize(int new_width, int new_height) {
        if (start_screen_height == 0 && start_screen_width == 0){
            start_screen_height = new_height;
            start_screen_width = new_width;
        }
        else{
            float bx = default_x / Constants.START_SCREEN_WIDTH * new_width;
            float by = default_y / Constants.START_SCREEN_HEIGHT * new_height;
            float width = default_width / Constants.START_SCREEN_WIDTH * new_width;
            float height = default_height / Constants.START_SCREEN_HEIGHT * new_height;
            System.out.println("Melon " + width + " " + height);
            setBounds(bx, by, width, height);
        }
    }

    public void receiveDamage(int dmg){
        hp = hp - dmg < 0 ? 0 : hp - dmg;
        hpBar.updateHealthBar(hp);
    }

    public void setFightController(FightController c){
        Mars = c;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (shaded){
            batch.setColor((float)0.5, (float)0.5, (float)0.5, (float) 0.5);
        }
        batch.draw(Image, this.x ,this.y);
        mark.updateStateTime();
        if (marked){
            batch.draw(mark.getCurrentFrame(), mark.getX(), mark.getY());
        }
        if (shaded){
            batch.setColor(1, 1, 1, 1);
        }
    }

    public void setShade(){
        shaded = true;
    }

    public void unsetShade(){
        shaded = false;
    }

    public void setMark(){
        marked = true;
    }

    public void unsetMark(){
        marked = false;
    }


}
