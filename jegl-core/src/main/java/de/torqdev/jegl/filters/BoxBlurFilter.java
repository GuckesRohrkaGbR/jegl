package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.FloatImage;
import org.kohsuke.MetaInfServices;

import java.util.stream.IntStream;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class BoxBlurFilter implements ImageFilter {
    private static final float[] matrix = new float[]{
            // @formatter:off
            1, 1, 1,
            1, 1, 1,
            1, 1, 1,
            // @formatter:on
    };

    private static final float factor = 1 / 9F;

    @Override
    public FloatImage processImage(FloatImage image) {
        return blurred(image);
    }

    private FloatImage blurred(FloatImage image) {
        FloatImage blurred = new FloatImage(image.getWidth(), image.getHeight(),
                                            image.getChannels());

        IntStream.range(0, image.getHeight()).forEach(
                y -> IntStream.range(0, image.getWidth()).forEach(
                        x -> blurred.setPixel(x, y, blur(x, y, image))));

        return blurred;
    }

    private float[] blur(int x, int y, FloatImage image) {
        float[] newPixel = getArrayWithSameChannelsAs(x, y, image);
        int channels = image.getChannels();

        IntStream.range(-1, 2).forEach(matrixY -> IntStream.range(-1, 2).forEach(
                matrixX -> IntStream.range(channels == 4 ? 1 : 0, channels).forEach(
                        channel -> newPixel[channel] += image.getCappedPixel(x + matrixX,
                                                                             y + matrixY)
                                [channel] * matrix[(matrixX + 1) + (matrixY + 1) * 3])));

        // divide at the end to avoid all your colors being rounded down to null;
        IntStream.range(channels == 4 ? 1 : 0, channels).forEach(
                channel -> newPixel[channel] *= factor);
        return newPixel;
    }

    private float[] getArrayWithSameChannelsAs(int x, int y, FloatImage image) {
        int channels = image.getChannels();
        if (channels == 4) {
            return new float[]{image.getPixel(x, y)[0], 0F, 0F, 0F};
        }
        return new float[channels];
    }
}
