package character;

import character.skillEffectRemoveThread.BasicSkillRemoveThread;
import character.skillEffectRemoveThread.UltimateSkillRemoveThread;

public class Elf extends Character {

	@Override
	public void useBasicSkill() {
		decreaseMp(30);
		this.evasionRate += (int)(this.evasionRate * 0.3);
		removeSkillThread(new BasicSkillRemoveThread(this));	
	}

	@Override
	public void useUltimateSkill() {
		decreaseMp(50);
		this.attackSpeed += (int)(attackSpeed * 5);
		removeSkillThread(new UltimateSkillRemoveThread(this));
	}

	@Override
	public void removeBasicSkill() {
		this.evasionRate -= (int)(this.evasionRate * 0.3);
	}

	@Override
	public void removeUltimateSkill() {
		this.attackSpeed -= (int)(attackSpeed * 5);	
		
	}

}
