package squareception;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application{
	Cell cell;

	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = new Pane();
		pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		cell = new Square(pane, null, 50, 50, 700, 700, 0);
		primaryStage.setTitle("SquareSception");
		primaryStage.setScene(new Scene(pane, 800, 800));
		primaryStage.show();
	}
}
