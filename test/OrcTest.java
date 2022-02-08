package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import character.Orc;
import exception.InvalidCharacterWeaponException;
import monster.Monster;
import weapon.Weapon;
import weapon.axe.ShortAxe;
import weapon.bow.ShortBow;

class OrcTest {

	Orc orc;
    
	@BeforeEach
    public void init() {
		orc = new Orc();
	}

	@Test
	@DisplayName("오크가 anger 스킬을 사용 후 공격력 50% 상승, 방어력 10% 하락, mp50 감소")
	public void testUseBasicSkill() {
		int currentDefense = orc.getDefense();
		int defenseAfterSkillApplication = currentDefense - (int)(currentDefense * 0.1);
		int currentOffensePower = orc.getOffensePower();
		int offensePowerAfterWeaponApplication = currentOffensePower + (int)(currentOffensePower * 0.5);
		int currentMp = orc.getMp();
		int mpAfterSkillApplication = currentMp - 30;
		
		orc.useBasicSkill();
		
		assertEquals(orc.getMp(), mpAfterSkillApplication);
		assertEquals(orc.getOffensePower(), offensePowerAfterWeaponApplication);
		assertEquals(orc.getDefense(), defenseAfterSkillApplication);
	}

	@Test
	@DisplayName("오크가 frenzy 스킬을 사용 후 1분 동안 공격력이 500% 상승, mp50감소")
	public void testUseUltimateSkill() {
		int currentOffensePower = orc.getOffensePower();
		int offensePowerAfterWeaponApplication = currentOffensePower + (int)(currentOffensePower * 5);
		int currentMp = orc.getMp();
		int mpAfterSkillApplication = currentMp - 50;
		
		orc.useUltimateSkill();
		
		assertEquals(orc.getMp(), mpAfterSkillApplication);
		assertEquals(orc.getOffensePower(), offensePowerAfterWeaponApplication);
	}

	@Test
	@DisplayName("오크가 shortSword을 착용하고 공격력이 10% 상승, 공격 속도 5% 하락")
	public void testWear() {
		int currentOffensePower = orc.getOffensePower();
		int offensePowerAfterWeaponApplication = currentOffensePower + (int)(currentOffensePower * 0.1);
		int currentAttackSpeed = orc.getAttackSpeed();
		int attackSpeedAfterSkillApplication = currentAttackSpeed - (int)(currentAttackSpeed * 0.05);
		
		Weapon shortAxe = new ShortAxe();
		orc.wear(shortAxe);
		
		assertEquals(orc.getOffensePower(), offensePowerAfterWeaponApplication);
		assertEquals(orc.getAttackSpeed(), attackSpeedAfterSkillApplication);
	}
	
	@Test
	@DisplayName("오크가 엘프 무기를 착용 시 예외 발생")
	public void testWearException() {
		Weapon shortBow = new ShortBow();
		
		assertThrows(InvalidCharacterWeaponException.class, () -> {
			orc.wear(shortBow);
		});
	}
	
	@Test
	@DisplayName("오크가 몬스터를 사냥 시 휴먼의 공격력 - 방어력 만큼 hp 감소")
	public void testAttack() {
		Monster monster = new Monster();
		
		int orcOffensePower = orc.attack();
		monster.beAttacked(orcOffensePower, orc);
		
		assertEquals(monster.getHp(), 70);
	}
	
	@Test
	@DisplayName("오크가 몬스터를 죽일 시 레벨 업")
	public void testLevelUp() {
		int currentOrcLevel = orc.getLevel();
		int levelAfterAttackApplication = currentOrcLevel + 1;
		
		Monster monster = new Monster();
		int orcFakeOffensePower = orc.attack() + 100; // 몬스터를 한번에 죽이기 위해서 공격력을 임의적으로 높임
		
		monster.beAttacked(orcFakeOffensePower, orc);
		
		assertEquals(orc.getLevel(), levelAfterAttackApplication);
	}

	@Test
	@DisplayName("오크가 회피율이 0일 경우 대미지를 받을 시 hp 감소")
	public void testBeAttacked() {
		int currentOrcHp = orc.getHp();
		int hpAfterAttackApplication = currentOrcHp - 30;
		
		orc.setEvasionRate(0);
		orc.beAttacked(30);
		
		assertEquals(orc.getHp(), hpAfterAttackApplication);
	}

	@Test
	@DisplayName("오크가 heal 스킬을 사용 시 hp 30 상승")
	public void testHeal() {
		orc.decreaseHp(50);  //hp 상승 테스트를 위해서 hp 50 감소 시킴
		
		int currentOrcHp = orc.getHp();
		int hpAfterHealApplication = currentOrcHp + 30;
		
		orc.heal();
		assertEquals(orc.getHp(), hpAfterHealApplication);
	}

	@Test
	@DisplayName("오크가 Steam 스킬을 사용 시 공격력 20% 상승, mp 30감소")
	public void testSteam() {
		int currentOffensePower = orc.getOffensePower();
		int offensePowerAfterSkillApplication = currentOffensePower + (int)(currentOffensePower * 0.2);
		int currentMp = orc.getMp();
		int mpAfterSkillApplication = currentMp - 30;
		
		orc.steam();
		
		assertEquals(orc.getMp(), mpAfterSkillApplication);
		assertEquals(orc.getOffensePower(), offensePowerAfterSkillApplication);
	}

}
