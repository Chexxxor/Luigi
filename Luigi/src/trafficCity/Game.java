package trafficCity;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Game extends Application implements Settings{
	@Override
	public void start(Stage primaryStage) throws Exception {
		ImageLoader il = new ImageLoader("traffic_tiles.png", 4, 1, 32);
		ImageLoader car = new ImageLoader("car8x12.png", 1, 1, 8, 12);
		GridPane grid = new GridPane();
		grid.setHgap(0);
		grid.setMinSize(COLS*TILE_SIZE, ROWS*TILE_SIZE);
		for(int i = 0; i < 4; i++){
			grid.add(il.getView(0, i), i, 0);
		}
		for(int y = 0; y < COLS; y++){
			for(int x = 0; x < ROWS; x++){
				grid.add(il.getView(0, (int)(Math.random()*4)), x, y);
			}
		}
		//StackPane pane = new StackPane(grid, );
		Scene scene = new Scene(grid, 320, 320);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args){
		Application.launch(args);
	}
}
