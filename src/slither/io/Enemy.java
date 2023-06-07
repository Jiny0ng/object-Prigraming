package slither.io;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Enemy extends Player {
	
	private static final int  EMPTY= 0;
	private static final int  FOOD= 1;
	private static final int  POISON= 2;
	private static final int  ENEMY = 8;
	private int x;//x좌표
	private int y;//y좌표
	
	
	
	private int[][] map;//서버에서 맵을 받아와 저장할 변수
	
	
	 
	public  Enemy(int[][] map) {
		super(map);
		callmap(map);
		generate(ENEMY);//적 생성
	}
	
	
	private static class Node implements Comparable<Node> {
        private final Point position;
        private int gScore;
        private int fScore;
        private Node parent;

        public Node(Point position, int gScore, int fScore, Node parent) {
            this.position = position;
            this.gScore = gScore;
            this.fScore = fScore;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.fScore, other.fScore);
        }
    }

	
	
   

    public List<Point> findPath(Point start, Point target) {
        int[][] gScores = new int[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                gScores[i][j] = Integer.MAX_VALUE;
            }
        }
        gScores[start.y][start.x] = 0;

        int[][] fScores = new int[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                fScores[i][j] = Integer.MAX_VALUE;
            }
        }
        fScores[start.y][start.x] = heuristicCostEstimate(start, target);

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        openSet.add(new Node(start, 0, fScores[start.y][start.x], null));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.position.equals(target)) {
                return reconstructPath(current);
            }

            List<Point> neighbors = getNeighbors(current.position);
            for (Point neighbor : neighbors) {
                int tentativeGScore = current.gScore + 1;
                if (tentativeGScore < gScores[neighbor.y][neighbor.x]) {
                    gScores[neighbor.y][neighbor.x] = tentativeGScore;
                    fScores[neighbor.y][neighbor.x] = tentativeGScore + heuristicCostEstimate(neighbor, target);
                    openSet.add(new Node(neighbor, tentativeGScore, fScores[neighbor.y][neighbor.x], current));
                }
            }
        }

        return null;  // No path found
    }
    
    private List<Point> reconstructPath(Node node) {
        List<Point> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node.position);
            node = node.parent;
        }
        return path;
    }

    private List<Point> getNeighbors(Point position) {
        int x = position.x;
        int y = position.y;
        List<Point> neighbors = new ArrayList<>();

        // Check top, right, bottom, left neighbors
        if (isValidPoint(x, y - 1)) neighbors.add(new Point(x, y - 1));
        if (isValidPoint(x + 1, y)) neighbors.add(new Point(x + 1, y));
        if (isValidPoint(x, y + 1)) neighbors.add(new Point(x, y + 1));
        if (isValidPoint(x - 1, y)) neighbors.add(new Point(x - 1, y));

        return neighbors;
    }

    private boolean isValidPoint(int x, int y) {
        return x >= 0 && x < 50 && y >= 0 && y < 50 && map[y][x] != FOOD && map[y][x] != POISON;
    }

    private int heuristicCostEstimate(Point start, Point target) {
        return Math.abs(start.x - target.x) + Math.abs(start.y - target.y);
    }






}

