package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	protected ObjectId id;
	protected double x,y;
	protected double velX = 0, velY = 0;
	
	public GameObject(double x, double y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	
	public abstract void render(Graphics g);
	
	public abstract Rectangle getBounds();
	
	public double getX() {
		return x;
	}
	public  double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y=y;
	}
	
	
	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}
	
	public void setVelX(double x) {
		this.velX = velX;
	}
	public void setVelY(double y) {
		this.velY = velY;
	}
	
	public ObjectId getId() {
		return id;
	}

}
