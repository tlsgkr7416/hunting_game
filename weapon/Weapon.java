package weapon;
import character.Character;

public interface Weapon {
	
	public void addEffect(Character character);
	
	public void removeEffect(Character character);
}
