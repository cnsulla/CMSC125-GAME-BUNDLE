package games.quizGame;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.CardLayout;
import games.AbstractGame;
import gui.GameSelectGUI;

public class QuizGame extends AbstractGame{
    private static JFrame quizFrame;
    private static JPanel gamePanel;
    private static CardLayout cardLayout;
    private static int state;
    public QuizGame(){
        quizFrame = new JFrame("Are you smarter than a college junior?");
        quizFrame.setUndecorated(true);
        quizFrame.setResizable(false);
        quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        setState(1);

        quizFrame.pack();
        quizFrame.setLocationRelativeTo(null);
        quizFrame.setVisible(true);
        
    }
    private void initComponents(){
        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(800,500));
        cardLayout = new CardLayout();
        gamePanel.setLayout(cardLayout);

        gamePanel.add(new MenuPanel(), "A");
        gamePanel.add(new StageSelectPanel(), "B");
        gamePanel.add(new QuestionPanel(), "C");
        gamePanel.add(new HelpPanel(), "D");

        cardLayout.show(gamePanel, "Main Menu");
        quizFrame.add(gamePanel);
    }
    static void setState(int state){
        QuizGame.state = state;
        if(state == 1){//mainmenu
            cardLayout.show(gamePanel, "A");
        }
        else if(state == 2){//stage select
            cardLayout.show(gamePanel, "B");
        }
        else if(state == 3){//question
            cardLayout.show(gamePanel, "C");
        }
        else if(state == 4){//how to play
            cardLayout.show(gamePanel, "D");
        }
    }
    static int getState(){
        return state;
    }
    @Override
    public void run() {
        while(running){
            //System.out.println("quiz");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static void exitGame(){
        quizFrame.dispose();
        GameSelectGUI.showScreen();
    }
    
}
