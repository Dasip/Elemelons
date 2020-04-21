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
	private MelonMage player;

	private Stage currentStage;
	public DuelScreen duel;
	private Stage stage;

	ArrayList<SizeChangable> toResize = new ArrayList<SizeChangable>();

	@Override
	public void create () {
		/*
		duel = new DuelScreen(this);
		main = new MainScreen(this);

		main.addScreen(lib);
		lib.addScreen(main);

		setScreen(main);*/
		main = new Stage(new FitViewport(960, 540));
		lib = new Stage(new FitViewport(960, 540));

		// !========================! Создаем главное меню !========================! \\
		Panel panel = new Panel(0, 0, "GUI/main_panel.png");
		main.addActor(panel);

		Panel currentRunes = new Panel(panel.getWidth(), 0, "GUI/Panel.png");
		main.addActor(currentRunes);

		Panel custom = new Panel(panel.getWidth(), currentRunes.getHeight(), "GUI/melon_panel.png");
		main.addActor(custom);

		GuiButton gui1 = new GuiButton(200, 200, this);
		main.addActor(gui1);
		gui1.setStage(lib);

		// !========================================================================! \\

		// !========================! Создаем меню лавки знаний !========================! \\
		Panel panel1 = new Panel(0, 0, "GUI/lib_panel.png");
		lib.addActor(panel1);

		GuiButton gui2 = new GuiButton(200, 200, this);
		lib.addActor(gui2);
		gui2.setStage(main);

		// !========================================================================! \\

		currentStage = main;
		Gdx.input.setInputProcessor(currentStage);
	}

	@Override
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		currentStage.draw();
	}

	public void changeStage(Stage s){
		currentStage = s;
		Gdx.input.setInputProcessor(currentStage);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		System.out.println(width + " " + height);
		for (SizeChangable i: toResize){
			i.resize(width, height);
		}
	}

	public void addResizable(SizeChangable s){
		toResize.add(s);
	}
}
