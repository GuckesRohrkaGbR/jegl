package de.torqdev.jegl.core;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public interface AbstractFloatImageFactory<T> {
    FloatImage createByteImageFrom(T imageSource);
}
