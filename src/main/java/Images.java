import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Images {
	
	static Image Bomb, Explosion, HillaryClinton, LeifGWPersson, Lightning, 
				Hearth, Blood, Trump;
	
    static void loadImages(){
        String path = "/src/main/resources/" ;

		InputStream input = Images.class.getResourceAsStream(path + "Explosion.png");
		try {
			Explosion = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = Images.class.getResourceAsStream(path + "HillaryClinton.png");
		try {
			HillaryClinton = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
    	input = Images.class.getResourceAsStream(path + "LeifGWPersson.jpg");

		try {
			LeifGWPersson = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = Images.class.getResourceAsStream(path + "Lightning.png");
		try {
			Lightning = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = Images.class.getResourceAsStream(path + "Bomb.png");
		try {
			Bomb = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = Images.class.getResourceAsStream(path + "Heart.png");
		try {
			Hearth = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = Images.class.getResourceAsStream(path + "Blood.jpg");
		try {
			Blood = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		input = Images.class.getResourceAsStream(path + "Trump.png");
		try {
			Trump = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }

	}
	
	
}
