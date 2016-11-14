package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import window.Handler;

public class Runoff extends GameObject {
	Handler handler;
	public int type;
	public Runoff(double x, double y,Dimension dm, Handler handler, ObjectId id, int type) {
		super(x, y, id);
		this.handler=handler;
		setVelX(1.2);
		this.type=type;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		if(falling)
			velY+=gravity;
		x+=velX;
		y+=velY;
		collision(handler.object);
	}

	@Override
	public void render(Graphics g) {
		switch(type){
		case 0:
			g.setColor(Color.magenta);
			break;
		case 1:
			g.setColor(Color.white);
			break;
		}
		
		g.fillRect((int)x, (int)y, 32, 32);

	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	private void collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ObjectId.landSurface){
				if(getBounds().intersects(temp.getBounds())){
					falling=false;
					velY=0;
					setY(temp.getY()-32);
				}
				else{
					falling=true;
				}
			}
			if(temp.getId() == ObjectId.Sand){
				if(getBounds().intersects(temp.getBounds())){
					falling=false;
					velY=0;
					setY(temp.getY()-32);
				}
				else{
					falling=true;
				}
			}
			
		}	
	}

}
