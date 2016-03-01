package skillshots;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block implements Constants {
	Rectangle rectangle;

	public Block(Pane pane, int x, int y, Color c) {
		rectangle = new Rectangle(x, y, BLOCK_SIZE, BLOCK_SIZE);
		rectangle.setFill(c);
		pane.getChildren().add(rectangle);
	}

}
