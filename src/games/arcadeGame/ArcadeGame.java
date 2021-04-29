package games.arcadeGame;

public class ArcadeGame implements Runnable{

    @Override
    public void run() {
        while(true){
            System.out.println("arcade");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
