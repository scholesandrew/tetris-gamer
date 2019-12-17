package Tetris;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class imageloader {

	public static Image[] loadImage(String path, int width) throws IOException {
		BufferedImage load = ImageIO.read(imageloader.class.getResource(path));
		Image[] Images = new Image[load.getWidth() / width];
		for(int i = 0; i < Images.length; i++) {
			Images[i] = load.getSubimage(i * width, 0, width, width);
		}
		return Images;
	}
	
}
