package de.torqdev.jegl.core;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public interface AbstractFloatImageConverter<T> {
    FloatImage toFloatImage(T imageSource);

    T fromFloatImage(FloatImage floatImage);
}
