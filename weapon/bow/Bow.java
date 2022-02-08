package weapon.bow;

import character.Character;
import character.Elf;
import exception.InvalidCharacterWeaponException;
import weapon.Weapon;

public abstract class Bow implements Weapon {
	
	void checkElf(Character character) {
		if(!(character instanceof Elf)) {
			throw new InvalidCharacterWeaponException("엘프 이외의 캐릭터는 이 무기를 착용할 수 없습니다.");
		}
	}

}
