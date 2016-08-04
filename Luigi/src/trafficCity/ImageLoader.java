package trafficCity;

import java.io.File;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.*;

public class ImageLoader {
	Image image;
	Rectangle2D[][] viewports;
	
	public ImageLoader(String filename, int cols, int rows, int size){
		this(filename, cols, rows, size, size);
	}
	public ImageLoader(String filename, int cols, int rows, int width, int height){
		File file = new File(filename);
		image = new Image(file.toURI().toString());
		viewports = new Rectangle2D[rows][cols];
		for(int y = 0; y < rows; y++){
			for(int x = 0; x < cols; x++){
				viewports[y][x] = new Rectangle2D(x*(width+1), y*(height+1), width, height);
			}
		}
	}
	
	public ImageView getView(int y, int x){
		ImageView iv = new ImageView(image);
		iv.setViewport(viewports[y][x]);
		return iv;
	}

	
}
