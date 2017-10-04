
public class Levels {
	
	private final int numOfEnemyTypes = 2;
	
	private int[][] enemies = {
			{0, 0, 20, 30, 40, 50}, // Number of Hillary on the diffrent levels
			{1, 10, 20, 30, 40, 50}, // Number of Trumps
	};
	
	private void doSpawning(Handler handler, BasicTile tile){
		
		int indexY = tile.getIndexY();
		int indexX = tile.getIndexX();
		int curLevel = handler.getCurLevel();
		
		// Checking if it is a good place to spaswn
		if ((indexY == 1 && 
				(Game.windowYPos > Game.TILE_SIZE || 
				 indexX*Game.TILE_SIZE < Game.windowXPos ||
				 indexX*Game.TILE_SIZE > Game.windowXPos + Game.WIDTH)) ||
				(indexY == Game.TILE_HEIGHT - 2 && 
				(Game.windowYPos < Game.MAP_HEIGHT - Game.HEIGHT - 50 || 
				 indexX*Game.TILE_SIZE < Game.windowXPos ||
				 indexX*Game.TILE_SIZE > Game.windowXPos + Game.WIDTH))) {
			
			// Spawn Hillary
			double r = Math.random();
			if (r < 0.005 && enemies[0][curLevel] > 0 && tile.isSpawningPlace()){
				tile.setWaitToSpawn(500);
				enemies[0][curLevel]--;
				handler.addObject(new BasicEnemy(indexX * Game.TILE_SIZE, indexY * Game.TILE_SIZE,
												ID.BASIC_ENEMY, Game.TILE_SIZE, 25, Images.HillaryClinton));
			}
			
			r = Math.random();
			if (r < 0.005 && enemies[1][curLevel] > 0 && tile.isSpawningPlace()){
				tile.setWaitToSpawn(500);
				enemies[1][curLevel]--;
				handler.addObject(new AdvancedEnemy(indexX * Game.TILE_SIZE, indexY * Game.TILE_SIZE,
												ID.BASIC_ENEMY, Game.TILE_SIZE, 50, Images.Trump));
			}
		}
	}
	
	
	public void spawn(Handler handler){
		
		int curLevel = handler.getCurLevel();
		
		int numLeftToSpawn = 0;
		for (int i=0; i < numOfEnemyTypes; i++ ){
			numLeftToSpawn += enemies[i][curLevel];
		}
		
		if (handler.getNumOfEnemies() == 0 && numLeftToSpawn == 0){
			handler.setCurLevel(curLevel + 1);
			curLevel++;
		}
		
		
		for (int i=0;i<Game.TILE_WIDTH; i++){
			for (int j=0;j<Game.TILE_HEIGHT;j++){
				doSpawning(handler, handler.Tiles[i][j]);
			}
		}
		
	}
	
	
	
}
