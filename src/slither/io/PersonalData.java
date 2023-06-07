package slither.io;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PersonalData extends JPanel{
	
	private String playerName; 		//이름 변수 
    private int gameDifficulty;		//난이도 변수 
    
    private JPanel personalPanel;
    private JLabel nameLabel;			
    private JTextField nameField;
    private JLabel difficultyLabel;
    private JTextField difficultyField;	
    private JButton jb;
    private boolean inputCompleted = false; 
    
	public PersonalData() {
		setLayout(null);
        setBounds(350,300,300,130);
        setBackground(Color.ORANGE);
		
	    //이름(Label)과 텍스트 필드 추가 
	    nameLabel = new JLabel("이름 ");
	    nameField = new JTextField();
	    nameLabel.setBounds(20,20,50,20);
	    nameField.setBounds(80,20,150,20);
	    
	    //난이도(Label)과 텍스트 필드 추가 
	    difficultyLabel = new JLabel("게임 난이도(1~3) ");
	    difficultyField = new JTextField();
	    difficultyLabel.setBounds(20,50,100,20);
	    difficultyField.setBounds(130,50,100,20);
	    
	    //이름 리스너 
	    nameField.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) { 
	    		JTextField t = (JTextField)e.getSource(); 
	    		playerName = t.getText();
	    	}
	    }); 
	    
	    //난이도 리스너 
	    difficultyField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		JTextField t = (JTextField)e.getSource(); 
        		gameDifficulty = Integer.parseInt(t.getText());
        	}
        }); 
	    
	    //"OK"버튼 (입력완료시) 
	    jb = new JButton("OK");
	    jb.setBounds(120,80,60,30);
	    jb.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            try {
	                int difficulty = Integer.parseInt(difficultyField.getText());
	                if (difficulty < 1 || difficulty > 3) {
	                    throw new IllegalArgumentException("1, 2, 3 중에서 선택하시오.");
	                }
	                setVisible(false);
	                inputCompleted = true;
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null, "숫자를 입력해주세요.");
	            } catch (IllegalArgumentException ex) {
	                    JOptionPane.showMessageDialog(null, ex.getMessage());
	            }
	                difficultyField.setText("");
	                difficultyField.requestFocus();
	        }
	    });
	    
	    add(nameLabel);
        add(nameField);
        add(difficultyLabel);
        add(difficultyField);
        add(jb);
	}
	
    public String getName() { return playerName; }
    public int getDifficulty() { return gameDifficulty; }
    
    public int  level() {
    	JFrame jf = new JFrame("PersonalPanel");
    	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(null);
        jf.setSize(900, 900);
        
        PersonalData personaldata = new PersonalData();
        jf.add(personaldata);

        jf.setVisible(true);
        
        synchronized (personaldata) {
            try {
                personaldata.wait(); // 입력이 완료될 때까지 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        jf.dispose(); 
        
        return personaldata.getDifficulty();
    }
}