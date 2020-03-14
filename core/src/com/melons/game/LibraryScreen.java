package com.melons.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class LibraryScreen implements Screen {

    Game game;
    Panel panel;
    SpriteBatch batch;
    OrthographicCamera cam;
    GuiButton main;
    Vector3 touchPos;

    public LibraryScreen(Game g){
        this.game = g;
        main = new GuiButton(200, 200, game);
    }

    @Override
    public void show() {
        panel = new Panel(0, 0, "GUI/lib_panel.png");

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1920, 1080);

        batch = new SpriteBatch();

        touchPos = new Vector3();
    }

    public void addScreen(MainScreen s){
        main.setScreen(s);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f	, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        panel.drawAll(batch);

        batch.draw(main.getImage(), main.getX(), main.getY());

        batch.end();

        if (Gdx.input.justTouched()) {

            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);
            if (main.areCoordsRight(touchPos.x, touchPos.y)) {
                main.execute();
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
        main.getImage().dispose();
    }
}
