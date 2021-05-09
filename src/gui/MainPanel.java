package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import utility.BoundsHandler;
import utility.ImageLoader;

import java.awt.*;
import java.awt.image.*;

class MainPanel extends JPanel implements MouseMotionListener, MouseListener{
    private BufferedImage bg;
    private BoundsHandler boundsHandler;
    private final String bg_path = "src/assets/main_menu_bg.png";
    private final int   PANEL_WIDTH = 800,
                        PANEL_HEIGHT = 500,
                        BUTTON_WIDTH = 100,
                        BUTTON_HEIGHT = 30;
    MainPanel(){
        GridLayout centerLayout = new GridLayout(0,3);
        centerLayout.setHgap(10);
        setLayout(centerLayout);
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        int topMargin = 150;
        int bottomMargin = 75;
        int leftMargin = 75;
        int rightMargin = 75; 
        setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));


        bg = ImageLoader.loadImage(bg_path);
        boundsHandler = new BoundsHandler();
        setButtonRectangles();
        drawButtons(-1);
        
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private void setButtonRectangles(){
        //about
        boundsHandler.addRectangle(PANEL_WIDTH-BUTTON_WIDTH, PANEL_HEIGHT - 2*BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        //exit
        boundsHandler.addRectangle(PANEL_WIDTH-BUTTON_WIDTH, PANEL_HEIGHT - BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
    }
    //draw about and exit buttons

    @Override
    public void paint(Graphics g){         
        
        super.paint(g);       
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(bg, 0 , 0, this);  
        super.paintComponents(g);        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int posX = e.getX();
        int posY = e.getY();
        handleClick(posX, posY);
        
    }
    
    private void handleClick(int posX, int posY) {
        int index = boundsHandler.getRectangleIndex(posX, posY);
        if(index == 0){
            //show about dialog;
            JOptionPane.showMessageDialog(this, "Glendel B. Calvo\n\nAdrian Lorenzo U. Mabansag\n\nMichael Joshua C. Orais\n\nCris Niño N. Sulla", "ABOUT US", JOptionPane.PLAIN_MESSAGE);
        }
        else if (index == 1){
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int posX = e.getX();
        int posY = e.getY();
        handleHover(posX, posY);
        
    }    
    private void handleHover(int posX, int posY) {
        int index = boundsHandler.getRectangleIndex(posX, posY);
        drawButtons(index);
    }
    private void drawButtons(int index){
        Graphics2D g2d = (Graphics2D) bg.getGraphics();
        g2d.drawImage(bg,0,0,null);
        Font font = new Font("Lucida Sans Console", Font.PLAIN, 20);
        g2d.setFont(font);
        FontMetrics metrics = bg.getGraphics().getFontMetrics(font);

        g2d.setColor(Color.WHITE);
        if(index == 0){
            g2d.setColor(Color.RED);
        }
        int posX = PANEL_WIDTH - BUTTON_WIDTH/2  - metrics.stringWidth("ABOUT")/2; 
        int posY = PANEL_HEIGHT - BUTTON_HEIGHT;
        g2d.drawString("ABOUT", posX, posY);

        g2d.setColor(Color.WHITE);
        if(index == 1){
            g2d.setColor(Color.RED);
        }
        posX = PANEL_WIDTH - BUTTON_WIDTH/2  - metrics.stringWidth("EXIT")/2; 
        posY = PANEL_HEIGHT;
        g2d.drawString("EXIT", posX, posY);
        repaint();
    }


    //does nothing
    //
    //
    //
    //
    //
    //

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
