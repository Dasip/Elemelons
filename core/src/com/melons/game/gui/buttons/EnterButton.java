package com.melons.game.gui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.melons.game.Constants;
import com.melons.game.MelonCycle;
import com.melons.game.gui.buttons.GuiButton;
import com.melons.game.interfaces.API;
import com.melons.game.models.UserData;
import com.melons.game.models.UserResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
            //System.out.println(Constants.GET_TOKEN());

            Retrofit retrofit = Constants.GET_RETROFIT();
            API api = retrofit.create(API.class);

            HashMap<String, String> user = new HashMap<>();
            user.put("username", login.getText());
            user.put("password", password.getText());

            Call<UserResponse<UserData>> userCall = api.login(Constants.GET_API_KEY(), user);
            System.out.println(userCall.request().body());

            userCall.enqueue(new Callback<UserResponse<UserData>>() {
                @Override
                public void onResponse(Call<UserResponse<UserData>> call, Response<UserResponse<UserData>> response) {
                    if (response.code() == 200){
                        UserResponse<UserData> userResponse = response.body();

                        System.out.println(userResponse.getData().getUsername());

                        Constants.LOG_IN(userResponse.getData(), game);

                    }
                    else{
                        System.out.println("pizdec");
                        System.out.println(response.raw());
                    }
                }

                @Override
                public void onFailure(Call<UserResponse<UserData>> call, Throwable t) {
                    System.out.println("BROKEN");
                }
            });


            }
        }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (Constants.logged && !Constants.started){
            game.changeStage(toChange);
            Constants.started = true;
        }
    }
}


