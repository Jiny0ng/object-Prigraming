package slither.io;

import java.util.Random;

public class Food {
	
	private static final int  EMPTY= 0;
	private static final int  FOOD= 1;
	private static final int  POISON= 2;
	private static final int  ENEMY = 8;
	private static final int  PLAYER = 9;
	
	private int x;
    private int y;
    ///3개만 생성하게 만들어서 각 쓰레드 돌려서 텀 주면 될
    
    
    Random rand = new Random();
    
    public Food() {
    	Food food = new Food();
    	
    }
    
    
    public void generate(int[][] map,int object) {
		this.x = rand.nextInt() / 30;
		this.y = rand.nextInt() / 30;
		
		while(map[y][x] != EMPTY) {
			this.x = rand.nextInt() / 50;
		}
		
		x += 20;
		y += 20;
		
		map[y][x] = object;
	
    }
}
