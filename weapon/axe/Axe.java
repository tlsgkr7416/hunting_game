package weapon.axe;

import weapon.Weapon;
import character.Character;
import character.Orc;
import exception.InvalidCharacterWeaponException;

public abstract class Axe implements Weapon {
	
	void checkOrc(Character character) {
		if(!(character instanceof Orc)) {
			throw new InvalidCharacterWeaponException("오크 이외의 캐릭터는 이 무기를 착용할 수 없습니다.");
		}
	}
}
