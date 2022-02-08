package weapon.sword;

import character.Character;
import character.Human;
import exception.InvalidCharacterWeaponException;
import weapon.Weapon;

public abstract class Sword implements Weapon {
	void checkHuman(Character character) {
		if(!(character instanceof Human)) {
			throw new InvalidCharacterWeaponException("휴먼 이외의 캐릭터는 이 무기를 착용할 수 없습니다.");
		}
	}
}
