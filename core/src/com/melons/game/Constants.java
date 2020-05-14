package com.melons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.melons.game.interfaces.API;
import com.melons.game.models.TokenData;
import com.melons.game.models.ServerTokenResponse;
import com.melons.game.skills.ElectricField;
import com.melons.game.skills.Fireball;
import com.melons.game.skills.FlameWave;
import com.melons.game.skills.Lightning;
import com.melons.game.skills.Skill;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Constants {

    // !===================! Переменные разметки !===================! \\

    public static final BitmapFont MELON_FONT = new BitmapFont(Gdx.files.internal("Fonts/MelonFont32/melon32.fnt"));
    public static final Skin SKIN = new Skin(Gdx.files.internal("UI/uiskin.json"));
    public static final int START_SCREEN_WIDTH = 960;
    public static final int START_SCREEN_HEIGHT = 540;

    // !===================! Переменные серверной части !===================! \\

    public static final String BASE_URL = "http://195.19.44.146:89/";
    public static final String API_KEY = "2295262834A821A2FD76A6B3C6495E02";

    public static String TOKEN = "";

    public static final String ADMIN_NAME = "dima@dima.com";
    public static final String ADMIN_PASS = "123456";

    // !===================! Переменные механик игры !===================! \\

    public static final int MAX_SKILLS = 3;

    public static final String IMMUNE = "immune";
    public static final String CURSE = "curse";
    public static final String BLESS = "bless";

    public static final String ELECTRIC = "electric";
    public static final String FIRE = "fire";
    public static final String WATER = "water";
    public static final String EARTH = "earth";
    public static final String AIR = "air";


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

    public static Retrofit GET_RETROFIT(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static String GET_TOKEN(){
        Retrofit retrofit = GET_RETROFIT();
        API api = retrofit.create(API.class);

        HashMap<String, String> data = new HashMap<>();
        data.put("username", ADMIN_NAME);
        data.put("password", ADMIN_PASS);

        Call<ServerTokenResponse<TokenData>> tokenResponse = api.request_token(Constants.API_KEY, data);

        tokenResponse.enqueue(new Callback<ServerTokenResponse<TokenData>>() {
            @Override
            public void onResponse(Call<ServerTokenResponse<TokenData>> call, Response<ServerTokenResponse<TokenData>> response) {
                if (response.code() == 200){
                    ServerTokenResponse<TokenData> tokenData = response.body();
                    TOKEN = tokenData.getTokenData().getToken();
                }
            }

            @Override
            public void onFailure(Call<ServerTokenResponse<TokenData>> call, Throwable t) {
                System.out.println("Chto-to slomalos");
            }
        }
        );

        return TOKEN;
    }

}
