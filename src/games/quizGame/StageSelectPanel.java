package games.quizGame;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import utility.ImageLoader;
class StageSelectPanel extends QuizSubpanel{    
    private BufferedImage stageSelectImage;
    private Font font;
    private final int   BUTTON_HEIGHT = 70,
                        BUTTON_WIDTH = 350,
                        BUTTON_GAP = 10,
                        QUIT_HEIGHT = 50,
                        QUIT_WIDTH = 200,
                        TOP_MARGIN = 75;
    StageSelectPanel(){
        super();
        //event handling
        setButtonRectangles();
        stageSelectImage = ImageLoader.loadImage(QUIZ_BG_PATH);
        font = new Font("Lucida Sans Console", Font.BOLD, 20);
        drawButtons(-1);
    }
    @Override 
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(stageSelectImage, 0, 0, this);

    }
    private void drawButtons(int index){
        Graphics2D g2d = (Graphics2D) stageSelectImage.getGraphics();
        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        FontMetrics metrics = stageSelectImage.getGraphics().getFontMetrics(font);
        int i;
        int posX, posY;
        for(i = 0; i < 10; i++){
            String topic = QuizGame.getTopic(i);
            String difficulty = QuizGame.getDifficulty(i);
            String buttonText = difficulty + ": "+topic; 
            int textWidth = metrics.stringWidth(buttonText);
            int textHeight = metrics.getHeight();
            
            
            if(!isActive(i)){
                g2d.setColor(Color.GRAY);
            }
            else if(index == i){
                g2d.setColor(Color.RED);
            }
            else{
                g2d.setColor(Color.WHITE);
            }
            //left side
            if(i%2 == 0){
                posX = FRAME_WIDTH/4 - textWidth/2;
                posY = TOP_MARGIN + (i/2) * (BUTTON_HEIGHT+BUTTON_GAP) + BUTTON_HEIGHT/2 + textHeight/2;
                g2d.drawString(buttonText, posX, posY);
            }
            //rightside
            else{
                posX = FRAME_WIDTH*3/4 - textWidth/2;
                posY = TOP_MARGIN + (i/2) * (BUTTON_HEIGHT+BUTTON_GAP) + BUTTON_HEIGHT/2 + textHeight/2;
                g2d.drawString(buttonText, posX, posY);
            }
        }
        //quit button
        if(index == i){
            g2d.setColor(Color.RED);
        }
        else{
            g2d.setColor(Color.WHITE);
        }
        g2d.setFont(new Font("Lucida Sans Console", Font.BOLD, 20));
        posX = FRAME_WIDTH - QUIT_WIDTH/2 - metrics.stringWidth("Quit")/2;
        posY = QUIT_HEIGHT/2 + metrics.getHeight()/2;
        g2d.drawString("QUIT", posX, posY);
        repaint();
    }
    @Override
    protected void setButtonRectangles() {
        for(int i = 0; i < 5; i++){
            //left button
            int posX = FRAME_WIDTH/4 - BUTTON_WIDTH/2;
            int posY = TOP_MARGIN + i * (BUTTON_HEIGHT+BUTTON_GAP);
            boundsHandler.addRectangle(posX, posY, BUTTON_WIDTH, BUTTON_HEIGHT);

            //right button
            posX = FRAME_WIDTH * 3/4 - BUTTON_WIDTH/2;
            boundsHandler.addRectangle(posX, posY, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        //add quit button @ index 10
        boundsHandler.addRectangle(FRAME_WIDTH - QUIT_WIDTH, 0, QUIT_WIDTH, QUIT_HEIGHT);
    }

    @Override
    protected void handleHover(int mouseX, int mouseY) {
        int index = boundsHandler.getRectangleIndex(mouseX, mouseY);
        if(index >=  0 && index < 10 && !isActive(index)){
            return;
        }
        else{
            drawButtons(index);        
        }
        
    }

    @Override
    protected void handleClick(int mouseX, int mouseY) {
        int index = boundsHandler.getRectangleIndex(mouseX, mouseY);
        if(index >= 0 && index < 10){
            if(isActive(index)){
                QuizGame.setSelectedQuestion(index);
            }
            
        }
        else if(index == 10){
            int selection = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit? Your current game will be lost.","CONFIRM EXIT", JOptionPane.CANCEL_OPTION);
            if(selection == JOptionPane.OK_OPTION){
                QuizGame.setState(1);
            }
        }
    }
    private boolean isActive(int index){
        return !QuizGame.isOpened(index);
    }
    
}
