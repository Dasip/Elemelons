package com.melons.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainScreen implements Screen {

    Game game;
    Panel panel;
    Panel custom;
    Panel currentRunes;
    SpriteBatch batch;
    OrthographicCamera cam;
    Vector3 touchPos;
    GuiButton lib;

    public MainScreen(Game g){
        game = g;
        lib = new GuiButton(200, 200, game);
    }

    @Override
    public void show() {
        panel = new Panel(0, 0, "GUI/main_panel.png");

        currentRunes = new Panel(panel.getWidth(), 0, "GUI/Panel.png");
        currentRunes.flip(panel.getWidth(), 0);

        custom = new Panel(panel.getWidth(), currentRunes.getWidth(), "GUI/melon_panel.png");

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1920, 1080);

        batch = new SpriteBatch();

        touchPos = new Vector3();

    }

    public void addScreen(LibraryScreen s){
        lib.setScreen(s);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f	, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        panel.drawAll(batch);
        custom.drawAll(batch);
        currentRunes.drawAll(batch);

        batch.draw(lib.getImage(), lib.getX(), lib.getY());

        batch.end();

        if (Gdx.input.justTouched()) {

            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);
            if (lib.areCoordsRight(touchPos.x, touchPos.y)) {
                lib.execute();
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        panel.disposeImage();
        currentRunes.disposeImage();
        custom.disposeImage();
        lib.getImage().dispose();
    }
}
