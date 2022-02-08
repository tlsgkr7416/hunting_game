package character;

import character.skillEffectRemoveThread.BasicSkillRemoveThread;
import character.skillEffectRemoveThread.UltimateSkillRemoveThread;

public class Orc extends Character {

	@Override
	public void useBasicSkill() {
		decreaseMp(30);
		this.offensePower += (int)(this.offensePower * 0.5);
		this.defense -= (int)(this.defense * 0.1);
		removeSkillThread(new BasicSkillRemoveThread(this));
		
	}

	@Override
	public void useUltimateSkill() {
		decreaseMp(50);
		this.offensePower += (int)(offensePower * 5);
		removeSkillThread(new UltimateSkillRemoveThread(this));
		
	}

	@Override
	public void removeBasicSkill() {
		this.offensePower -= (int)(this.offensePower * 0.5);
		this.defense -= (int)(this.defense * 0.1);
		
	}

	@Override
	public void removeUltimateSkill() {
		this.offensePower -= (int)(offensePower * 5);
		
	}

}
