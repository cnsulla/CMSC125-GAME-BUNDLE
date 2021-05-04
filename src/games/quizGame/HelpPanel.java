package games.quizGame;

import java.awt.*;
import java.awt.image.BufferedImage;

import utility.BoundsHandler;
import utility.ImageLoader;
class HelpPanel extends QuizSubpanel{
    private BufferedImage helpImage;
    private Font font;
    private String helpImgPath = "./src/assets/quiz_help.png";
    private static final int    BUTTON_WIDTH = 200,
                                BUTTON_HEIGHT = 50;
    HelpPanel(){
        super();
        boundsHandler = new BoundsHandler();
        setButtonRectangles();

        font = new Font("Lucida Sans Console", Font.BOLD,25);
        
        helpImage = ImageLoader.loadImage(helpImgPath);
        drawButtons(-1); // no highlights
    }

    @Override
    public void paint(Graphics g){
        super.paint(g); 
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(helpImage, 0 , 0, this);
    }
    @Override
    protected void setButtonRectangles() {
        boundsHandler.addRectangle(FRAME_WIDTH-BUTTON_WIDTH, 0,BUTTON_WIDTH, BUTTON_HEIGHT);     
    }
    private void drawButtons(int index){
        Graphics2D g2d = (Graphics2D) helpImage.getGraphics();
        g2d.drawImage(helpImage,0,0,null);
        g2d.setFont(font);
        if(index == 0){
            g2d.setColor(Color.RED);
        }
        else{
            g2d.setColor(Color.BLACK);
        }
        FontMetrics metrics = helpImage.getGraphics().getFontMetrics(font);
        int posX = FRAME_WIDTH - BUTTON_WIDTH/2  - metrics.stringWidth("Back")/2; 
        int posY = metrics.getHeight();
        g2d.drawString("Back", posX, posY);
        repaint();
    }   

    @Override
    protected void handleHover(int mouseX, int mouseY) {   
        int index = boundsHandler.getRectangleIndex(mouseX, mouseY);
        drawButtons(index);
    }

    @Override
    protected void handleClick(int mouseX, int mouseY) { 
        int index = boundsHandler.getRectangleIndex(mouseX, mouseY);
        if(index == 0){
            QuizGame.setState(1);
        }
    }
}
