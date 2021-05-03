package games.quizGame;

import java.awt.Dimension;

import javax.swing.JPanel;

import utility.BoundsHandler;
import utility.ImageLoader;

import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;

public class MenuPanel extends JPanel implements MouseMotionListener, MouseListener{
    private BufferedImage menuImage;
    private BufferedImage bg;
    private Font font;
    private String bg_path = "./src/assets/quiz_menu_bg.png";
    private BoundsHandler boundsHandler;
    private static final int    FRAME_WIDTH = 800, 
                                FRAME_HEIGHT = 500,
                                FRAME_TOP_GAP = 250,
                                BUTTON_WIDTH = 200,
                                BUTTON_HEIGHT = 50,
                                BUTTON_GAP = 5;
    MenuPanel(){
        setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
        
        //event handling
        boundsHandler = new BoundsHandler();
        setButtonRectangles();

        //init images;
        font = new Font("Lucida Sans Console", Font.BOLD,25);
        bg = ImageLoader.loadImage(bg_path);
        menuImage = new BufferedImage(FRAME_WIDTH,FRAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        initMenuImage();

        
        addMouseMotionListener(this);
    }
    private void setButtonRectangles(){               
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
        g2d.drawImage(bg, 0, 0, null);
        g2d.setFont(font);
        FontMetrics metrics = menuImage.getGraphics().getFontMetrics(font);
        int posX = 0, posY = 0;

        //if highlighted, set to red
        g2d.setColor(Color.BLACK);
        if(highlightedIndex == 0){
            g2d.setColor(Color.RED);
        }
        //center string before drawing
        posX = FRAME_WIDTH/2 - metrics.stringWidth("START GAME")/2;
        posY = FRAME_TOP_GAP +metrics.getHeight();
        g2d.drawString("START GAME", posX, posY);
        
        //if highlighted, set to red
        g2d.setColor(Color.BLACK);
        if(highlightedIndex == 1){
            g2d.setColor(Color.RED);
        }            
        //center string before drawing
        posX = FRAME_WIDTH/2 - metrics.stringWidth("HOW TO PLAY")/2;
        posY += BUTTON_HEIGHT + BUTTON_GAP;
        g2d.drawString("HOW TO PLAY", posX, posY);

        //if highlighted, set to red
        g2d.setColor(Color.BLACK);
        if(highlightedIndex == 2){
            g2d.setColor(Color.RED);
        }        
        //center string before drawing
        posX = FRAME_WIDTH/2 - metrics.stringWidth("QUIT")/2;
        posY += BUTTON_HEIGHT + BUTTON_GAP;
        g2d.drawString("QUIT", posX, posY);
        repaint();
    }
    private void initMenuImage(){
        Graphics2D graphics = (Graphics2D) menuImage.getGraphics();
        graphics.setFont(font);
        graphics.drawImage(bg, 0, 0, null);
        drawButtons(-1);

    }
    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();        

        handleHover(mouseX, mouseY);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    //make text glow
    private void handleHover(int mouseX, int mouseY){
        int index = boundsHandler.getRectangleIndex(mouseX, mouseY);
        drawButtons(index);
    }
    //process input
    private void handleClick(){

    }
}
