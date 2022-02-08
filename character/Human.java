package character;

import character.skillEffectRemoveThread.BasicSkillRemoveThread;
import character.skillEffectRemoveThread.UltimateSkillRemoveThread;

public class Human extends Character {
	
	@Override
	public void useBasicSkill() {
		decreaseMp(30);
		this.defense += (int)(this.defense * 0.2);
		removeSkillThread(new BasicSkillRemoveThread(this));
	}

	@Override
	public void useUltimateSkill() {
		decreaseMp(50);
		this.evasionRate += 100;
		removeSkillThread(new UltimateSkillRemoveThread(this));
		
	}

	@Override
	public void removeBasicSkill() {
		this.defense -= (int)(this.defense * 0.2);

	}

	@Override
	public void removeUltimateSkill() {
		this.evasionRate -= 100;
		
	}

}
