package squareception;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Cell{
	Rectangle rect;

	public Square(Pane pane, Grid parent, int x, int y, int w, int h, int id) {
		super(pane, parent, x, y, w, h, id);
		rect = new Rectangle(x, y, w, h);
		rect.setFill(Color.RED);
		pane.getChildren().add(rect);
		rect.setOnMouseClicked(e ->{
			if(e.getButton().equals(MouseButton.PRIMARY))
				divide();
			else if(e.getButton().equals(MouseButton.SECONDARY) && parent != null)
				parent.combine();
		});
	}
	
	private void divide(){
		if(w > 3 && h > 3){
			remove();
			Grid newGrid = new Grid(pane, parent, x, y, w, h, id);
			if(parent != null)
				parent.setCell(id, newGrid);
		}
	}

	@Override
	void remove(){
		pane.getChildren().remove(rect);
	}
}
