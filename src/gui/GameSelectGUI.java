package gui;

import javax.swing.JButton;
import javax.swing.JFrame;

import games.AbstractGame;
import games.arcadeGame.ArcadeGame;
import games.quizGame.QuizGame;
import games.pvzGame.PVZGame;
import java.awt.event.*;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class GameSelectGUI implements MouseListener{
    private static JFrame selectionFrame;
    private MainPanel mainPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    
    private static AbstractGame currGame;
    private Thread mainThread;
    
    GameSelectGUI(){
        if(selectionFrame != null){
            GameSelectGUI.showScreen();
            return;
        }
        selectionFrame = new JFrame("Game Catalog");
        selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectionFrame.setResizable(false);

        initComponents();

        
        selectionFrame.pack();
        selectionFrame.setLocationRelativeTo(null);
        selectionFrame.setVisible(true);
    }
    private void initComponents(){
        mainPanel = new MainPanel();
       
        button1 = new JButton("Are you smarter than a college junior?");
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                start(new QuizGame());
            }
        });
        button1.setIcon(new ImageIcon("./src/assets/game_button_1.png"));

        button2 = new JButton("PVZ variant");
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                start(new PVZGame());
            }
        });
        button2.setIcon(new ImageIcon("./src/assets/game_button_2.png"));

        button3 = new JButton("Resource Inlay");
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                start(new ArcadeGame());
            }
        });
        button3.setIcon(new ImageIcon("./src/assets/game_button_3.png"));


        button1.addMouseListener(this);
        button2.addMouseListener(this);
        button3.addMouseListener(this);
        
        mainPanel.add(button1);
        mainPanel.add(button2);
        mainPanel.add(button3);

        selectionFrame.add(mainPanel);               
    }
    private void start(AbstractGame game){        
        hideScreen();
        currGame = game;
        currGame.start();
        mainThread = new Thread(currGame);
        mainThread.start();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
       
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == button1 ){
            button1.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        }
        else if(e.getSource() == button2){
            button2.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        }
        else if(e.getSource() == button3){
            button3.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
         if(e.getSource() == button1 ){
            button1.setBorder(null);
        }
        else if(e.getSource() == button2){
            button2.setBorder(null);
        }
        else if(e.getSource() == button3){
            button3.setBorder(null);
        }
    }
    public static void showScreen(){//try to add a loading screen
        if(currGame != null){
            currGame.stop();
            currGame = null;
        }
        if(selectionFrame != null){
            //showLoading();
            selectionFrame.setVisible(true);
        }
    }
    public static void hideScreen(){
        //showLoading();
        if(selectionFrame != null){
            selectionFrame.setVisible(false);
        }
    }
}