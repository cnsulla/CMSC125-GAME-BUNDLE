package games;

public abstract class AbstractGame implements Runnable{
    private boolean running;    
    protected boolean isRunning(){
        return running;
    }
    public void start(){
        running = true;
    }
    public void stop(){
        running = false;
    }
    
}
