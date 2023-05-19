import java.util.Random;
public class Enemy {
	
	int target_x, target_y;//목표로 할 머리 좌표
	Random rand = new Random();
	public Enemy() {
		int x = (rand.nextInt()+100)/12;
		int y = (rand.nextInt()+100)/12;//좌표 기록
	}

	public void chase(int target_x, int target_y) {
		//추적 알고리즘
		private static int 
	}
}
