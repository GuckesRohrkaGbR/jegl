package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.FloatImage;

/**
 * @author <a href="mailto:jonas.egert@torq-dev.de">Jonas Egert</a>
 * @version 1.0
 */
public class GrayscaleFilter implements ImageFilter {
    @Override
    public FloatImage processImage(FloatImage image) {
        return new FloatImage(image.getWidth(), image.getHeight(), 1);


    }
}
