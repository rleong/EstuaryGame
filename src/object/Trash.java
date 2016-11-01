package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;

public class Trash extends GameObject {
	int damage=5;
	double health=20;
	boolean canAttack=false;
	
	TrashBin trashBin;
	public Trash(double x, double y, ObjectId id,TrashBin trashBin) {
		super(x, y, id);
		this.trashBin=trashBin;
	}
	
	/*
	 * get damage
	 */
	public int getDamage(){
		return this.damage;
	}
	
	public void setDamage(int damage){
		this.damage=damage;
	}
	
	public void dead(){
		if(health<=0){
			setVelX((trashBin.getX()-x)/50);
			setVelY(-5);
			
			canAttack=false;
		}
		
	}
	@Override
	public void tick(LinkedList<GameObject> object) {
		
		x+=velX;
		y+=velY;
		if(health<=0){
			velY+=gravity*10;
		}

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, 32, 32);
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, (int) ((health/20)*32), 2);
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,32,32);
	}

}
