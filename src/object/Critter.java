package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Critter extends GameObject {
	int landCo=1;
	int waterCo=2;
	
	Game game;
	public int seed1=0;
	public int seed2=0;
	boolean xdir;
	boolean ydir;
	int character;
	int health0;
	int health1;
	int health2;
	int damage;
	public boolean jump;
	private Handler handler;
	public boolean onLand;
	public boolean inWater;
	Dimension dm;
	/*
	 * Critter constructor
	 * 
	 */
	public Critter(double x, double y, ObjectId id,boolean xdir, boolean ydir,Handler handler, Dimension dm, Game game) {
		super(x, y, id);
		this.xdir=xdir;
		this.ydir=ydir;
		character=0;
		setDamage();
		health0=100;
		health1=100;
		health2=100;
		this.handler=handler;
		jump=false;
		inWater=false;
		this.dm = dm;
		this.game=game;
	}
	
	public void setDamage(){
		switch(character){
		case 0:
			damage=20;
			break;
		case 1:
			damage=10;
			break;
		case 2:
			damage=10;
			break;
		}
	}
	public void changeCharacter(){
		character+=1;
		character=character%3;
		//System.out.println(character);
		setDamage();
	}
	public int getDamage(){
		return this.damage;
	}
	/*
	 * set character depends on character token
	 */
	public void setCharacter(int character){
		this.character=character;
	}
	
	/*
	 * ability switch depends on character token
	 */
	private void ability(int character){
		switch(character){
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		}
	}
	
	/*
	 * attack enemy
	 */
	public void attack(LinkedList<GameObject> object){
		for(int i=0;i<object.size();i++){
			GameObject temp = object.get(i);
			if(temp.getId()==ObjectId.trash){
				Trash trash = (Trash)temp;
				if(trash.canAttack){
					trash.health-=damage;
				}
				if(trash.health<=0){
					trash.dead();
				}
				
			}
			if(temp.getId()==ObjectId.waterTree){
				WaterTree wt = (WaterTree)temp;
				if(wt.canAttack){
					wt.hp-=damage;
				}
				if(wt.hp<=0)
					wt.dead();
			}
		}
	}
	
	
	public void planT(int type){
		handler.addObject(new Tree(x,y,ObjectId.tree,type,game));
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x+=velX;
		y+=velY;
		if(x>dm.getWidth()*3/4-64){
			onLand=false;
			falling=true;
		}
		
		if(falling ||(falling && x>dm.getWidth()*3/4)){
			velY+=gravity;
		}
		
		if(y<dm.getHeight()*3/5-48 && x>dm.getWidth()*3/4-32){
			setY(dm.getHeight()*3/5-48);
			setVelY(0);
			jump=true;
		}
		
		collision(object);

	}
	
	private void collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ObjectId.landSurface){
				if(getBoundsBottom().intersects(temp.getBounds())){
					setY(temp.getY()-31);
					falling=false;
					setVelY(0);
					jump=false;
					onLand=true;
					
				}
			}
			if(temp.getId()== ObjectId.seaLevel){
				if(getBoundsSelf().intersects(temp.getBounds())){
					
					
					jump = false;
					
					
				}
				
				
			}
			if(temp.getId() == ObjectId.Sand){
				if(getBoundsBottom().intersects(temp.getBounds())){
					setY(temp.getY()-31);
					setVelY(0);
					jump=false;
				}
			}
			if(temp.getId() == ObjectId.trash){
				Trash trash = (Trash)temp;
				if(getBounds().intersects(temp.getBounds())){
					trash.canAttack=true;
					//System.out.println("in range");
				}
				if(!getBounds().intersects(temp.getBounds())){
					trash.canAttack=false;
					//System.out.println("out of range");
				}
			}
			if(temp.getId()==ObjectId.wall){
				if(getBoundsLeft().intersects(temp.getBounds())){
					setX(dm.getWidth()*3/4);
					setVelX(0);
				}
			}
			if(temp.getId()==ObjectId.seed){
				Seed seed= (Seed)temp;
				if(getBoundsSelf().intersects(temp.getBounds())){
					switch(seed.type){
					case 0:
						seed1+=1;
						break;
					case 1:
						seed2+=2;
						break;
					}
					object.remove(temp);
				}
			}
		}
	}
	
	

	@Override
	public void render(Graphics g) {
		switch(character){
		case 0:
			g.setColor(Color.RED);
			g.fillRect((int)x, (int)y, 32, 32);
			break;
		case 1:
			g.setColor(Color.CYAN);
			g.fillRect((int)x, (int)y, 32, 32);
			break;
		case 2:
			g.setColor(Color.lightGray);
			g.fillRect((int)x, (int)y, 32, 32);
			break;
		}
		
		
		
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(Color.green);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		
		g.setColor(Color.gray);
		g2d.draw(getBounds());
	}

	@Override
	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x-16,(int)y-16, 64,64);
	}
	
	public Rectangle getBoundsSelf(){
		return new Rectangle((int)x, (int)y, 32,32);
	}
	
	public Rectangle getBoundsTop() {
		
		return new Rectangle((int)x+6,(int)y, 20,6);
	}
	public Rectangle getBoundsBottom() {
		
		return new Rectangle((int)x+6,(int)y+26, 20,6);
	}
	
	public Rectangle getBoundsLeft() {
		
		return new Rectangle((int)x,(int)y+6, 6,20);
	}
	public Rectangle getBoundsRight() {
		
		return new Rectangle((int)x+26,(int)y+6, 6,20);
	}

}
