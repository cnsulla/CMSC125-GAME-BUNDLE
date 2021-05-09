package games.quizGame;
import utility.ImageLoader;
import java.awt.image.*;
import java.awt.*;

class MenuPanel extends QuizSubpanel{
    private BufferedImage menuImage;
    private Font font;
    private static final int    FRAME_TOP_GAP = 250,
                                BUTTON_WIDTH = 200,
                                BUTTON_HEIGHT = 50,
                                BUTTON_GAP = 5;
    MenuPanel(){
        super();
        
        //event handling
        setButtonRectangles();

        //init images;
        font = new Font("Lucida Sans Console", Font.BOLD,25);
        menuImage = ImageLoader.loadImage(MENU_BG_PATH);
        //menuImage = new BufferedImage(FRAME_WIDTH,FRAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        drawButtons(-1);
    }
    protected void setButtonRectangles(){     
        for(int i = 0; i < 3; i++){
            int posX = (FRAME_WIDTH - BUTTON_WIDTH)/2;
            int posY = FRAME_TOP_GAP + (BUTTON_HEIGHT + BUTTON_GAP)*i;
            boundsHandler.addRectangle(posX, posY, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
    }
    @Override
    public void paint(Graphics g){
        super.paint(g); 
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(menuImage, 0 , 0, this);  
    }
    private void drawButtons(int highlightedIndex){
        Graphics2D g2d = (Graphics2D) menuImage.getGraphics();
        g2d.setFont(font);
        FontMetrics metrics = menuImage.getGraphics().getFontMetrics(font);
        int posX = 0, posY = 0;

        //if highlighted, set to red
        g2d.setColor(Color.WHITE);
        if(highlightedIndex == 0){
            g2d.setColor(Color.RED);
        }
        //center string before drawing
        posX = FRAME_WIDTH/2 - metrics.stringWidth("START GAME")/2;
        posY = FRAME_TOP_GAP +metrics.getHeight();
        g2d.drawString("START GAME", posX, posY);
        
        //if highlighted, set to red
        g2d.setColor(Color.WHITE);
        if(highlightedIndex == 1){
            g2d.setColor(Color.RED);
        }            
        //center string before drawing
        posX = FRAME_WIDTH/2 - metrics.stringWidth("HOW TO PLAY")/2;
        posY += BUTTON_HEIGHT + BUTTON_GAP;
        g2d.drawString("HOW TO PLAY", posX, posY);

        //if highlighted, set to red
        g2d.setColor(Color.WHITE);
        if(highlightedIndex == 2){
            g2d.setColor(Color.RED);
        }        
        //center string before drawing
        posX = FRAME_WIDTH/2 - metrics.stringWidth("QUIT")/2;
        posY += BUTTON_HEIGHT + BUTTON_GAP;
        g2d.drawString("QUIT", posX, posY);
        repaint();
    }
    //make text glow
    protected void handleHover(int mouseX, int mouseY){
        int index = boundsHandler.getRectangleIndex(mouseX, mouseY);
        drawButtons(index);
    }
    //process input
    protected void handleClick(int mouseX, int mouseY){
        int index = boundsHandler.getRectangleIndex(mouseX, mouseY);
        if(index == 0){
            //start game
            QuizGame.initializeGame();
            QuizGame.setState(2);
        }
        else if(index == 1){
            //how to play
            QuizGame.setState(4);
        }
        else if(index == 2){
            QuizGame.exitGame();
        }
    }
}
