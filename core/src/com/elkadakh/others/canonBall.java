package com.elkadakh.others;

public class canonBall {

	public float x, y, speed, ratio, xToCen, yToCen, OrinalLvWidth, OrinalLvHeight;
	
	public canonBall (float x, float y, float speed, float ratio, float xToCen, float yToCen, int OrinalLvWidth, int OrinalLvHeight){
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.ratio = ratio;
		this.xToCen = xToCen;
		this.yToCen = yToCen;
		this.OrinalLvWidth = OrinalLvWidth;
		this.OrinalLvHeight = OrinalLvHeight;
	}
	
	public boolean isUnderScreen (){
		return (y+11*ratio <= 0);
	}
	
	public void updateY (float delta){
		y-=speed*ratio*delta;
	}
	
	public boolean isInLevelBorders (){
		return !(x < xToCen || y < yToCen ||  x >= xToCen + OrinalLvWidth*ratio || y >= yToCen + OrinalLvHeight*ratio);
	}
}
