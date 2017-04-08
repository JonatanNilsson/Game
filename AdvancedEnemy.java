import java.awt.Image;


public class AdvancedEnemy extends BasicEnemy{
	
	
	AdvancedEnemy(int x, int y, ID id, int size, int health, Image img) {
		super(x, y, id, size, health, img);
	}
	
	public void tick(Handler handler){
		super.tick(handler);
		
		if (Game.tickCounter % 300 == 0 && freeSight(handler)){
			handler.addObject(new Bomb(this.getX(), this.getY(), 1, 1, ID.ENEMY_BOMB));
		}
		
	}
	
	
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
