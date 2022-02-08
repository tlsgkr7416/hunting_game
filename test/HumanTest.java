package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import character.Human;
import exception.InvalidCharacterWeaponException;
import monster.Monster;
import weapon.Weapon;
import weapon.axe.ShortAxe;
import weapon.sword.ShortSword;

class HumanTest {
	
	Human human;
    
	@BeforeEach
    public void init() {
		human = new Human();
	}

	@Test
	@DisplayName("휴먼이 guard 스킬을 사용 후 30초 동안 방어력이 30% 상승, mp 30 감소")
	public void testUseBasicSkill() {
		int currentDefense = human.getDefense();
		int defenseAfterSkillApplication = currentDefense + (int)(currentDefense * 0.2);
		int currentMp = human.getMp();
		int mpAfterSkillApplication = currentMp - 30;
		
		human.useBasicSkill();
		
		assertEquals(human.getMp(), mpAfterSkillApplication);
		assertEquals(human.getDefense(), defenseAfterSkillApplication);
	}

	@Test
	@DisplayName("휴먼이 Invincible 스킬을 사용 후 10초 동안 회피율이 100 상승하여 무적이 되고 mp는 50 감소")
	public void testUseUltimateSkill() {
		int currentEvasionRate = human.getEvasionRate();
		int evasionRateAfterSkillApplication = currentEvasionRate + 100;
		int currentMp = human.getMp();
		int mpAfterSkillApplication = currentMp - 50;
		
		human.useUltimateSkill();
		
		assertEquals(human.getMp(), mpAfterSkillApplication);
		assertEquals(human.getEvasionRate(), evasionRateAfterSkillApplication);
	}

	@Test
	@DisplayName("휴먼이 shortSword을 착용하고 공격력이 5% 상승")
	public void testWear() {
		int currentOffensePower = human.getOffensePower();
		int offensePowerAfterWeaponApplication = currentOffensePower + (int)(currentOffensePower * 0.05);
		
		Weapon shortSword = new ShortSword();
		human.wear(shortSword);
		
		assertEquals(human.getOffensePower(), offensePowerAfterWeaponApplication);
	}
	
	@Test
	@DisplayName("휴먼이 오크 무기를 착용 시 예외 발생")
	public void testWearException() {
		Weapon shortAxe = new ShortAxe();
		
		assertThrows(InvalidCharacterWeaponException.class, () -> {
			human.wear(shortAxe);
		});
	}
	
	@Test
	@DisplayName("휴먼이 몬스터를 사냥 시 휴먼의 공격력 - 방어력 만큼 hp 감소")
	public void testAttack() {
		Monster monster = new Monster();
		
		int humanOffensePower = human.attack();
		monster.beAttacked(humanOffensePower, human);
		
		assertEquals(monster.getHp(), 70);
	}
	
	@Test
	@DisplayName("휴먼이 몬스터를 죽일 시 레벨 업")
	public void testLevelUp() {
		int currentHumanLevel = human.getLevel();
		int levelAfterAttackApplication = currentHumanLevel + 1;
		
		Monster monster = new Monster();
		int humanFakeOffensePower = human.attack() + 100; // 몬스터를 한번에 죽이기 위해서 공격력을 임의적으로 높임
		
		monster.beAttacked(humanFakeOffensePower, human);
		
		assertEquals(human.getLevel(), levelAfterAttackApplication);
	}

	@Test
	@DisplayName("휴먼의 회피율이 0일 경우 대미지를 받을 시 hp 감소")
	public void testBeAttacked() {
		int currentHumanHp = human.getHp();
		int hpAfterAttackApplication = currentHumanHp - 30;
		
		human.setEvasionRate(0);
		human.beAttacked(30);
		
		assertEquals(human.getHp(), hpAfterAttackApplication);
	}

	@Test
	@DisplayName("휴먼이 heal 스킬을 사용 시 hp 30 상승")
	public void testHeal() {
		human.decreaseHp(50);  //hp 상승 테스트를 위해서 hp 50 감소 시킴
		
		int currentHumanHp = human.getHp();
		int hpAfterHealApplication = currentHumanHp + 30;
		
		human.heal();
		assertEquals(human.getHp(), hpAfterHealApplication);
	}

	@Test
	@DisplayName("휴먼이 Steam 스킬을 사용 시 공격력 20% 상승, mp30 감소")
	public void testSteam() {
		int currentOffensePower = human.getOffensePower();
		int offensePowerAfterSkillApplication = currentOffensePower + (int)(currentOffensePower * 0.2);
		int currentMp = human.getMp();
		int mpAfterSkillApplication = currentMp - 30;
		
		human.steam();
		
		assertEquals(human.getMp(), mpAfterSkillApplication);
		assertEquals(human.getOffensePower(), offensePowerAfterSkillApplication);
	}

}
