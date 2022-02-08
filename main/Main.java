package main;
import character.Human;
import monster.Monster;
import weapon.Weapon;
import weapon.sword.ShortSword;

public class Main {

	public static void main(String[] args) {
		
		Human human = new Human();
		
		Weapon shortSword = new ShortSword();
		
		System.out.println("무기 착용 전 공격력 : " + human.getOffensePower());
		human.wear(shortSword);
		System.out.println("short sword 무기 착용 후 공격력 : " + human.getOffensePower());
		
		Monster monster = new Monster();
		
		System.out.println("스킬 사용 전 공격력 : " + human.getOffensePower());
		human.steam();
		System.out.println("스킬 사용 후 공격력 : " + human.getOffensePower());
		
		//  사냥 시작
		while(human.getHp() > 0 && monster.getHp() > 0) {
			// character가 Monster 공격
			int characterOffensePower = human.attack();
			int monsterDamage = monster.beAttacked(characterOffensePower, human);
			System.out.println("몬스터 데미지 " + monsterDamage + "감소, 몬스터 체력 " + monster.getHp());
			
			// momster가 character 반격
			if(monster.getHp() > 0) {
				int characterDamage = monster.attack();
				boolean isAttack = human.beAttacked(characterDamage);
				
				if(isAttack) {
					System.out.println("몬스터의 공격 미스");
				} else {
					System.out.println("케릭터 데미지 " + characterDamage + "감소, 케릭터 체력 " + human.getHp());
				}
			}
			
	        try{
	        	int baseAttackSpeedTime = 3000;
	            Thread.sleep(baseAttackSpeedTime - human.getAttackSpeed());	            
	        }catch (InterruptedException e){
	            e.printStackTrace();
	        }
		}
		
	}

}
