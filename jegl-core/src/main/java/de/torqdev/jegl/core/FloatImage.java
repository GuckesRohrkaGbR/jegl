package de.torqdev.jegl.core;

import java.util.stream.IntStream;

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
    private final int width;

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
        return width == 0
                || rawData.length == 0
                || channels == 0;
    }

    public float[] getPixel(int x, int y) {
        int start = coordsToArrayIndex(x, y);
        return subarray(rawData, start, start + channels);
    }

    public void setPixel(int x, int y, float[] pixel) {
        IntStream.range(0, pixel.length).forEach(
                i -> rawData[coordsToArrayIndex(x, y) + i] = pixel[i]
        );
    }

    private int coordsToArrayIndex(int x, int y) {
        return (x + (y * width)) * channels;
    }

    public int getChannels() {
        return channels;
    }
}
