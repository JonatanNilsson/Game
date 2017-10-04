import java.awt.Graphics;
import java.awt.Image;


public abstract class GameObject {

	private int x, y, size, health, maxHealth;
	protected int damage; 
	private ID id; 
	private Image img;
	
	GameObject(int x, int y, ID id, int size, int health, Image img){
		this.x = x;
		this.y = y;
		this.id = id;
		this.size = size;
		this.health = health;
		this.img = img;
		this.setMaxHealth(health);
	}
	
	public abstract void tick(Handler handler);
	public abstract void render(Graphics g, Game ImgObserver);
		
	
	public static boolean intersect(GameObject a, GameObject b){
		if (a.getX() <= b.getX() && a.getX() + a.getSize() > b.getX() ||
				b.getX() <= a.getX() && b.getX() + b.getSize() > a.getX()){
			if (a.getY() <= b.getY() && a.getY() + a.getSize() > b.getY() ||
					b.getY() <= a.getY() && b.getY() + b.getSize() > a.getY()){
				return true;
			}	
		}
		return false;
	}
	
	public boolean validPos(Handler handler){
		int x = this.getX() / Game.TILE_SIZE, y = this.getY() / Game.TILE_SIZE;
		if ( (handler.Tiles[x][y]).getType() == ID.WALL){
			return false;
		}
		if ( this.getX() / Game.TILE_SIZE < (this.getX() + this.getSize() - 1) / Game.TILE_SIZE && 
				(handler.Tiles[x+1][y]).getType() == ID.WALL){
			return false;
		}
		if ( this.getY() / Game.TILE_SIZE < (this.getY() + this.getSize() - 1) / Game.TILE_SIZE &&
				(handler.Tiles[x][y+1]).getType() == ID.WALL){
			return false;
		}
		if (  this.getX() / Game.TILE_SIZE < (this.getX() + this.getSize() - 1) / Game.TILE_SIZE &&
				 this.getY() / Game.TILE_SIZE < (this.getY() + this.getSize() - 1) / Game.TILE_SIZE && 
					(handler.Tiles[x+1][y+1]).getType() == ID.WALL){
			return false;
		}
		return true;
	}
	
	public void setX(int x){
		if (x >= 0 && x <= Game.MAP_WIDTH){
			this.x = x;
		}
	}
	public void setY(int y){
		if (y >= 0 && y <= Game.MAP_HEIGHT){
			this.y = y;
		}
	}
	public void setID(ID id){
		this.id = id;
	}
	public void setHealth(int health){
		if (health < 0) health = 0;
		this.health = health;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public ID getID(){
		return this.id;
	}
	public int getSize(){
		return this.size;
	}
	public int getHealth(){
		return this.health;
	}
	public Image getImage(){
		return this.img;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	public void setSize(int size){
		this.size = size;
	}
}
