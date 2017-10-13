import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AdvancedEnemy extends BasicEnemy{

	int hiddenRandom;

	AdvancedEnemy(int x, int y, ID id, int size, int health, ArrayList<ArrayList<BufferedImage> > img) {
		super(x, y, id, size, health, img);
		this.hiddenRandom = (int)(Math.random() * 299);
	}

	public void tick(Handler handler){
		super.tick(handler);

		if (Game.tickCounter % 300 == this.hiddenRandom && freeSight(handler)){

			int dirX, dirY;

			dirX = (handler.player.getX() / Game.TILE_SIZE - this.getX() / Game.TILE_SIZE);
			if (dirX > 1){
				dirX = 1;
			}
			else if (dirX < -1){
				dirX = -1;
			}
			else{
				dirX = 0;
			}

			dirY = (handler.player.getY() / Game.TILE_SIZE - this.getY() / Game.TILE_SIZE);
			if (dirY > 1){
				dirY = 1;
			}
			else if (dirY < -1){
				dirY = -1;
			}
			else{
				dirY = 0;
			}

			handler.addObject(new Bomb(this.getX(), this.getY(), dirX, dirY, ID.ENEMY_BOMB));

		}

	}


	// Check if there is a wall in between the enemy and the player
	private boolean freeSight(Handler handler){
		double x = this.getX(), y = this.getY();
		for (int i = 0; i < 50; i++){
			if (  handler.Tiles[(int)x / Game.TILE_SIZE][(int)y / Game.TILE_SIZE].getType() == ID.WALL ){
				return false;
			}
			x -= (this.getX() - handler.player.getX()) / 50.0;
			y -= (this.getY() - handler.player.getY()) / 50.0;
		}
		return true;
	}



}
