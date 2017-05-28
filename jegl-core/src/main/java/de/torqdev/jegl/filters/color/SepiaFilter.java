package de.torqdev.jegl.filters.color;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

import static java.lang.Math.min;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class SepiaFilter implements ImageFilter {
    @Override
    public FloatImage processImage(FloatImage image) {
        FloatImage sepia = new FloatImage(image.getWidth(), image.getHeight(), 3);

        int bound1 = image.getHeight();
        for (int y = 0; y < bound1; y++) {
            int bound = image.getWidth();
            for (int x = 0; x < bound; x++) {
                sepia.setPixel(x, y, sepiaForPixel(image.getPixel(x, y)));
            }
        }
        return sepia;
    }

    private float[] sepiaForPixel(float[] pixel) {
        return new float[]{calculateRed(pixel), calculateGreen(pixel), calculateBlue(pixel)};
    }

    private float calculateRed(float[] pixel) {
        return min(1F, 0.393F * pixel[0] + 0.769F * pixel[1] + 0.189F * pixel[2]);
    }

    private float calculateGreen(float[] pixel) {
        return min(1F, 0.349F * pixel[0] + 0.686F * pixel[1] + 0.168F * pixel[2]);
    }

    private float calculateBlue(float[] pixel) {
        return min(1F, 0.272F * pixel[0] + 0.534F * pixel[1] + 0.131F * pixel[2]);
    }
}
