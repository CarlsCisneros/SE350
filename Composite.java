import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Composite extends Rectangle implements Component{
	
	ArrayList<Component> circlesInside = new ArrayList<>();
	Color color;
	Random GenColor = new Random();
	
	public Composite(double x, double y, int w, int h) {
		super(x,y,w,h);
		this.color = RandomColor();
		this.setStrokeWidth(6);
		this.setStroke(this.color);
		this.setFill(Color.TRANSPARENT);
	}

	@Override
	public void move(double deltaX, double deltaY) {
		this.setX(this.getX() + deltaX);
		this.setY(this.getY() + deltaY);
		Iterator<Component> iter = circlesInside.iterator();
		while(iter.hasNext()) {
			Component circle = iter.next();
			if(circle instanceof DragCircle) ((DragCircle) circle).move(deltaX, deltaY);
 		}
	}

	@Override
	public Point2D getLocation() {
		return new Point2D(this.getX(), this.getY());
	}
	///changing the color of the circle once dragged into a colored square
	public void addCircle(Component circle) {
		circlesInside.add(circle);
		Iterator<Component> changeCircle = circlesInside.iterator();
		while(changeCircle.hasNext()) {
			Component c = changeCircle.next();
			if(c instanceof DragCircle) ((DragCircle)  c).ChangeColor(this.color);
		}
		
	}
	
	public boolean CheckCircle(Component circle) {
		if(circle instanceof DragCircle) {
			if(this.contains(((DragCircle) circle).getCenterX(), ((DragCircle) circle).getCenterY())){
				return true;
			}
		}
		return false;
	}
	///random color generator for all the squares 
	public Color RandomColor() {
		float r = GenColor.nextFloat();
		float g = GenColor.nextFloat();
		float b = GenColor.nextFloat();
		float a = GenColor.nextFloat();
		
		while(r == 1.0 || g == 1.0  || b == 1.0 ) {
			r  = GenColor.nextFloat();
			g  = GenColor.nextFloat();
			b  = GenColor.nextFloat();
		}
		
		return new Color(b, g, r, a);		
	}
	
	
}
