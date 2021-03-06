import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class Images {

	static Image Bomb, Explosion, HillaryClinton, LeifGWPersson, Lightning,
				Hearth, Blood, Trump;

	static ArrayList< ArrayList <BufferedImage> > Character = new ArrayList< ArrayList<BufferedImage> >();
	static ArrayList< ArrayList <BufferedImage> > CharacterScientist = new ArrayList< ArrayList<BufferedImage> >();


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


		input = Images.class.getResourceAsStream(path + "CharacterSprite.png");
		try {
			BufferedImage sprite = ImageIO.read(input);
			for (int i=0; i < 4; i++){
				Character.add(new ArrayList<BufferedImage>());
				for(int j = 0 ; j < 3;j++){
					Character.get(i).add(sprite.getSubimage(32 * j, 32 * i, 33, 33) );
				}
			}
		} catch (IOException e) { e.printStackTrace(); }

		input = Images.class.getResourceAsStream(path + "CharacterSprite.png");
		try {
			BufferedImage sprite = ImageIO.read(input);
			for (int i=0; i < 4; i++){
				CharacterScientist.add(new ArrayList<BufferedImage>());
				for(int j = 0 ; j < 3;j++){
					CharacterScientist.get(i).add(sprite.getSubimage(32 * (j+3), 32 * i, 33, 33) );
				}
			}
		} catch (IOException e) { e.printStackTrace(); }



	}


}
