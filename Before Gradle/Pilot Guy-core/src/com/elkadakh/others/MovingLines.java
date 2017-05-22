package com.elkadakh.others;

public class MovingLines {

	public float initx1, initx2, inity1, inity2, speed, ratio, xToCen, yToCen, OrinalLvWidth, OrinalLvHeight;
	public float updatedX1, updatedX2, updatedY1, updatedY2;
	public static boolean isEasy = false;
	
	public MovingLines (float initx1, float initx2, float inity1,float inity2, float speed, 
			float ratio, float xToCen, float yToCen, int OrinalLvWidth, int OrinalLvHeight){
		this.ratio = ratio;
		this.speed = speed*ratio;
		this.xToCen = xToCen;
		this.yToCen = yToCen;
		this.initx1 = initx1*ratio + xToCen;
		this.initx2 = initx2*ratio + xToCen;
		this.inity1 = inity1*ratio + yToCen;
		this.inity2 = inity2*ratio + yToCen;
		this.OrinalLvWidth = OrinalLvWidth;
		this.OrinalLvHeight = OrinalLvHeight;
		updatedX1 = this.initx1;
		updatedX2 = this.initx2;
		updatedY1 = this.inity1;
		updatedY2 = this.inity2;
	}
	
	public void UpdateForHor (float delta){
		updatedY1 += delta*speed;
		updatedY2 += delta*speed;
		if(updatedY1 >= inity1 + 90*ratio && !isEasy){
			updatedY1 = inity1;
			updatedY2 = inity2;
		}
		if(updatedY1 >= inity1 + 149*ratio && isEasy){
			updatedY1 = inity1;
			updatedY2 = inity2;
		}
	}
	
	public void UpdateForVer (float delta, MovingLines line){
		updatedX1 += delta*speed;
		updatedX2 += delta*speed;
		if(updatedX1 >= initx1 + 150*ratio){
			updatedX1 = initx1;
			updatedX2 = initx2;
		}
		
		if(updatedX1 >= OrinalLvWidth*ratio+xToCen){
			line.updatedX1 = line.initx1;
			line.updatedX2 = line.initx2;
			updatedX1 = initx1;
			updatedX2 = initx2;
		}
	}
}
