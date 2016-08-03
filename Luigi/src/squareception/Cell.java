package squareception;

import javafx.scene.layout.Pane;

public abstract class Cell {
	int x, y, w, h, id;
	Pane pane;
	Grid parent;

	public Cell(Pane pane, Grid parent, int x, int y, int w, int h, int id) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.id = id;
		this.pane = pane;
		this.parent = parent;
	}
	
	abstract void remove();
}
