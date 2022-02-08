package weapon.sword;

import character.Character;
import character.Human;
import exception.InvalidCharacterWeaponException;
import weapon.Weapon;

public abstract class Sword implements Weapon {
	void checkHuman(Character character) {
		if(!(character instanceof Human)) {
			throw new InvalidCharacterWeaponException("�޸� �̿��� ĳ���ʹ� �� ���⸦ ������ �� �����ϴ�.");
		}
	}
}
