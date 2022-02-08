package monster;
import character.Character;

public interface MonsterAttack {
	
	public int attack();
	
	public boolean isAttackPossible();
	
	public int beAttacked(int characterOffensePower, Character character);
}
