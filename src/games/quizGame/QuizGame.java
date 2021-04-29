package games.quizGame;

import javax.swing.JFrame;

public class QuizGame implements Runnable{
    private JFrame quizFrame;
    private boolean running;
    public QuizGame(){
        running = true;
        quizFrame = new JFrame("Are you smarter than a college junior?");
    }
    @Override
    public void run() {
        while(running){
            //System.out.println("quiz");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
