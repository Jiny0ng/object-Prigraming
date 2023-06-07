package slither.io;

import java.util.HashMap;
public class Point {

	    private HashMap<String, Integer> scoreBoard;
	    
	    public Point() {
	        scoreBoard = new HashMap<>();
	    }

	    public void updateScore(String playerName, int score) {
	        scoreBoard.put(playerName, score);
	    }

	    public void displayScoreBoard() {
	        System.out.println("Scoreboard:");
	        for (String playerName : scoreBoard.keySet()) {
	            int score = scoreBoard.get(playerName);
	            System.out.println(playerName + ": " + score);
	        }
	    }

	    public static void main(String[] args) {
	        // 게임 로직 실행

	        Point point = new Point();
	        // 게임 종료 후, 플레이어의 이름과 점수를 업데이트
	        point.updateScore("Player 1", 100);
	        point.updateScore("Player 2", 50);

	        // 최종 스코어 보드 출력
	        point.displayScoreBoard();
	    }
	}


