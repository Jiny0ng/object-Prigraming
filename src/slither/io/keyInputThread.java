package slither.io;


public class keyInputThread implements Runnable {
    private Controller controller;
    
    public keyInputThread(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10); // 적절한 대기 시간 설정
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Controller의 키 입력 처리를 수행
            int keyResult = controller.handleKeyPress(controller.getLastKeyCode());
            // 키 입력 결과에 따른 동작 수행
            switch (keyResult) {
                case 1:
                    // UP 키 입력에 대한 동작 수행
                    break;
                case 2:
                    // DOWN 키 입력에 대한 동작 수행
                    break;
                case 3:
                    // LEFT 키 입력에 대한 동작 수행
                    break;
                case 4:
                    // RIGHT 키 입력에 대한 동작 수행
                    break;
                default:
                    // 기본 동작 수행
                    break;
            }
        }
    }
}