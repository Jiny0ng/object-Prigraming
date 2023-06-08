package slither.io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Controller extends JPanel { 
    
	int LastKeyCode;
	public Controller() {
	
        // 키 이벤트 리스너 등록
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
        });
        
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    	LastKeyCode = e.getKeyCode();
                		handleKeyPress(e.getKeyCode());
                }
                return false;
            }
        });
        
        
        setFocusable(true);
        requestFocus();
	}
	
	public int getLastKeyCode() {
		return LastKeyCode;
	}
	
	
	// 키 입력에 따른 플레이어 방향 설정
	public int handleKeyPress(int keyCode) {
    	int result;
        switch (keyCode) {
        	
            case KeyEvent.VK_UP:
                result = 1;
                break;
            case KeyEvent.VK_DOWN:
                result = 2;
                break;
            case KeyEvent.VK_LEFT:
                result = 3;
                break;
            case KeyEvent.VK_RIGHT:
                result =4;
                break;
            default:
            	result = 5;
        }
        return result;
	}
}