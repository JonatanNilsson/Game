import java.awt.Color;
import java.awt.Graphics;


public class BasicTile {

	public static int MAP = 1;
	
	private boolean spawning;
	private int waitToSpawn = 0;
	private int indexX, indexY;
	private ID tile_type;
	
	BasicTile(int indexX, int indexY, ID tile_type, boolean isSpawningPlace){
		this.indexX = indexX;
		this.indexY = indexY;
		this.tile_type = tile_type;
		this.spawning = isSpawningPlace;
	}
	
	
	
	
	public static void generateMap(Handler handler){
		
		if (MAP == 1){
			for (int i=0;i<Game.TILE_WIDTH;i++){
				for (int j=0;j<Game.TILE_HEIGHT;j++){
					if (i==0 || j == 0 || i == Game.TILE_WIDTH-1 || j == Game.TILE_HEIGHT - 1){
						handler.Tiles[i][j] = new BasicTile(i, j, ID.WALL, false);
					}
					else if (i % 5 == 0 && 
							(j < 2 * Game.TILE_HEIGHT / 5 ||
									j > 3 * Game.TILE_HEIGHT / 5)){
						handler.Tiles[i][j] = new BasicTile(i, j, ID.WALL, false);
					}
					else if (j != 1 && j!= Game.TILE_HEIGHT-2){
						handler.Tiles[i][j] = new BasicTile(i, j, ID.FLOOR, false);
					}
					else{
						handler.Tiles[i][j] = new BasicTile(i, j, ID.FLOOR, true);
					}
				}
			}
		}
		
	}
	
	
	public void tick(){
		this.waitToSpawn = Math.max(this.waitToSpawn - 1, 0);
	}

	public void render(Graphics g) {
		
		switch (this.tile_type){
		case FLOOR: 
			g.setColor(Color.DARK_GRAY);
			break;
		case WALL:
			g.setColor(Color.BLACK);
			break;
		default:
			break;
		}
		g.fillRect(this.indexX * Game.TILE_SIZE - Game.windowXPos, 
				   this.indexY * Game.TILE_SIZE - Game.windowYPos, 
				   Game.TILE_SIZE, Game.TILE_SIZE);
	}
	
	
	
	
	public ID getType(){
		return this.tile_type;
	}
	
	public boolean isSpawningPlace(){
		if (waitToSpawn > 0) return false;
		return this.spawning;
	}
	
	public int getIndexY(){
		return this.indexY;
	}
	public int getIndexX(){
		return this.indexX;
	}
	public int getWaitToSpawn(){
		return this.waitToSpawn;
	}
	public void setWaitToSpawn(int waitToSpawn){
		this.waitToSpawn = waitToSpawn;
	}




	
}
