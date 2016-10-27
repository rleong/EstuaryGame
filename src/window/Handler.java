package window;

import java.awt.Graphics;
import java.util.LinkedList;

import framework.GameObject;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject temp;
	
	public void tick(){
		for(int i=0; i<object.size();i++){
			temp = object.get(i);
			
			temp.tick(object);
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
}
