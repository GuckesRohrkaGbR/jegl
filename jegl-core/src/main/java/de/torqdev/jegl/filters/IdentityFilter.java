package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.FloatImage;
import org.kohsuke.MetaInfServices;

/**
 * Does literally nothing with the image.
 *
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class IdentityFilter implements ImageFilter {
    @Override
    public FloatImage processImage(FloatImage image) {
        return image;
    }
}
