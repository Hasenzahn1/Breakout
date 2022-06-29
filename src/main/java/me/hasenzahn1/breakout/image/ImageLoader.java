package me.hasenzahn1.breakout.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage loadImage(String path){
        BufferedImage img = null;
        if(!path.endsWith(".png")) path += ".png";
        try {
            img = ImageIO.read(ImageLoader.class.getClassLoader().getResource(path));
        }catch (IOException e){
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            System.getLogger("Breakout").log(System.Logger.Level.ERROR, "No image at path :" + path);
            System.exit(-1);
        }
        return img;
    }

}
