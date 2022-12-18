package Core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
    public class Images{
        public static BufferedImage smiley = null;
    }

    public static void loadImages(){
        //BufferedImage img = null;
        
        try {
            Images.smiley = ImageIO.read(new File("Core/Images/smiley.png"));
        } catch (IOException e) {
        }
    }
}
