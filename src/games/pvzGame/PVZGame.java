package games.pvzGame;

import games.AbstractGame;

public class PVZGame extends AbstractGame{

    @Override
    public void run() {
        while(super.isRunning()){
            System.out.println("pvz");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
