package com.elkadakh.others;

import java.util.List;

import screens.Level10;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Lvs10Balls {
	
	public int speed,initX,initY,pWidth,pHeight,curX,curY, ballCounter;
	public float ratio;
	private final int ballsHeight_Wid = 11, helisWidth = 74;
	
	public Lvs10Balls (int speed, int initX, int initY, float ratio, int pWidth, int pHeight, int ballCounter){
		this.speed = speed;
		this.initX = initX;
		this.initY = initY;
		this.ratio = ratio;
		this.pWidth = pWidth;
		this.pHeight = pHeight;
		this.ballCounter = ballCounter;
		
		curX = this.initX;
		curY = this.initY;
		if(ballCounter % 2 == 0)
			curX = this.initX + (int)((helisWidth - ballsHeight_Wid)*ratio);
	}
	
	public void Update (float delta,List<Lvs10Balls> ballsList){
		curY -= (int)(speed*delta);
		RemoveUnderZero(ballsList);
	}
	
	public Vector2 CollCheckWithCoords (float transedHeliX, float transedHeliY, Level10 lv10Ref, int xToCen, int yToCen){
		if(lv10Ref.isCollision((int)((curX-xToCen)/ratio), (int)((curY-yToCen)/ratio), 
				ballsHeight_Wid, ballsHeight_Wid, transedHeliX, transedHeliY)){
			return new Vector2(transedHeliX,transedHeliY);
		}
		return new Vector2(-1,-1); //No collision flag
	}
	
	public void DrawBall (SpriteBatch sb, int bgNum){
		Texture ball = AssetLoader.canonBall;
		if(bgNum == 2)
			ball = AssetLoader.canonBallWhite;
		sb.begin();
		sb.draw(ball, curX, curY, (int)(ballsHeight_Wid*ratio), (int)(ballsHeight_Wid*ratio), 
				0, 0, ballsHeight_Wid, ballsHeight_Wid, false, false);
		sb.end();
	}
	
	private void RemoveUnderZero (List<Lvs10Balls> ballsList){
		if(curY + (int)(ballsHeight_Wid*ratio) <= 0)
			ballsList.remove(this);
	}
}
