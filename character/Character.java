package character;

import character.skillEffectRemoveThread.SteamRemoveThread;
import weapon.Weapon;

public abstract class Character implements CharacterWear, CharacterHealth, CharacterSkill, CharacterAttack, CharacterSubThread {
	
	int level = 1;
	
	int hp = 100;
	
	int mp = 100;
	
	int offensePower = 50;
	
	int attackSpeed = 50;
	
	int defense = 50;
	
	int evasionRate = 20;
	
	Weapon wornWeapon;
	
	@Override
	public void wear(Weapon weapon) {
		
		if(this.wornWeapon != null) {
			undress();
		}
		
		weapon.addEffect(this);
		this.wornWeapon = weapon;
	}
	
	@Override
	public void undress() {
		if(wornWeapon != null) {
			this.wornWeapon.removeEffect(this);
			this.wornWeapon = null;
		}
		
	}
	
	@Override
	public int attack() {
		return this.offensePower;
	}
	
	@Override
	public boolean beAttacked(int damage) {
		if(!isEvasionRate()) {
			decreaseHp(damage);
			return false;
		}
		return true;
	}
	
	@Override
	public boolean isEvasionRate() {
		int random = (int) (Math.random() * 100);

		return this.evasionRate > random ? true : false;
	}
	
	@Override
	public void heal() {
		increaseHp(30);
	}
	
	@Override
	public void steam() {
		decreaseMp(30);
		this.offensePower = this.offensePower + (int)(offensePower * 0.2);
		removeSkillThread(new SteamRemoveThread(this));
	}
	
	@Override
	public void removeSteamEffect() {
		this.offensePower = this.offensePower - (int)(offensePower * 0.2);
	}
	
	@Override
	public void increaseHp(int hp) {
		this.hp += hp;
		
		if(this.hp > 100) {
			this.hp = 100;
		}
	}

	@Override
	public void decreaseHp(int hp) {
		this.hp -= hp;
		if(this.hp < 0) {
			this.hp = 0;
		}
		
		checkDeath();	
	}

	@Override
	public void increaseMp(int mp) {
		this.mp += mp;
		
		if(this.mp > 100) {
			this.mp = 100;
		}
		
	}

	@Override
	public void decreaseMp(int mp) {
		this.mp -= mp;
		
		if(this.mp < 0) {
			this.mp = 0;
		}
		
	}
	
	@Override
	public void checkDeath() {
		if(0 >= getHp()) {
			System.out.println("ÄÉ¸¯ÅÍ Á×À½");
		}
	}
	
	@Override
	public void levelUp() {
		this.level += 1;
	}
	
	@Override
	public void removeSkillThread(Thread thread) {
		thread.start();
	}
	
	public Weapon getWornWeapon() {
		return wornWeapon;
	}

	public void setWornWeapon(Weapon wornWeapon) {
		this.wornWeapon = wornWeapon;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getOffensePower() {
		return offensePower;
	}

	public void setOffensePower(int offensePower) {
		this.offensePower = offensePower;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getEvasionRate() {
		return evasionRate;
	}

	public void setEvasionRate(int evasionRate) {
		this.evasionRate = evasionRate;
	}


}
