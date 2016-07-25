package squareception;

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
		
		if(Math.random() > 0.7)
			this.divide();
	}
	
	@Override
	public void divide(){
		if(w > 3 && h > 3){
			pane.getChildren().remove(rect);
			if(parent != null && parent.getClass().equals(Grid.class))
				((Grid)parent).grid[id] = new Grid(pane, parent, x, y, w, h, id);
			else
				parent = new Grid(pane, parent, x, y, w, h, id);
		}
	}
}
