import java.awt.Point;
import java.util.Random;

import javafx.scene.layout.Pane;


public class OceanMap {
	int[][] islands; 
	int dimensions;
	int scale;
	Random rand = new Random(); 
	Point location;
	Ship ship; 
	Pane root; 
	
	public OceanMap(int dimensions, int scale) {
		this.dimensions = dimensions; 
		this.scale = scale; 
		islands = new int[dimensions][dimensions]; 
		 
	}
	
	
	public int[][] getMap() {
		return islands; 
	}
	
	
}
