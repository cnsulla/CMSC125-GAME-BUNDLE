package gui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

class MainPanel extends JPanel {
    private BufferedImage bg;
    MainPanel(){
        GridLayout centerLayout = new GridLayout(0,3);
        centerLayout.setHgap(10);
        setLayout(centerLayout);
        setPreferredSize(new Dimension(800,500));
        int topMargin = 150;
        int bottomMargin = 75;
        int leftMargin = 75;
        int rightMargin = 75; 
        setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));
        try {
            bg = ImageIO.read(new File("src/assets/main_menu_bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
    @Override
    public void paint(Graphics g){         
        
        super.paint(g);       
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(bg, 0 , 0, this);  
        super.paintComponents(g);

        
    }
}
