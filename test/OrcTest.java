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
	@DisplayName("��ũ�� anger ��ų�� ��� �� ���ݷ� 50% ���, ���� 10% �϶�, mp50 ����")
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
	@DisplayName("��ũ�� frenzy ��ų�� ��� �� 1�� ���� ���ݷ��� 500% ���, mp50����")
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
	@DisplayName("��ũ�� shortSword�� �����ϰ� ���ݷ��� 10% ���, ���� �ӵ� 5% �϶�")
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
	@DisplayName("��ũ�� ���� ���⸦ ���� �� ���� �߻�")
	public void testWearException() {
		Weapon shortBow = new ShortBow();
		
		assertThrows(InvalidCharacterWeaponException.class, () -> {
			orc.wear(shortBow);
		});
	}
	
	@Test
	@DisplayName("��ũ�� ���͸� ��� �� �޸��� ���ݷ� - ���� ��ŭ hp ����")
	public void testAttack() {
		Monster monster = new Monster();
		
		int orcOffensePower = orc.attack();
		monster.beAttacked(orcOffensePower, orc);
		
		assertEquals(monster.getHp(), 70);
	}
	
	@Test
	@DisplayName("��ũ�� ���͸� ���� �� ���� ��")
	public void testLevelUp() {
		int currentOrcLevel = orc.getLevel();
		int levelAfterAttackApplication = currentOrcLevel + 1;
		
		Monster monster = new Monster();
		int orcFakeOffensePower = orc.attack() + 100; // ���͸� �ѹ��� ���̱� ���ؼ� ���ݷ��� ���������� ����
		
		monster.beAttacked(orcFakeOffensePower, orc);
		
		assertEquals(orc.getLevel(), levelAfterAttackApplication);
	}

	@Test
	@DisplayName("��ũ�� ȸ������ 0�� ��� ������� ���� �� hp ����")
	public void testBeAttacked() {
		int currentOrcHp = orc.getHp();
		int hpAfterAttackApplication = currentOrcHp - 30;
		
		orc.setEvasionRate(0);
		orc.beAttacked(30);
		
		assertEquals(orc.getHp(), hpAfterAttackApplication);
	}

	@Test
	@DisplayName("��ũ�� heal ��ų�� ��� �� hp 30 ���")
	public void testHeal() {
		orc.decreaseHp(50);  //hp ��� �׽�Ʈ�� ���ؼ� hp 50 ���� ��Ŵ
		
		int currentOrcHp = orc.getHp();
		int hpAfterHealApplication = currentOrcHp + 30;
		
		orc.heal();
		assertEquals(orc.getHp(), hpAfterHealApplication);
	}

	@Test
	@DisplayName("��ũ�� Steam ��ų�� ��� �� ���ݷ� 20% ���, mp 30����")
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
