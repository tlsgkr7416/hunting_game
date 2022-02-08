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
	@DisplayName("�޸��� guard ��ų�� ��� �� 30�� ���� ������ 30% ���, mp 30 ����")
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
	@DisplayName("�޸��� Invincible ��ų�� ��� �� 10�� ���� ȸ������ 100 ����Ͽ� ������ �ǰ� mp�� 50 ����")
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
	@DisplayName("�޸��� shortSword�� �����ϰ� ���ݷ��� 5% ���")
	public void testWear() {
		int currentOffensePower = human.getOffensePower();
		int offensePowerAfterWeaponApplication = currentOffensePower + (int)(currentOffensePower * 0.05);
		
		Weapon shortSword = new ShortSword();
		human.wear(shortSword);
		
		assertEquals(human.getOffensePower(), offensePowerAfterWeaponApplication);
	}
	
	@Test
	@DisplayName("�޸��� ��ũ ���⸦ ���� �� ���� �߻�")
	public void testWearException() {
		Weapon shortAxe = new ShortAxe();
		
		assertThrows(InvalidCharacterWeaponException.class, () -> {
			human.wear(shortAxe);
		});
	}
	
	@Test
	@DisplayName("�޸��� ���͸� ��� �� �޸��� ���ݷ� - ���� ��ŭ hp ����")
	public void testAttack() {
		Monster monster = new Monster();
		
		int humanOffensePower = human.attack();
		monster.beAttacked(humanOffensePower, human);
		
		assertEquals(monster.getHp(), 70);
	}
	
	@Test
	@DisplayName("�޸��� ���͸� ���� �� ���� ��")
	public void testLevelUp() {
		int currentHumanLevel = human.getLevel();
		int levelAfterAttackApplication = currentHumanLevel + 1;
		
		Monster monster = new Monster();
		int humanFakeOffensePower = human.attack() + 100; // ���͸� �ѹ��� ���̱� ���ؼ� ���ݷ��� ���������� ����
		
		monster.beAttacked(humanFakeOffensePower, human);
		
		assertEquals(human.getLevel(), levelAfterAttackApplication);
	}

	@Test
	@DisplayName("�޸��� ȸ������ 0�� ��� ������� ���� �� hp ����")
	public void testBeAttacked() {
		int currentHumanHp = human.getHp();
		int hpAfterAttackApplication = currentHumanHp - 30;
		
		human.setEvasionRate(0);
		human.beAttacked(30);
		
		assertEquals(human.getHp(), hpAfterAttackApplication);
	}

	@Test
	@DisplayName("�޸��� heal ��ų�� ��� �� hp 30 ���")
	public void testHeal() {
		human.decreaseHp(50);  //hp ��� �׽�Ʈ�� ���ؼ� hp 50 ���� ��Ŵ
		
		int currentHumanHp = human.getHp();
		int hpAfterHealApplication = currentHumanHp + 30;
		
		human.heal();
		assertEquals(human.getHp(), hpAfterHealApplication);
	}

	@Test
	@DisplayName("�޸��� Steam ��ų�� ��� �� ���ݷ� 20% ���, mp30 ����")
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
