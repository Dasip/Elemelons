package com.melons.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

public class MelonCycle extends Game {

	private Stage main;
	private Stage lib;

	public DuelScreen duel;
	//public MainScreen main;
	//public LibraryScreen lib;
	private Stage stage;

	@Override
	public void create () {
		/*
		duel = new DuelScreen(this);
		main = new MainScreen(this);
		lib = new LibraryScreen(this);

		main.addScreen(lib);
		lib.addScreen(main);

		setScreen(main);*/

		// !========================! Создаем главное меню !========================! \\
		main = new Stage(new FitViewport(1920, 1080));
		Panel panel = new Panel(0, 0, "GUI/main_panel.png");
		main.addActor(panel);

		Panel currentRunes = new Panel(panel.getWidth(), 0, "GUI/Panel.png");
		currentRunes.flip(panel.getWidth(), 0);
		main.addActor(currentRunes);

		Panel custom = new Panel(panel.getWidth(), currentRunes.getWidth(), "GUI/melon_panel.png");
		main.addActor(custom);

		GuiButton gui1 = new GuiButton(200, 700, this);
		main.addActor(gui1);
		// !========================================================================! \\

		// !========================! Создаем меню лавки знаний !========================! \\
		lib = new Stage(new FitViewport(1920, 1080));

		Panel panel1 = new Panel(0, 0, "GUI/lib_panel.png");
		lib.addActor(panel1);

		GuiButton gui2 = new GuiButton(200, 200, this);
		lib.addActor(gui2);


		Gdx.input.setInputProcessor(lib);
	}

	@Override
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		lib.draw();
	}

}
