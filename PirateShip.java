import java.awt.Point;
import java.util.Observable; 
import java.util.Observer;
import java.util.Random;

public class PirateShip implements Observer {
	Point PirateShipL;
	Point shipLocation;
	OceanMap ocean; 
	Random rand = new Random();
	
	public PirateShip(OceanMap ocean) {
		this.ocean = ocean; 
		while(true) {
			int x = rand.nextInt(ocean.dimensions);
			int y = rand.nextInt(ocean.dimensions);
			if(ocean.getMap()[x][y] != 1) {
				PirateShipL = new Point(x,y);
				break;
			}
		}
	}
	
	public Point PirateShipLocation() {
		return this.PirateShipL;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Ship) {
			shipLocation = ((Ship)o).location();
			movePirateShip();
			
		}
	}
	
	public void movePirateShip() {
		if(PirateShipL.x - shipLocation.x == 0) {
			
		}
		else if(PirateShipL.x - shipLocation.x < 0) {
			if(PirateShipL.x < 24 && ocean.getMap()[PirateShipL.x+1][PirateShipL.y]!=1) {
				PirateShipL.x++; 
			}
		}
		else if(PirateShipL.x > 0 && ocean.getMap()[PirateShipL.x-1][PirateShipL.y] != 1) {
			PirateShipL.x--; 
		}
		
		if(PirateShipL.y - shipLocation.y == 0) {
			
		}
		else if(PirateShipL.y - shipLocation.y < 0) {
			if(PirateShipL.y < 24 && ocean.getMap()[PirateShipL.x][PirateShipL.y+1]!=1) {
				PirateShipL.y++; 
			}
		}
		else if(PirateShipL.y > 0 && ocean.getMap()[PirateShipL.x][PirateShipL.y+1] != 1) {
			PirateShipL.y--; 
		}
		
	}
	
}
