import java.util.Random;

import javafx.application.*;
import javafx.event.ActionEvent; 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class OceanExplorer extends Application {
	int[][] island; 
	int count;
	final int dimensions = 10; 
	final int islandCounter = 10;
	final int scale = 50;
	OceanMap ocean;
	Ship ship;
	PirateShip pirateShip;
	PirateShip pirateShip1; 
	ImageView shipPView;
	ImageView pirateShipPView;
	ImageView pirateShipPView2;
	Pane root;
	Scene scene;
	Label moves;
	
	@Override
	public void start(Stage oceanStage) throws Exception {
		count = 0;
		ocean = new OceanMap(dimensions, scale);
		island = ocean.getMap();
		root = new AnchorPane();
		drawMap();
		drawIsland(10);
		ship = new Ship(ocean);
		pirateShip = new PirateShip(ocean); 
		pirateShip1 = new PirateShip(ocean);
		ship.addObserver(pirateShip);
		ship.addObserver(pirateShip1);
		loadShipImage();
		loadPirateImage();
		loadPirateImage2(); 
		
		Button reset = new Button("reset");
		reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				try {
					start(oceanStage);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		reset.setLayoutX(0);
		reset.setLayoutY(500);
		root.getChildren().add(reset);
		
		moves = new Label("Total moves:" + count);
		moves.setLayoutX(100);
		moves.setLayoutY(500);
		root.getChildren().add(moves);
		
		scene = new Scene(root, 500, 500); 
		oceanStage.setTitle("Ocean Stage");
		oceanStage.setScene(scene);
		oceanStage.show();
		navigate(); 
	
	}
	
	public void drawMap() {
		for(int x = 0; x < dimensions; x++) {
			for(int y = 0; y < dimensions; y++) {
				Rectangle rect = new Rectangle(x*scale, y*scale, scale, scale); 
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.PALETURQUOISE);
				root.getChildren().add(rect);
			}
		}
		
	}
	
	public void loadShipImage() {
		Image shipImage = new Image("ship.png",50,50,true,true);
		shipPView = new ImageView(shipImage);
		shipPView.setX(ship.location().x*scale);
		shipPView.setY(ship.location().y*scale);
		root.getChildren().add(shipPView); 
	}
	
	public void loadPirateImage() {
		Image shipImage = new Image("pirateShip.png",50,50,true,true);
		pirateShipPView = new ImageView(shipImage);
		pirateShipPView.setX(pirateShip.PirateShipLocation().x*scale);
		pirateShipPView.setY(pirateShip.PirateShipLocation().y*scale);
		root.getChildren().add(pirateShipPView); 
	}
	
	public void loadPirateImage2() {
		Image shipImage = new Image("pirateShip.png",50,50,true,true);
		pirateShipPView2 = new ImageView(shipImage);
		pirateShipPView2.setX(pirateShip1.PirateShipLocation().x*scale);
		pirateShipPView2.setY(pirateShip1.PirateShipLocation().y*scale);
		root.getChildren().add(pirateShipPView2); 
	}
	
	public void drawIsland(int i) {
		int count = 0;
		Random rand = new Random();
		while(count < i) {
			int x;
			int y;
			while(true) {
				x = rand.nextInt(dimensions);
				y = rand.nextInt(dimensions);
				if(island[x][y] != 1) 
					break;
			}
			Image islandImage = new Image("island.jpg", 50, 50, true, true);
			ImageView islandImageView = new ImageView(islandImage);
			islandImageView.setX(x*scale);
			islandImageView.setY(y*scale);
			island[x][y] = 1;
			root.getChildren().add(islandImageView);
			count++; 
			}
	}
	
	
	public void navigate() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
		@Override
		public void handle(KeyEvent ke) {
			switch(ke.getCode()) {
			case RIGHT:
				ship.move("EAST");
				break;
			case LEFT:
				ship.move("WEST");
				break;
			case UP:
				ship.move("NORTH");
				break;
			case DOWN:
				ship.move("SOUTH");
				break;
			default:
				break;
			}
			moves.setText("Total moves: " + count++);
			shipPView.setX(ship.location().x*scale);
			shipPView.setY(ship.location().y*scale);
			pirateShipPView.setX(pirateShip.PirateShipLocation().x*scale);
			pirateShipPView.setY(pirateShip.PirateShipLocation().y*scale);
			pirateShipPView2.setX(pirateShip.PirateShipLocation().x*scale);
			pirateShipPView2.setY(pirateShip.PirateShipLocation().y*scale);
			
		}
			
		});
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
