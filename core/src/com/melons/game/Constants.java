package com.melons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class Constants {

    public static final BitmapFont MELON_FONT = new BitmapFont(Gdx.files.internal("Fonts/MelonFont32/melon32.fnt"));
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

}
