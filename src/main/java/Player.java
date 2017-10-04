import java.awt.Graphics;
import java.awt.Image;



public class Player extends GameObject{

	static int health, power = 400;
	private int dirX, dirY, tick_since_last_bomb = 10;
	private int increaseHealth = 0, increaseAttack = 0;
	private boolean playerHurt = false;
	
	
	Player(int x, int y, ID id, int size, int health, Image img) {
		super(x, y, id, size, health, img);
	}
	

	public void tick(Handler handler) {
		
		playerHurt = false;
		
		if (tick_since_last_bomb < 10) tick_since_last_bomb++;
		
		if (increaseAttack > 0) {
			increaseAttack--;
			power = 400;
		}
		
		if (increaseHealth > 0){ 
			increaseHealth-=10; 
			setHealth(Math.min(getHealth() + 10, 1000));
		}
		
		if (power < 400) power++;
		
		int new_dirX = 0, new_dirY = 0;
		if (Game.Keys['A']){
			this.setX( this.getX() - 3 );
			while (!this.validPos(handler)) this.setX( this.getX() + 1 );
			new_dirX = -1;
		}
		if (Game.Keys['D']){
			this.setX(this.getX() + 3);
			while (!this.validPos(handler)) this.setX( this.getX() - 1 );
			new_dirX = 1;
		}
		if (Game.Keys['S']){
			this.setY(this.getY() + 3);
			while (!this.validPos(handler)) this.setY( this.getY() - 1 );
			new_dirY=1;
		}
		if (Game.Keys['W']){
			this.setY(this.getY() - 3);
			while (!this.validPos(handler)) this.setY( this.getY() + 1 );
			new_dirY=-1;
		}
		if (new_dirX != 0 || new_dirY != 0){
			dirX = new_dirX; dirY = new_dirY;
		}
		if (Game.Keys[' '] && power > 75 && tick_since_last_bomb == 10){
			tick_since_last_bomb = 0;
			power -= 75;
			Bomb newBomb = new Bomb(this.getX() + this.getSize()/3, this.getY() + this.getSize() / 3, dirX, dirY);
			handler.addObject(newBomb);
		}

		Game.windowXPos = Math.min(Math.max(0, this.getX() - Game.WIDTH/2),
									Game.MAP_WIDTH - Game.WIDTH);
		Game.windowYPos = Math.min(Math.max(0, this.getY() - Game.HEIGHT/2),
									Game.MAP_HEIGHT - Game.HEIGHT);
	}

	public void render(Graphics g, Game ImgObserver) {
		
		Player.health = this.getHealth();
		g.drawImage(this.getImage(), this.getX() - Game.windowXPos, 
				   this.getY() - Game.windowYPos, 
				   this.getSize(), this.getSize(), ImgObserver);
	}

	
	
	public int getIncreaseAttack(){
		return this.increaseAttack;
	}
	
	public void setIncreaseHealth(int increaseHealth){
		this.increaseHealth = increaseHealth;
	}


	public void setIncreaseAttack(int increaseAttack) {
		this.increaseAttack = increaseAttack;
	}


	public boolean isPlayerHurt() {
		return playerHurt;
	}


	public void setPlayerHurt(boolean playerHurt) {
		this.playerHurt = playerHurt;
	}
	

	
}
