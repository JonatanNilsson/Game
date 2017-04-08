import java.awt.Graphics;
import java.awt.Image;


public class Bomb extends GameObject{
	private static int time_to_live = 100;
	private int velX, velY;
	private Image explosion;
	
	
	Bomb(int x, int y, ID id, int size, int health, Image img, Image explosion) {
		super(x, y, id, size, health, img);
		this.explosion = explosion;
		this.setDamage(10);
	}
	Bomb(int x, int y, int dirX, int dirY){
		this(x, y, ID.BOMB, Game.TILE_SIZE / 2, time_to_live, Images.Bomb, Images.Explosion);
		this.velX = 4 * dirX;
		this.velY = 4 * dirY;
	}
	Bomb(int x, int y, int dirX, int dirY, ID id){
		this(x,y,dirX,dirY);
		this.setID(id);
	}

	public void tick(Handler handler) {
		this.setHealth(getHealth()-1);
		this.setX(this.getX() + this.velX);
		this.setY(this.getY() + this.velY);
		if (!this.validPos(handler)){
			this.setHealth( Math.min(this.getHealth(), 15));
			this.velY /= 2;
			this.velX /= 2;
		}
	}

	public void render(Graphics g, Game ImgObserver) {
		if (this.getHealth() > 25){
			g.drawImage(this.getImage(), this.getX() - Game.windowXPos, 
					this.getY() - Game.windowYPos, Game.TILE_SIZE/2, 
					Game.TILE_SIZE/2, ImgObserver);
		}
		else{
			g.drawImage(this.explosion, this.getX() - Game.TILE_SIZE / 4  - Game.windowXPos, 
					this.getY() - Game.TILE_SIZE / 4 - Game.windowYPos, Game.TILE_SIZE + Game.TILE_SIZE / 2, 
					Game.TILE_SIZE + Game.TILE_SIZE / 4, ImgObserver);
		}
	}

	public void setHealth(int health){
		super.setHealth(health);
		if (this.getHealth() <= 10){
			this.setX(this.getX() - Game.TILE_SIZE / 4);
			this.setY(this.getY() - Game.TILE_SIZE / 4);
			this.setSize(Game.TILE_SIZE);
			if (this.getID() == ID.BOMB) this.setID(ID.EXPLOSION);
			if (this.getID() == ID.ENEMY_BOMB) this.setID(ID.ENEMY_EXPLOSION);
		}
	}
	
}
