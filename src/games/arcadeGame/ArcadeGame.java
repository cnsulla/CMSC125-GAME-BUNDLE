package games.arcadeGame;

import games.AbstractGame;

public class ArcadeGame extends AbstractGame{

    @Override
    public void run() {
        while(super.isRunning()){
            System.out.println("arcade");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
