import java.util.Random;

public class Grid {

	private int status;
	public Grid[][] map = new Grid[50][50];//맵 객체
	
	public Grid() {//기본 상태를 0으로 초기화
		for(int i = 0;i<50;i++) {
			for(int j = 0;j<50;j++) {
				map[i][j] = new Grid(0);//2차원 객체 초기화
			}
		}
	}
	
	public Grid(int num) {
		this.status = num;	
	}
	
	
	public int getStatus() {//status 호출
		return status;
	}
	
	public void setStatus(int status) {//status 변경
		this.status = status;
	}
	
	public void food_zen() {
		Random rand = new Random();
		//음식 좌표용 난수생성
		int food_x = rand.nextInt(50);
		int food_y = rand.nextInt(50);
		
		while(map[food_y][food_x].status==0) {//음식을 빈칸에 만들도록 변경
			food_x = rand.nextInt(50);
			food_y = rand.nextInt(50);
		}
		map[food_y][food_x] = FOOD;//이건 값을 어떻게 처리할지 생각좀. 좌표받아서 여기서 처리 or 가져가서 처리
	}
}
