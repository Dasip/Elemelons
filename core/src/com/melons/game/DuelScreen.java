package com.melons.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class DuelScreen implements Screen {
    OrthographicCamera cam;
    SpriteBatch batch;
    Texture RuneImage;
    Music Sing;
    MelonMage Melon;
    Vector3 touchPos;
    Array<Rectangle> runes;
    Tilemap tilemap;
    Panel panel;
    Game game;

    public DuelScreen(Game g){
        game = g;
    }

    @Override
    public void show() {
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1920, 1080);
        batch = new SpriteBatch();
        RuneImage = new Texture("Cold_ring.png");

        Sing = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Singularity.mp3"));
        Sing.setLooping(true);
        Sing.play();

        Melon = new MelonMage(800/2, 48);

        ArrayList<String> r = new ArrayList<String>();
        r.add("Fireball");
        r.add("Fireball");
        r.add("Fireball");
        r.add("Fireball");
        panel = new Panel(0, 0, "GUI/Panel.png");
        panel.setRunePanel(r);

        tilemap = new Tilemap(301, 0,4, 6);

        touchPos = new Vector3();

        runes = new Array<Rectangle>();
        for (int i=0; i<12; i++) spawnRune();
    }

    private void spawnRune(){
        Rectangle rune = new Rectangle();
        rune.x = MathUtils.random(0, 800 - 32);
        rune.y = MathUtils.random(0, 800 - 32);
        rune.width = 32;
        rune.height = 32;
        runes.add(rune);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f	, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        for (Tile tile: tilemap.getTiles()){
            batch.draw(tile.getImage(), tile.getX(), tile.getY());
        }
        for (Rectangle rune: runes){
            batch.draw(RuneImage, rune.x, rune.y);
        }
        batch.draw(Melon.getImage(), Melon.getX(), Melon.getY());
        panel.drawAll(batch);
        batch.end();

        if (Gdx.input.isTouched()) {

            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);
            if (tilemap.areCoordsRight(touchPos.x, touchPos.y)) {
                Tile s = tilemap.getTileByCoords(touchPos.x, touchPos.y);
                Melon.setCoords(s.getX(), s.getY());
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
        tilemap.dispose();
        panel.disposeImage();
        Melon.disposeImage();
        Sing.dispose();
    }
}
