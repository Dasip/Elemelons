package com.melons.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.melons.game.gui.EndTurnButton;
import com.melons.game.gui.GuiButton;
import com.melons.game.gui.HealthBar;
import com.melons.game.gui.Panel;
import com.melons.game.interfaces.SizeChangable;
import com.melons.game.interfaces.SkillButton;
import com.melons.game.skills.Fireball;
import com.melons.game.skills.FlameWave;
import com.melons.game.skills.Lightning;
import com.melons.game.skills.Skill;

import java.util.ArrayList;

public class MelonCycle extends Game {

	private Stage main;
	private Stage lib;
	private Stage fight;
	private MelonMage player;
	private MelonMage enemy;

	private ArrayList<Panel> fight_panels = new ArrayList<Panel>();

	private Stage currentStage;
	private FightController Mars;

	ArrayList<com.melons.game.interfaces.SizeChangable> toResize = new ArrayList<com.melons.game.interfaces.SizeChangable>();

	@Override
	public void create () {

		main = new Stage(new StretchViewport(960, 540));
		fight = new Stage(new StretchViewport(960, 540));
		//lib = new Stage(new FitViewport(960, 540));



		// !========================! Создаем главное меню !========================! \\
        com.melons.game.gui.Panel panel1 = new com.melons.game.gui.Panel(0, 0, "GUI/Panels/lib_panel.png");
        main.addActor(panel1);

        /*
		Panel panel = new Panel(0, 0, "GUI/main_panel.png");
		main.addActor(panel);

		Panel currentRunes = new Panel(panel.getWidth(), 0, "GUI/Panel.png");
		main.addActor(currentRunes);

		Panel custom = new Panel(panel.getWidth(), currentRunes.getHeight(), "GUI/melon_panel.png");
		main.addActor(custom);*/

		com.melons.game.gui.GuiButton gui1 = new GuiButton(100, 400, this, "GUI/Buttons/fight_button.png");
		main.addActor(gui1);
		gui1.setStage(fight);

		// !========================================================================! \\

		// !========================! Создаем меню лавки знаний !========================! \\
		/*

		GuiButton gui2 = new GuiButton(200, 200, this);
		lib.addActor(gui2);
		gui2.setStage(main);*/

		// !========================================================================! \\

		// !========================! Создаем поле боя !========================! \\

		com.melons.game.gui.Panel panel2 = new com.melons.game.gui.Panel(0, 0, "GUI/Panels/Panel2.png");
		fight.addActor(panel2);

		com.melons.game.gui.Panel panel3 = new Panel(0, panel2.getHeight(), "GUI/Panels/melon_panel2.png");
		fight.addActor(panel3);

		fight_panels.add(panel2);
		fight_panels.add(panel3);

		HealthBar hp1 = new HealthBar(140, 300);
		player = new MelonMage(0, 0, hp1, this);


		// !========================================================================! \\

		currentStage = main;
		Gdx.input.setInputProcessor(currentStage);

        // !========================! Учим персонажа навыкам !========================! \\
        player.addSkill(new Fireball(this));
       // player.addSkill(new Lightning(this));
        player.addSkill(new FlameWave(this));
        // !========================================================================! \\
	}

	@Override
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		currentStage.draw();
	}

	public void organizeFight(){
		System.out.println(currentStage.getActors());
		while (currentStage.getActors().size != 0){
			for (Actor i: currentStage.getActors()){
				i.remove();
			}
		}
		System.out.println(currentStage.getActors());

		for (Actor i: fight_panels){
			currentStage.addActor(i);
		}

		currentStage.addActor(player);
		currentStage.addActor(player.hpBar);
		player.setCoords(150, 200);

		enemy = generateEnemy();
		currentStage.addActor(enemy);
		currentStage.addActor(enemy.hpBar);
		enemy.setCoords(700, 200);

		Mars = createMars();

		EndTurnButton end = new EndTurnButton(700, 410, this, "GUI/Buttons/turn_button.png", Mars);
		currentStage.addActor(end);

	    float x_step = 75;
	    float x_delim = 100;
	    int n = 0;
	    float y_start = 20;
	    for (Skill i: player.getSkills()){
			currentStage.addActor(i);
	        i.setX(x_step * (n+1) + x_delim * n);
	        i.setY(y_start);
	        i.setController(Mars);
	        i.setOwner(player);
	        n++;
        }
    }

	public void changeStage(Stage s){
		currentStage = s;
		Gdx.input.setInputProcessor(currentStage);
		if (s == fight){
		    organizeFight();
        }
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		System.out.println(width + " " + height);
		for (com.melons.game.interfaces.SizeChangable i: toResize){
			i.resize(width, height);
		}
	}

	public FightController createMars(){
		ArrayList<MelonMage> rivals = new ArrayList<>();
		rivals.add(player);
		rivals.add(enemy);
		FightController M = new FightController(rivals, player, this, fight);
		return M;
	}

	public void addResizable(SizeChangable s){
		toResize.add(s);
	}

	public MelonMage generateEnemy(){
		HealthBar hp = new HealthBar(300, 300);
		fight.addActor(hp);
		MelonMage enemy = new MelonMage(100, 100, hp, this);
		return enemy;
	}

	public Stage getMain(){ return main; }
}
