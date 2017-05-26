package de.torqdev.jegl.filters.grayscale;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

import java.util.stream.IntStream;

import static java.lang.Math.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class LightnessGrayscaleFilter extends AbstractGrayScaleFilter {
    @Override
    protected float calculateGrayValueAt(int i, FloatImage image) {
        int channels = image.getChannels();
        int alphaOffset = channels == 4 ? 1 : 0;

        float[] rawData = image.getRawData();
        float red = rawData[i * channels + alphaOffset];
        float green = rawData[i * channels + alphaOffset + 1];
        float blue = rawData[i * channels + alphaOffset + 2];

        return (max(red, max(green, blue)) + min(red, min(green, blue))) / 2F;
    }
}
