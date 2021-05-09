package games.quizGame;

import java.io.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Random;
class QuizModel {
    private Sheet sheet;
    private int[] questionIndices;
    private int selectedQuestion;
    private boolean[] opened;
    private int points;
    QuizModel(){
        loadQuestionPool();//loads sheet from excel file
        randomizeQuestions();
        selectedQuestion = -1;
        opened = new boolean[10];
        
    }
    void randomizeQuestions(){
        questionIndices = new int[10];
        Random rand = new Random();
        //3 easy questions, randomize topic, randomize item
        for(int i = 0; i < 3; i++){
            int topic = rand.nextInt(5);
            int item = rand.nextInt(10);
            questionIndices[i] = topic * 30 + item ;
        }
        //3 medium questions
        for(int i = 3; i < 6; i++){
            int topic = rand.nextInt(5);
            int item = rand.nextInt(10);
            questionIndices[i] = topic * 30 + item + 10;
        }
        //4 hard
        for(int i = 6; i < 10; i++){
            int topic = rand.nextInt(5);
            int item = rand.nextInt(10);
            questionIndices[i] = topic * 30 + item + 20;
        }
    }
    String getTopic(int index){
        Row currRow = sheet.getRow(questionIndices[index]);
        return currRow.getCell(1).getStringCellValue();
    }
    String getQuestion(){
        Row currRow = sheet.getRow(questionIndices[selectedQuestion]);
        return currRow.getCell(2).getStringCellValue();
    }
    private void loadQuestionPool(){        
        File file = new File("./src/question_pool.xlsx");
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);            
            Workbook workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheetAt(0);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void setSelectedQuestion(int index) {
        selectedQuestion = index;
        opened[index] = true;
    }
    int getSelectedQuestion(){
        return selectedQuestion;
    }
    boolean isOpened(int index){
        return opened[index];
    }
    char getAnswer() {
        Row currRow = sheet.getRow(questionIndices[selectedQuestion]);
        return currRow.getCell(7).getStringCellValue().charAt(0);
    }
    String getChoice(int index){
        Row currRow = sheet.getRow(questionIndices[selectedQuestion]);
        return currRow.getCell(index + 3).getStringCellValue();
        //choices start at index 3 in file
    }
    int getPoints(){
        return points;
    }
    void setPoints(int points){
        this.points = points;
    }
    boolean done(){
        for(int i = 0; i < opened.length; i++){
            if(opened[i] == false){
                return false;
            }
        }
        return true;
    }
}
