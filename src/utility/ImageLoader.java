package utility;


import java.io.*;

import javax.imageio.ImageIO;
import java.awt.image.*;
public class ImageLoader {
    public static BufferedImage loadImage(String path){
        try {
            BufferedImage bg = ImageIO.read(new File(path));
            return bg;
        } catch (IOException e) {
            e.printStackTrace();
        }   
        return null;
    }
}
