package main;
import character.Human;
import monster.Monster;
import weapon.Weapon;
import weapon.sword.ShortSword;

public class Main {

	public static void main(String[] args) {
		
		Human human = new Human();
		
		Weapon shortSword = new ShortSword();
		
		System.out.println("���� ���� �� ���ݷ� : " + human.getOffensePower());
		human.wear(shortSword);
		System.out.println("short sword ���� ���� �� ���ݷ� : " + human.getOffensePower());
		
		Monster monster = new Monster();
		
		System.out.println("��ų ��� �� ���ݷ� : " + human.getOffensePower());
		human.steam();
		System.out.println("��ų ��� �� ���ݷ� : " + human.getOffensePower());
		
		//  ��� ����
		while(human.getHp() > 0 && monster.getHp() > 0) {
			// character�� Monster ����
			int characterOffensePower = human.attack();
			int monsterDamage = monster.beAttacked(characterOffensePower, human);
			System.out.println("���� ������ " + monsterDamage + "����, ���� ü�� " + monster.getHp());
			
			// momster�� character �ݰ�
			if(monster.getHp() > 0) {
				int characterDamage = monster.attack();
				boolean isAttack = human.beAttacked(characterDamage);
				
				if(isAttack) {
					System.out.println("������ ���� �̽�");
				} else {
					System.out.println("�ɸ��� ������ " + characterDamage + "����, �ɸ��� ü�� " + human.getHp());
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
