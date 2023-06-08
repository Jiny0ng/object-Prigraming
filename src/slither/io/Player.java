package slither.io;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class Player extends JLabel{
	
	private static final int  EMPTY= 0;
	private static final int  FOOD= 1;
	private static final int  POISON= 2;
	private static final int  ENEMY = 8;
	private static final int  PLAYER = 9;
    private int x;
    private int y;
    private int setX;
    private int setY;
    private int length;
    private int max_length;
    private boolean isHead = false;
    private boolean isVisible;
    Random rand = new Random();
    
   
    private int[][] map;//서버에서 맵을 받아와 저장할 변수
    public void callmap(int[][] map) {
        this.map = map;
    }
    
    public Player(int i) {
    	callmap(map);
        this.length = 1;
        this.max_length = 1;
        this.isVisible = true;      
       if(i==0) {
    	   this.isHead = true;
       }              
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
    
    public void SnakeComponent() {
        ImageIcon snakeIcon = new ImageIcon("images/snake.png"); // 지렁이 이미지 파일 로드
        setIcon(snakeIcon); // 이미지를 컴포넌트에 설정
    }

    
    public void moving(int direction, Player[] body) {
    	 int x = body.x;
    	 int y = body.y;
    	
    	 
    	 if(body.isHead) {
    		 switch(direction) {
 			case 1 ://up
 				if(body.y>0) {
 				y = body.y -1;}
 			case 2 ://down
 				if(body.y<50) {
 				y = body.y+1;}
 			case 3 ://left
 				if(body.x>0) {
 				x = body.x-1;}
 			case 4 ://right
 				if(body.x<50) {
 				x = body.x+1;}
 		}
    	 }
		/*for(int i  = 19;i>0;i--) {//몸통이 앞 몸통의 주소로 따라
			snake[i].x = snake[i-1].x;
			snake[i].y = snake[i-1].y;
		}
		
		x = snake[0].x;
		y = snake[0].y;
		switch(direction) {
			case 1 ://up
				if(snake[0].y>0) {
				x = snake[0].y -1;}
			case 2 ://down
				if(snake[0].y<50) {
				x = snake[0].y+1;}
			case 3 ://left
				if(snake[0].x>0) {
				x = snake[0].x-1;}
			case 4 ://right
				if(snake[0].x<50) {
				x = snake[0].x+1;}
		}
		
		snake[0].x = x;
		snake[0].y = y;*/
	
    }
    
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y =  y;
    }

    public int getLength() {
        return length;
    }
    
    public int getMaxLength() {
        return max_length;
    }
}
    
    /*public boolean isHead() {
        return isHead;
    }*/


/*@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == startButton) {
        startGame();
    }
}*/



/*
private void movePlayer(int dx, int dy) {
    Player currentPlayer = players[currentPlayerIndex];
    int newX = currentPlayer.getX() + dx;
    int newY = currentPlayer.getY() + dy;

    if (newX < 1 || newX >= 12 || newY < 1 || newY >= 12) {
        endGame();
        return;
    }

    if (!grid[newX][newY].isEmpty()) {
        if (grid[newX][newY].isFood()) {
            currentPlayer.length++;
            score++;
        } else if (grid[newX][newY].isPoison()) {
            currentPlayer.length--;
        } else {
            endGame();
            return;
        }
    }

    currentPlayer.x = newX;
    currentPlayer.y = newY;

    if (currentPlayer.getLength() > MAX_LENGTH) {
        currentPlayer.length = MAX_LENGTH;
    }

    if (currentPlayerIndex == 0) {
        currentPlayerIndex = 1;
    } else {
        currentPlayerIndex = 0;
    }

    drawGrid();
}
*/

/*
@Override
public void keyTyped(KeyEvent e) {
}

@Override
public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();

    switch (keyCode) {
        case KeyEvent.VK_UP:
            movePlayer(-1, 0);
            break;
        case KeyEvent.VK_DOWN:
            movePlayer(1, 0);
            break;
        case KeyEvent.VK_LEFT:
            movePlayer(0, -1);
            break;
        case KeyEvent.VK_RIGHT:
            movePlayer(0, 1);
            break;
    }
}
*/

