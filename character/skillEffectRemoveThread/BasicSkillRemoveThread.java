package character.skillEffectRemoveThread;

import character.CharacterSkill;

public class BasicSkillRemoveThread extends Thread {
	
	CharacterSkill character;

	public BasicSkillRemoveThread(CharacterSkill character) {
		this.character = character;
	}
	
	@Override
	public void run() {
        try{
            Thread.sleep(30000);
            character.removeBasicSkill();
            
        }catch (InterruptedException e){
            e.printStackTrace();
        }	
	}

}
