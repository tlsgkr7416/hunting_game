package weapon.bow;
import character.Character;

public class ShortBow extends Bow {

	@Override
	public void addEffect(Character character) {
		checkElf(character);
		
		int attackSpeed = character.getAttackSpeed();
		attackSpeed = attackSpeed + (int)(attackSpeed * 0.05);
		character.setAttackSpeed(attackSpeed);
		
	}

	@Override
	public void removeEffect(Character character) {
		checkElf(character);
		
		int attackSpeed = character.getAttackSpeed();
		attackSpeed = attackSpeed - (int)(attackSpeed * 0.05);
		character.setAttackSpeed(attackSpeed);
		
	}


}
