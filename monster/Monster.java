package monster;
import character.Character;

public class Monster implements MonsterAttack, MonsterHealth {
	
	private int hp = 100;
	
	private int offensePower = 20;
	
	private int defense = 20;
	
	@Override
	public int attack() {
		if(isAttackPossible()) {
			return (int) (this.offensePower * 0.7);
		}
		
		System.out.println("몬스터 반격 실패");
		return 0;
	}
	
	@Override
	public boolean isAttackPossible() {
		int random = (int) (Math.random() * 10);
		
		return 3 > random ? true : false;
	}
	
	@Override
	public int beAttacked(int characterOffensePower, Character character) {
		int damage = characterOffensePower - this.defense;
		int hp = decreaseHp(damage);
		
		if(0 >= hp) {
			character.levelUp();
		}
		
		return damage;
	}

	@Override
	public int decreaseHp(int hp) {
		this.hp -= hp;
		
		if(this.hp < 0) {
			this.hp = 0;
		}
		
		return this.hp;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

}
