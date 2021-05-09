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
    private static QuizModel quizModel;
    private static MenuPanel menuPanel;
    private static StageSelectPanel stageSelectPanel;
    private static QuestionPanel questionPanel;
    private static HelpPanel helpPanel;
    private static int state;
    private static int musicVolume;
    private static int soundVolume;
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

        musicVolume = 50;
        soundVolume = 50;
        
    }
    private void initComponents(){
        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(800,500));
        cardLayout = new CardLayout();
        gamePanel.setLayout(cardLayout);

        menuPanel = new MenuPanel();
        helpPanel = new HelpPanel();
        gamePanel.add(menuPanel, "A");
        gamePanel.add(helpPanel, "D");

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
    static void initializeGame(){
        quizModel = new QuizModel();          
        if(stageSelectPanel != null){        
            gamePanel.remove(stageSelectPanel);  
        }
        stageSelectPanel = new StageSelectPanel();    
        gamePanel.add(stageSelectPanel, "B");
    }
    static void exitGame(){
        quizFrame.dispose();
        GameSelectGUI.showScreen();
    }
    static String getTopic(int index){
        return quizModel.getTopic(index);
    }
    static String getQuestion(){
        return quizModel.getQuestion();
    }
    static char getAnswer(){
        return quizModel.getAnswer();
    }
    static void setSelectedQuestion(int index) {
        quizModel.setSelectedQuestion(index);
        if(questionPanel != null){        
            gamePanel.remove(questionPanel);  
        }
        questionPanel = new QuestionPanel();
        gamePanel.add(questionPanel, "C");
        cardLayout.show(gamePanel, "C");
    }
    static boolean isOpened(int index){
        return quizModel.isOpened(index);
    }
    static String getChoice(int index){
        return quizModel.getChoice(index);
    }
    static void setMusicVolume(int volume){
       QuizGame.musicVolume = volume;
    }
    static void setSoundVolume(int volume){
        QuizGame.soundVolume = volume;
    }
    static int getMusicVolume(){
        return musicVolume;
    }
    static int getSoundVolume(){
        return soundVolume;
    }
    static int getPoints(){
        return quizModel.getPoints();
    }
    static void setPoints(int points){
        quizModel.setPoints(points);
    }

    //for stage select
    static String getDifficulty(int i){
        if(i < 3){
            return"EASY";
        }
        else if(i < 6){
            return "MEDIUM";
        }
        else{
            return "HARD";
        }
    }

    //for questionPanel
    static String getSelectedDifficulty(){
        int i = quizModel.getSelectedQuestion();
        if(i < 3){
            return"EASY";
        }
        else if(i < 6){
            return "MEDIUM";
        }
        else{
            return "HARD";
        }
    }
    static boolean isDone(){
        return quizModel.done();
    }
}
