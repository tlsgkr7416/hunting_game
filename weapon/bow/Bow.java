package weapon.bow;

import character.Character;
import character.Elf;
import exception.InvalidCharacterWeaponException;
import weapon.Weapon;

public abstract class Bow implements Weapon {
	
	void checkElf(Character character) {
		if(!(character instanceof Elf)) {
			throw new InvalidCharacterWeaponException("���� �̿��� ĳ���ʹ� �� ���⸦ ������ �� �����ϴ�.");
		}
	}

}
