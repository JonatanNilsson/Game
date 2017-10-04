import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


public class BasicEnemy extends GameObject{

	private int stepLength;
	private double velocity;
	
	BasicEnemy(int x, int y, ID id, int size, int health, Image img) {
		super(x, y, id, size, health, img);
		this.damage = 10;
		this.velocity = 0.3;
		this.stepLength = 1;
	}


	public void tick(Handler handler) {
		if (Game.tickCounter % (int) (1.0/velocity) != 0) return;
		int pX = handler.player.getX(), pY = handler.player.getY();
		
		int pSectionX = pX / (Game.TILE_SIZE * 5);
		
		int eSectionX = this.getX() / (Game.TILE_SIZE * 5);
		int eSectionY = (this.getY()*5 ) / (Game.MAP_HEIGHT);
		
		if (pSectionX == eSectionX) {
			double r = Math.random();
			if (pX < this.getX() || r < 0.2){
				this.setX(this.getX() - stepLength);
				while (!this.validPos(handler)){
					this.setX(this.getX() + 1);
				}
			}
			else if (pX > this.getX() || r > 0.8){
				this.setX(this.getX() + stepLength);
				while (!this.validPos(handler)){
					this.setX(this.getX() - 1);
				}
			}
			if (pY < this.getY() || r > 0.8){
				this.setY(this.getY() - stepLength);
				while (!this.validPos(handler)){
					this.setY(this.getY() + 1);
				}
			}
			else if (pY > this.getY() || r < 0.2){
				this.setY(this.getY() + stepLength);
				while (!this.validPos(handler)){
					this.setY(this.getY() - 1);
				}
			}
		}
		else {
			double r = Math.random();
			if (pX < this.getX() ){
				this.setX(this.getX() - stepLength);
				while (!this.validPos(handler)){
					this.setX(this.getX() + 1);
				}
			}
			else if (pX > this.getX()){
				this.setX(this.getX() + stepLength);
				while (!this.validPos(handler)){
					this.setX(this.getX() - 1);
				}
			}
			if ((pY < this.getY() && r > 0.9 && this.getY() > 2*Game.MAP_HEIGHT / 5) || eSectionY > 2){
				this.setY(this.getY() - stepLength);
				while (!this.validPos(handler)){
					this.setY(this.getY() + 1);
				}
			}
			else if ((pY > this.getY() && r < 0.9  && this.getY() + 1 < 3*Game.MAP_HEIGHT / 5) || eSectionY < 2){
				this.setY(this.getY() + stepLength);
				while (!this.validPos(handler)){
					this.setY(this.getY() - 1);
				}
			}
			
		}
		
		
		
	}

	public void render(Graphics g, Game ImgObserver) {
		g.setColor(Color.GRAY);
		g.drawImage(this.getImage(), this.getX() - Game.windowXPos, 
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
