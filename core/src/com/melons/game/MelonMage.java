package com.melons.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.melons.game.buffs.ElectricFieldBuff;
import com.melons.game.gui.HealthBar;
import com.melons.game.gui.Mark;
import com.melons.game.interfaces.SizeChangable;
import com.melons.game.skills.Skill;
import com.melons.game.buffs.SpellBuff;

import java.util.ArrayList;


public class MelonMage extends Actor implements SizeChangable {

    private Texture Image;

    ArrayList<Skill> skills = new ArrayList<>();
    ArrayList<SpellBuff> buffs = new ArrayList<>();
    ArrayList<SpellBuff> binBuffs = new ArrayList<>();

    private int max_seeds = 6;
    private int seeds = 6;
    private int seeds_in_use = 0;
    private ArrayList<Texture> seedPanel = new ArrayList<Texture>();
    private boolean drawSeeds = false;

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
    int max_hp = 100;
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
        hpBar.setVal(this.hp, this.max_hp);
        hpBar.setX(x-20);
        hpBar.setY(y+20);

        mark = new Mark(x+10, y-100);


        seedPanel = new ArrayList<Texture>();
        // Заполняем список текстур семянами
        for (int i=0; i<max_seeds; i++){
            seedPanel.add(new Texture("GUI/Seeds/seedFull.png"));
        }

        setTouchable(Touchable.enabled);
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                giveMarsVictim();
                return true;
            }
        });
        g.addResizable(this);

        SpellBuff a = new ElectricFieldBuff();
        addBuff(a);
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
        hpBar.setX(x-20);
        hpBar.setY(y+Image.getHeight()+20);
    }

    public void giveMarsVictim(){
        System.out.println("Executed Order 66");
        Mars.pickMelon(this);
    }

    public void addBuff(SpellBuff s){
        s.setOwner(this);
        buffs.add(s);
    }

    public void removeBuff(SpellBuff s){
        buffs.remove(s);
    }

    public void addToRemove(SpellBuff a){
        binBuffs.add(a);
    }

    public void runOverBuffs(){

        ArrayList<SpellBuff> temp = buffs;

        for (SpellBuff i : temp) {
            i.use();
        }

        for (SpellBuff i: binBuffs){
            removeBuff(i);
        }
        binBuffs = new ArrayList<>();

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
        if (hp <= 0){
            Mars.defeated(this);
            hpBar.remove();
            remove();
        }

    }

    public void setFightController(FightController c){
        Mars = c;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if (drawSeeds) {
            for (int i = 0; i < max_seeds; i++) {
                batch.draw(seedPanel.get(i), 40 + i * 74, Constants.START_SCREEN_HEIGHT - 70);
            }
        }

        if (shaded){
            batch.setColor((float)0.5, (float)0.5, (float)0.5, (float) 0.5);
        }

        batch.draw(Image, this.x ,this.y);
        for (SpellBuff i: buffs){
            i.draw(batch);
        }
        //System.out.println(seedPanel);
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

    public void setSeedDraw(boolean v){
        drawSeeds = v;
    }

    public void showSeedsToUse(int s){
        int distance = s;
        String path = "GUI/Seeds/seedInUse.png";
        int max_val = seeds;

        if (s == 0){
            path = "GUI/Seeds/seedFull.png";
            distance = seeds_in_use;
        }

        seeds_in_use = s;
        System.out.println("Max val " + max_val);
        if (max_val - distance >= 0) {
            for (int i = max_val - 1; i >= max_val - distance; i--) {
                seedPanel.set(i, new Texture(path));
            }
        }

    }

    public void decreaseSeeds(int v){
        seeds -= v;
        for (int i=max_seeds-1; i>seeds-1; i--){
            seedPanel.set(i, new Texture("GUI/Seeds/seedEmpty.png"));
        }
        System.out.println("Decreased " + seeds);
        if (seeds == 0){
            Mars.setMustChange(true);
        }
    }

    public void refreshSeeds(){
        seeds = max_seeds;
        for (int i=0; i<seeds; i++){
            seedPanel.set(i, new Texture("GUI/Seeds/seedFull.png"));
        }
    }

    public int getSeeds(){ return seeds; }

    public void refreshAll(){
        seeds = max_seeds;
        showSeedsToUse(0);
        hp = max_hp;
        for (int i=0; i<seedPanel.size(); i++){
            seedPanel.set(i, new Texture("GUI/Seeds/seedFull.png"));
        }
    }

    public ArrayList<SpellBuff> getImmunes(){
        ArrayList<SpellBuff> immunes = new ArrayList<SpellBuff>();
        for (SpellBuff i: buffs){
            if (i.getType() == Constants.IMMUNE){
                immunes.add(i);
            }
        }
        return immunes;
    }

    public void receiveSpell(Damage s){
        receiveDamage(s.getDamage());
        for (SpellBuff i: s.getBuffs()){
            addBuff(i);
        }
    }

}
