import java.awt.Point;
import java.util.Observable;
import java.util.Random;

public class Ship extends Observable {
	Point curLocation;
	OceanMap map; 
	Random rand = new Random();
	
	public Ship(OceanMap map) {
		this.map = map;
		map.ship = this; 
		while(true) {
			int x = rand.nextInt(map.dimensions);
			int y= rand.nextInt(map.dimensions);
			if(map.getMap()[x][y] != 1) {
				curLocation = new Point(x,y);
				break;
			}
		}
	}
	
	
	
	public Point location() {
		return this.curLocation; 
	}
	
	public void move(String s) {
		if(s.equals("EAST")) {
			if(curLocation.getX() < map.dimensions -1) {
				if(map.getMap()[curLocation.x+1] [curLocation.y] != 1) {
					curLocation.x++;
		}
		else if(s.equals("WEST")) {
			if(curLocation.getX() > 0) {
				if(map.getMap()[curLocation.x-1] [curLocation.y] != 1) {
					curLocation.x--;
		}
		else if(s.equals("NORTH")) {
			if(curLocation.y > 0) {
				if(map.getMap()[curLocation.x] [curLocation.y-1] != 1) {
					curLocation.y--;
		}
		else {
			if(curLocation.y < map.dimensions - 1) {
				if(map.getMap()[curLocation.x] [curLocation.y+1] != 1) {
					curLocation.y++;		
		}
		
		setChanged();
		notifyObservers(); 
	}
	
		}
				
			}
			}
				}
			}
				}
			}
		}
	
}
