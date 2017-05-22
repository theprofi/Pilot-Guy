package screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.elkadakh.others.AssetLoader;
import com.elkadakh.others.Helicopter;
import com.elkadakh.pilotguy.GameMainClass;

public class Menu implements Screen {

	protected int bgNum;// 1 = Light, 2 = Dark
	protected int phoneWidth, phoneHeight;
	private int screenSize;
	protected SpriteBatch drawToScreen;
	private static final int SMALL_BG_WIDTH = 1920, SMALL_BG_HEIGHT_TOP = 249, SMALL_BG_HEIGHT = 1080;
	private static final int LARGE_BG_WIDTH = 4096, LARGE_BG_HEIGHT_TOP = 652, LARGE_BG_HEIGHT = 2160;
	int startPointTop, startPointBottom;
    protected Helicopter helicopter; 
    private float runTime, uiHiehgt;
    float screenBGmultiplier;
    float uDistanceMax, uDistanceCurrent;
    boolean uDirUp;
    static boolean okTouch;
    float betweenBeepsMinTime;
    public static boolean chooseLv;
    public static Preferences prefs;
	int yMinus;
	
	public Menu(){
		prefs = Gdx.app.getPreferences("PGsavegame");
		chooseLv = false;
		okTouch = true;
		betweenBeepsMinTime = 0;
		drawToScreen = new SpriteBatch();
		bgNum = 1;//randInt(1,2); //Choose a background
		phoneWidth = GameMainClass.phoneWidth; //Get phones resolutions
		phoneHeight = GameMainClass.phoneHeight;	
		AssetLoader.subText.setColor(Color.RED);
		AssetLoader.subText.setScale(1f, 1f);
		screenBGmultiplier = 1;
		startPointBottom = 0;
		startPointTop = 0;
		runTime = 0;
		uDistanceMax = (35 * ((float)phoneHeight/720));
		uDistanceCurrent = 0;
		uDirUp = true;
		if(phoneWidth <= 2560)
			screenSize = 1;
		else if (phoneWidth > 2560 && phoneWidth <= 4096)
			screenSize = 2;
		else if (phoneWidth > 4096)
			screenSize = 3;
		
		if(screenSize == 1){
			if(SMALL_BG_WIDTH <= phoneWidth) //Screen's width is bigger than 1920px
				screenBGmultiplier = (float) phoneWidth / SMALL_BG_WIDTH;
			else{
				startPointTop = randInt(0, SMALL_BG_WIDTH - phoneWidth); 
				startPointBottom = randInt(0, SMALL_BG_WIDTH - phoneWidth); 
			}
		}
		else if (screenSize == 2){
			startPointTop = randInt(0, LARGE_BG_WIDTH - phoneWidth); 
			startPointBottom = randInt(0, LARGE_BG_WIDTH - phoneWidth); 
		}
		else if (screenSize == 3){
			if(LARGE_BG_WIDTH <= phoneWidth) //Screen's width is bigger than 4096px
				screenBGmultiplier = (float) phoneWidth / LARGE_BG_WIDTH;
			else{
				startPointTop = randInt(0, LARGE_BG_WIDTH - phoneWidth); 
				startPointBottom = randInt(0, LARGE_BG_WIDTH - phoneWidth); 
			}
		}
		
		/*if(phoneWidth < 1119)
			uiHiehgt = 83; //114
		else if(phoneWidth >= 1119 &&  phoneWidth < 1900)
			uiHiehgt = 162; //162//114
		else if(phoneWidth >= 1900 &&  phoneWidth < 3800)
			uiHiehgt = 162; //268
		else if(phoneWidth >= 3800)
			uiHiehgt = 268; //566
		if(phoneWidth > 1920)
			uiHiehgt += 280 + 177 + 50 + 20;
		else
			uiHiehgt += 100 + 79 + 40 + 14;*/
		uiHiehgt = phoneHeight - phoneHeight*0.64f;
		
		if(phoneHeight>=550) // for smallPlay button in very small screens cuz then play overrides sound and rate btns
			yMinus =  150 + 80;
		else
			yMinus = 170;

	}
	
	public static void SaveLevel (int levNum){
		prefs.putInteger("lastPlayedLevel", levNum); 
		prefs.flush();
	}
	
	@Override
	public void render(float delta) {
		GameMainClass.HideAds();
		Gdx.gl.glClearColor(255, 255, 255, 1); //Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		betweenBeepsMinTime+=delta;
		if(uDirUp)
			uDistanceCurrent += (50*delta * ((float)phoneHeight/720));
		else
			uDistanceCurrent -= (50*delta * ((float)phoneHeight/720));
		if(uDistanceCurrent >= uDistanceMax){
			uDirUp = false;
		}
		else if (uDistanceCurrent <= -uDistanceMax)
			uDirUp = true;
			

		runTime += delta;
		DrawBG();
		DrawLogo();
		//DrawSubText();
		if(!chooseLv)
			DrawButtons();
		else{
			DrawLevelButtons();
			DrawStars();
			DrawBackButton();
		}
		CheckClicks();
	}
	
	private void CheckClicks (){
		if(!Gdx.input.isTouched()) okTouch = true;
		if(Gdx.input.isTouched(0) && !chooseLv && okTouch){
			if(phoneWidth >= 1770){
				if(Gdx.input.getX(0) >= phoneWidth/2 + -280/2 && 
						phoneHeight - Gdx.input.getY(0) >= (phoneHeight - uiHiehgt - 280 - 40) &&
						Gdx.input.getX(0) <= phoneWidth/2 + -280/2 + 280 &&
						phoneHeight - Gdx.input.getY(0) <= (phoneHeight - uiHiehgt - 280 - 40 + 280)){
					//Large play button has been touched/clicked
					if(betweenBeepsMinTime > 0.1f){
						chooseLv = true;
						if(!GameMainClass.muteSound)
							AssetLoader.beep.play();
						betweenBeepsMinTime = 0;
						okTouch = false;
					}
				}
				if(Gdx.input.getX(0) >= phoneWidth/2 + 20 && 
						phoneHeight - Gdx.input.getY(0) >= 40 &&
						Gdx.input.getX(0) <= phoneWidth/2 + 20 + 198 &&
						phoneHeight - Gdx.input.getY(0) <= 40 + 198){
					//Large sound button has been touched/clicked
					if(betweenBeepsMinTime > 0.1f){
						GameMainClass.muteSound = !GameMainClass.muteSound;
						if(!GameMainClass.muteSound)
							AssetLoader.beep.play();
						betweenBeepsMinTime = 0;
						okTouch = false;
					}
				}
				if(Gdx.input.getX(0) >= phoneWidth/2 - 218 && 
						phoneHeight - Gdx.input.getY(0) >= 40 &&
						Gdx.input.getX(0) <=  phoneWidth/2 -218 + 198 &&
						phoneHeight - Gdx.input.getY(0) <= 40 + 198){
					//Large rate button has been touched/clicked
					if(betweenBeepsMinTime > 0.1f){
						Gdx.net.openURI("https://play.google.com/store/apps/details?id=com.elkadakh.pilotguy.android");
						if(!GameMainClass.muteSound)
							AssetLoader.beep.play();
						betweenBeepsMinTime = 0;
						okTouch = false;
					}
				}
				/*if(Gdx.input.getX(0) >= 10 && 
						phoneHeight - Gdx.input.getY(0) >= 120 &&
						Gdx.input.getX(0) <= 110 &&
						phoneHeight - Gdx.input.getY(0) <= 120 + 100){
					//how to play button has been touched/clicked
					if(betweenBeepsMinTime > 0.1f){
						GameMainClass.setCurScreen(-1);
						if(!GameMainClass.muteSound)
							AssetLoader.beep.play();
						betweenBeepsMinTime = 0;
						okTouch = false;
					}
				}*/
			}
			else{ 
				if(Gdx.input.getX(0) >= phoneWidth/2 -150/2 && 
						phoneHeight - Gdx.input.getY(0) >= (int) (phoneHeight - uiHiehgt - yMinus) &&
						Gdx.input.getX(0) <= phoneWidth/2 -150/2 + 150 &&
						phoneHeight - Gdx.input.getY(0) <= (int) (phoneHeight - uiHiehgt - yMinus) + 150){
					//small play button has been touched/clicked
					if(betweenBeepsMinTime > 0.1f){
						chooseLv = true;
						if(!GameMainClass.muteSound)
							AssetLoader.beep.play();
						betweenBeepsMinTime = 0;
						okTouch = false;
					}
				}
				if(Gdx.input.getX(0) >= phoneWidth/2 + 20 && 
						phoneHeight - Gdx.input.getY(0) >= 20 &&
						Gdx.input.getX(0) <= phoneWidth/2 + 20  + 100 &&
						phoneHeight - Gdx.input.getY(0) <= 20 + 100){
					//small sound button has been touched/clicked
					if(betweenBeepsMinTime > 0.1f){
						GameMainClass.muteSound = !GameMainClass.muteSound;
						if(!GameMainClass.muteSound)
							AssetLoader.beep.play();
						betweenBeepsMinTime = 0;
						okTouch = false;
					}
				}
				if(Gdx.input.getX(0) >= phoneWidth/2 - 110 && 
						phoneHeight - Gdx.input.getY(0) >= 20 &&
						Gdx.input.getX(0) <=  phoneWidth/2 - 110 + 100 &&
						phoneHeight - Gdx.input.getY(0) <= 20 + 100){
					//small rate button has been touched/clicked
					if(betweenBeepsMinTime > 0.1f){
						Gdx.net.openURI("https://play.google.com/store/apps/details?id=com.elkadakh.pilotguy.android");
						if(!GameMainClass.muteSound)
							AssetLoader.beep.play();
						betweenBeepsMinTime = 0;
						okTouch = false;
					}
				}
			}
		}
		else if(Gdx.input.isTouched(0) && chooseLv && okTouch){
			int y;
			if(phoneWidth >= 1770)
			y = (int) (phoneHeight - uiHiehgt - 80 - 100);
			else
				y = (int) (phoneHeight - uiHiehgt - 20 - 100);
			int x = phoneWidth/2 - 700/2;
			
			if(Gdx.input.getX() >= x && Gdx.input.getX() <= x +100  && phoneHeight - Gdx.input.getY() >= y && phoneHeight - Gdx.input.getY() <= y + 100){
				if(betweenBeepsMinTime > 0.1f){
					if(!GameMainClass.muteSound)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					GameMainClass.setCurScreen(-1);
					okTouch = false;
				}
			}
			
			if(Gdx.input.getX() >= x + 120 && Gdx.input.getX() <= x + 220  && phoneHeight - Gdx.input.getY() >= y && phoneHeight - Gdx.input.getY() <= y + 100){
				if(betweenBeepsMinTime > 0.1f && prefs.getInteger("lastPlayedLevel", 1) >= 1){
					if(!GameMainClass.muteSound)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					GameMainClass.setCurScreen(1);
					okTouch = false;
				}
			}
			
			if(Gdx.input.getX() >= x + 240  && Gdx.input.getX() <= x + 340  && phoneHeight - Gdx.input.getY() >= y && phoneHeight - Gdx.input.getY() <= y + 100){
				if(betweenBeepsMinTime > 0.1f && prefs.getInteger("lastPlayedLevel", 1) >= 2){
					if(!GameMainClass.muteSound)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					GameMainClass.setCurScreen(2);
					okTouch = false;
				}
			}
			
			if(Gdx.input.getX() >= x + 360  && Gdx.input.getX() <= x + 460  && phoneHeight - Gdx.input.getY() >= y && phoneHeight - Gdx.input.getY() <= y + 100){
				if(betweenBeepsMinTime > 0.1f && prefs.getInteger("lastPlayedLevel", 1) >= 3){
					if(!GameMainClass.muteSound)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					GameMainClass.setCurScreen(3);
					okTouch = false;
				}
			}
			
			if(Gdx.input.getX() >= x + 480  && Gdx.input.getX() <= x + 580  && phoneHeight - Gdx.input.getY() >= y && phoneHeight - Gdx.input.getY() <= y + 100){
				if(betweenBeepsMinTime > 0.1f && prefs.getInteger("lastPlayedLevel", 1) >= 4){
					if(!GameMainClass.muteSound)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					GameMainClass.setCurScreen(4);
					okTouch = false;
				}
			}
			

			if(Gdx.input.getX() >= x + 600 && Gdx.input.getX() <= x + 700  && phoneHeight - Gdx.input.getY() >= y && phoneHeight - Gdx.input.getY() <= y + 100){
				if(betweenBeepsMinTime > 0.1f){
					if(!GameMainClass.muteSound)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					GameMainClass.setCurScreen(5);
					okTouch = false;
				}
			}
			
			y-=120;
			
			if(Gdx.input.getX() >= x && Gdx.input.getX() <= x + 100  && phoneHeight - Gdx.input.getY() >= y && phoneHeight - Gdx.input.getY() <= y + 100){
				if(betweenBeepsMinTime > 0.1f && prefs.getInteger("lastPlayedLevel", 1) >= 6){
					if(!GameMainClass.muteSound)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					GameMainClass.setCurScreen(6);
					okTouch = false;
				}
			}
			
			if(Gdx.input.getX() >= x + 120  && Gdx.input.getX() <= x + 220  && phoneHeight - Gdx.input.getY() >= y && phoneHeight - Gdx.input.getY() <= y + 100){
				if(betweenBeepsMinTime > 0.1f && prefs.getInteger("lastPlayedLevel", 1) >= 7){
					if(!GameMainClass.muteSound)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					GameMainClass.setCurScreen(7);
					okTouch = false;
				}
			}
			
			if(Gdx.input.getX() >= x + 240  && Gdx.input.getX() <= x + 340  && phoneHeight - Gdx.input.getY() >= y && phoneHeight - Gdx.input.getY() <= y + 100){
				if(betweenBeepsMinTime > 0.1f && prefs.getInteger("lastPlayedLevel", 1) >= 8){
					if(!GameMainClass.muteSound)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					GameMainClass.setCurScreen(8);
					okTouch = false;
				}
			}
			
			if(Gdx.input.getX() >= x + 360  && Gdx.input.getX() <= x + 460  && phoneHeight - Gdx.input.getY() >= y && phoneHeight - Gdx.input.getY() <= y + 100){
				if(betweenBeepsMinTime > 0.1f && prefs.getInteger("lastPlayedLevel", 1) >= 9){
					if(!GameMainClass.muteSound)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					GameMainClass.setCurScreen(9);
					okTouch = false;
				}
			}
			
			if(Gdx.input.getX() >= x + 480  && Gdx.input.getX() <= x + 580  && phoneHeight - Gdx.input.getY() >= y && phoneHeight - Gdx.input.getY() <= y + 100){
				if(betweenBeepsMinTime > 0.1f && prefs.getInteger("lastPlayedLevel", 1) >= 10){
					if(!GameMainClass.muteSound)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					GameMainClass.setCurScreen(10);
					okTouch = false;
				}
			}
			/*
			 * here below is the back button in the bottom left
			 */
			int desiredHeight = phoneHeight/11;
			if(Gdx.input.isTouched()){
				if(Gdx.input.getX() >= 10 && Gdx.input.getX() <= 10 + desiredHeight &&
						(phoneHeight - Gdx.input.getY()) >= 10 && (phoneHeight - Gdx.input.getY()) <= 10 + desiredHeight && chooseLv && okTouch){
					if(!GameMainClass.muteSound)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					chooseLv = false;
					okTouch = false;
				}
			}
		}
	}

	private void DrawSubText() {
		drawToScreen.begin();
		//AssetLoader.subText.draw(drawToScreen, "IT'S AMAZING!", phoneWidth/2, (int)(phoneHeight - phoneHeight*0.18 - 20));
		drawToScreen.end();	
		}
	
	private void DrawLogo (){
		drawToScreen.begin();
		if(phoneWidth < 1119){
			drawToScreen.draw(AssetLoader.logo800, phoneWidth/2 - 559/2,(int) (phoneHeight - uiHiehgt), 0, 0, 559, 83);
	 		drawToScreen.draw(AssetLoader.uLet800Animation.getKeyFrame(runTime), 
	 				phoneWidth/2 - 550/2 + 416 - 31,(int) (phoneHeight- uiHiehgt +  uDistanceCurrent) + 10);
		}
		else if(phoneWidth >= 1119 &&  phoneWidth < 1900){

			/*
			drawToScreen.draw(AssetLoader.logo800, phoneWidth/2 - 774/2,(int) (phoneHeight/2 +  uiHiehgt/2 - 114), 0, 0, 774, 114);
	 		drawToScreen.draw(AssetLoader.uLet800Animation.getKeyFrame(runTime), 
	 				phoneWidth/2 - 774/2 + 576 -49,(float) (phoneHeight/2 +  uiHiehgt/2 - 114) + uDistanceCurrent, //X,Y
					0, 0, 187, 141, 1, 1, 0);*/
			
			drawToScreen.draw(AssetLoader.logo1119, phoneWidth/2 - 1119/2,(int) (phoneHeight-uiHiehgt), 0, 0, 1119, 162);
	 		drawToScreen.draw(AssetLoader.uLet1119Animation.getKeyFrame(runTime), 
	 				(int)(phoneWidth/2 - 1119/2 + 830 -56),
	 				(int) ((int)(phoneHeight-uiHiehgt) + uDistanceCurrent));
		}
		else if(phoneWidth >= 1900 &&  phoneWidth < 3800){
			drawToScreen.draw(AssetLoader.logo1119, phoneWidth/2 - 1119/2,(int) (phoneHeight-uiHiehgt), 0, 0, 1119, 162);
	 		drawToScreen.draw(AssetLoader.uLet1119Animation.getKeyFrame(runTime), 
	 				phoneWidth/2 - 1119/2 + 830 -56,(int) (phoneHeight- uiHiehgt +  uDistanceCurrent));
			/*
			drawToScreen.draw(AssetLoader.logo2000, phoneWidth/2 - 1893/2,(int) (phoneHeight/2 + uiHiehgt/2 - 268), 0, 0, 1893, 268);
	 		drawToScreen.draw(AssetLoader.uLet2000Animation.getKeyFrame(runTime), 
	 				phoneWidth/2 - 1920/2 + 1412 -50,(float) (phoneHeight/2 +  uiHiehgt/2 - 268) + uDistanceCurrent, //X,Y
					0, 0, 350, 292, 1, 1, 0);*/
		}
		else if(phoneWidth >= 3800){
			drawToScreen.draw(AssetLoader.logo2000, phoneWidth/2 - 1893/2,(int) (phoneHeight- uiHiehgt), 0, 0, 1893, 268);
	 		drawToScreen.draw(AssetLoader.uLet2000Animation.getKeyFrame(runTime), 
	 				phoneWidth/2 - 1920/2 + 1412 -50,(int) (phoneHeight -  uiHiehgt) + uDistanceCurrent);
			/*
			drawToScreen.draw(AssetLoader.logo4000, phoneWidth/2 - 4000/2,(int) (phoneHeight + uiHiehgt/2 - 566), 0, 0, 3955, 566);
			drawToScreen.draw(AssetLoader.uLet4000Animation.getKeyFrame(runTime), 
					phoneWidth/2 - 3955/2 + 2949 -112,(float) (phoneHeight/2 +  uiHiehgt/2 - 566) + uDistanceCurrent, //X,Y
					0, 0, 690, 606, 1, 1, 0);*/
		}
		drawToScreen.end();
	}
	
	private void DrawLevelButtons (){
		drawToScreen.begin();
		int y;
		if(phoneWidth >= 1770)
		y = (int) (phoneHeight - uiHiehgt - 80 - 100);
		else
			y = (int) (phoneHeight - uiHiehgt - 20 - 100);
		int x = phoneWidth/2 - 700/2;
		
		drawToScreen.draw(AssetLoader.howTo, x, y , 0, 0, 100, 100);
		drawToScreen.draw(AssetLoader.lvl1Btn, x + 120, y, 0, 0, 100, 100);
		
		if(prefs.getInteger("lastPlayedLevel", 1) >= 2)
			drawToScreen.draw(AssetLoader.lvl2Btn, x + 240, y, 0, 0, 100, 100);
		if(prefs.getInteger("lastPlayedLevel", 1) >= 3)
			drawToScreen.draw(AssetLoader.lvl3Btn, x + 360, y, 0, 0, 100, 100);
		if(prefs.getInteger("lastPlayedLevel", 1) >= 4)
			drawToScreen.draw(AssetLoader.lvl4Btn, x + 480, y, 0, 0, 100, 100);
		if(prefs.getInteger("lastPlayedLevel", 1) >= 5)
			drawToScreen.draw(AssetLoader.lvl5Btn, x + 600 , y, 0, 0, 100, 100);
		
		y -= 120;
		
		if(prefs.getInteger("lastPlayedLevel", 1) >= 6)
			drawToScreen.draw(AssetLoader.lvl6Btn, x , y, 0, 0, 100, 100);
		if(prefs.getInteger("lastPlayedLevel", 1) >= 7)
			drawToScreen.draw(AssetLoader.lvl7Btn, x + 120, y, 0, 0, 100, 100);
		if(prefs.getInteger("lastPlayedLevel", 1) >= 8)
			drawToScreen.draw(AssetLoader.lvl8Btn, x + 240, y, 0, 0, 100, 100);
		if(prefs.getInteger("lastPlayedLevel", 1) >= 9)
			drawToScreen.draw(AssetLoader.lvl9Btn, x + 360, y, 0, 0, 100, 100);
		if(prefs.getInteger("lastPlayedLevel", 1) >= 10)
			drawToScreen.draw(AssetLoader.lvl10Btn, x + 480, y, 0, 0, 100, 100);
		if(prefs.getInteger("lastPlayedLevel", 1) == 11){
			AssetLoader.subText.draw(drawToScreen, "Congratulations, you beat all the levels!", phoneWidth/2 - 587/2 -2 ,y - 15);
			AssetLoader.subText.draw(drawToScreen, "Newer levels are coming soon!", phoneWidth/2 - 457/2 + 2,y - 43);
		}
		drawToScreen.end();
	}
	
	private void DrawStars (){
		int y;
		if(phoneWidth >= 1770)
		y = (int) (phoneHeight - uiHiehgt - 80 - 100);
		else
			y = (int) (phoneHeight - uiHiehgt - 20 - 100);
		int x = phoneWidth/2 - 700/2;
		drawToScreen.begin();
		if(Menu.prefs.getInteger("lvl1stars",0) >= 1)
			drawToScreen.draw(AssetLoader.star, x+120 + 100/2 - 10 - 4 - 20, y + 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl1stars",1) >= 2)
			drawToScreen.draw(AssetLoader.star, x+120 + 100/2 - 10, y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl1stars",1) >= 3)
			drawToScreen.draw(AssetLoader.star, x+120 + 100/2 + 10 + 4 , y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		
		if(Menu.prefs.getInteger("lvl2stars",0) >= 1)
			drawToScreen.draw(AssetLoader.star, x+240 + 100/2 - 10 - 4 - 20, y + 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl2stars",1) >= 2)
			drawToScreen.draw(AssetLoader.star, x+240 + 100/2 - 10, y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl2stars",1) >= 3)
			drawToScreen.draw(AssetLoader.star, x+240 + 100/2 + 10 + 4 , y+ 2, 20, 21, 0, 0, 20, 21, false, false);

		if(Menu.prefs.getInteger("lvl3stars",0) >= 1)
			drawToScreen.draw(AssetLoader.star, x+360 + 100/2 - 10 - 4 - 20, y + 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl3stars",1) >= 2)
			drawToScreen.draw(AssetLoader.star, x+360 + 100/2 - 10, y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl3stars",1) >= 3)
			drawToScreen.draw(AssetLoader.star, x+360 + 100/2 + 10 + 4 , y+ 2, 20, 21, 0, 0, 20, 21, false, false);

		if(Menu.prefs.getInteger("lvl4stars",0) >= 1)
		drawToScreen.draw(AssetLoader.star, x+480 + 100/2 - 10 - 4 - 20, y + 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl4stars",1) >= 2)
			drawToScreen.draw(AssetLoader.star, x+480 + 100/2 - 10, y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl4stars",1) >= 3)
			drawToScreen.draw(AssetLoader.star, x+480 + 100/2 + 10 + 4 , y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		
		if(Menu.prefs.getInteger("lvl5stars",0) >= 1)
		drawToScreen.draw(AssetLoader.star, x + 600 + 100/2 - 10 - 4 - 20, y + 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl5stars",1) >= 2)
			drawToScreen.draw(AssetLoader.star, x + 600 + 100/2 - 10, y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl5stars",1) >= 3)
			drawToScreen.draw(AssetLoader.star, x + 600 + 100/2 + 10 + 4 , y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		
		y -= 120;
		
		
		if(Menu.prefs.getInteger("lvl6stars",0) >= 1)
		drawToScreen.draw(AssetLoader.star, x + 100/2 - 10 - 4 - 20, y + 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl6stars",1) >= 2)
			drawToScreen.draw(AssetLoader.star, x + 100/2 - 10, y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl6stars",1) >= 3)
			drawToScreen.draw(AssetLoader.star, x + 100/2 + 10 + 4 , y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		
		if(Menu.prefs.getInteger("lvl7stars",0) >= 1)
		drawToScreen.draw(AssetLoader.star, x + 120 + 100/2 - 10 - 4 - 20, y + 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl7stars",1) >= 2)
			drawToScreen.draw(AssetLoader.star, x + 120 + 100/2 - 10, y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl7stars",1) >= 3)
			drawToScreen.draw(AssetLoader.star, x + 120 + 100/2 + 10 + 4 , y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		
		if(Menu.prefs.getInteger("lvl8stars",0) >= 1)
		drawToScreen.draw(AssetLoader.star, x + 240 + 100/2 - 10 - 4 - 20, y + 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl8stars",1) >= 2)
			drawToScreen.draw(AssetLoader.star, x + 240 + 100/2 - 10, y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl8stars",1) >= 3)
			drawToScreen.draw(AssetLoader.star, x + 240 + 100/2 + 10 + 4 , y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		
		if(Menu.prefs.getInteger("lvl9stars",0) >= 1)
		drawToScreen.draw(AssetLoader.star, x + 360 + 100/2 - 10 - 4 - 20, y + 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl9stars",1) >= 2)
			drawToScreen.draw(AssetLoader.star, x + 360 + 100/2 - 10, y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl9stars",1) >= 3)
			drawToScreen.draw(AssetLoader.star, x + 360 + 100/2 + 10 + 4 , y+ 2, 20, 21, 0, 0, 20, 21, false, false);

		if(Menu.prefs.getInteger("lvl10stars",0) >= 1)
		drawToScreen.draw(AssetLoader.star, x + 480 + 100/2 - 10 - 4 - 20, y + 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl10stars",1) >= 2)
			drawToScreen.draw(AssetLoader.star, x + 480 + 100/2 - 10, y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		if(Menu.prefs.getInteger("lvl10stars",1) >= 3)
			drawToScreen.draw(AssetLoader.star, x + 480 + 100/2 + 10 + 4 , y+ 2, 20, 21, 0, 0, 20, 21, false, false);
		drawToScreen.end();
	}
	
	private void DrawBackButton (){
		drawToScreen.begin();
		int desiredHeight = phoneHeight/11;
		if(bgNum == 1){
			if(desiredHeight > 75){
				drawToScreen.draw(AssetLoader.largeBackDark, 10, 10);//, desiredHeight, desiredHeight);
				desiredHeight = 100;
			}
			else if (desiredHeight <= 43) {
				drawToScreen.draw(AssetLoader.XsmallBackDark, 10, 10);
				desiredHeight = 40;
			}
			else {
				drawToScreen.draw(AssetLoader.smallBackDark, 10, 10);//, desiredHeight, desiredHeight);
				desiredHeight = 50;
			}
		}
		else{
			if(desiredHeight > 75){
				drawToScreen.draw(AssetLoader.largeBackLight, 10, 10);//, desiredHeight, desiredHeight);
				desiredHeight = 100;
			}
			else if (desiredHeight <= 43) {
				drawToScreen.draw(AssetLoader.XsmallBackLight, 10, 10);
				desiredHeight = 40;
			}
			else {
				drawToScreen.draw(AssetLoader.smallBackLight, 10, 10);//, desiredHeight, desiredHeight);
				desiredHeight = 50;
			}
		}
		drawToScreen.end();
	}
	
	private void DrawButtons (){
		drawToScreen.begin();
		if(phoneWidth >= 1770){
			Texture txt;
			if(GameMainClass.muteSound == true)
				txt = AssetLoader.largeSoundCanceled;
			else
				txt = AssetLoader.largeSound;
		drawToScreen.draw(txt, phoneWidth/2 + 20, 40,
				0, 0, 198, 198);
		drawToScreen.draw(AssetLoader.largePlay, phoneWidth/2 - 280/2,
				(int) (phoneHeight -  uiHiehgt - 280 - 40), 
				0, 0, 280, 280);
		drawToScreen.draw(AssetLoader.largeRate, phoneWidth/2 -218 ,40, 
				0, 0, 198, 198);
		}
		else{
			Texture txt;
			if(GameMainClass.muteSound == true)
				txt = AssetLoader.smallSoundCanceled;
			else
				txt = AssetLoader.smallSound;
			drawToScreen.draw(AssetLoader.smallPlay, phoneWidth/2 -150/2,
					(int) (phoneHeight - uiHiehgt - yMinus),
					0, 0, 150, 150);
			drawToScreen.draw(AssetLoader.smallRate, phoneWidth/2 - 110, 20, 
					0, 0, 100, 100);
			drawToScreen.draw(txt, phoneWidth/2 + 10, 20, 
					0, 0, 100, 100);
		}
		drawToScreen.end();
	}
	
	private void DrawBG (){
		drawToScreen.begin();
		if(screenSize == 1){
			int raiseBGforShortScreens = 0;
			if(phoneHeight < 1350){
				raiseBGforShortScreens = 50;
			}//bug that in a certain galaxy 2 it hasnt showed the bottom BG but in the else of if 1920 yes
			if(phoneWidth >= 1920){
				if (bgNum == 1){
					drawToScreen.draw(AssetLoader.bgLightSmall, 0, 0, //The ground
							startPointBottom, 0, (int) (SMALL_BG_WIDTH * screenBGmultiplier), (int) (SMALL_BG_HEIGHT * screenBGmultiplier));
					drawToScreen.draw(AssetLoader.bgTopLightSmall, 0, phoneHeight - SMALL_BG_HEIGHT_TOP + raiseBGforShortScreens, //The sky
							(int) (SMALL_BG_WIDTH * screenBGmultiplier), (int) (SMALL_BG_HEIGHT_TOP * screenBGmultiplier), startPointTop,0, SMALL_BG_WIDTH,SMALL_BG_HEIGHT_TOP, false, false);
				}
				else {
					drawToScreen.draw(AssetLoader.bgDarkLarge, 0, 0, //The ground
							  (int) (SMALL_BG_WIDTH * screenBGmultiplier), (int) (SMALL_BG_HEIGHT * screenBGmultiplier),startPointBottom, 0,LARGE_BG_WIDTH,LARGE_BG_HEIGHT, false, false);
					drawToScreen.draw(AssetLoader.bgTopDarkSmall, 0, phoneHeight - SMALL_BG_HEIGHT_TOP + raiseBGforShortScreens, //The sky
							(int) (SMALL_BG_WIDTH * screenBGmultiplier), (int) (SMALL_BG_HEIGHT_TOP * screenBGmultiplier), startPointTop,0, SMALL_BG_WIDTH,SMALL_BG_HEIGHT_TOP, false, false);
				}
			}
			else{
				if (bgNum == 1){
					drawToScreen.draw(AssetLoader.bgLightSmall, 0, 0, //The ground
							startPointBottom, 0, (int) (SMALL_BG_WIDTH * screenBGmultiplier), (int) (SMALL_BG_HEIGHT * screenBGmultiplier));
					drawToScreen.draw(AssetLoader.bgTopLightSmall, 0, phoneHeight - SMALL_BG_HEIGHT_TOP + raiseBGforShortScreens, //The sky
							startPointTop, 0, (int) (SMALL_BG_WIDTH * screenBGmultiplier), (int) (SMALL_BG_HEIGHT_TOP * screenBGmultiplier));
				}
				else {
					drawToScreen.draw(AssetLoader.bgDarkSmall, 0, 0, //The ground
							startPointBottom, 0, (int) (SMALL_BG_WIDTH * screenBGmultiplier), (int) (SMALL_BG_HEIGHT * screenBGmultiplier));
					drawToScreen.draw(AssetLoader.bgTopDarkSmall, 0, phoneHeight - SMALL_BG_HEIGHT_TOP + raiseBGforShortScreens, //The sky
							startPointTop, 0, (int) (SMALL_BG_WIDTH * screenBGmultiplier), (int) (SMALL_BG_HEIGHT_TOP * screenBGmultiplier));
				}
			}
		}
		else if(screenSize == 2){
			if (bgNum == 1){
				drawToScreen.draw(AssetLoader.bgLightLarge, 0, 0, 
						startPointBottom, 0, LARGE_BG_WIDTH, LARGE_BG_HEIGHT);
			drawToScreen.draw(AssetLoader.bgTopLightLarge, 0, phoneHeight - LARGE_BG_HEIGHT_TOP, 
						startPointTop, 0, LARGE_BG_WIDTH, LARGE_BG_HEIGHT_TOP);
			}
			else {
				drawToScreen.draw(AssetLoader.bgDarkLarge, 0, 0, 
						startPointBottom, 0, LARGE_BG_WIDTH, SMALL_BG_HEIGHT);
				drawToScreen.draw(AssetLoader.bgTopDarkLarge, 0, phoneHeight - LARGE_BG_HEIGHT_TOP,
						startPointTop, 0, LARGE_BG_WIDTH, LARGE_BG_HEIGHT_TOP);
			}
		}
		else if(screenSize == 3){
			if (bgNum == 1){
				drawToScreen.draw(AssetLoader.bgLightLarge, 0, 0, 
						startPointBottom, 0, (int) (LARGE_BG_WIDTH * screenBGmultiplier), (int) (LARGE_BG_HEIGHT * screenBGmultiplier));
				drawToScreen.draw(AssetLoader.bgTopLightLarge, 0, phoneHeight - LARGE_BG_HEIGHT_TOP, 
						startPointTop, 0, (int) (LARGE_BG_WIDTH * screenBGmultiplier), (int) (LARGE_BG_HEIGHT_TOP * screenBGmultiplier));
			}
			else {
				drawToScreen.draw(AssetLoader.bgDarkLarge, 0, 0, 
						startPointBottom, 0, (int) (LARGE_BG_WIDTH * screenBGmultiplier), (int) (LARGE_BG_HEIGHT * screenBGmultiplier));
				drawToScreen.draw(AssetLoader.bgTopDarkLarge, 0, phoneHeight - LARGE_BG_HEIGHT_TOP, 
						startPointTop, 0, (int) (LARGE_BG_WIDTH * screenBGmultiplier), (int) (LARGE_BG_HEIGHT_TOP * screenBGmultiplier));
			}
		}
		drawToScreen.end();                              
	}

	
	private static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
