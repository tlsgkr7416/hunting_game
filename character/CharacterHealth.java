package character;

public interface CharacterHealth {
	
	public void increaseHp(int hp);
	
	public void decreaseHp(int hp);
	
	public void increaseMp(int mp);
	
	public void decreaseMp(int mp);
	
	public boolean isEvasionRate();
	
	public void checkDeath();
	
	public void levelUp();
}
