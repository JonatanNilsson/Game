
public enum ID {
	PLAYER, 
	BASIC_ENEMY,
	ADVANCED_ENEMY,
	BOMB,
	ENEMY_BOMB,
	EXPLOSION,
	ENEMY_EXPLOSION,
	FLOOR,
	WALL,
	POWER_UP_HEALTH,
	POWER_UP_ATTACK;
	
	public boolean damagePlayer(){
		switch (this){
			case BASIC_ENEMY : 
			case ADVANCED_ENEMY :
			case ENEMY_EXPLOSION :
				return true;
			default:
				return false;
		}
	}
	
	public boolean isPowerUp(){
		switch (this){
			case POWER_UP_HEALTH : 
			case POWER_UP_ATTACK :
				return true;
			default:
				return false;
		}
	}

	public boolean isEnemy() {
		switch (this){
		case BASIC_ENEMY : 
		case ADVANCED_ENEMY :
			return true;
		default:
			return false;
		}
	}

	public boolean makeDamageTo(ID idVictim) {
		if (idVictim.isPowerUp()) return false;
		if (this == EXPLOSION) return true;
		if (this.isEnemy() && !idVictim.isEnemy()) return true;
		return false;
	}
	
	
}
