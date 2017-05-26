package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.FloatImage;
import org.kohsuke.MetaInfServices;

import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by jonas on 26.05.17.
 */
@MetaInfServices
public class PrewittVerticalEdgeDetectionFilter implements ImageFilter {
    private static final float[] prewittOperator = new float[]{
            -1, 0, 1,
            -1, 0, 1,
            -1, 0, 1,
    };
    private static final float factor = 1F;

    private ImageFilter grayscale = new AverageGrayscaleFilter();

    @Override
    public FloatImage processImage(FloatImage image) {
        return applyPrewitt(grayscale.processImage(image));
    }

    private FloatImage applyPrewitt(FloatImage image) {
        FloatImage prewittImage = new FloatImage(image.getWidth(), image.getHeight(), image.getChannels());

        IntStream.range(0, image.getHeight()).forEach(
                y -> IntStream.range(0, image.getWidth()).forEach(
                        x -> prewittImage.setPixel(x, y, calculatePixel(x, y, image))));

        return prewittImage;
    }

    private float[] calculatePixel(int x, int y, FloatImage image) {
        float[] newPixel = getArrayWithSameChannelsAs(x, y, image);
        int channels = image.getChannels();

        IntStream.range(-1, 2).forEach(matrixY -> IntStream.range(-1, 2).forEach(
                matrixX -> IntStream.range(channels == 4 ? 1 : 0, channels).forEach(
                        channel -> newPixel[channel] += image.getCappedPixel(x + matrixX,
                                y + matrixY)
                                [channel] * prewittOperator[(matrixX + 1) + (matrixY + 1) * 3])));

        IntStream.range(channels == 4 ? 1 : 0, channels).forEach(
                channel -> newPixel[channel] = normalize(newPixel[channel] *= factor));
        return newPixel;

    }

    private float[] getArrayWithSameChannelsAs(int x, int y, FloatImage image) {
        int channels = image.getChannels();
        if (channels == 4) {
            return new float[]{image.getPixel(x, y)[0], 0F, 0F, 0F};
        }
        return new float[channels];
    }

    private float normalize(float value) {
        return (max(-1F, min(1F, value)) + 1F) / 2F;
    }

}

