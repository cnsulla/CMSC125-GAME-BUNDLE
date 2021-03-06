package games.quizGame;

import javax.swing.JPanel;
import java.awt.event.*;
import utility.*;
import java.awt.*;

abstract class QuizSubpanel extends JPanel implements MouseMotionListener, MouseListener{
    //image locations
    protected static final String MENU_BG_PATH = "./src/assets/quiz_menu_bg.png";
    protected static final String QUIZ_BG_PATH = "./src/assets/quiz_bg.png";
    protected static final String HELP_IMG_PATH = "./src/assets/quiz_help.png";
    protected static final String QUESTION_IMG_PATH = "./src/assets/quiz_question.png";
    
    protected BoundsHandler boundsHandler;
    protected static final int      FRAME_WIDTH = 800, 
                                    FRAME_HEIGHT = 500;
    
    QuizSubpanel(){
        setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
        boundsHandler = new BoundsHandler();
        addMouseListener(this);
        addMouseMotionListener(this);
    }  
    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();        

        handleHover(mouseX, mouseY);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();        

        handleClick(mouseX, mouseY);
    }
    abstract protected void setButtonRectangles();
    abstract protected void handleHover(int mouseX, int mouseY);
    abstract protected void handleClick(int mouseX, int mouseY);
    

    //does nothing
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
    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
}
