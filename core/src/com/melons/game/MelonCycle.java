package com.melons.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.melons.game.controllers.FightController;
import com.melons.game.controllers.LibController;
import com.melons.game.controllers.SimpleAI;
import com.melons.game.gui.MelonTextField;
import com.melons.game.gui.buttons.RuneButton;
import com.melons.game.gui.containers.CurrentContainer;
import com.melons.game.gui.containers.DescContainer;
import com.melons.game.gui.buttons.EndTurnButton;
import com.melons.game.gui.buttons.EnterButton;
import com.melons.game.gui.buttons.GuiButton;
import com.melons.game.gui.HealthBar;
import com.melons.game.gui.buttons.PageButton;
import com.melons.game.gui.containers.Panel;
import com.melons.game.gui.containers.RuneContainer;
import com.melons.game.interfaces.SizeChangable;
import com.melons.game.skills.Skill;

import java.util.ArrayList;


public class MelonCycle extends Game {

	private Stage main;
	private Stage lib;
	private Stage fight;
	private Stage login;
	private Stage register;

	private StretchViewport viewport = new StretchViewport(Constants.START_SCREEN_WIDTH, Constants.START_SCREEN_HEIGHT);

	private MelonMage player;
	private MelonMage enemy;

	private ArrayList<Panel> fight_panels = new ArrayList<Panel>();

	private Stage currentStage;
	private FightController Mars;
	private LibController Minerva;

	private DescContainer desc;
	private CurrentContainer curr;

	private MelonTextField loginField;
	private MelonTextField passwordField;

	private MelonTextField newLoginField;
	private MelonTextField newPasswordField;
	private MelonTextField newEmail;


	ArrayList<SizeChangable> toResize = new ArrayList<com.melons.game.interfaces.SizeChangable>();

	@Override
	public void create () {

		Constants.GET_TOKEN();

		main = new Stage(viewport); //  Сцена главного меню
		fight = new Stage(viewport);  // Сцена боя
		lib = new Stage(viewport);  // Сцена лавки знаний
		login = new Stage(viewport);  // Сцена окна авторизации
		register = new Stage(viewport);   // Сцена окна регистрации

		Minerva = new LibController(this);

		// !========================! Создаем входное меню !========================! \\

		Panel panellog = new Panel(0, 0, "GUI/Panels/lib_panel.png");
		login.addActor(panellog);

		loginField = new MelonTextField("", Constants.getSkin(), "Email", login, this);
		login.addActor(loginField);
		loginField.setMelonSize(Constants.START_SCREEN_WIDTH/4-100, Constants.START_SCREEN_HEIGHT-180,
				250, 60);

		passwordField = new MelonTextField("", Constants.getSkin(), "Пароль", login, this);
		login.addActor(passwordField);
		passwordField.setMelonSize(Constants.START_SCREEN_WIDTH/2+50, Constants.START_SCREEN_HEIGHT-180,
				250, 60);
		passwordField.setPasswordCharacter('*');
		passwordField.setPasswordMode(true);

		EnterButton button23 = new EnterButton(Constants.START_SCREEN_WIDTH/4+100, Constants.START_SCREEN_HEIGHT/3+40, this, "Войти", "login", login);
        login.addActor(button23);
        button23.setStage(main);
        button23.setFields(loginField, passwordField);

        GuiButton butt333 = new GuiButton(Constants.START_SCREEN_WIDTH/4+100, Constants.START_SCREEN_HEIGHT/5, this, "Новый аккаунт", login);
        login.addActor(butt333);
        butt333.setStage(register);

        /*     Кнопка для перехода в игру БЕЗ авторизации
        					Заглушена
		butt333 = new GuiButton(Constants.START_SCREEN_WIDTH/4+100, Constants.START_SCREEN_HEIGHT/40, this, "В игру", login);
		login.addActor(butt333);
		butt333.setStage(main);
		*/

        // !========================! Создаем меню регистрации !========================! \\

        panellog = new Panel(0, 0, "GUI/Panels/lib_panel.png");
        register.addActor(panellog);

        newLoginField = new MelonTextField("", Constants.getSkin(),"Ник", register, this);
        register.addActor(newLoginField);
        newLoginField.setMelonSize(Constants.START_SCREEN_WIDTH/4-100, Constants.START_SCREEN_HEIGHT-300,
				250, 60);

        newPasswordField = new MelonTextField("", Constants.getSkin(),"Пароль", register, this);
        register.addActor(newPasswordField);
        newPasswordField.setMelonSize(Constants.START_SCREEN_WIDTH/2+50, Constants.START_SCREEN_HEIGHT-180,
				250, 60);
        newPasswordField.setPasswordCharacter('*');
        newPasswordField.setPasswordMode(true);

        newEmail = new MelonTextField("", Constants.getSkin(), "Email", register, this);
        register.addActor(newEmail);
        newEmail.setMelonSize(Constants.START_SCREEN_WIDTH/4-100, Constants.START_SCREEN_HEIGHT-180,
				250, 60);

        button23 = new EnterButton(Constants.START_SCREEN_WIDTH/4+25, Constants.START_SCREEN_HEIGHT/3-30, this, "Зарегистрироваться", "register", register);
		register.addActor(button23);
		button23.setFields(newLoginField, newPasswordField, newEmail);
		button23.setStage(main);

        GuiButton button233 = new GuiButton(Constants.START_SCREEN_WIDTH/4+100, Constants.START_SCREEN_HEIGHT/7-30, this, "Отмена", register);
        register.addActor(button233);
        button233.setStage(login);

		// !========================! Создаем главное меню !========================! \\
        Panel panel1 = new Panel(0, 0, "GUI/Panels/lib_panel.png");
        main.addActor(panel1);

		GuiButton gui1 = new GuiButton(100, 400, this, "В бой", main);
		main.addActor(gui1);
		gui1.setStage(fight);

		gui1 = new GuiButton(100, 200, this, "Лавка", main);
		main.addActor(gui1);
		gui1.setStage(lib);


		// !========================================================================! \\

		// !========================! Создаем меню лавки знаний !========================! \\

		panel1 = new Panel(0, 0, "GUI/Panels/Panel.png");
		lib.addActor(panel1);

		Panel panel4 = new Panel(panel1.getWidth()+1, 0, "GUI/Panels/melon_panel.png");
		lib.addActor(panel4);

		GuiButton gui2 = new GuiButton(Constants.START_SCREEN_WIDTH-220, 10, this, "Меню", lib);
		lib.addActor(gui2);
		gui2.setStage(main);

		RuneContainer cont = new RuneContainer(panel1.getWidth()+1, 0, 525, Constants.START_SCREEN_HEIGHT, lib, this);
		lib.addActor(cont);
		cont.setRunes(Constants.GET_SKILLS());

		PageButton pb1 = new PageButton(panel1.getWidth()+100, 20, this, "GUI/Buttons/left_button.png", cont, -1);
		lib.addActor(pb1);

		PageButton pb2 = new PageButton(panel1.getWidth()+400, 20, this, "GUI/Buttons/right_button.png", cont, 1);
		lib.addActor(pb2);

		desc = new DescContainer(675, 0, 525, Constants.START_SCREEN_HEIGHT, lib, this);
		lib.addActor(desc);

		curr = new CurrentContainer(0, 0, 150, Constants.START_SCREEN_HEIGHT, lib, this);
		lib.addActor(curr);

		Minerva.addDesc(desc);
		Minerva.addCurr(curr);

		// !========================================================================! \\

		// !========================! Создаем поле боя !========================! \\

		Panel panel2 = new Panel(0, 0, "GUI/Panels/Panel2.png");
		fight.addActor(panel2);

		Panel panel3 = new Panel(0, panel2.getHeight(), "GUI/Panels/melon_panel2.png");
		fight.addActor(panel3);

		fight_panels.add(panel2);
		fight_panels.add(panel3);

		HealthBar hp1 = new HealthBar(140, 300);
		player = new MelonMage(0, 0, hp1, this);


		// !========================================================================! \\

		currentStage = login;
		Gdx.input.setInputProcessor(currentStage);

        // !========================! Учим персонажа навыкам !========================! \\
		player.setUser(true);
        //player.addSkill(new Fireball(this));
        //player.addSkill(new Lightning(this));
        //player.addSkill(new FlameWave(this));
        //player.addSkill(new ElectricField(this));
        // !========================================================================! \\
	}

	public void updatePlayerSkills(){
		for (Skill i: Constants.GENERATE_SKILL_PACK(Constants.GET_USER().getSkillbuild())){
			player.preloadSkill(i);
			Minerva.updateCurrent();
		}
	}

	@Override
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		currentStage.draw();
		if (Constants.skillsToChange){
			updatePlayerSkills();
			Constants.skillsToChange = false;
		}
	}

	public Stage getLib() {
		return lib;
	}

	public Stage getFight() {
		return fight;
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
		player.refreshAll();

		enemy = generateEnemy();
		currentStage.addActor(enemy);
		currentStage.addActor(enemy.hpBar);
		enemy.setCoords(700, 200);

		Mars = createMars();

		EndTurnButton end = new EndTurnButton(700, 410, this, Mars);
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

    public void openLib(){
	    Minerva.updateCurrent();

	}

	public MelonMage getPlayer(){
		return player;
	}

	public ArrayList<RuneButton> getPlayerRunes(){
		ArrayList<RuneButton> runes = new ArrayList<>();
		for (Skill i: player.getSkills()){
			RuneButton r = new RuneButton(0, 0, this, i);
			runes.add(r);
		}
		return runes;
	}

	public void changeStage(Stage s){
		currentStage = s;
		Gdx.input.setInputProcessor(currentStage);
		if (s == fight){
		    organizeFight();
        }
		else if(s == lib){
			openLib();
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		System.out.println(width + " " + height);
		int counter = 0;
		for (com.melons.game.interfaces.SizeChangable i: toResize){
			i.resize(width, height);
			counter++;
		}

	}

	public com.melons.game.controllers.FightController createMars(){
		ArrayList<MelonMage> rivals = new ArrayList<>();
		rivals.add(player);
		rivals.add(enemy);
		com.melons.game.controllers.FightController M = new FightController(rivals, player, this, fight);
		return M;
	}

	public void addResizable(SizeChangable s){
		toResize.add(s);
	}

	public MelonMage generateEnemy(){
		HealthBar hp = new HealthBar(300, 300);
		fight.addActor(hp);
		MelonMage enemy = new MelonMage(100, 100, hp, this);

		ArrayList<Skill> skills = Constants.GET_SKILLS();
		while (enemy.getSkills().size() < Constants.MAX_SKILLS){
			int random_index = (int) (Math.random() * skills.size());
			enemy.addSkill(skills.get(random_index));
			System.out.println("============");
			System.out.println(random_index);
		}
		System.out.println(enemy.getSkills());
		enemy.setGuide(new SimpleAI());
		return enemy;
	}

	public Stage getMain(){ return main; }

	public LibController getMinerva() {
		return Minerva;
	}
}
