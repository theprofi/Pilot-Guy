package com.elkadakh.pilotguy;

import screens.Howto;
import screens.Level1;
import screens.Level10;
import screens.Level2;
import screens.Level6;
import screens.Level4;
import screens.Level5;
import screens.Level3;
import screens.Level7;
import screens.Level8;
import screens.Level9;
import screens.LevelBase;
import screens.Menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.elkadakh.others.ActivityRequestHandler;
import com.elkadakh.others.AssetLoader;

public class GameMainClass extends Game {
	
	public static final int SOURCE_WIDTH = 1280; 
	public static final int SOURCE_HEIGHT = 720; 
	public static int phoneWidth, phoneHeight;
	public static int gameWidth, gameHeight;
	public static boolean muteSound;
	private static Menu menu;
	private static LevelBase howto;
	private static LevelBase level1;
	private static LevelBase level2;
	private static LevelBase level3;
	private static LevelBase level4;
	private static LevelBase level5;
	private static LevelBase level6;
	private static LevelBase level7;
	private static LevelBase level8;
	private static LevelBase level9;
	private static LevelBase level10;
	private static Screen curScreen;
	private static ActivityRequestHandler myRequestHandler;

	public GameMainClass (ActivityRequestHandler handler) {
		myRequestHandler = handler;
	}
	@Override
	public void create () {
		muteSound = false;
		//Load all the textures
		AssetLoader.load();
		
		//Get phones resolution
		phoneWidth = Gdx.graphics.getWidth();
		phoneHeight = Gdx.graphics.getHeight();
		
		//Get the ratios of phone to game resolutions
		float ratioW = (float) phoneWidth / SOURCE_WIDTH;
		float ratioH = (float) phoneHeight / SOURCE_HEIGHT;
		
		//Take lowest to determine the game resolution
		float lowestRatio = (ratioW < ratioH) ? ratioW : ratioH;
		
		//Set game resolution
		gameWidth = (int) (lowestRatio*SOURCE_WIDTH);
		gameHeight = (int) (lowestRatio*SOURCE_HEIGHT);
		
		menu = new Menu();
		howto = new Howto();
		level1 = new Level1();
		level2 = new Level2();
		level3 = new Level3();
		level4 = new Level4();
		level5 = new Level5();
		level6 = new Level6();
		level7 = new Level7();
		level8 = new Level8();
		level9 = new Level9();
		level10 = new Level10();
		curScreen = menu;
	}

	@Override
	public void render () {
		super.render();
		setScreen(curScreen);	
	}
	
	public static void setCurScreen (int screenNum){
		if(screenNum == 1)
			curScreen = level1;
		else if(screenNum == 2)
			curScreen = level2;
		else if(screenNum == 3)
			curScreen = level3;
		else if(screenNum == 4)
			curScreen = level4;
		else if(screenNum == 5)
			curScreen = level5;
		else if(screenNum == 6)
			curScreen = level6;
		else if(screenNum == 7)
			curScreen = level7;
		else if(screenNum == 8)
			curScreen = level8;
		else if(screenNum == 9)
			curScreen = level9;
		else if(screenNum == 10)
			curScreen = level10;
		else if (screenNum == 0)
			curScreen = menu;
		else if (screenNum == -1)
			curScreen = howto;
	}
	
	public static void ShowAds (){
		myRequestHandler.showAds(true);
	}
	public static void HideAds (){
		myRequestHandler.showAds(false);
	}
}
