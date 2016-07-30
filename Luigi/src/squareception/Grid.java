package squareception;

import javafx.scene.layout.Pane;

public class Grid extends Cell {
	Cell[] grid = new Cell[4];

	public Grid(Pane pane, Cell parent, int x, int y, int w, int h, int id) {
		super(pane, parent, x, y, w, h, id);
		int ver = x + w/2;
		int hor = y + h/2;
		grid[0] = new Square(pane, this, x, y, w/2, h/2, 0);
		grid[1] = new Square(pane, this, ver+1, y, x+w-1-ver, h/2, 1);
		grid[2] = new Square(pane, this, x, hor+1, w/2, y+h-1-hor, 2);
		grid[3] = new Square(pane, this, ver+1, hor+1, x+w-1-ver, y+h-1-hor, 3);
	}
	
	public void combine(){
		remove();
		parent = new Square(pane, parent, x, y, w, h, id);
	}
	
	public void remove(){
		for(Cell c : grid){
			c.remove();
		}
	}
}
