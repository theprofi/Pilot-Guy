package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.elkadakh.others.AssetLoader;
import com.elkadakh.others.DifficultyLevels;
import com.elkadakh.others.Helicopter;

public class Howto extends LevelBase {
	protected static final int PLAY_AR_WID = 297, PLAY_AR_HEI = 385;
	protected int targetX, targetY;
	private boolean isFalling;
	private ShapeRenderer linesRenderer;
	int widthOfTarget;
	
	public Howto (){
		xToCen = 0;
		yToCen = 0;
		linesRenderer = new ShapeRenderer();
		super.CalculatePlayArRatio(PLAY_AR_WID, PLAY_AR_HEI); //Sets "lvsPlayAreaRatio"
        helicopter = new Helicopter(super.lvsPlayAreaRatio);
        super.SetScreenOptimizedLvsRes(PLAY_AR_WID, PLAY_AR_HEI); //Uses the ratio of screen/level
		borderLine = new boolean[PLAY_AR_HEI][PLAY_AR_WID];
		playArrWid = PLAY_AR_WID;
		playArrHeight = PLAY_AR_HEI;
		isFalling = false;
		nextLevelSuspend = 0; //2 secs
		AssetLoader.subText.setScale(1f, 1f);
		AssetLoader.subTextSmall.setScale(1f, 1f);
		heliPosX = (int)(phoneWidth/2 - (int)(34*lvsPlayAreaRatio)/1);
		heliPosY = (int)(phoneHeight/2/1);
		helicopter.SetPosition(heliPosX, heliPosY);
		difLvls = new DifficultyLevels(drawToScreen,helicopter,phoneWidth,phoneHeight);
	}
	
	public void render(float delta){
		super.isFalling = isFalling;
		super.render(delta);
		DrawArrows();
		DrawDivider();
		DrawSides();
		DrawBackButton();
		if(lvsPlayAreaRatio < 0.9f)
			widthOfTarget = (int)(28*lvsPlayAreaRatio/0.8);
		else
			widthOfTarget = (int)(35*lvsPlayAreaRatio);
		difLvls.DoThings(bgNum, false, widthOfTarget, (int)(targetX*lvsPlayAreaRatio + xToCen),(int)(targetY*lvsPlayAreaRatio + yToCen), delta,false);
		
		if(helicopter.GetX() > phoneWidth || helicopter.GetX() + 74*lvsPlayAreaRatio < 0 
				|| helicopter.GetY() > phoneHeight || helicopter.GetY() + 34*lvsPlayAreaRatio < 0){
			startMovingHeli = false;
			helicopter.ResetVelocityRotation();
			helicopter.SetPosition(heliPosX*1, heliPosY*1);
		}
	}
	
	private void DrawSides(){
		if((difLvls == null ||
				!(Gdx.input.getX() >= difLvls.dif1PosX && Gdx.input.getX() <= difLvls.dif1PosX + difLvls.size && phoneHeight - Gdx.input.getY() >= difLvls.dif1PosY && phoneHeight - Gdx.input.getY() <= difLvls.dif1PosY + difLvls.size)&&
				!(Gdx.input.getX() >= difLvls.dif2PosX && Gdx.input.getX() <= difLvls.dif2PosX + difLvls.size && phoneHeight - Gdx.input.getY() >= difLvls.dif2PosY && phoneHeight - Gdx.input.getY() <= difLvls.dif2PosY + difLvls.size) &&
				!(Gdx.input.getX() >= difLvls.dif3PosX && Gdx.input.getX() <= difLvls.dif3PosX + difLvls.size && phoneHeight - Gdx.input.getY() >= difLvls.dif3PosY && phoneHeight - Gdx.input.getY() <= difLvls.dif3PosY + difLvls.size) &&
				!(Gdx.input.getX() >= difLvls.dif4PosX && Gdx.input.getX() <= difLvls.dif4PosX + difLvls.size && phoneHeight - Gdx.input.getY() >= difLvls.dif4PosY && phoneHeight - Gdx.input.getY() <= difLvls.dif4PosY + difLvls.size)
				)){
			Gdx.gl.glEnable(GL20.GL_BLEND);
		    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			linesRenderer.begin(ShapeType.Filled);
			linesRenderer.setColor(new Color(0, 1, 0, 0.2f));
			if(Gdx.input.isTouched(0) && Gdx.input.getX(0) > phoneWidth/2 || Gdx.input.isTouched(1) && Gdx.input.getX(1) > phoneWidth/2)
				linesRenderer.rect(phoneWidth/2 + 5, 0, phoneWidth - phoneWidth/2 - 5, phoneHeight);
			if(Gdx.input.isTouched(0) && Gdx.input.getX(0) < phoneWidth/2 || Gdx.input.isTouched(1) && Gdx.input.getX(1) < phoneWidth/2)
				linesRenderer.rect(0, 0, phoneWidth - phoneWidth/2, phoneHeight);
			linesRenderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);
		}
	}
	
	private void DrawDivider (){
		linesRenderer.begin(ShapeType.Filled);
		linesRenderer.setColor((float)1,(float)1,(float)1,1);
		linesRenderer.rect(phoneWidth/2, 0, 5, phoneHeight);
		linesRenderer.end();
	}
	
	private void DrawArrows () {
		boolean notouchonDiffs = (difLvls == null ||
				!(Gdx.input.getX() >= difLvls.dif1PosX && Gdx.input.getX() <= difLvls.dif1PosX + difLvls.size && phoneHeight - Gdx.input.getY() >= difLvls.dif1PosY && phoneHeight - Gdx.input.getY() <= difLvls.dif1PosY + difLvls.size)&&
				!(Gdx.input.getX() >= difLvls.dif2PosX && Gdx.input.getX() <= difLvls.dif2PosX + difLvls.size && phoneHeight - Gdx.input.getY() >= difLvls.dif2PosY && phoneHeight - Gdx.input.getY() <= difLvls.dif2PosY + difLvls.size) &&
				!(Gdx.input.getX() >= difLvls.dif3PosX && Gdx.input.getX() <= difLvls.dif3PosX + difLvls.size && phoneHeight - Gdx.input.getY() >= difLvls.dif3PosY && phoneHeight - Gdx.input.getY() <= difLvls.dif3PosY + difLvls.size) &&
				!(Gdx.input.getX() >= difLvls.dif4PosX && Gdx.input.getX() <= difLvls.dif4PosX + difLvls.size && phoneHeight - Gdx.input.getY() >= difLvls.dif4PosY && phoneHeight - Gdx.input.getY() <= difLvls.dif4PosY + difLvls.size)
				);
		
		drawToScreen.begin();
		if(phoneWidth > 1500){
			if((Gdx.input.isTouched(0) && Gdx.input.getX(0) >= phoneWidth / 2 || Gdx.input.isTouched(1) &&  Gdx.input.getX(1) >= phoneWidth / 2) && notouchonDiffs)
				drawToScreen.draw(AssetLoader.upGreen, phoneWidth - 235, phoneHeight / 2 + 10, 
						(int)(48*lvsPlayAreaRatio), 
						(int)(83*lvsPlayAreaRatio), 0, 0, 48, 83, false, false);
			else
				drawToScreen.draw(AssetLoader.up, phoneWidth - 235, phoneHeight / 2 + 10, 
						(int)(48*lvsPlayAreaRatio), 
						(int)(83*lvsPlayAreaRatio), 0, 0, 48, 83, false, false);
			if(Gdx.input.isTouched(0) && Gdx.input.getX(0) < phoneWidth / 2 || Gdx.input.isTouched(1) &&  Gdx.input.getX(1) < phoneWidth / 2)
				drawToScreen.draw(AssetLoader.leftGreen, 60 , phoneHeight / 2 , 
						(int)(83*lvsPlayAreaRatio), 
						(int)(48*lvsPlayAreaRatio), 0, 0, 83, 48, false, false);
			else
				drawToScreen.draw(AssetLoader.left, 60 , phoneHeight / 2, 
						(int)(83*lvsPlayAreaRatio), 
						(int)(48*lvsPlayAreaRatio), 0, 0, 83, 48, false, false);
		}
		else{
			if((Gdx.input.isTouched(0) && Gdx.input.getX(0) >= phoneWidth / 2 || Gdx.input.isTouched(1) &&  Gdx.input.getX(1) >= phoneWidth / 2) && notouchonDiffs)
				drawToScreen.draw(AssetLoader.upGreen, phoneWidth - 140, phoneHeight / 2, 
						(int)(48*lvsPlayAreaRatio), 
						(int)(83*lvsPlayAreaRatio), 0, 0, 48, 83, false, false);
			else
				drawToScreen.draw(AssetLoader.up, phoneWidth - 140, phoneHeight / 2, 
						(int)(48*lvsPlayAreaRatio), 
						(int)(83*lvsPlayAreaRatio), 0, 0, 48, 83, false, false);
			if(Gdx.input.isTouched(0) && Gdx.input.getX(0) < phoneWidth / 2 || Gdx.input.isTouched(1) &&  Gdx.input.getX(1) < phoneWidth / 2)
				drawToScreen.draw(AssetLoader.leftGreen, 60 , phoneHeight / 2 , 
						(int)(83*lvsPlayAreaRatio), 
						(int)(48*lvsPlayAreaRatio), 0, 0, 83, 48, false, false);
			else
				drawToScreen.draw(AssetLoader.left, 60 , phoneHeight / 2, 
						(int)(83*lvsPlayAreaRatio), 
						(int)(48*lvsPlayAreaRatio), 0, 0, 83, 48, false, false);
		}
		if(phoneWidth > 1500){
			AssetLoader.subText.draw(drawToScreen, "Touch and hold this side",phoneWidth - 380, phoneHeight / 2);
			AssetLoader.subText.draw(drawToScreen, "to go up", phoneWidth - 250, phoneHeight / 2 - 35);
			AssetLoader.subText.draw(drawToScreen, "Touch and hold this side", 20 , phoneHeight / 2);	
			AssetLoader.subText.draw(drawToScreen, "to go left", 80 , phoneHeight / 2 - 35);	
		}
		else{
			AssetLoader.subTextSmall.draw(drawToScreen, "Touch and hold this side", phoneWidth - 180, phoneHeight / 2 - 20);
			AssetLoader.subTextSmall.draw(drawToScreen, "to go up", phoneWidth - 120, phoneHeight / 2 - 40);
			AssetLoader.subTextSmall.draw(drawToScreen, "Touch and hold this side", 20 , phoneHeight / 2 - 20);	
			AssetLoader.subTextSmall.draw(drawToScreen, "to go left", 50 , phoneHeight / 2 - 40);	
		}
		drawToScreen.end();
		
	}
}