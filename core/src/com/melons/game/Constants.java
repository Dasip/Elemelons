package com.melons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.melons.game.gui.buttons.EnterButton;
import com.melons.game.interfaces.API;
import com.melons.game.models.MelonData;
import com.melons.game.models.Melongame;
import com.melons.game.models.PostData;
import com.melons.game.models.TokenData;
import com.melons.game.models.ServerTokenResponse;
import com.melons.game.models.UserData;
import com.melons.game.models.UserResponse;
import com.melons.game.skills.ElectricField;
import com.melons.game.skills.Fireball;
import com.melons.game.skills.FlameWave;
import com.melons.game.skills.Lightning;
import com.melons.game.skills.Skill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import jdk.nashorn.api.scripting.JSObject;
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
    public static final String UserToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjp7ImlkIjoiNjEifSwiaWF0IjoxNTg5NTM0NjQ1LCJleHAiOjE1ODk2MjEwNDV9.fOrxMjaw5_DPMrIBSPxklOCmj32zTz2voDqoaFCcVdI";

    public static String ADMIN_TOKEN = "";
    public static final String ADMIN_NAME = "dima@dima.com";
    public static final String ADMIN_PASS = "123456";

    public static boolean logged = false;
    public static boolean started = false;
    public static MelonData currentUser = new MelonData();
    public static String username = "";
    public static ArrayList<Skill> skillbuild = new ArrayList<>();
    public static String skillshidden = "";
    public static boolean skillsToChange = false;

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

        skills.add(new Fireball());
        skills.add(new Lightning());
        skills.add(new FlameWave());
        skills.add(new ElectricField());

        skills.add(new Fireball());
        skills.add(new Lightning());
        skills.add(new FlameWave());
        skills.add(new ElectricField());

        skills.add(new Fireball());
        skills.add(new Lightning());
        skills.add(new FlameWave());
        skills.add(new ElectricField());

        skills.add(new Fireball());
        skills.add(new Lightning());
        skills.add(new FlameWave());
        skills.add(new ElectricField());
        return skills;
    }

    public static Skill GET_SKILL_BY_NAME(String name){
        for (Skill i: GET_SKILLS()){
            if (i.getTextureName().equals(name)) {
                return i;
            }
        }
        return new Fireball();
    }


    public static Skin getSkin(){
        return SKIN;
    }


    public static Retrofit GET_RETROFIT(){
        /*
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }



    public static void GET_TOKEN(){
        Retrofit retrofit = GET_RETROFIT();
        API api = retrofit.create(API.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("username", ADMIN_NAME);
        map.put("password", ADMIN_PASS);

        Call<ServerTokenResponse<TokenData>> tokenCall = api.request_token(API_KEY, map);

        tokenCall.enqueue(new Callback<ServerTokenResponse<TokenData>>() {
            @Override
            public void onResponse(Call<ServerTokenResponse<TokenData>> call, Response<ServerTokenResponse<TokenData>> response) {
                if (response.code() == 200){
                    ADMIN_TOKEN = response.body().getTokenData().getToken();
                }
                else{
                    System.out.println("TOKEN NOT GOT");
                }
            }

            @Override
            public void onFailure(Call<ServerTokenResponse<TokenData>> call, Throwable t) {

            }
        });
    }

    public static String GET_API_KEY(){
        return API_KEY;
    }

    public static void SET_SKILLBUILD(String fullBuild){
        String[] words = fullBuild.split("\\s");
        for (String i: words){
            Skill skill = GET_SKILL_BY_NAME(i);
            skillbuild.add(skill);
        }
        currentUser.setSkillbought(fullBuild);
        currentUser.setSkillbuild(fullBuild);
    }


    public static int LOG_IN(HashMap<String, String> data) {

        Retrofit retrofit = GET_RETROFIT();
        API api = retrofit.create(API.class);

        Call<UserResponse<UserData>> userCall = api.login(Constants.GET_API_KEY(), data);

        int code = 0;
        UserData user = null;

        try {
            Response<UserResponse<UserData>> response = userCall.execute();
            code = response.code();
            if (code == 200) {
                user = response.body().getData();
                System.out.println("LOGGED IN");
                return Constants.MELON_IN(user);
            } else {
                return code;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;}



    public static int MELON_IN(UserData data){
        logged = true;
        username = data.getUsername();

        Retrofit retrofit = GET_RETROFIT();
        API api = retrofit.create(API.class);

        Call<UserResponse<UserData>> melonCall = api.get_melon(Constants.GET_API_KEY(), Constants.username, "nickname");

        melonCall.enqueue(new Callback<UserResponse<UserData>>() {
            @Override
            public void onResponse(Call<UserResponse<UserData>> call, Response<UserResponse<UserData>> response) {
                if (response.code() == 200){
                    Melongame melon = response.body().getData().getMelongame().get(0);
                    currentUser = new MelonData();
                    currentUser.setId(melon.getId());
                    currentUser.setNickname(melon.getNickname());
                    currentUser.setSkillbuild(melon.getSkillbuild());
                    currentUser.setSkillbought(melon.getSkillbought());
                    currentUser.setSeedcurrency(melon.getSeedcurrency());

                    skillsToChange = true;
                    System.out.println("MELON IN");
                }
                else{
                    System.out.println("MELON NOT IN");
                    System.out.println(response.raw());
                }
            }

            @Override
            public void onFailure(Call<UserResponse<UserData>> call, Throwable t) {
                System.out.println("BROKEN WHILE MELON IN");
                System.out.println(t);

            }
        });
    return 1;
    }

    public static void UP_TO_DATE_SKILLS(ArrayList<Skill> skills){
        String names = "";
        for (Skill i: skills){
            if (i == skills.get(skills.size()-1)) {
                names += i.getTextureName();
            }
            else{
                names += i.getTextureName() + " ";
            }
        }
        skillshidden = names;
        SET_SKILLBUILD(skillshidden);
        UPDATE_DB();
    }

    public static void UPDATE_DB(){

        Retrofit retrofit = GET_RETROFIT();
        API api = retrofit.create(API.class);

        HashMap<String, String> data = new HashMap<String, String>();
        data.put("_id", currentUser.getId());
        data.put("nickname", currentUser.getNickname());
        data.put("skillbuild", skillshidden);
        data.put("skillbought", skillshidden);
        data.put("seedcurrency", currentUser.getSeedcurrency());

        Call<UserResponse<PostData>> request = api.update_melon(API_KEY, data);
        request.enqueue(new Callback<UserResponse<PostData>>() {
            @Override
            public void onResponse(Call<UserResponse<PostData>> call, Response<UserResponse<PostData>> response) {
                if (response.code() == 200){
                    System.out.println("UPLOADED");
                }
                else{
                    System.out.println("NOT UPLOADED");
                }
            }

            @Override
            public void onFailure(Call<UserResponse<PostData>> call, Throwable t) {
                System.out.println();
                System.out.println(t);
            }
        });

    }

    public static void REGISTER(final HashMap<String, String> data){

        Retrofit retrofit = Constants.GET_RETROFIT();
        API api = retrofit.create(API.class);

        Call<UserResponse<PostData>> registerResp = api.register_user(Constants.API_KEY, ADMIN_TOKEN, data);
        registerResp.enqueue(new Callback<UserResponse<PostData>>() {
            @Override
            public void onResponse(Call<UserResponse<PostData>> call, Response<UserResponse<PostData>> response) {
                if (response.code() == 200){
                    System.out.println("REGISTERED!");

                    HashMap<String, String> data0 = new HashMap<>();
                    data0.put("username", data.get("username"));
                    data0.put("password", data.get("password"));
                    Constants.CREATE_MELON(data);

                }
                else{
                    System.out.println("NOT REGISTERED");
                    System.out.println(response.raw());
                }
            }

            @Override
            public void onFailure(Call<UserResponse<PostData>> call, Throwable t) {
                System.out.println("BROKEN ON REGISTERING");
                System.out.println(t);
            }
        });

    }


    public static void CREATE_MELON(final HashMap<String, String> data0){
        Retrofit retrofit = GET_RETROFIT();
        API api = retrofit.create(API.class);

        HashMap<String, String> data2 = new HashMap<>();
        data2.put("nickname", data0.get("username"));
        data2.put("skillbuild", "Fireball");
        data2.put("skillbought", "Fireball");
        data2.put("seedcurrency", "100");



        Call<UserResponse<UserData>> melonResp = api.add_melon(API_KEY, data2);
        melonResp.enqueue(new Callback<UserResponse<UserData>>() {
            @Override
            public void onResponse(Call<UserResponse<UserData>> call, Response<UserResponse<UserData>> response) {
                if (response.code() == 200){

                    HashMap<String, String> dataLog = new HashMap<>();
                    dataLog.put("username", data0.get("email"));
                    dataLog.put("password", data0.get("password"));

                    System.out.println("CREATED MELON");
                    LOG_IN(dataLog);
                }
                else{
                    System.out.println("NOT CREATED MELON");
                }
            }

            @Override
            public void onFailure(Call<UserResponse<UserData>> call, Throwable t) {
                System.out.println("BROKEN ON CREATING MELON");
                System.out.println(t);
            }
        });
    }

    public static MelonData GET_USER(){
        return currentUser;
    }

    public static ArrayList<Skill> GENERATE_SKILL_PACK(String build){
        String[] words = build.split("\\s");
        ArrayList<Skill> skills = new ArrayList<>();
        for (String i: words){
            Skill j = GET_SKILL_BY_NAME(i);
            skills.add(j);
        }
        return skills;
    }

}

