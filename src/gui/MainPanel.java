package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utility.ImageLoader;

import java.awt.*;
import java.awt.image.*;

class MainPanel extends JPanel {
    private BufferedImage bg;
    private final String bg_path = "src/assets/main_menu_bg.png";
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
        bg = ImageLoader.loadImage(bg_path);
    }
    @Override
    public void paint(Graphics g){         
        
        super.paint(g);       
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(bg, 0 , 0, this);  
        super.paintComponents(g);

        
    }
}
