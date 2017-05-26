package de.torqdev.jegl.filters.trivial;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import org.apache.commons.lang3.ArrayUtils;
import org.kohsuke.MetaInfServices;

/**
 * Returns a new image with the same values.
 *
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class CopyFilter implements ImageFilter {

    @Override
    public FloatImage processImage(FloatImage image) {
        FloatImage newImage = new FloatImage(image.getWidth(), image.getHeight(), image.getChannels());
        newImage.setRawData(ArrayUtils.clone(image.getRawData()));
        return newImage;
    }
}
