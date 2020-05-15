package com.melons.game.gui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.melons.game.Constants;
import com.melons.game.MelonCycle;
import com.melons.game.gui.MelonTextField;
import com.melons.game.gui.buttons.GuiButton;
import com.melons.game.interfaces.API;
import com.melons.game.models.PostData;
import com.melons.game.models.UserData;
import com.melons.game.models.UserResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EnterButton extends GuiButton {

    protected String mode;
    protected MelonTextField login;
    protected MelonTextField password;
    protected MelonTextField email;

    public EnterButton self = this;


    public EnterButton(float x, float y, MelonCycle g, String text, String mode, Stage owner) {
        super(x, y, g, text, owner);
        this.mode = mode;
    }

    public void setFields(MelonTextField l, MelonTextField p){
        login = l;
        password = p;
    }

    public void setFields(MelonTextField l, MelonTextField p, MelonTextField e){
        setFields(l, p);
        email = e;
    }

    public void setMode(String m){
        mode = m;
    }

    @Override
    public void execute() {
        if (this.mode == "login") {
            //System.out.println(Constants.GET_TOKEN());

            HashMap<String, String> user = new HashMap<>();
            user.put("username", login.getText());
            user.put("password", password.getText());

            Constants.LOG_IN(user);

        } else if (mode == "register") {


            HashMap<String, String> data = new HashMap<>();
            data.put("username", login.getText());
            data.put("password", password.getText());
            data.put("email", email.getText());
            data.put("full_name", login.getText());

            Constants.REGISTER(data);

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


