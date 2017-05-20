package de.torqdev.jegl.core;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public interface ImageFilter {
    FloatImage processImage(FloatImage image);
}
