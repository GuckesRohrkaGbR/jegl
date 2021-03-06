package de.torqdev.jegl.core;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.apache.commons.lang3.ArrayUtils.subarray;

/**
 * Image representation in ARGB format.
 *
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class FloatImage {
    private final int channels;
    private float[] rawData;
    private int width;

    public FloatImage(int width, int height, int channels) {
        this.width = width;
        this.channels = channels;
        rawData = new float[width * height * channels];
    }

    public float[] getRawData() {
        return rawData;
    }

    public void setRawData(float[] rawData) {
        this.rawData = rawData;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return sizeIsZero() ? 0 : rawData.length / width / channels;
    }

    private boolean sizeIsZero() {
        return width == 0 || rawData.length == 0 || channels == 0;
    }

    public float[] getPixel(int x, int y) {
        int start = coordsToArrayIndex(x, y);
        return subarray(rawData, start, start + channels);
    }

    public void setPixel(int x, int y, float[] pixel) {
        int bound = pixel.length;
        for (int i = 0; i < bound; i++) {
            rawData[coordsToArrayIndex(x, y) + i] = pixel[i];
        }
    }

    private int coordsToArrayIndex(int x, int y) {
        return (x + (y * width)) * channels;
    }

    public int getChannels() {
        return channels;
    }

    public float[] getCappedPixel(int x, int y) {
        int cappedX = max(0, min(getWidth() - 1, x));
        int cappedY = max(0, min(getHeight() - 1, y));
        return getPixel(cappedX, cappedY);
    }
}
