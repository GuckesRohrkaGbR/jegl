package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.FloatImage;

import java.util.stream.IntStream;

/**
 * Simple filter that renders all colors above the given threshold white and all others black.
 *
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class BlackAndWhiteFilter implements ImageFilter {
    private final float threshold;

    public BlackAndWhiteFilter() {
        this(0.5F);
    }

    public BlackAndWhiteFilter(float threshold) {
        this.threshold = threshold;
    }

    @Override
    public FloatImage processImage(FloatImage image) {
        IntStream.range(0, image.getHeight()).forEach(
                y -> IntStream.range(0, image.getWidth()).forEach(
                        x -> image.setPixel(x, y, thresholdFilter(image.getPixel(x, y)))
                )
        );
        return image;
    }

    private float[] thresholdFilter(float[] color) {
        float avg = average(color);

        return (avg < threshold) ? blackPixel(color) : whitePixel(color);
    }

    private float average(float[] color) {
        float avg = 0F;
        for (float value : color) {
            avg += value;
        }
        return color.length == 0 ? 0F : avg / color.length;
    }

    private float[] whitePixel(float[] pixel) {
        return coloredPixel(pixel, 1F);
    }

    private float[] blackPixel(float[] pixel) {
        return coloredPixel(pixel, 0F);
    }

    private float[] coloredPixel(float[] pixel, float color) {
        for (int i = 0; i < pixel.length; i++) {
            pixel[i] = color;
        }
        return pixel;
    }
}
