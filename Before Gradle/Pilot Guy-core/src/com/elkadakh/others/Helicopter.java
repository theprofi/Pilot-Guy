package com.elkadakh.others;

import com.badlogic.gdx.math.Vector2;

public class Helicopter {

	private float rotation; 
	private float lvsPlayArRatio;
	private boolean reversed;
	public boolean vertical, horizontal;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private float Ver_Vel_Cap = 45;//-45
	private float Ver_Acc = -25;//-25
	private float Ver_Vel_Push = 50;//50
	
	private float Hor_Vel_Cap = 45;//45
	private float Hor_Acc = +25;//+25
	private float Hor_Vel_Push = -50;//-50
	
	private float rotationUnits = 50;//50
	private float maxRotation = (float) 45;//45
	
	private boolean isEasy;
	
	public Helicopter (float ratio) {
		lvsPlayArRatio = ratio;
		isEasy = false;
		TransUnits();
		vertical = false;
		horizontal = false;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(Hor_Acc, Ver_Acc);
        reversed = false;
	}
	
	private void TransUnits (){
		Ver_Vel_Cap *= lvsPlayArRatio; 
		Ver_Acc *= lvsPlayArRatio; 
		Ver_Vel_Push *= lvsPlayArRatio; 
		
		Hor_Vel_Cap *= lvsPlayArRatio; 
		Hor_Acc *= lvsPlayArRatio; 
		Hor_Vel_Push *= lvsPlayArRatio; 
	}
	
    public void update(float delta) {
	    HorizontalAcceleration(delta);
	    if(horizontal){
	    	HorizontalPush(delta);
		    rotation += rotationUnits * delta;
	    }
	    else{
		    rotation -= rotationUnits * delta;
	    }
	    SetHorizontalPos(delta);
	    
	    VerticalAcceleration(delta);
	    if(vertical){
	    	VerticalPush(delta);
	    }

	    SetVerticalPos(delta);
	    LimitRotation();
	    horizontal = false;
	    vertical = false;
    }
    
    private void LimitRotation (){
	    if(rotation > maxRotation) rotation = maxRotation;
	    else if(rotation < -maxRotation) rotation = -maxRotation;
    }
    
    private void VerticalAcceleration (float delta){
    	if(isEasy){
    		if(reversed){
    			if(velocity.y < 0)
    				velocity.y = 5;
        		velocity.y += Ver_Vel_Push * delta;
    		}
        	else{
        		if(velocity.y > 0 && !vertical)
    				velocity.y = -5;
        		velocity.y -= Ver_Vel_Push/2 * delta ;
        	}
    	}
    	else{
	    	if(reversed)
	    		velocity.y -= acceleration.y * delta;
	    	else
	    		velocity.y += acceleration.y * delta;
    	}
    }
    
    private void VerticalPush (float delta){
    	if(isEasy){
    		if(reversed){
    			if(velocity.y > 0 && !vertical)
    				velocity.y = -5;
        		velocity.y -= Ver_Vel_Push/2 * delta ;
    		}
    		else{
    			if(velocity.y < 0)
    				velocity.y = 5;
        		velocity.y += Ver_Vel_Push * delta;
        	}
    	}
    	else{
	    	if(reversed)
	    		velocity.y -= Ver_Vel_Push * delta;
	    	else
	    		velocity.y += Ver_Vel_Push * delta;
    	}
    }
    
    private void SetVerticalPos (float delta){
	    if (velocity.y > Ver_Vel_Cap) {
	        velocity.y = Ver_Vel_Cap;
	    }
	    else if (velocity.y < -Ver_Vel_Cap) {
            velocity.y = -Ver_Vel_Cap;
       	}
	    position.y += velocity.y * delta;
    }
    
    private void SetHorizontalPos (float delta){
	    if (velocity.x > Hor_Vel_Cap) {
	        velocity.x = Hor_Vel_Cap;
	    }
	    else if (velocity.x < -Hor_Vel_Cap) {
            velocity.x = -Hor_Vel_Cap;
       	}
	    position.x += velocity.x * delta;
    }
    
	public void SetEasy (){
		isEasy = true;
		Ver_Vel_Cap = 55*lvsPlayArRatio;//-45
		Ver_Acc = -15*lvsPlayArRatio;//-25
		Ver_Vel_Push = 25*lvsPlayArRatio;//50
		
		Hor_Vel_Cap = 45*lvsPlayArRatio;//45
		Hor_Acc = +20*lvsPlayArRatio;//+25
		Hor_Vel_Push = -20*lvsPlayArRatio;//-50
		
		rotationUnits = 35;//50
		maxRotation = (float) 45;//45
	}
	
	public void SetMedium (){
		isEasy = false;
		Ver_Vel_Cap = 40*lvsPlayArRatio;//-45
		Ver_Acc = -22*lvsPlayArRatio;//-25
		Ver_Vel_Push = 44*lvsPlayArRatio;//50
		
		Hor_Vel_Cap = 40*lvsPlayArRatio;//45
		Hor_Acc = +22*lvsPlayArRatio;//+25
		Hor_Vel_Push = -44*lvsPlayArRatio;//-50
		
		rotationUnits = 50;//50
		maxRotation = (float) 45;//45
	}
	
	public void SetHard (){
		isEasy = false;
		Ver_Vel_Cap = 60*lvsPlayArRatio;//-45
		Ver_Acc = -33*lvsPlayArRatio;//-25
		Ver_Vel_Push = 66*lvsPlayArRatio;//50
		
		Hor_Vel_Cap = 60*lvsPlayArRatio;//45
		Hor_Acc = +28*lvsPlayArRatio;//+25
		Hor_Vel_Push = -56*lvsPlayArRatio;//-50
		
		rotationUnits = 56;//50
		maxRotation = (float) 50;//45
	}
	
	public void SetVeryHard (){
		isEasy = false;
		Ver_Vel_Cap = 89*lvsPlayArRatio;//-45
		Ver_Acc = -45*lvsPlayArRatio;//-25
		Ver_Vel_Push = 90*lvsPlayArRatio;//50
		
		Hor_Vel_Cap = 89*lvsPlayArRatio;//45
		Hor_Acc = +45*lvsPlayArRatio;//+25
		Hor_Vel_Push = -90*lvsPlayArRatio;//-50
		
		rotationUnits = 65;//50
		maxRotation = (float) 55;//45
	}
    
    public void ResetVelocityRotation (){
    	velocity.x = 0;
    	velocity.y = 0;
    	rotation = 0;
    }
    
    public void ResetVelocity (){
    	velocity.x = 0;
    	velocity.y = 0;
    }
    
    public void ToggleReverse (){
    	reversed = !reversed;
    }
    
    public void SetPosition (float x, float y){
    	position = new Vector2(x,y);
    }
    
    private void HorizontalAcceleration (float delta){
    	if(isEasy){
    		if(velocity.x < 0 && !horizontal)
				velocity.x = 5;
    		velocity.x += Hor_Acc/2 * delta;
    	}
        else
        	velocity.x += acceleration.x * delta;
    }
    
    public void HorizontalPush (float delta){
     	if(isEasy){
    		if(velocity.x > 0)
				velocity.x = -5;
    		velocity.x -= Hor_Acc * delta;
    	}
    	else
    		velocity.x += Hor_Vel_Push * delta;
    }
    
    public boolean GetReversedStatus (){
    	return reversed;
    }
    
    public void SetRotation (float rotation){
    	this.rotation = rotation;
    }

    public float GetX() {
        return position.x;
    }

    public float GetY() {
        return position.y;
    }
    
    public float GetRotation (){
    	return rotation;
    }
}
