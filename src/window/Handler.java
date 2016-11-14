package window;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import object.LandSurface;
import object.Tree;
import object.WaterTree;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject temp;
	Game game;
	public Handler(Game game){
		this.game=game;
	}
	
	public void tick(){
		
		for(int i=0; i<object.size();i++){
			temp = object.get(i);
			
			temp.tick(object);
		}
		
		for(int i=0;i<object.size();i++){
			temp = object.get(i);
			if(temp.getId()==ObjectId.tree){
				Tree tree = (Tree)temp;
				if(tree.hp<=0){
					object.remove(temp);
				}
			}
			if(temp.getId()==ObjectId.waterTree){
				WaterTree tree = (WaterTree)temp;
				if(tree.hp<=0){
					object.remove(temp);
					game.trees-=1;
				}
			}
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
	
	public void creatSurface(Dimension dm){
		int i=0;
		for(;i<dm.getWidth()*3/4;i+=32){
			for(double j=dm.getHeight()*3/5;j<dm.getHeight();j+=32){
				addObject(new LandSurface(i, j, ObjectId.landSurface, game));
			}
			
		}
		for(double j=dm.getHeight()*3/5;j<dm.getHeight();j+=32){
			addObject(new LandSurface(i-32,j,ObjectId.wall, game));
		}
		for(;i<=dm.getWidth();i+=32){
			for(double j=dm.getHeight()*3/5;j<dm.getHeight()-64;j+=32){
				addObject(new LandSurface(i,j, ObjectId.seaLevel, game));
			}
			for(double j=dm.getHeight()-96;j<dm.getHeight();j+=32){
				addObject(new LandSurface(i,j,ObjectId.Sand, game));
			}
		}
	}
}
