package games.pvzGame;

public class PVZGame implements Runnable{

    @Override
    public void run() {
        while(true){
            System.out.println("pvz");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
