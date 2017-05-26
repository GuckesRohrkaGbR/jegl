package de.torqdev.jegl.filters.grayscale;

import de.torqdev.jegl.core.FloatImage;
import org.kohsuke.MetaInfServices;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @author <a href="mailto:jonas.egert@torq-dev.de">Jonas Egert</a>
 * @version 1.0
 */
@MetaInfServices
public class LuminosityGrayscaleFilter extends AbstractGrayscaleFilter {
    @Override
    protected float calculateGrayValueAt(int i, FloatImage image) {
        int channels = image.getChannels();
        int alphaOffset = channels == 4 ? 1 : 0;

        float[] rawData = image.getRawData();
        float red = rawData[i * channels + alphaOffset];
        float green = rawData[i * channels + alphaOffset + 1];
        float blue = rawData[i * channels + alphaOffset + 2];
        return red * 0.21F + green * 0.72F + blue * 0.07F;
    }
}
