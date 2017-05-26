package de.torqdev.jegl.filters.color;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

/**
 * Created by jonas on 26.05.17.
 */
@MetaInfServices
public class TransmuteColorFilter implements ImageFilter {
    @Override
    public FloatImage processImage(FloatImage image){
        FloatImage transmuteColor = new FloatImage(image.getWidth(), image.getHeight(),3);

        for (int y=0; y < image.getHeight(); y++){
            for (int x = 0; x < image.getWidth(); x++){
                float [] pixel = image.getPixel(x,y);
                transmuteColor.setPixel(x,y, transmutecolorForPixel(pixel));
            }
        }
        return transmuteColor;
    }

    private float[] transmutecolorForPixel(float[] pixel) {
        return new float[]{
                pixel[2],
                pixel[0],
                pixel[1]
        };
    }

}
