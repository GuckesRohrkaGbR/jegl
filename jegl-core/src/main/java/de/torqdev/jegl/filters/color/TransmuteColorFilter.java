package de.torqdev.jegl.filters.color;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

import java.util.stream.IntStream;

/**
 * Created by jonas on 26.05.17.
 */
@MetaInfServices
public class TransmuteColorFilter implements ImageFilter {
    @Override
    public FloatImage processImage(FloatImage image) {
        FloatImage transmuteColor = new FloatImage(image.getWidth(), image.getHeight(), 3);

        IntStream.range(0, image.getHeight()).parallel().forEach(
                y -> IntStream.range(0, image.getWidth()).forEach(
                        x -> transmuteColor.setPixel(x, y, transmutecolorForPixel(image.getPixel(x, y)))
                )
        );
        return transmuteColor;
    }

    private float[] transmutecolorForPixel(float[] pixel) {
        return new float[]{pixel[2], pixel[0], pixel[1]};
    }

}
