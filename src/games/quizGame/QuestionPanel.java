package games.quizGame;

import java.awt.Graphics;
import java.awt.Graphics2D;

import utility.ImageLoader;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

public class QuestionPanel extends QuizSubpanel{
    private BufferedImage baseImage;
    private BufferedImage questionImage;
    private Font questionFont;
    private Font choicesFont;
    private Font pointsFont;
    private SettingsDialog settingsDialog;
    private FinalQuestionDialog finalQuestionDialog;
    private static final int    CHOICE_WIDTH = 750,
                                CHOICE_HEIGHT = 40,
                                CHOICE_TOP_MARGIN = FRAME_HEIGHT - (CHOICE_HEIGHT*4 + 20),//space from the top
                                QUESTION_TOP_MARGIN = 100,
                                QUESTION_WIDTH = 500,
                                POINTS_TOP_MARGIN = QUESTION_TOP_MARGIN-20,
                                SETTINGS_WIDTH = 75,
                                SETTINGS_HEIGHT = 75;
    QuestionPanel(){
        super();
        setButtonRectangles();
        baseImage = ImageLoader.loadImage(QUESTION_IMG_PATH);
        questionImage = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics g = questionImage.getGraphics();
        g.drawImage(baseImage, 0,0, null);

        questionFont = new Font("Lucida Sans Console", Font.BOLD, 18);
        choicesFont = new Font("Lucida Sans Console", Font.BOLD, 15);
        pointsFont = new Font("Lucida Sans Console", Font.BOLD, 15);

        drawInfo(-1);//index of highlighted choice
        //System.out.println(QuizGame.getAnswer());
    }
    @Override
    public void paint(Graphics g){
        super.paint(g); 
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(questionImage, 0 , 0, this);
    }
    @Override
    protected void setButtonRectangles() {
        //4 choices + upper right settings 
        int posX, posY;
        for(int i = 0; i < 4; i++){
            posX = (FRAME_WIDTH - CHOICE_WIDTH)/2;
            posY = CHOICE_TOP_MARGIN + i*CHOICE_HEIGHT;
            boundsHandler.addRectangle(posX, posY, CHOICE_WIDTH, CHOICE_HEIGHT);
        }

        posX = FRAME_WIDTH - SETTINGS_WIDTH;
        posY = 0;
        boundsHandler.addRectangle(posX, posY, SETTINGS_WIDTH, SETTINGS_HEIGHT);
    }

    @Override
    protected void handleHover(int mouseX, int mouseY) {
        int index = boundsHandler.getRectangleIndex(mouseX, mouseY);
        drawInfo(index);
        
    }

    @Override
    protected void handleClick(int mouseX, int mouseY) {
        int index = boundsHandler.getRectangleIndex(mouseX, mouseY);
        char answer = QuizGame.getAnswer();
        int lowerCase = answer - 97;
        int upperCase = answer - 65;
        if(index >= 0 && index < 4){
            if(index == lowerCase || index == upperCase){
                JOptionPane.showMessageDialog(this, "Good Job!", "CORRECT",JOptionPane.INFORMATION_MESSAGE);
                if(QuizGame.getSelectedDifficulty().equals("EASY")){
                    QuizGame.setPoints(QuizGame.getPoints() + 1000);
                }
                else if(QuizGame.getSelectedDifficulty().equals("MEDIUM")){
                    QuizGame.setPoints(QuizGame.getPoints() + 2000);
                }
                else if(QuizGame.getSelectedDifficulty().equals("HARD")){
                    QuizGame.setPoints(QuizGame.getPoints() + 5000);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Sorry, the correct answer is " + QuizGame.getAnswer(), "WRONG",JOptionPane.ERROR_MESSAGE);
            }

            if(!QuizGame.isDone()){
                QuizGame.setState(2);
            }
            else{
                showFinalQuestion();
            }
        }
        else if(index == 4){
            showSettingsDialog();
        }
    }
    private void showFinalQuestion(){
        finalQuestionDialog = new FinalQuestionDialog(getParentFrame(), "BONUS", true );
        finalQuestionDialog.setVisible(true);
    }
    private void drawInfo(int index){
        Graphics2D g2d = (Graphics2D) questionImage.getGraphics();
        g2d.drawImage(baseImage,0,0,null);
        drawPoints(g2d);
        drawQuestion(g2d);
        drawDifficulty(g2d);
        drawChoices(index, g2d);
        repaint();
    }
    private void drawPoints(Graphics2D g2d){
        g2d.setFont(pointsFont);
        g2d.setColor(Color.WHITE);
        FontMetrics metrics = questionImage.getGraphics().getFontMetrics(choicesFont);
        int posX = 25;
        int posY = POINTS_TOP_MARGIN +  metrics.getHeight(); 
        String pointsText = "POINTS: "+QuizGame.getPoints();
        g2d.drawString(pointsText, posX , posY);
    }
    private void drawQuestion(Graphics2D g2d){
        g2d.setFont(questionFont);
        int posX, posY;
        FontMetrics metrics = questionImage.getGraphics().getFontMetrics(questionFont);
        posX = (FRAME_WIDTH - metrics.stringWidth("QUESTION:"))/2;
        posY = QUESTION_TOP_MARGIN + metrics.getHeight();
        g2d.drawString("QUESTION:",posX, posY);

        String question = QuizGame.getQuestion();
        String[] tokenized = question.split(" "); 
        String currLine = "";
        int lineCount = 1;
        for(int i = 0; i < tokenized.length; i++){
            if(metrics.stringWidth(currLine + " "+tokenized[i]) < QUESTION_WIDTH){
                currLine = currLine + " "+ tokenized[i];
            }
            else{
                posX = (FRAME_WIDTH - metrics.stringWidth(currLine))/2;
                posY = (QUESTION_TOP_MARGIN + (lineCount+1)*metrics.getHeight());
                g2d.drawString(currLine, posX, posY);
                lineCount++;
                currLine = tokenized[i];
            }
        }
        posX = (FRAME_WIDTH - metrics.stringWidth(currLine))/2;
        posY = (QUESTION_TOP_MARGIN + (lineCount+1)*metrics.getHeight());
        g2d.drawString(currLine, posX, posY);
        //System.out.println(question);

    }
    private void drawDifficulty(Graphics2D g2d){
        g2d.setFont(pointsFont);
        g2d.setColor(Color.WHITE);
        int posX = 25;
        int posY = CHOICE_TOP_MARGIN;
        String difficulty ="DIFFICULTY: " + QuizGame.getSelectedDifficulty();
        g2d.drawString(difficulty, posX, posY);
    }
    private void drawChoices(int index, Graphics2D g2d){ 
        g2d.setFont(choicesFont);
        Color highlight = new Color(100,100,100, 120);
        FontMetrics metrics = questionImage.getGraphics().getFontMetrics(choicesFont);
        

        for(int i = 0; i < 4; i++){            
            String currChoice = QuizGame.getChoice(i);
            int textWidth = metrics.stringWidth(currChoice);            
            int textHeight = metrics.getHeight();
            
            int textX = (FRAME_WIDTH - textWidth)/2;
            int textY = CHOICE_TOP_MARGIN + i*CHOICE_HEIGHT + CHOICE_HEIGHT/2  + textHeight/2;
            if(i != index){
                g2d.setColor(Color.WHITE);                
            }   
            else{
                g2d.setColor(highlight);                
                int posX = (FRAME_WIDTH - CHOICE_WIDTH)/2;
                int posY = CHOICE_TOP_MARGIN + i*CHOICE_HEIGHT;
                g2d.fillRect(posX, posY, CHOICE_WIDTH, CHOICE_HEIGHT);

                g2d.setColor(Color.RED);
            }
            g2d.drawString(currChoice, textX, textY);
            g2d.setColor(Color.WHITE);
        }
    }
    private void showSettingsDialog(){
        settingsDialog = new SettingsDialog(getParentFrame(), "Settings",true);
        settingsDialog.setVisible(true);
    }
    private JFrame getParentFrame(){
        return (JFrame)SwingUtilities.getWindowAncestor(this);
    }
    private class SettingsDialog extends JDialog{
        private JPanel containerPanel;
        SettingsDialog(JFrame parent, String title, boolean modal){
            super(parent,title, true);
            initComponents();
            pack();
            setLocationRelativeTo(null);
        }
        private void initComponents(){
            containerPanel = new JPanel();
            //containerPanel.setPreferredSize(new Dimension(200,200));
            containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.PAGE_AXIS));
            JButton quit = new JButton("Quit to Main Menu");
            quit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt){      
                    int selection = JOptionPane.showConfirmDialog(containerPanel, "Are you sure you want to quit? Your current game will be lost.","CONFIRM EXIT", JOptionPane.CANCEL_OPTION);
                    if(selection == JOptionPane.OK_OPTION){              
                        SettingsDialog.this.dispose();
                        QuizGame.setState(1);
                    }
                }
            });
            quit.setAlignmentX(Component.CENTER_ALIGNMENT);
            JSlider musicControl = new JSlider(0,100);
            musicControl.setValue(QuizGame.getMusicVolume());
            musicControl.addChangeListener(new ChangeListener(){
                @Override
                public void stateChanged(ChangeEvent e) {
                    QuizGame.setMusicVolume(musicControl.getValue());
                }
            });
            
            musicControl.setAlignmentX(Component.CENTER_ALIGNMENT);
            musicControl.setBorder(new EmptyBorder(0, 0, 10,0));

            JLabel musicLabel = new JLabel("Volume");
            musicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JSlider soundControl = new JSlider(0,100);
            soundControl.setValue(QuizGame.getSoundVolume());
            soundControl.addChangeListener(new ChangeListener(){
                @Override
                public void stateChanged(ChangeEvent e) {
                    QuizGame.setSoundVolume(soundControl.getValue());
                }
            });
            
            soundControl.setBorder(new EmptyBorder(0, 0, 10,0));

            JLabel soundLabel = new JLabel("Sound");
            soundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            
            containerPanel.add(soundLabel);
            containerPanel.add(soundControl);
            containerPanel.add(musicLabel);
            containerPanel.add(musicControl);
            containerPanel.add(quit);
            add(containerPanel);
        }
    }
    private class FinalQuestionDialog extends JDialog{
        private JPanel mainPanel;
        private CardLayout cardLayout;
        private JPanel finalQuestionPanel;
        private JPanel evaluationPanel;
        private final String answer = "OS";
        FinalQuestionDialog(JFrame parent, String title, boolean modal){
            super(parent,title, true);
            initComponents();
            setUndecorated(true);
            pack();
            setLocationRelativeTo(null);
        }
        private void initComponents(){
            mainPanel = new JPanel();
            cardLayout = new CardLayout();
            mainPanel.setLayout(cardLayout);

            finalQuestionPanel = new JPanel();            
            
            finalQuestionPanel.setPreferredSize(new Dimension(500,200));
            finalQuestionPanel.setLayout(new GridLayout(4,0));

            evaluationPanel = new JPanel();
            evaluationPanel.setPreferredSize(new Dimension(800,500));
            
            JLabel instructions = new JLabel("Answer one final question for extra credit!\n\n");
            instructions.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel question = new JLabel("Tell me what?");
            question.setAlignmentX(Component.LEFT_ALIGNMENT);

            JTextField input = new JTextField();
    

            JButton enter = new JButton("SUBMIT");
            enter.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(input.getText().equals(answer)){
                        QuizGame.setPoints(QuizGame.getPoints()+10000);
                    }
                    //display evaluation                    
                    drawEvaluation();
                    cardLayout.show(mainPanel, "Evaluation");
                    revalidate();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    FinalQuestionDialog.this.dispose();

                    //back to main menu
                    QuizGame.setState(1);
                }
            });

            finalQuestionPanel.add(instructions);
            finalQuestionPanel.add(question);
            finalQuestionPanel.add(input);
            finalQuestionPanel.add(enter);

            mainPanel.add(finalQuestionPanel, "Final Question");
            mainPanel.add(evaluationPanel, "Evaluation");

            add(mainPanel);
        }
        private void drawEvaluation(){
            Graphics2D g2d = (Graphics2D)evaluationPanel.getGraphics();
            BufferedImage evalImage;
            if(QuizGame.getPoints() == 39000){
                evalImage = ImageLoader.loadImage("./src/assets/quiz_eval1.png");
            }
            else if(QuizGame.getPoints() >= 15000){
                evalImage = ImageLoader.loadImage("./src/assets/quiz_eval2.png");
            }
            else{
                evalImage = ImageLoader.loadImage("./src/assets/quiz_eval3.png");
            }
            g2d.drawImage(evalImage, 0 , 0, null);
            evaluationPanel.repaint();
        }
    }
    
}
