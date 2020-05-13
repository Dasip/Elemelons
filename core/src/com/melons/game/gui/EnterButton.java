package com.melons.game.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.melons.game.Constants;
import com.melons.game.MelonCycle;
import com.melons.game.interfaces.API;
import com.melons.game.models.TokenGetter;

import java.util.HashMap;

import retrofit.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnterButton extends GuiButton {

    protected String mode;
    protected TextField login;
    protected TextField password;

    public EnterButton(float x, float y, MelonCycle g, String text, String mode, Stage owner) {
        super(x, y, g, text, owner);
        this.mode = mode;
    }

    public void setFields(TextField l, TextField p){
        login = l;
        password = p;
    }

    @Override
    public void execute() {
        if (this.mode == "login"){
            String username = login.getText();
            String passWord = password.getText();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            API api = retrofit.create(API.class);

            HashMap<String, String> data = new HashMap<>();
            data.put("username", username);
            data.put("password", passWord);

            Call<TokenGetter> token = api.request_token(Constants.API_KEY, data);

        }
    }
}
