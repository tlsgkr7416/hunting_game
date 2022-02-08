package weapon.axe;
import character.Character;

public class ShortAxe extends Axe {

	@Override
	public void addEffect(Character character) {
		checkOrc(character);
		
		int offensePower = character.getOffensePower();
		offensePower = offensePower + (int)(offensePower * 0.1);
		character.setOffensePower(offensePower);
		
		int attackSpeed = character.getAttackSpeed();
		attackSpeed = attackSpeed - (int)(attackSpeed * 0.05);
		character.setAttackSpeed(attackSpeed);
	}

	@Override
	public void removeEffect(Character character) {
		checkOrc(character);
		
		int offensePower = character.getOffensePower();
		offensePower = offensePower - (int)(offensePower * 0.1);
		character.setOffensePower(offensePower);
		
		int attackSpeed = character.getAttackSpeed();
		attackSpeed = attackSpeed + (int)(attackSpeed * 0.05);
		character.setAttackSpeed(attackSpeed);
		
	}

}
