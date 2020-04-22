package com.melons.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class GuiButton extends Actor implements SizeChangable{

    private static Texture Image;

    float default_x;
    float default_y;

    float default_width ;
    float default_height;

    float start_screen_width = 0;
    float start_screen_height = 0;

    private float x;
    private float y;

    private MelonCycle game;
    Stage toChange;

    GuiButton(float x, float y, final MelonCycle g, String path){
        toChange = null;
        this.x = x;
        this.y = y;
        this.game = g;
        Image = new Texture(path);

        default_x = x;
        default_y = y;
        default_width = Image.getWidth();
        default_height = Image.getWidth();

        setX(x);
        setY(y);
        setBounds(x, y, Image.getWidth(), Image.getHeight());
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

    public float getX(){
            return x;
        }

    public float getY() {
        return y;
    }

    public Texture getImage(){
        return Image;
    }

    public void setStage(Stage s){
        toChange = s;
    }

    public void execute(){
        game.changeStage(toChange);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Image, this.x, this.y);
    }

    @Override
    public void resize(int nw, int nh) {
        if (start_screen_height == 0 && start_screen_width == 0){
            start_screen_height = nh;
            start_screen_width = nw;
        }
        else{
            float bx = default_x / start_screen_width * nw;
            float by = default_y / start_screen_height * nh;
            float width = default_width / start_screen_width * nw;
            float height = default_height / start_screen_height * nh;
            System.out.println(width + " " + height);
            setBounds(bx, by, width, height);
        }
    }
}

