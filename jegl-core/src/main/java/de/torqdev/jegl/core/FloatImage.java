package de.torqdev.jegl.core;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class FloatImage {
    private float[] rawData;
    private int width;

    public FloatImage(int width, int height, int channels) {
        this.width = width;
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
        return rawData.length / width;
    }
}
