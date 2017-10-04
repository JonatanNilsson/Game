import java.awt.Graphics;
import java.awt.Image;


public class PowerUp extends GameObject{

	
	
	PowerUp(int x, int y, ID id, int size, int health, Image img) {
		super(x, y, id, size, health, img);
	}

	public void givePower(Player player){
		if (this.getID() == ID.POWER_UP_HEALTH)
			player.setIncreaseHealth(1000);
		if (this.getID() == ID.POWER_UP_ATTACK)
			player.setIncreaseAttack(500);
	}
	
	public void tick(Handler handler) { 
		this.setHealth(this.getHealth() - 1);
	}

	public void render(Graphics g, Game ImgObserver) {
		g.drawImage(this.getImage(), this.getX() - Game.windowXPos, this.getY() - Game.windowYPos,
						this.getSize(), this.getSize(), ImgObserver);
	}
	
}
