import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ComponentMain extends Application {
	
	Point2D lastPosition = null;

	private final double CircleR = 12; 
	private final int RectW = 150; 
	private final int RectH = 150; 
	Scene scene;
	Pane root;
	Component shape;
	ArrayList<Component> Shapes = new ArrayList<>();
	
	boolean dragging = false;
	
	Composite rec;
	DragCircle cir;
	
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
		
		@Override
		public void handle(MouseEvent mouseEvent) {
			Point2D clickPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
			String eventName = mouseEvent.getEventType().getName();
			if(dragging == false) shape = getShape(clickPoint);
			
			switch(eventName) {
			///once mouse is dragged
			case("MOUSE_DRAGGED"):
				dragging = true;
				if(shape != null && lastPosition != null) {
					double deltaX = clickPoint.getX() - lastPosition.getX();
					double deltaY = clickPoint.getY() - lastPosition.getY();
					shape.move(deltaX, deltaY);
				}
				break;
			///Once mouse is released
			case("MOUSE_RELEASED"):
					if(shape == null) {
						if(mouseEvent.getButton() == MouseButton.PRIMARY) {
							DragCircle circle = new DragCircle(clickPoint,CircleR,Color.BLACK);
							Shapes.add(circle);
							root.getChildren().add(circle);
						}
						if(mouseEvent.getButton() == MouseButton.SECONDARY) {
							Composite rectangle = new Composite(clickPoint.getX(), clickPoint.getY(), RectW, RectH);
							Shapes.add(rectangle);
							root.getChildren().add(rectangle);
						}	
					}
					else if(shape != null && shape instanceof DragCircle) {
						Iterator<Component> i = Shapes.iterator();
						while(i.hasNext()) {
							Component shp = i.next();
							if(shp instanceof Composite && ((Composite) shp).contains(clickPoint)) {
									((Composite) shp).addCircle(shape);
									break;
								}
							}
						}
					dragging=false;
					break;
					
			}
			lastPosition = clickPoint;
		}
	};

	@Override
	public void start(Stage stage) throws Exception {
		root = new AnchorPane();
		scene = new Scene(root, 500, 500);
		stage.setScene(scene);
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMouseReleased(mouseHandler);
		scene.setOnMousePressed (mouseHandler); 
		stage.show();
		
	}
	
	private Component getShape(Point2D point) {
		Iterator<Component> i = Shapes.iterator();
		Component returner = null;
		while(i.hasNext()) {
			Component temp = i.next();
			if(temp instanceof Composite) {
				if(((Composite) temp).contains(point))
					returner = temp;
			}
			if(temp instanceof DragCircle) {
				if(((DragCircle) temp).contains(point))
					returner = temp;
			}
		}
		return returner;
	}
	
	public static void main(String[] args) {
		launch(args);
	}	
}	