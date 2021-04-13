package uk.ac.uos.n6assignment2723297;

import java.awt.Graphics2D;

import game2D.*;

public class Player extends Sprite{
	
	
	Animation anim=new Animation();
	String action;
	String position;
	int x;
	int y;
	int life;
	
	
	public Player(Animation anim)
	{
		super(anim);
		
		
	}

	public void setAnimation(String action,String position)
	{
		if(position=="left")
		{
			switch (action) {
			case "running":
			anim.loadAnimationFromSheetOpposite("images/gothic-hero-run-left.png", 12, 1, 60);	
			break;
			
			case "idle":
		    anim.loadAnimationFromSheetOpposite("images/gothic-hero-idle-left.png", 4, 1, 60);
			break;
			
			case "attack":
		    anim.loadAnimationFromSheetOpposite("images/gothic-hero-attack-left.png",6,1,90);
			break;
			
			case "die":
			anim.loadAnimationFromSheetOpposite("images/gothic-hero-die-left.png",3,1,120);
			break;
			}
			
		}
		else{
			switch (action) {
			case "running":
			anim.loadAnimationFromSheet("images/gothic-hero-run.png", 12, 1, 60);	
			break;
			
			case "idle":
		    anim.loadAnimationFromSheet("images/gothic-hero-idle.png", 4, 1, 60);
			break;
			
			case "attack":
		    anim.loadAnimationFromSheet("images/gothic-hero-attack.png",6,1,90);
			break;
			
			case "die":
			anim.loadAnimationFromSheet("images/gothic-hero-die.png",3,1,120);
			break;
			}
		
		}
	}
	
	public String getPosition()
	{
		return position;
	}
	
	public void setPosition(String p)
	{
	      position=p;
	}
	public String getAction()
	{
		return action;
	}
	
	public void setAction(String a)
	{
	      action=a;
	}
	
	public int getLife()
	{
		return life;
	}
	
	public void setLife(int l)
	{
		life=l;
	}
	

	

}
