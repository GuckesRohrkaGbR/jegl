package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by lennart on 26.05.2017.
 */
public abstract class AbstractMatrixFilter implements ImageFilter {
    private final float[] matrix;
    private final float factor;

    protected AbstractMatrixFilter(float[] matrix, float factor) {
        this.matrix = matrix;
        this.factor = factor;
    }

    protected float normalize(float color) {
        return max(0F, min(1F, color));
    }

    @Override
    public FloatImage processImage(FloatImage image) {
        return blurred(image);
    }

    private FloatImage blurred(FloatImage image) {
        FloatImage blurred = new FloatImage(image.getWidth(), image.getHeight(),
                image.getChannels());

        int bound1 = image.getHeight();
        for (int y = 0; y < bound1; y++) {
            int bound = image.getWidth();
            for (int x = 0; x < bound; x++) {
                blurred.setPixel(x, y, blur(x, y, image));
            }
        }

        return blurred;
    }

    private float[] blur(int x, int y, FloatImage image) {
        float[] newPixel = getArrayWithSameChannelsAs(x, y, image);
        int channels = image.getChannels();

        for (int matrixY = -1; matrixY < 2; matrixY++) {
            for (int matrixX = -1; matrixX < 2; matrixX++) {
                for (int channel = channels == 4 ? 1 : 0; channel < channels; channel++) {
                    newPixel[channel] += getChannelValue(x, y, image, matrixY,
                            matrixX, channel);
                }
            }
        }

        for (int channel = channels == 4 ? 1 : 0; channel < channels; channel++) {
            newPixel[channel] = normalize(newPixel[channel] * factor);
        }
        return newPixel;
    }

    private float getChannelValue(int x, int y, FloatImage image, int matrixY, int matrixX,
                                  int channel) {
        return image.getCappedPixel(x + matrixX, y + matrixY)[channel] * matrix[matrixPosition(
                matrixY, matrixX)];
    }

    private int matrixPosition(int matrixY, int matrixX) {
        return (matrixX + 1) + (matrixY + 1) * 3;
    }

    private float[] getArrayWithSameChannelsAs(int x, int y, FloatImage image) {
        int channels = image.getChannels();
        if (channels == 4) {
            return new float[]{image.getPixel(x, y)[0], 0F, 0F, 0F};
        }
        return new float[channels];
    }
}
