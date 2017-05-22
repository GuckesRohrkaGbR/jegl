package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.FloatImage;
import org.kohsuke.MetaInfServices;

import java.util.stream.IntStream;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class AverageGrayscaleFilter implements ImageFilter {
    @Override
    public FloatImage processImage(FloatImage image) {
        return grayScale(image);
    }

    private FloatImage grayScale(FloatImage image) {
        FloatImage gray = new FloatImage(image.getWidth(), image.getHeight(), 1);
        float[] grayData = gray.getRawData();
        IntStream.range(0, grayData.length).forEach(
                i -> grayData[i] = calculateGrayValueAt(i, image));
        return gray;
    }

    private float calculateGrayValueAt(int i, FloatImage image) {
        int channels = image.getChannels();
        int alphaOffset = channels == 4 ? 1 : 0;

        float[] rawData = image.getRawData();
        float red = rawData[i * channels + alphaOffset];
        float green = rawData[i * channels + alphaOffset + 1];
        float blue = rawData[i * channels + alphaOffset + 2];

        return (red + green + blue) / 3F;
    }
}
