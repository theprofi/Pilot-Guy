package com.elkadakh.others;

import screens.Level8;
import screens.LevelBase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.elkadakh.pilotguy.GameMainClass;

public class DifficultyLevels {

	SpriteBatch screenToDraw;
	Helicopter heli;
	boolean CanDraw_Check;
	int pWidth, pHeight;
	public int chosen = 2;
	public int dif1PosX, dif1PosY;
	public int dif2PosX = 0, dif2PosY = 0;
	public int dif3PosX = 0, dif3PosY = 0;
	public int dif4PosX = 0, dif4PosY = 0;
	public int size = 0;
	float betweenBeepsMinTime;
	private boolean playBeep;
	private Texture star;
	boolean oneBeep;
	
	public DifficultyLevels (SpriteBatch SB,Helicopter LvlBaseHeli, int phoneWidth, int phoneHeight){
		oneBeep = true;
		screenToDraw = SB;
		heli = LvlBaseHeli;
		pWidth = phoneWidth;
		pHeight = phoneHeight;
		betweenBeepsMinTime = 0;
		playBeep = false;
	}
	
	public void DoThings (int bgNum, boolean startMovingHeli, int widthOftarget, int targetX, int targetY, float delta, boolean isFalling){
		if(!Gdx.input.isTouched())
			oneBeep = true;
		CanDraw_Check = !startMovingHeli && !isFalling;
		int distanceEachOther = 30, distanceCorner = 30;
		betweenBeepsMinTime += delta;
		Texture dif1 = null, dif2 = null, dif3 = null, dif4 = null;
		if(pWidth <= 1280){
			size = 80;
			if(bgNum == 1){
				dif1 = AssetLoader.dif1Dark80;
				dif2 = AssetLoader.dif2Dark80;
				dif3 = AssetLoader.dif3Dark80;
				dif4 = AssetLoader.dif4Dark80;
				if(chosen == 1)
					dif1 = AssetLoader.dif1Light80;
				else if (chosen == 2)
					dif2 = AssetLoader.dif2Light80;
				else if (chosen == 3)
					dif3 = AssetLoader.dif3Light80;
				else if (chosen == 4)
					dif4 = AssetLoader.dif4Light80;
			}
			else if(bgNum == 2){
				dif1 = AssetLoader.dif1Light80;
				dif2 = AssetLoader.dif2Light80;
				dif3 = AssetLoader.dif3Light80;
				dif4 = AssetLoader.dif4Light80;
				if(chosen == 1)
					dif1 = AssetLoader.dif1Dark80;
				if(chosen == 2)
					dif2 = AssetLoader.dif2Dark80;
				if(chosen == 3)
					dif3 = AssetLoader.dif3Dark80;
				if(chosen == 4)
					dif4 = AssetLoader.dif4Dark80;
			}
		}
		else if(pWidth > 1280 && pWidth <= 1920){
			size = 200;
			if(bgNum == 1){
				dif1 = AssetLoader.dif1Dark200;
				dif2 = AssetLoader.dif2Dark200;
				dif3 = AssetLoader.dif3Dark200;
				dif4 = AssetLoader.dif4Dark200;
				if(chosen == 1)
					dif1 = AssetLoader.dif1Light200;
				if(chosen == 2)
					dif2 = AssetLoader.dif2Light200;
				if(chosen == 3)
					dif3 = AssetLoader.dif3Light200;
				if(chosen == 4)
					dif4 = AssetLoader.dif4Light200;
			}
			else if(bgNum == 2){
				dif1 = AssetLoader.dif1Light200;
				dif2 = AssetLoader.dif2Light200;
				dif3 = AssetLoader.dif3Light200;
				dif4 = AssetLoader.dif4Light200;
				if(chosen == 1)
					dif1 = AssetLoader.dif1Dark200;
				if(chosen == 2)
					dif2 = AssetLoader.dif2Dark200;
				if(chosen == 3)
					dif3 = AssetLoader.dif3Dark200;
				if(chosen == 4)
					dif4 = AssetLoader.dif4Dark200;
				}
		}
		else if(pWidth > 1920){
			size = 300;
			if(bgNum == 1){
				dif1 = AssetLoader.dif1Dark300;
				dif2 = AssetLoader.dif2Dark300;
				dif3 = AssetLoader.dif3Dark300;
				dif4 = AssetLoader.dif4Dark300;
				if(chosen == 1)
					dif1 = AssetLoader.dif1Light300;
				if(chosen == 2)
					dif2 = AssetLoader.dif2Light300;
				if(chosen == 3)
					dif3 = AssetLoader.dif3Light300;
				if(chosen == 4)
					dif4 = AssetLoader.dif4Light300;
			}
			else if(bgNum == 2){
				dif1 = AssetLoader.dif1Light300;
				dif2 = AssetLoader.dif2Light300;
				dif3 = AssetLoader.dif3Light300;
				dif4 = AssetLoader.dif4Light300;
				
				if(chosen == 1)
					dif1 = AssetLoader.dif1Dark300;
				if(chosen == 2)
					dif2 = AssetLoader.dif2Dark300;
				if(chosen == 3)
					dif3 = AssetLoader.dif3Dark300;
				if(chosen == 4)
					dif4 = AssetLoader.dif4Dark300;
			}
		}
		dif1PosX = pWidth - 4*distanceEachOther - size*4; dif1PosY = distanceCorner;
		dif2PosX = pWidth - 3*distanceEachOther - size*3; dif2PosY = distanceCorner;
		dif3PosX = pWidth - 2*distanceEachOther - size*2; dif3PosY = distanceCorner;
		dif4PosX = pWidth - 1*distanceEachOther - size*1; dif4PosY = distanceCorner;
		
		//Draw stars
		if(bgNum == 1)
			star = AssetLoader.starBordered;
		else
			star = AssetLoader.star;
		screenToDraw.begin();

		if(chosen == 2 && LevelBase.lvs8Part != 1)
			screenToDraw.draw(star,targetX + widthOftarget/2 - 10 - 4 - 20, targetY - 25, 20, 21, 0, 0, 20, 21, false, false);
		else if(chosen == 3 && LevelBase.lvs8Part != 1){
			screenToDraw.draw(star,targetX + widthOftarget/2 - 10 - 4 - 20, targetY - 25, 20, 21, 0, 0, 20, 21, false, false);
			screenToDraw.draw(star,targetX + widthOftarget/2 - 10, targetY - 25, 20, 21, 0, 0, 20, 21, false, false);
		}
		else if(chosen == 4 && LevelBase.lvs8Part != 1){
			screenToDraw.draw(star,targetX + widthOftarget/2 - 10 - 4 - 20, targetY - 25, 20, 21, 0, 0, 20, 21, false, false);
			screenToDraw.draw(star,targetX + widthOftarget/2 - 10, targetY - 25, 20, 21, 0, 0, 20, 21, false, false);
			screenToDraw.draw(star,targetX + widthOftarget/2 + 10 + 4, targetY - 25, 20, 21, 0, 0, 20, 21, false, false);
		}
		screenToDraw.end();
		
		//Draw
		if(CanDraw_Check){
			screenToDraw.begin();
			screenToDraw.draw(dif1, dif1PosX, dif1PosY, size, size, 0, 0, size, size, false, false);
			screenToDraw.draw(dif2, dif2PosX, dif2PosY, size, size, 0, 0, size, size, false, false);
			screenToDraw.draw(dif3, dif3PosX, dif3PosY, size, size, 0, 0, size, size, false, false);
			screenToDraw.draw(dif4, dif4PosX, dif4PosY, size, size, 0, 0, size, size, false, false);
			screenToDraw.end();
			//Check for clicks
			int touchedX = Gdx.input.getX();
			int touchedY = pHeight - Gdx.input.getY();
			if(Gdx.input.isTouched()){
				if(touchedX >= dif1PosX && touchedX <= dif1PosX + size && touchedY >= dif1PosY && touchedY <= dif1PosY + size){
					heli.SetEasy();
					chosen = 1;
					playBeep = true;
				}
				else if(touchedX >= dif2PosX && touchedX <= dif2PosX + size && touchedY >= dif2PosY && touchedY <= dif2PosY + size){
					heli.SetMedium();
					chosen = 2;
					playBeep = true;
				}
				else if(touchedX >= dif3PosX && touchedX <= dif3PosX + size && touchedY >= dif3PosY && touchedY <= dif3PosY + size){
					heli.SetHard();
					chosen = 3;
					playBeep = true;
				}
				else if(touchedX >= dif4PosX && touchedX <= dif4PosX + size && touchedY >= dif4PosY && touchedY <= dif4PosY + size){
					heli.SetVeryHard();
					chosen = 4;
					playBeep = true;
				}
				if(betweenBeepsMinTime > 0.1f && oneBeep){
					if(!GameMainClass.muteSound && playBeep)
						AssetLoader.beep.play();
					betweenBeepsMinTime = 0;
					playBeep = false;
					oneBeep = false;
				}
			}
		}
	}
}
