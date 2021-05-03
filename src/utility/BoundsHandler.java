package utility;

import java.util.ArrayList;
import java.awt.Rectangle;

//handles bounds issues for the "artifical" buttons
public class BoundsHandler{
    private ArrayList<Rectangle> buttonAreas;
    public BoundsHandler(){
        buttonAreas = new ArrayList<Rectangle>();
    }
    public void addRectangle(int x, int y, int width, int height){
        buttonAreas.add(new Rectangle(x,y,width,height));
    }    
    public int getRectangleIndex(int x, int y){
        for(int i = 0; i < buttonAreas.size(); i++){
            Rectangle rect = buttonAreas.get(i);
            if(Math.abs(x - rect.getCenterX()) <= rect.getWidth()/2){
                if(Math.abs(y - rect.getCenterY()) <= rect.getHeight()/2){
                    return i;
                }
            }
        }
        return -1;
    }
}
