package temp_code;


import javax.swing.*;

import slither.io.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Grid extends JFrame implements KeyListener, ActionListener {
    private static final int MAX_LENGTH = 20;

    private JPanel gamePanel;
    private JButton startButton;
    private JLabel scoreLabel;
    private Cell[][] grid;
    private Player[] players;
    private int currentPlayerIndex;
    private Timer gameTimer;
    private int score;
    ImageIcon img[] = new ImageIcon[0];
    private JLayeredPane layeredPane;
    private JLabel headLabel;
    private JLabel[] bodyLabels;


    class Cell {
        private boolean isEmpty;
        private boolean isFood;
        private boolean isPoison;

        public Cell() {
            this.isEmpty = true;
            this.isFood = false;
            this.isPoison = false;
        }

        public void setEmpty(boolean empty) {
            isEmpty = empty;
        }

        public void setFood(boolean food) {
            isFood = food;
        }

        public void setPoison(boolean poison) {
            isPoison = poison;
        }

        public boolean isEmpty() {
            return isEmpty;
        }

        public boolean isFood() {
            return isFood;
        }

        public boolean isPoison() {
            return isPoison;
        }
    }

    private void showSettingsDialog() {
        JDialog dialog = new JDialog(this, "Game Settings", true);
        dialog.setLayout(new BorderLayout());

        // 이름 입력 컴포넌트
        JLabel nameLabel = new JLabel("Enter your name:");
        JTextField nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(100, nameTextField.getPreferredSize().height));
        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);

        // 난이도 설정 컴포넌트
        JLabel difficultyLabel = new JLabel("Select difficulty:");
        String[] difficultyOptions = {"Easy", "Medium", "Hard"};
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficultyOptions);
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(difficultyComboBox);

        // 확인 버튼
        JButton confirmButton = new JButton("Start Game");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = nameTextField.getText();
                String difficulty = (String) difficultyComboBox.getSelectedItem();
                // 여기서 playerName과 difficulty 변수를 사용하여 설정에 대한 처리를 수행할 수 있습니다.
                dialog.dispose(); // 다이얼로그 닫기
            }
        });

        // 다이얼로그 컨텐츠 설정
        dialog.add(namePanel, BorderLayout.NORTH);
        dialog.add(difficultyPanel, BorderLayout.CENTER);
        dialog.add(confirmButton, BorderLayout.SOUTH);
        
        // 다이얼로그 크기 및 위치 설정
        dialog.setSize(300, 200); // 다이얼로그 크기 설정
        dialog.setLocationRelativeTo(this);

        
        // 다이얼로그 표시
        dialog.setVisible(true);
    }

    public void Final() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setSize(900, 900);
        addKeyListener(this);

        // 게임 패널 생성
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("src/images/Tile.jpg").getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        gamePanel.setBounds(100, 50, 600, 600);
        add(gamePanel);
        
        // LayeredPane 생성
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(100, 50, 600, 600);
        layeredPane.setBackground(Color.BLACK);
        add(layeredPane);

        // 머리 레이블 생성
        headLabel = new JLabel();
        headLabel.setOpaque(true);
        headLabel.setBackground(Color.WHITE);
        headLabel.setBounds(0, 0, 50, 50);
        layeredPane.add(headLabel, JLayeredPane.DEFAULT_LAYER);

        // 몸통 레이블 배열 생성
        bodyLabels = new JLabel[MAX_LENGTH - 1];
        for (int i = 0; i < MAX_LENGTH - 1; i++) {
            bodyLabels[i] = new JLabel();
            bodyLabels[i].setOpaque(true);
            bodyLabels[i].setBackground(Color.WHITE);
            bodyLabels[i].setBounds(0, 0, 50, 50);
            layeredPane.add(bodyLabels[i], JLayeredPane.DEFAULT_LAYER);
        }


        // 시작 버튼 생성
        startButton = new JButton("Start");
        startButton.setBounds(750, 700, 100, 50);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSettingsDialog(); // 설정 다이얼로그 표시
            }
        });
        add(startButton);

        // 점수 레이블 생성
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(750, 50, 100, 50);
        scoreLabel.setForeground(Color.BLACK);
        add(scoreLabel);

        // 그리드 생성 및 초기화
        grid = new Cell[12][12];
        initializeGrid();

        setVisible(true);
    }

    private void initializeGrid() {
        for (int row = 1; row < 12; row++) {
            for (int col = 1; col < 12; col++) {
                grid[row][col] = new Cell();
            }
        }
    }

    private void drawGrid() {//인스턴스로 좌표 받아서 그걸로 처
        Graphics g = gamePanel.getGraphics();
        int cellSize = 50;

        for (int row = 1; row <= 12; row++) {
            for (int col = 1; col <= 12; col++) {
                int x = col * cellSize + 100;
                int y = row * cellSize + 100;
                
                // 머리 레이블 위치 설정
                int headX = players[currentPlayerIndex].getX() * cellSize + 100;
                int headY = players[currentPlayerIndex].getY() * cellSize + 100;
                headLabel.setBounds(headX, headY, cellSize, cellSize);

                // 몸통 레이블 위치 설정
                for (int i = 0; i < players[currentPlayerIndex].getLength() - 1; i++) {
                    int bodyX = players[currentPlayerIndex].getBodyX(i) * cellSize + 100;
                    int bodyY = players[currentPlayerIndex].getBodyY(i) * cellSize + 100;
                    bodyLabels[i].setBounds(bodyX, bodyY, cellSize, cellSize);
                
                // 머리와 몸통이 겹치는지 확인
                if (players[currentPlayerIndex].getX() == players[currentPlayerIndex].getBodyX(i)
                            && players[currentPlayerIndex].getY() == players[currentPlayerIndex].getBodyY(i)) {
                        endGame(); // 게임 종료 처리
                        return;
                    }
                }

                if (grid[row][col].isEmpty()) {
                    g.setColor(Color.BLACK);
                } else if (grid[row][col].isFood()) {
                    g.setColor(Color.YELLOW);
                } else if (grid[row][col].isPoison()) {
                    g.setColor(Color.RED);
                }

                g.fillRect(x, y, cellSize, cellSize);

                if (players[currentPlayerIndex].getX() == row && players[currentPlayerIndex].getY() == col) {
                    g.setColor(Color.WHITE);
                    if (players[currentPlayerIndex].isHead) {
                        g.fillOval(x, y, cellSize, cellSize);
                    } else {
                        g.fillRect(x, y, cellSize, cellSize);
                    }
                }
            }
        }
    }


    private void endGame() {
        gameTimer.stop();
        startButton.setEnabled(true);
        score = 0;
        scoreLabel.setText("Score: 0");
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}