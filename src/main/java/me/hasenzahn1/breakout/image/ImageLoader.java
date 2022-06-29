package me.hasenzahn1.breakout.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage loadImage(String path){
        BufferedImage img = null;
        try {
            img = ImageIO.read(ImageLoader.class.getClassLoader().getResource(path));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

}
