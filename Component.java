import javafx.geometry.Point2D;

public interface Component {
	public void move(double deltaX, double deltaY);
	public Point2D getLocation();
}
