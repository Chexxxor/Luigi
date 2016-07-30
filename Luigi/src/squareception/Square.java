package squareception;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Cell {
	Rectangle rect;

	public Square(Pane pane, Cell parent, int x, int y, int w, int h, int id) {
		super(pane, parent, x, y, w, h, id);
		rect = new Rectangle(x, y, w, h);
		rect.setFill(Color.RED);
		pane.getChildren().add(rect);
		rect.setOnMouseClicked(e ->{
			if(e.getButton().equals(MouseButton.PRIMARY))
				divide();
			else if(e.getButton().equals(MouseButton.SECONDARY) && parent != null && parent.getClass().equals(Grid.class))
				((Grid)parent).combine();
		});
	}
	
	@Override
	public void divide(){
		if(w > 3 && h > 3){
			remove();
			if(parent != null && parent.getClass().equals(Grid.class))
				((Grid)parent).grid[id] = new Grid(pane, parent, x, y, w, h, id);
			else
				parent = new Grid(pane, parent, x, y, w, h, id);
		}
	}

	public void remove(){
		pane.getChildren().remove(rect);
	}
}
