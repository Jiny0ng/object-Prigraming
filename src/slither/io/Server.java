package slither.io;

import temp_code.Grid;

public class Server {
	public static final int ENPTY = 0;	//빈공간
	public static final int FOOD = 1;	//음식
	public static final int POISON = 2;	//독
	public static final int FAKE = 3;	//가짜먹이
	public static final int ENEMY = 10;	//적
	public static final int BODY = 11;	//뱀 몸
	
	private static int level;
	
	public static int[][] map = new int[50][50];
	
	

	
	
	
	
	
	
	public static void main(String[] args) {
		

		PersonalData input = new PersonalData();//이름, 난이도 입력 컴포넌트
		level =	input.level();									 		 // 입력받은 난이도를 저장
		Controller controller = new Controller();   //컨트롤러 객체
		Printer printer = new Printer();					//맵 출력 컴포넌트
		Player user = new Player();						     //유저 객체안에 배열 있음
		//Grid world = new Grid();								 //맵이 들어있는 world객체 선
		
		
		
		Enemy monster = new Enemy(map);		 //몬스터 객체
		
		/*for(int i = 0;i<20;i++) {//플레이어 객체 생
			user[i] = new Player(map);
		}*/
		
		/*Thread inputThread = new Thread(() -> {
            input.level();
        });*/

        Thread controllerThread = new Thread(() -> {
        	//controller.handleKeyPress(controller.getLastKeyCode());
        	while (true) {
                int keyCode = controller.getLastKeyCode();
                controller.handleKeyPress(keyCode);

                // 원하는 딜레이 시간(밀리초)만큼 쓰레드 일시 정지
                try {
                    Thread.sleep(100); // 예시로 100밀리초(0.1초)로 설정
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread printerThread = new Thread(() -> {
           // printer.run();
        });

        Thread playerThread = new Thread(() -> {
            //user.run();
        });
		
		
				

		
	}
	
	public void map_gen() {
		for(int i =0;i<50;i++) {
			for(int j = 0;j<50;j++) {
				map[i][j] = 0;
			}
		}
	}
	
	
}
