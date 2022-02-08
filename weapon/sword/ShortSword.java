package weapon.sword;

import character.Character;

public class ShortSword extends Sword {

	@Override
	public void addEffect(Character character) {
		checkHuman(character);
		
		int offensePower = character.getOffensePower();
		offensePower = offensePower + (int)(offensePower * 0.05);
		character.setOffensePower(offensePower);
		
	}

	@Override
	public void removeEffect(Character character) {
		checkHuman(character);
		
		int offensePower = character.getOffensePower();
		offensePower = offensePower - (int)(offensePower * 0.05);
		character.setOffensePower(offensePower);
		
	}

}
