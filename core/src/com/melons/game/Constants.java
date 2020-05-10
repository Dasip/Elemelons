package com.melons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.melons.game.skills.ElectricField;
import com.melons.game.skills.Fireball;
import com.melons.game.skills.FlameWave;
import com.melons.game.skills.Lightning;
import com.melons.game.skills.Skill;

import java.util.ArrayList;


public class Constants {

    public static final BitmapFont MELON_FONT = new BitmapFont(Gdx.files.internal("Fonts/MelonFont32/melon32.fnt"));
    public static final Skin SKIN = new Skin(Gdx.files.internal("UI/uiskin.json"));
    public static final int START_SCREEN_WIDTH = 960;
    public static final int START_SCREEN_HEIGHT = 540;

    public static final int MAX_SKILLS = 3;

    public static final String IMMUNE = "immune";
    public static final String CURSE = "curse";
    public static final String BLESS = "bless";

    public static final String ELECTRIC = "electric";
    public static final String FIRE = "fire";
    public static final String WATER = "water";
    public static final String EARTH = "earth";
    public static final String AIR = "air";


    public static final String Username = "Dasip";
    public static final String password = "qwerty";


    public static ArrayList<Skill> GET_SKILLS(){
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(new Fireball());
        skills.add(new Lightning());
        skills.add(new FlameWave());
        skills.add(new ElectricField());
        return skills;
    }

    public static Skill GET_SKILL_BY_NAME(String name){
        for (Skill i: GET_SKILLS()){
            if (i.getTextureName() == name) {
                return i;
            }
        }
        return new Fireball();
    }


    public static Skin getSkin(){
        return SKIN;
    }



}
