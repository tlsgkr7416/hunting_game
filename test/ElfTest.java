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
	@DisplayName("������ elusion ��ų�� ��� �� 30�� ���� ȸ������ 30% ���, mp 30 ����")
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
	@DisplayName("������ Rapid ��ų�� ��� �� ���� �ӵ��� 500%�� ���, mp 50����")
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
	@DisplayName("������ shortBow�� �����ϰ� ���� �ӵ��� 5% ���")
	public void testWear() {
		int currentAttackSpeed = elf.getAttackSpeed();
		int attackSpeedAfterSkillApplication = currentAttackSpeed + (int)(currentAttackSpeed * 0.05);
		
		Weapon shortBow = new ShortBow();
		elf.wear(shortBow);
		
		assertEquals(elf.getAttackSpeed(), attackSpeedAfterSkillApplication);
	}
	
	@Test
	@DisplayName("������ ��ũ ���⸦ ���� �� ���� �߻�")
	public void testWearException() {
		Weapon shortAxe = new ShortAxe();
		
		assertThrows(InvalidCharacterWeaponException.class, () -> {
			elf.wear(shortAxe);
		});
	}
	
	@Test
	@DisplayName("������ ���͸� ��� �� �޸��� ���ݷ� - ���� ��ŭ hp ����")
	public void testAttack() {
		Monster monster = new Monster();
		
		int elfOffensePower = elf.attack();
		monster.beAttacked(elfOffensePower, elf);
		
		assertEquals(monster.getHp(), 70);
	}
	
	@Test
	@DisplayName("������ ���͸� ���� �� ���� ��")
	public void testLevelUp() {
		int currentElfLevel = elf.getLevel();
		int levelAfterAttackApplication = currentElfLevel + 1;
		
		Monster monster = new Monster();
		int elfFakeOffensePower = elf.attack() + 100; // ���͸� �ѹ��� ���̱� ���ؼ� ���ݷ��� ���������� ����
		
		monster.beAttacked(elfFakeOffensePower, elf);
		
		assertEquals(elf.getLevel(), levelAfterAttackApplication);
	}

	@Test
	@DisplayName("������ ȸ������ 0�� ��� ������� ���� �� hp ����")
	public void testBeAttacked() {
		int currentElfHp = elf.getHp();
		int hpAfterAttackApplication = currentElfHp - 30;
		
		elf.setEvasionRate(0);
		elf.beAttacked(30);
		
		assertEquals(elf.getHp(), hpAfterAttackApplication);
	}

	@Test
	@DisplayName("������ heal ��ų�� ��� �� hp 30 ���")
	public void testHeal() {
		elf.decreaseHp(50);  //hp ��� �׽�Ʈ�� ���ؼ� hp 50 ���� ��Ŵ
		
		int currentElfHp = elf.getHp();
		int hpAfterHealApplication = currentElfHp + 30;
		
		elf.heal();
		assertEquals(elf.getHp(), hpAfterHealApplication);
	}

	@Test
	@DisplayName("������ Steam ��ų�� ��� �� ���ݷ� 20% ���, mp30 ����")
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
