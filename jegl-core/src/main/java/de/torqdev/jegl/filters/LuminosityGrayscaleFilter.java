package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.FloatImage;
import org.kohsuke.MetaInfServices;

/**
 * @author <a href="mailto:jonas.egert@torq-dev.de">Jonas Egert</a>
 * @version 1.0
 */
@MetaInfServices
public class LuminosityGrayscaleFilter implements ImageFilter {
    @Override
    public FloatImage processImage(FloatImage image) {
        if (image.getChannels() == 1) {
            return image;
        }
        return toGrayscale(image);
    }

    private FloatImage toGrayscale(FloatImage image) {
        FloatImage grayScale = new FloatImage(image.getWidth(), image.getHeight(), 1);

        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                float[] pixel = image.getPixel(x, y);
                grayScale.setPixel(x, y, calculateLuminosity(pixel));
            }
        }

        return grayScale;
    }

    private float[] calculateLuminosity(float[] pixel) {
        return new float[] {pixel[0] * 0.21F + pixel[1] * 0.72F + pixel[2] * 0.07F};
    }
}
