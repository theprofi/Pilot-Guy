package screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.elkadakh.others.AssetLoader;
import com.elkadakh.others.DifficultyLevels;
import com.elkadakh.others.Helicopter;
import com.elkadakh.pilotguy.GameMainClass;

public class LevelBase implements Screen {
	
	protected int heliPosX = -1, heliPosY = -1;
	protected int bgNum;// 1 = Light, 2 = Dark
	protected int phoneWidth, phoneHeight;
	private int screenSize;
	protected SpriteBatch drawToScreen;
	private static final int SMALL_BG_WIDTH = 1920, SMALL_BG_HEIGHT_TOP = 249, SMALL_BG_HEIGHT = 1080;
	private static final int LARGE_BG_WIDTH = 4096, LARGE_BG_HEIGHT_TOP = 652, LARGE_BG_HEIGHT = 2160;
	int startPointTop, startPointBottom;
    protected Helicopter helicopter; 
    private float runTime; //For determining helis animation
    protected boolean startMovingHeli, isFalling, dontDrawHeli;
    protected float lvsPlayAreaRatio;
    float screenBGmultiplier;
    protected boolean[ ][ ] borderLine;
    protected float xToCen, yToCen, playArNewWidth, playArNewHeight, playArrWid, playArrHeight; //All are initialized in childern
    private boolean beeped0, beeped1;
    protected float nextLevelSuspend;
    float betweenBeepsMinTime;
    boolean fa = false;
	public static int lvs8Part = 1;//so diflvls class will draw stars cuz in other lvls too
	protected static boolean newStart = true;
	protected ShapeRenderer linesRenderer;
	public DifficultyLevels difLvls;
	
	Rectangle topCol = new Rectangle();
	Rectangle midCol = new Rectangle();
	Rectangle botCol = new Rectangle();
	int skip = 0;
	
	float xAxis;
	float yAxis;
	float angel;
	float cosAngel;
	float sinAngel;
	
	float xNew;
	float yNew;
	
	public LevelBase(){
	    betweenBeepsMinTime = 0;
		linesRenderer = new ShapeRenderer();
		dontDrawHeli = false;
		drawToScreen = new SpriteBatch();
		bgNum = randInt(1,2); //Choose a background
		phoneWidth = GameMainClass.phoneWidth; //Get phones resolutions
		phoneHeight = GameMainClass.phoneHeight;	
		screenBGmultiplier = 1;
		startPointBottom = 0;
		startPointTop = 0;
		
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
		
        runTime = 0;
        startMovingHeli = false;
		if(screenBGmultiplier == 0) screenBGmultiplier++;
		beeped0 = false;
		beeped1 = false;
	}
	
	@Override
	public void render(float delta) {
		if(!startMovingHeli)
			GameMainClass.ShowAds();
		else
			GameMainClass.HideAds();
		if(!Gdx.input.isTouched()) Menu.okTouch = true;
		Gdx.gl.glClearColor(255, 255, 255, 1); //Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		betweenBeepsMinTime+=delta;
		DrawBG();
		DrawHelicopter();
		//DrawFence();
		if(Menu.okTouch && !fa){
			MoveHeli();
			if(startMovingHeli && nextLevelSuspend == 0){
				helicopter.update(delta);
			}
			if(startMovingHeli || isFalling)
				runTime += delta;
		}
		CheckBackBtnClicks();
	}
	
	protected void DrawGlass (){
		Gdx.gl.glEnable(GL20.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		linesRenderer.begin(ShapeType.Filled);
		linesRenderer.setColor(new Color(255/100f, 255/100f, 255/100f, 62/255f));
		if(heliPosX != 66 && heliPosY != 67){
			linesRenderer.rect(0, 0, phoneWidth, (int)yToCen);
		if(heliPosX != 160 && heliPosY != 17) // not lvl 3
			linesRenderer.rect(0, (int)((int)yToCen + (int)((int)playArrHeight*lvsPlayAreaRatio)), phoneWidth, phoneHeight);
		linesRenderer.rect((int)((int)xToCen + (int)(playArrWid*lvsPlayAreaRatio)), (int)(yToCen),
				phoneWidth, (int)((int)playArrHeight*lvsPlayAreaRatio));
		linesRenderer.rect(0, (int)yToCen   , (int)xToCen  ,(int)((int)playArrHeight*lvsPlayAreaRatio));
		}
		else {//Level 8
			if(lvs8Part == 1){//lvls 8 part
				linesRenderer.rect(0, 0, phoneWidth, (int)yToCen);
				linesRenderer.rect(0, (int)((int)yToCen + (int)((int)342*lvsPlayAreaRatio)), phoneWidth, phoneHeight);
				linesRenderer.rect((int)((int)xToCen + (int)(643*lvsPlayAreaRatio)), (int)(yToCen),
						phoneWidth, (int)((int)342*lvsPlayAreaRatio));
				linesRenderer.rect(0, (int)yToCen   , (int)xToCen  ,(int)((int)342*lvsPlayAreaRatio));
			}
			else{
				linesRenderer.rect(0, 0, phoneWidth, (int)yToCen);
				linesRenderer.rect(0, (int)((int)yToCen + (int)((int)321*lvsPlayAreaRatio)), phoneWidth, phoneHeight);
				linesRenderer.rect((int)((int)xToCen + (int)(593*lvsPlayAreaRatio)), (int)(yToCen),
						phoneWidth, (int)((int)321*lvsPlayAreaRatio));
				linesRenderer.rect(0, (int)yToCen   , (int)xToCen  ,(int)((int)321*lvsPlayAreaRatio));
			}
		}
		linesRenderer.end();
	}
	
	protected void DrawBackButton(){
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
	
	private void CheckBackBtnClicks (){	
		int desiredHeight = phoneHeight/11;
		if(Gdx.input.isTouched()){
			if(Gdx.input.getX() >= 10 && Gdx.input.getX() <= 10 + desiredHeight &&
					(phoneHeight - Gdx.input.getY()) >= 10 && (phoneHeight - Gdx.input.getY()) <= 10 + desiredHeight){
				AssetLoader.crash.stop();
				AssetLoader.nextLevel.stop();
				AssetLoader.propellorSpin.stop();
				if(betweenBeepsMinTime > 0.1f && !GameMainClass.muteSound)
					AssetLoader.beep.play();
				bgNum = randInt(1,2);
				helicopter.ResetVelocityRotation();
				//for the excersice level it checks
				if((phoneWidth/2 - (int)(34*lvsPlayAreaRatio)) == heliPosX)
					helicopter.SetPosition((float) (heliPosX), (float) (heliPosY));
				//for level 8 after the teleport
				else if (heliPosX == 66 && heliPosY == 67){
					lvs8Part = 1;
					if(helicopter.GetReversedStatus())
						helicopter.ToggleReverse();
					helicopter.SetPosition((float) (heliPosX*lvsPlayAreaRatio + xToCen), (float) (heliPosY*lvsPlayAreaRatio + yToCen));
				}
				else
					helicopter.SetPosition((float) (heliPosX*lvsPlayAreaRatio + xToCen), (float) (heliPosY*lvsPlayAreaRatio + yToCen));
				//for level 9
				dontDrawHeli = false;
				startMovingHeli = false;
				isFalling = false;
				newStart = true;
				Menu.okTouch = false;
				GameMainClass.setCurScreen(0);
			}
		}
	}
	
	private void MoveHeli (){
		boolean notouchonDiffs = (difLvls == null ||
				!(Gdx.input.getX() >= difLvls.dif1PosX && Gdx.input.getX() <= difLvls.dif1PosX + difLvls.size && phoneHeight - Gdx.input.getY() >= difLvls.dif1PosY && phoneHeight - Gdx.input.getY() <= difLvls.dif1PosY + difLvls.size)&&
				!(Gdx.input.getX() >= difLvls.dif2PosX && Gdx.input.getX() <= difLvls.dif2PosX + difLvls.size && phoneHeight - Gdx.input.getY() >= difLvls.dif2PosY && phoneHeight - Gdx.input.getY() <= difLvls.dif2PosY + difLvls.size) &&
				!(Gdx.input.getX() >= difLvls.dif3PosX && Gdx.input.getX() <= difLvls.dif3PosX + difLvls.size && phoneHeight - Gdx.input.getY() >= difLvls.dif3PosY && phoneHeight - Gdx.input.getY() <= difLvls.dif3PosY + difLvls.size) &&
				!(Gdx.input.getX() >= difLvls.dif4PosX && Gdx.input.getX() <= difLvls.dif4PosX + difLvls.size && phoneHeight - Gdx.input.getY() >= difLvls.dif4PosY && phoneHeight - Gdx.input.getY() <= difLvls.dif4PosY + difLvls.size)
				);
		if(notouchonDiffs){
			for(int i=0;i<2 && !isFalling;i++){
				if(Gdx.input.isTouched(i)){
					
					StartMovingSound();
					
					if(Gdx.input.getX(i) < phoneWidth / 2){
						helicopter.horizontal = true;
						startMovingHeli = true;
					}
					else {
						helicopter.vertical = true;
						startMovingHeli = true;
					}
					
				}
			}
		}
		if(!AssetLoader.propellorSpin.isPlaying() && startMovingHeli && !isFalling && !GameMainClass.muteSound && notouchonDiffs){
			if(Gdx.input.isTouched(0) && !beeped0 && betweenBeepsMinTime > 0.1f){
				AssetLoader.beep.stop();
				AssetLoader.beep.play();
				beeped0 = true;
				betweenBeepsMinTime = 0;
			}
			if(Gdx.input.isTouched(1) && !beeped1 && betweenBeepsMinTime > 0.1f && notouchonDiffs){
				AssetLoader.beep.stop();
				AssetLoader.beep.play();
				beeped1 = true;
				betweenBeepsMinTime = 0;
			}
			else if(!Gdx.input.isTouched(0) && beeped0 && betweenBeepsMinTime > 0.1f && notouchonDiffs){
				AssetLoader.beep.stop();
				AssetLoader.beep.play();
				beeped0 = false;
				betweenBeepsMinTime = 0;
			}
			else if(!Gdx.input.isTouched(1) && beeped1 && betweenBeepsMinTime > 0.1f && notouchonDiffs){
				AssetLoader.beep.stop();
				AssetLoader.beep.play();
				beeped1 = false;
				betweenBeepsMinTime = 0;
			}
		}
		//FOR PC:
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			if(!startMovingHeli)
				if(!AssetLoader.propellorSpin.isPlaying())
					AssetLoader.propellorSpin.play();
			helicopter.horizontal = true;
			startMovingHeli = true;
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			if(!startMovingHeli)
				if(!AssetLoader.propellorSpin.isPlaying())
					AssetLoader.propellorSpin.play();
			helicopter.vertical = true;
			startMovingHeli = true;
		}
	}
	
	private void StartMovingSound (){
		if(!startMovingHeli && !GameMainClass.muteSound){
			AssetLoader.propellorSpin.stop();
			AssetLoader.propellorSpin.play();
		}
	}
	
	private void DrawHelicopter(){
		if(!dontDrawHeli){
			drawToScreen.begin();
			if(bgNum == 1)
				drawToScreen.draw(AssetLoader.heliGreenAnimation.getKeyFrame(runTime), helicopter.GetX(), helicopter.GetY(), 
						(float) (74 / 2 * lvsPlayAreaRatio), (float) (34 / 2 * lvsPlayAreaRatio), //<-- Rotation points
						(float) (74 * lvsPlayAreaRatio), (float) (34 * lvsPlayAreaRatio), 1, 1, helicopter.GetRotation());
			else
				drawToScreen.draw(AssetLoader.heliGrayAnimation.getKeyFrame(runTime), helicopter.GetX(), helicopter.GetY(), 
						(float) (74 / 2 * lvsPlayAreaRatio), (float) (34 / 2 * lvsPlayAreaRatio), //<-- Rotation points
						(float) (74 * lvsPlayAreaRatio), (float) (34 * lvsPlayAreaRatio), 1, 1, helicopter.GetRotation());
			drawToScreen.end();
		}
	}
	
	private void DrawBG (){
		drawToScreen.begin();
		if(screenSize == 1){
			int raiseBGforShortScreens = 0;
			if(phoneHeight < 1350){
				raiseBGforShortScreens = 50;
			}
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

	protected boolean isCollision (boolean obstaclePoints[][], float TransedX, float TransedY){
		if(skip == 5 || (heliPosX == 5 && heliPosY == 157)){ //Level 5
			topCol.x = TransedX + 4;
			topCol.y = TransedY + 14;
			topCol.width = 68;
			topCol.height = 13;
			
			midCol.x = TransedX + 5;
			midCol.y = TransedY + 3;
			midCol.width = 63;
			midCol.height = 11;
			
			botCol.x = TransedX + 11;
			botCol.y = TransedY + -1;
			botCol.width = 52;
			botCol.height = 6;
			
			xAxis = 37 + TransedX;
			yAxis = 17 + TransedY;
			angel = (float) Math.toRadians(helicopter.GetRotation());
			cosAngel =  (float) Math.cos(angel);
			sinAngel =  (float) Math.sin(angel);
			
			for(int i = 0; i < obstaclePoints[0].length; i++){
				for(int j= 0;j < obstaclePoints.length; j++){
					if(obstaclePoints[j][i]){
						skip = 0;
						xNew = (float) (xAxis + (i - xAxis) * cosAngel + (j - yAxis ) * sinAngel);
						yNew = (float) (yAxis - (i - xAxis) * sinAngel + (j - yAxis ) * cosAngel);
						if(topCol.contains(xNew, yNew) || midCol.contains(xNew, yNew) || botCol.contains(xNew, yNew)){
							return true;
						}
					}
				}
			}
		}
		else
			skip++;
		return false;
	}
	
	public boolean isCollision (int squareX, int squareY, int wid, int hei, float TransedX, float TransedY){
		topCol.x = TransedX + 1;
		topCol.y = TransedY + 14;
		topCol.width = 71;
		topCol.height = 13;
		
		midCol.x = TransedX + 5;
		midCol.y = TransedY + 3;
		midCol.width = 63;
		midCol.height = 11;
		
		botCol.x = TransedX + 11;
		botCol.y = TransedY + -1;
		botCol.width = 52;
		botCol.height = 6;
		
		xAxis = 37 + TransedX;
		yAxis = 17 + TransedY;
		angel = (float) Math.toRadians(helicopter.GetRotation());
		cosAngel =  (float) Math.cos(angel);
		sinAngel =  (float) Math.sin(angel);
		
		for(int i = squareX; i < wid + squareX; i+=5){
			xNew = (float) (xAxis + (i - xAxis) * cosAngel + (squareY - yAxis ) * sinAngel);
			yNew = (float) (yAxis - (i - xAxis) * sinAngel + (squareY - yAxis ) * cosAngel);
			if(topCol.contains(xNew, yNew) || midCol.contains(xNew, yNew) || botCol.contains(xNew, yNew)){
				return true;
			}
		}
		for(int j = squareY;j < hei + squareY; j+=5){
			xNew = (float) (xAxis + (squareX - xAxis) * cosAngel + (j - yAxis ) * sinAngel);
			yNew = (float) (yAxis - (squareX - xAxis) * sinAngel + (j - yAxis ) * cosAngel);
			if(topCol.contains(xNew, yNew) || midCol.contains(xNew, yNew) || botCol.contains(xNew, yNew)){
				return true;
			}
		}
		for(int i = squareX; i < wid + squareX; i+=5){
			xNew = (float) (xAxis + (i - xAxis) * cosAngel + (hei + squareY - yAxis ) * sinAngel);
			yNew = (float) (yAxis - (i - xAxis) * sinAngel + (hei + squareY - yAxis ) * cosAngel);
			if(topCol.contains(xNew, yNew) || midCol.contains(xNew, yNew) || botCol.contains(xNew, yNew)){
				return true;
			}
		}
		for(int j = squareY;j < hei + squareY; j+=5){
			xNew = (float) (xAxis + (wid + squareX - xAxis) * cosAngel + (j - yAxis ) * sinAngel);
			yNew = (float) (yAxis - (wid + squareX - xAxis) * sinAngel + (j - yAxis ) * cosAngel);
			if(topCol.contains(xNew, yNew) || midCol.contains(xNew, yNew) || botCol.contains(xNew, yNew)){
				return true;
			}
		}
		return false;
	}
	
	/*private void DrawFence (){
		int sizeWH = (int) (22*lvsPlayAreaRatio);
		drawToScreen.begin();
		if(yToCen == 0){
			for(int i = (int) (0 - (sizeWH - (xToCen % sizeWH))); i < xToCen; i+=sizeWH){
				for(int j = 0; j < phoneHeight; j+=sizeWH){
					drawToScreen.draw(AssetLoader.fenceSmall, i, j, sizeWH, sizeWH, 0, 0, 22, 22, false, false);
				}
			}
			for(int i = (int) (xToCen + curLvsPlayArWID); i < phoneWidth; i+=sizeWH){
				for(int j = 0; j < phoneHeight; j+=sizeWH){
					drawToScreen.draw(AssetLoader.fenceSmall, i, j, sizeWH, sizeWH, 0, 0, 22, 22, false, false);
				}
			}
		}
		else if(xToCen == 0){
			for(int i = 0; i < phoneWidth; i+=22){
				for(int j =  (int) (0 - (sizeWH - (yToCen % sizeWH))); j < yToCen; j+=sizeWH){
					drawToScreen.draw(AssetLoader.fenceSmall, i, j, sizeWH, sizeWH, 0, 0, 22, 22, false, false);
				}
			}
			for(int i = (int) (yToCen + curLvsPlayArHEI); i < phoneHeight; i+=sizeWH){
				for(int j = 0; j < phoneWidth; j+=sizeWH){
					drawToScreen.draw(AssetLoader.fenceSmall, i, j, sizeWH, sizeWH, 0, 0, 22, 22, false, false);
				}
			}
		}
		drawToScreen.end();
	}*/
	
	private static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	protected void CalculatePlayArRatio (float PLAY_AR_WID, float PLAY_AR_HEI){
		float ratioW = (float) (phoneWidth - phoneWidth*(float)0.05) / PLAY_AR_WID;
		float ratioH = (float) (phoneHeight - phoneHeight*(float)0.05) / PLAY_AR_HEI;
		//Take lowest to determine the playable area resolution
		lvsPlayAreaRatio = (ratioW < ratioH) ? ratioW : ratioH;
	}
	
	protected void SetScreenOptimizedLvsRes (float PLAY_AR_WID, float PLAY_AR_HEI){
		playArNewWidth = (float) (PLAY_AR_WID * lvsPlayAreaRatio);
		playArNewHeight = (float) (PLAY_AR_HEI * lvsPlayAreaRatio);
	}
	
	public void SetStars (LevelBase lvl){
		if(lvl instanceof Level1){
			if(Menu.prefs.getInteger("lvl1stars", 1) < difLvls.chosen - 1){
				Menu.prefs.putInteger("lvl1stars", difLvls.chosen - 1); 
				Menu.prefs.flush();
			}
		}
		
		if(lvl instanceof Level2){
			if(Menu.prefs.getInteger("lvl2stars", 1)< difLvls.chosen - 1){
				Menu.prefs.putInteger("lvl2stars", difLvls.chosen - 1); 
				Menu.prefs.flush();
			}
		}
		
		if(lvl instanceof Level3){
			if(Menu.prefs.getInteger("lvl3stars", 1)< difLvls.chosen - 1){
				Menu.prefs.putInteger("lvl3stars", difLvls.chosen - 1); 
				Menu.prefs.flush();
			}
		}
		
		if(lvl instanceof Level4){
			if(Menu.prefs.getInteger("lvl4stars", 1) < difLvls.chosen - 1){
				Menu.prefs.putInteger("lvl4stars", difLvls.chosen - 1); 
				Menu.prefs.flush();
			}
		}
		
		if(lvl instanceof Level5){
			if(Menu.prefs.getInteger("lvl5stars", 1)< difLvls.chosen - 1){
				Menu.prefs.putInteger("lvl5stars", difLvls.chosen - 1); 
				Menu.prefs.flush();
			}
		}
		
		if(lvl instanceof Level6){
			if(Menu.prefs.getInteger("lvl6stars", 1)< difLvls.chosen - 1){
				Menu.prefs.putInteger("lvl6stars", difLvls.chosen - 1); 
				Menu.prefs.flush();
			}
		}
		
		if(lvl instanceof Level7){
			if(Menu.prefs.getInteger("lvl7stars", 1)< difLvls.chosen - 1){
				Menu.prefs.putInteger("lvl7stars", difLvls.chosen - 1); 
				Menu.prefs.flush();
			}
		}
		
		if(lvl instanceof Level8){
			if(Menu.prefs.getInteger("lvl8stars", 1)< difLvls.chosen - 1){
				Menu.prefs.putInteger("lvl8stars", difLvls.chosen - 1); 
				Menu.prefs.flush();
			}
		}
		
		if(lvl instanceof Level9){
			if(Menu.prefs.getInteger("lvl9stars", 1)< difLvls.chosen - 1){
				Menu.prefs.putInteger("lvl9stars", difLvls.chosen - 1); 
				Menu.prefs.flush();
			}
		}
		
		if(lvl instanceof Level10){
			if(Menu.prefs.getInteger("lvl10stars", 1)< difLvls.chosen - 1){
				Menu.prefs.putInteger("lvl10stars", difLvls.chosen - 1); 
				Menu.prefs.flush();
			}
		}

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
