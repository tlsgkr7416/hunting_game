package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import character.Elf;
import exception.InvalidCharacterWeaponException;
import monster.Monster;
import weapon.Weapon;
import weapon.axe.ShortAxe;
import weapon.bow.ShortBow;

class ElfTest {

	Elf elf;
    
	@BeforeEach
    public void init() {
		elf = new Elf();
	}

	@Test
	@DisplayName("엘프가 elusion 스킬을 사용 후 30초 동안 회피율이 30% 상승, mp 30 감소")
	public void testUseBasicSkill() {
		int currentEvasionRate = elf.getEvasionRate();
		int defenseAfterSkillApplication = currentEvasionRate + (int)(currentEvasionRate * 0.3);
		int currentMp = elf.getMp();
		int mpAfterSkillApplication = currentMp - 30;
		
		elf.useBasicSkill();
		
		assertEquals(elf.getMp(), mpAfterSkillApplication);
		assertEquals(elf.getEvasionRate(), defenseAfterSkillApplication);
	}

	@Test
	@DisplayName("엘프가 Rapid 스킬을 사용 후 공격 속도가 500%로 상승, mp 50감소")
	public void testUseUltimateSkill() {
		int currentAttackSpeed = elf.getAttackSpeed();
		int attackSpeedAfterSkillApplication = currentAttackSpeed + (int)(currentAttackSpeed * 5);
		int currentMp = elf.getMp();
		int mpAfterSkillApplication = currentMp - 50;
		
		elf.useUltimateSkill();
		
		assertEquals(elf.getMp(), mpAfterSkillApplication);
		assertEquals(elf.getAttackSpeed(), attackSpeedAfterSkillApplication);
	}

	@Test
	@DisplayName("엘프가 shortBow를 착용하고 공격 속도가 5% 상승")
	public void testWear() {
		int currentAttackSpeed = elf.getAttackSpeed();
		int attackSpeedAfterSkillApplication = currentAttackSpeed + (int)(currentAttackSpeed * 0.05);
		
		Weapon shortBow = new ShortBow();
		elf.wear(shortBow);
		
		assertEquals(elf.getAttackSpeed(), attackSpeedAfterSkillApplication);
	}
	
	@Test
	@DisplayName("엘프가 오크 무기를 착용 시 예외 발생")
	public void testWearException() {
		Weapon shortAxe = new ShortAxe();
		
		assertThrows(InvalidCharacterWeaponException.class, () -> {
			elf.wear(shortAxe);
		});
	}
	
	@Test
	@DisplayName("엘프가 몬스터를 사냥 시 휴먼의 공격력 - 방어력 만큼 hp 감소")
	public void testAttack() {
		Monster monster = new Monster();
		
		int elfOffensePower = elf.attack();
		monster.beAttacked(elfOffensePower, elf);
		
		assertEquals(monster.getHp(), 70);
	}
	
	@Test
	@DisplayName("엘프가 몬스터를 죽일 시 레벨 업")
	public void testLevelUp() {
		int currentElfLevel = elf.getLevel();
		int levelAfterAttackApplication = currentElfLevel + 1;
		
		Monster monster = new Monster();
		int elfFakeOffensePower = elf.attack() + 100; // 몬스터를 한번에 죽이기 위해서 공격력을 임의적으로 높임
		
		monster.beAttacked(elfFakeOffensePower, elf);
		
		assertEquals(elf.getLevel(), levelAfterAttackApplication);
	}

	@Test
	@DisplayName("엘프의 회피율이 0일 경우 대미지를 받을 시 hp 감소")
	public void testBeAttacked() {
		int currentElfHp = elf.getHp();
		int hpAfterAttackApplication = currentElfHp - 30;
		
		elf.setEvasionRate(0);
		elf.beAttacked(30);
		
		assertEquals(elf.getHp(), hpAfterAttackApplication);
	}

	@Test
	@DisplayName("엘프의 heal 스킬을 사용 시 hp 30 상승")
	public void testHeal() {
		elf.decreaseHp(50);  //hp 상승 테스트를 위해서 hp 50 감소 시킴
		
		int currentElfHp = elf.getHp();
		int hpAfterHealApplication = currentElfHp + 30;
		
		elf.heal();
		assertEquals(elf.getHp(), hpAfterHealApplication);
	}

	@Test
	@DisplayName("엘프가 Steam 스킬을 사용 시 공격력 20% 상승, mp30 감소")
	public void testSteam() {
		int currentOffensePower = elf.getOffensePower();
		int offensePowerAfterSkillApplication = currentOffensePower + (int)(currentOffensePower * 0.2);
		int currentMp = elf.getMp();
		int mpAfterSkillApplication = currentMp - 30;
		
		elf.steam();
		
		assertEquals(elf.getMp(), mpAfterSkillApplication);
		assertEquals(elf.getOffensePower(), offensePowerAfterSkillApplication);
	}

}
