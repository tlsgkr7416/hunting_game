package weapon.sword;
import character.Character;

public class LongSword extends Sword{

	@Override
	public void addEffect(Character character) {
		checkHuman(character);
		
		int offensePower = character.getOffensePower();
		offensePower = offensePower + (int)(offensePower * 0.1);
		character.setOffensePower(offensePower);
	}

	@Override
	public void removeEffect(Character character) {
		checkHuman(character);
		
		int offensePower = character.getOffensePower();
		offensePower = offensePower - (int)(offensePower * 0.1);
		character.setOffensePower(offensePower);
		
	}

}
