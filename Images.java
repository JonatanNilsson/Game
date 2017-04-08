import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Images {
	
	static Image Bomb, Explosion, HillaryClinton, LeifGWPersson, Lightning, 
				Hearth, Blood, Trump;
	
	static void loadImages(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("Explosion.png");
		try {
			Explosion = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = classLoader.getResourceAsStream("HillaryClinton.png");
		try {
			HillaryClinton = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = classLoader.getResourceAsStream("LeifGWPersson.jpg");
		try {
			LeifGWPersson = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = classLoader.getResourceAsStream("Lightning.png");
		try {
			Lightning = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = classLoader.getResourceAsStream("Bomb.png");
		try {
			Bomb = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = classLoader.getResourceAsStream("Heart.png");
		try {
			Hearth = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = classLoader.getResourceAsStream("Blood.jpg");
		try {
			Blood = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = classLoader.getResourceAsStream("Trump.png");
		try {
			Trump = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }

	}
	
	
}
