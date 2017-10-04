import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	BasicTile[][] Tiles = new BasicTile[Game.TILE_WIDTH][Game.TILE_HEIGHT];
	Player player;
	Levels levels = new Levels();
	private int curLevel = 0;
	private int numOfEnemies = 0;
	
	public void tick(){
		
		
		levels.spawn(this);
		
		
		// Tick all gameobjects
		player.tick(this);
		for (int i=0;i<objects.size();i++){
			GameObject tempObject = objects.get(i);
			tempObject.tick(this);
		}
		
		//Tick all tiles
		for (int i=0;i<Game.TILE_WIDTH;i++){
			for (int j=0;j<Game.TILE_HEIGHT;j++){
				Tiles[i][j].tick();
			}
		}
		
		
		// Remove dead object and add powerUp after dead enemies
		for (GameObject obj : objects){
			if (obj.getHealth() == 0 && obj.getID() == ID.BASIC_ENEMY){
				
				PowerUp life = new PowerUp(obj.getX(), obj.getY(), ID.POWER_UP_HEALTH,
						Game.TILE_SIZE / 2, 500, Images.Hearth);
				PowerUp attack = new PowerUp(obj.getX(), obj.getY(), ID.POWER_UP_ATTACK,
						Game.TILE_SIZE / 2, 500, Images.Lightning);
				objects.remove(obj);
				
				double rand = Math.random();
				
				if (rand < 0.1){ objects.add(life); }
				else if (rand < 0.2){ objects.add(attack); }
				
				break;
			}
			else if (obj.getHealth() == 0){
				objects.remove(obj);
				break;
			}
		}
		
		// Reduce player health 
		for (GameObject obj : objects){
			if (obj.getID().damagePlayer() && GameObject.intersect(obj, player)){
				player.setHealth(player.getHealth() - obj.getDamage());
				player.setPlayerHurt(true);
			}
		}
		
		
		// Check if player got hit by  enemy-bomb
		
			
		// Check if enemies got hit by bomb and count number of enemies
		numOfEnemies = 0;
		for (GameObject obj : objects){
			if (obj.getID().isEnemy()) {
				numOfEnemies++;
				continue;
			}
			if (obj.getID() != ID.BOMB) continue;
			for (GameObject enemy : objects){
				if (!enemy.getID().isEnemy()) continue;
				if (GameObject.intersect(enemy, obj)){
					obj.setHealth(obj.getHealth() - enemy.getDamage());
				}
			}
		}
		
		
		// Reduce health because of bombs
		for (GameObject obj : objects) {
			if (obj.getID() != ID.EXPLOSION) continue;
			for (GameObject objB : objects){
				if (GameObject.intersect(obj, objB)){
					objB.setHealth(objB.getHealth() - obj.getDamage());
				}
			}
		}
		
		// See if Player can get PowerUps
		for (GameObject obj : objects){
			if (!obj.getID().isPowerUp()) continue;
			if (GameObject.intersect(obj, player)){
				((PowerUp)obj).givePower(player);
				objects.remove(obj);
				break;
			}
		}
		
		
	}
	
	public void render(Graphics g, Game ImgObserver ){
			
		for (int i=0;i<Game.TILE_WIDTH;i++){
			for (int j=0;j<Game.TILE_HEIGHT;j++){
				Tiles[i][j].render(g);
			}
		}
		
		player.render(g, ImgObserver);
		
		for (GameObject obj : objects){
			obj.render(g, ImgObserver);
		}
		
		
		if (player.isPlayerHurt() && Game.tickCounter % 3 == 0) {
			g.drawImage(Images.Blood, player.getX() - Game.windowXPos,
					player.getY() - Game.windowYPos,  Game.TILE_SIZE, 
					Game.TILE_SIZE, ImgObserver);
		}
		
		// Display current level
		g.setColor(Color.RED);
		g.setFont(new Font("Hello", 50 ,50));
		g.drawString(Integer.toString(this.curLevel + 1), Game.WIDTH*4 / 5, Game.TILE_SIZE);	
		
		
		// Add health-bar and ammo-bar
		g.setColor(Color.DARK_GRAY);
		g.fillRect(Game.WIDTH / 4, Game.HEIGHT - Game.TILE_SIZE, 400, Game.TILE_SIZE / 2);
		g.setColor(Color.RED);
		g.fillRect(Game.WIDTH / 4, Game.HEIGHT - Game.TILE_SIZE, (int)(Player.health / 2.5), Game.TILE_SIZE / 2);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(Game.WIDTH / 4, Game.HEIGHT - Game.TILE_SIZE, 400, Game.TILE_SIZE / 4);
		g.setColor(Color.BLUE);
		g.fillRect(Game.WIDTH / 4, Game.HEIGHT - Game.TILE_SIZE, Player.power, Game.TILE_SIZE / 4);
		if (player.getIncreaseAttack() > 0){
			g.setColor(Color.YELLOW);
			g.fillRect(Game.WIDTH / 4 + (int)(Game.tickCounter % 3), Game.HEIGHT - Game.TILE_SIZE + (int)(Game.tickCounter % 4), 400, Game.TILE_SIZE / 4);
		}
	
	}
	
	public void addObject(GameObject object){
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object){
		this.objects.remove(object);
	}

	public int getCurLevel() {
		return curLevel;
	}

	public void setCurLevel(int curLevel) {
		this.curLevel = curLevel;
	}

	public int getNumOfEnemies() {
		return numOfEnemies;
	}

	public void setNumOfEnemies(int numOfEnemies) {
		this.numOfEnemies = numOfEnemies;
	}
	
	
}
