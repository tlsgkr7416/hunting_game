package weapon.axe;

import weapon.Weapon;
import character.Character;
import character.Orc;
import exception.InvalidCharacterWeaponException;

public abstract class Axe implements Weapon {
	
	void checkOrc(Character character) {
		if(!(character instanceof Orc)) {
			throw new InvalidCharacterWeaponException("��ũ �̿��� ĳ���ʹ� �� ���⸦ ������ �� �����ϴ�.");
		}
	}
}
