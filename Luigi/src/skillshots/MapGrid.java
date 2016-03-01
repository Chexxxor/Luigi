package skillshots;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MapGrid implements Constants {
	private Block[][] grid = new Block[GRID_ROWS][GRID_COLS];

	public MapGrid(Pane pane) {
		generateRandomBlocks(pane);
	}

	private void generateRandomBlocks(Pane pane){
		for(int y = 0; y < GRID_ROWS; y++){
			for(int x = 0; x < GRID_COLS; x++){
				Color c = (Math.random() * 6 < 2 ? Color.CYAN : Color.BLACK);
				grid[y][x] = new Block(pane, x * BLOCK_SIZE, y * BLOCK_SIZE, c);
			}
		}
	}
}
