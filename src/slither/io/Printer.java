package slither.io;

import javax.swing.*;
import java.awt.*;

public class Printer extends JFrame {
    
    public Printer() {
    	setTitle("지렁이 게임");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	Container window = getContentPane();
    	window.setLayout(null);
    
    	ImageIcon map = new ImageIcon("images/map.jpeg");
    	JLabel imageLabel = new JLabel(map);
    	imageLabel.setBounds(100,50,600,600);
    	window.add(imageLabel);//배경화을 이미지레이블로 추가
    	
    	setSize(900,900);
    	setVisible(true);
    }
    
    
    public void print(int position, int x, int y) {
    	x = (x*12)+100;
    	y = (y*12)+100;
    	for(int i = 1;i<=50;i++) {
    		for(int j = 1;j<=50;j++) {
    			
    			
    			
    		}
    	}
    	
    	
    }
   public static void main(String[] agrs) {
	   new Printer();
	}
}
