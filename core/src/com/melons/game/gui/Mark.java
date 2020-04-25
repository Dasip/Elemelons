package com.melons.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Mark extends Actor {

    private final int FRAME_COLS = 4;
    Animation pointAnimation;
    TextureRegion currentFrame;

    float stateTime;

    public Mark(float x, float y){
        setX(x);
        setY(y);
        Texture pointSheet = new Texture("GUI/Mark.png");
        TextureRegion[][] tmp = TextureRegion.split(pointSheet, pointSheet.getWidth()/FRAME_COLS, pointSheet.getHeight());
        TextureRegion[] pointFrames = new TextureRegion[FRAME_COLS];
        for (int i = 0; i<FRAME_COLS; i++){
            pointFrames[i] = tmp[0][i];
        }
        pointAnimation = new Animation(0.075f, pointFrames);
        stateTime = 0f;
    }

    public void updateStateTime(){
        stateTime += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getCurrentFrame(){
        return (TextureRegion) pointAnimation.getKeyFrame(stateTime, true);
    }

    /*
    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame =
        batch.draw(currentFrame, getX(), getY());
    }*/
}
