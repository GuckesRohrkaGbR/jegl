package de.torqdev.jegl.filters.grayscale;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;

import java.util.stream.IntStream;

/**
 * Created by lennart on 26.05.2017.
 */
public abstract class AbstractGrayscaleFilter implements ImageFilter {
    @Override
    public FloatImage processImage(FloatImage image) {
        return grayScale(image);
    }

    private FloatImage grayScale(FloatImage image) {
        if (image.getChannels() == 1) {
            return image;
        }

        FloatImage gray = new FloatImage(image.getWidth(), image.getHeight(), 1);
        float[] grayData = gray.getRawData();
        IntStream.range(0, grayData.length).forEach(
                i -> grayData[i] = calculateGrayValueAt(i, image));
        return gray;
    }

    protected abstract float calculateGrayValueAt(int i, FloatImage image);
}
