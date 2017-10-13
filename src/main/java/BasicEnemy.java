import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class BasicEnemy extends GameObject{

	private int stepLength;
	private double velocity;
	private ArrayList<ArrayList <BufferedImage> > img;
	private int dirX = 0;
	private int dirY = 0;

	BasicEnemy(int x, int y, ID id, int size, int health, ArrayList<ArrayList <BufferedImage> > img) {
		super(x, y, id, size, health);
        this.img = img;
		this.damage = 10;
		this.velocity = 0.3;
		this.stepLength = 1;
	}

	private void moveY(int stepLength, Handler handler) {
		this.setY(this.getY() + stepLength);
		dirY = stepLength > 0 ? 1 : -1;
		while (!this.validPos(handler)){
			this.setY(this.getY() - dirY);
		}
	}
	private void moveX(int stepLength, Handler handler){
		this.setX(this.getX() + stepLength);
		dirX = stepLength > 0 ? 1 : -1;
		while (!this.validPos(handler)){
			this.setX(this.getX() - dirX);
		}
	}


	public void tick(Handler handler) {
		if (Game.tickCounter % (int) (1.0/velocity) != 0) return;
		int pX = handler.player.getX(), pY = handler.player.getY();

		int pSectionX = pX / (Game.TILE_SIZE * 5);

		int eSectionX = this.getX() / (Game.TILE_SIZE * 5);
		int eSectionY = (this.getY()*5 ) / (Game.MAP_HEIGHT);

		dirX = 0;
		dirY = 0;

		if (pSectionX == eSectionX) {
			double r = Math.random();
			if (pX < this.getX() || r < 0.2){
				moveX(-stepLength, handler);
			}
			else if (pX > this.getX() || r > 0.8){
				moveX(stepLength, handler);
			}
			if (pY < this.getY() || r > 0.8){
				moveY(-stepLength, handler);
			}
			else if (pY > this.getY() || r < 0.2){
				moveY(stepLength, handler);
			}
		}
		else {
			double r = Math.random();
			if (pX < this.getX() ){
				moveX(-stepLength, handler);
			}
			else if (pX > this.getX()){
				moveX(stepLength, handler);
			}
			if ((pY < this.getY() && r > 0.9 && this.getY() > 2*Game.MAP_HEIGHT / 5) || eSectionY > 2){
				moveY(-stepLength, handler);
			}
			else if ((pY > this.getY() && r < 0.9  && this.getY() + 1 < 3*Game.MAP_HEIGHT / 5) || eSectionY < 2){
				moveY(stepLength, handler);
			}

		}



	}

	public void render(Graphics g, Game ImgObserver) {
		g.setColor(Color.GRAY);

		int dir = 0;
		if (dirX < 0) dir = 1;
		else if (dirX > 0) dir = 2;
		else if (dirY > 0) dir = 0;
		else if (dirY < 0) dir = 3;

		g.drawImage(img.get(dir).get( 0), this.getX() - Game.windowXPos,
					 this.getY() - Game.windowYPos,
					 this.getSize(), this.getSize(), ImgObserver);


		if (getHealth() != getMaxHealth()){
			g.setColor(Color.RED);
			g.fillRect(this.getX() - Game.windowXPos, this.getY() - Game.windowYPos - 10,
					this.getHealth() * 2, this.getSize() / 10);
		}
	}


	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		if (velocity >= 1 || velocity < 0) return;
		this.velocity = velocity;
	}



}
