package character.skillEffectRemoveThread;
import character.CharacterSkill;

public class SteamRemoveThread extends Thread {
	
	CharacterSkill character;
	
	public SteamRemoveThread(CharacterSkill character) {
		this.character = character;
	}
	
	@Override
	public void run() {
        try{
            Thread.sleep(30000);
            character.removeSteamEffect();
            
        }catch (InterruptedException e){
            e.printStackTrace();
        }	
	}

}
