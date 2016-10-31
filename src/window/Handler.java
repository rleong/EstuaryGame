package window;

import java.awt.Graphics;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import object.LandSurface;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject temp;
	
	public void tick(){
		for(int i=0; i<object.size();i++){
			temp = object.get(i);
			
			temp.tick(object);
		}
		if(temp.getX()>2000 || temp.getX()<-2000 || temp.getY()>2000||temp.getY()<-2000){
			object.remove(temp);
		}
	}
	
	
	public void render(Graphics g){
		for(int i=0; i<object.size();i++){
			temp=object.get(i);
			temp.render(g);
		}
	}
	
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public void creatSurface(){
		for(int i=0;i<9;i++){
			addObject(new LandSurface(i*30, 300, ObjectId.landSurface));
			addObject(new LandSurface(i*30+270, 450, ObjectId.landSurface));
		}
	}
}
