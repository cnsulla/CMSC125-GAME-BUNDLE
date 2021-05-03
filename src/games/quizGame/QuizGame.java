package games.quizGame;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.CardLayout;
import games.AbstractGame;

public class QuizGame extends AbstractGame{
    private JFrame quizFrame;
    private JPanel gamePanel;
    private CardLayout cardLayout;
    private int state;
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

        cardLayout.show(gamePanel, "Main Menu");
        /*
        int topMargin = 250;
        int bottomMargin = 50;
        int leftMargin = 250;
        int rightMargin = 250; 
        gamePanel.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));
        GridLayout layout = new GridLayout(3,0);
        layout.setVgap(10);
        gamePanel.setLayout(layout);

        JButton play = new JButton("Start Game");

        JButton howToPlay = new JButton("How To Play");        

        JButton exit = new JButton("Back to Main Menu");
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                quizFrame.dispose();
                GameSelectGUI.showScreen();
            }
        });

        gamePanel.add(play);
        gamePanel.add(howToPlay);
        gamePanel.add(exit);
        */
        quizFrame.add(gamePanel);
    }
    void setState(int state){
        this.state = state;
        if(state == 1){//mainmenu
            cardLayout.show(gamePanel, "A");
        }
        else if(state == 2){//stage select
            cardLayout.show(gamePanel, "B");
        }
        else if(state == 3){
            cardLayout.show(gamePanel, "C");
        }
    }
    @Override
    public void run() {
        while(super.isRunning()){
            //System.out.println("quiz");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
