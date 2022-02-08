package character.skillEffectRemoveThread;

import character.CharacterSkill;
import character.Elf;
import character.Orc;

public class UltimateSkillRemoveThread extends Thread {
	
	CharacterSkill character;
	
	public UltimateSkillRemoveThread(CharacterSkill character) {
		this.character = character;
	}
	
	@Override
	public void run() {
        try{
        	int skillTime = getSkillTime();
        	
        	Thread.sleep(skillTime);
        	character.removeUltimateSkill();
        }catch (InterruptedException e){
            e.printStackTrace();
        }	
	}
	
	private int getSkillTime() {
		
		if(this.character instanceof Orc) {
			return 60000;
		} else if(this.character instanceof Elf) {
			return 60000;
		} else {
			return 10000;
		}
	}

}
