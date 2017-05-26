package de.torqdev.jegl.awt;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.stream.IntStream;

import static java.awt.image.BufferedImage.*;

/**
 * So far only works with integer based buffered images!
 *
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class FloatImageFromBufferedImageConverter
        implements AbstractFloatImageConverter<BufferedImage> {
    @Override
    public FloatImage toFloatImage(BufferedImage bi) {
        int[] pixels = getPixelArrayFromBufferedImage(bi);
        int channels = getChannelsFromBufferedImage(bi);

        FloatImage image = new FloatImage(bi.getWidth(), bi.getHeight(), channels);
        image.setRawData(byteToFloatArray(pixels, channels));
        return image;
    }

    private int[] getPixelArrayFromBufferedImage(BufferedImage imageSource) {
        return ((DataBufferInt) imageSource.getRaster().getDataBuffer()).getData();
    }

    private int getChannelsFromBufferedImage(BufferedImage imageSource) {
        return imageSource.getAlphaRaster() != null ? 4 : 3;
    }

    private float[] byteToFloatArray(int[] pixels, int channels) {
        float[] myReturn = new float[pixels.length * channels];
        for (int i = 0; i < pixels.length; i++) {
            for (int channel = 0; channel < channels; channel++) {
                myReturn[i * channels + channel] = getFloatColorFromChannel(pixels[i], channel,
                                                                            channels);
            }
        }
        return myReturn;
    }

    private float getFloatColorFromChannel(int pixel, int channel, int channels) {
        int colorByte = ((pixel >> (channels - channel - 1) * 8) & 0xFF);
        return ((float) colorByte) / 255F;
    }

    @Override
    public BufferedImage fromFloatImage(FloatImage floatImage) {
        BufferedImage image = new BufferedImage(floatImage.getWidth(), floatImage.getHeight(),
                                                getTypeFrom(floatImage));
        writePixelsToImage(floatImage, image);
        return image;
    }

    private void writePixelsToImage(FloatImage orig, BufferedImage image) {
        IntStream.range(0, image.getHeight()).forEach(
                y -> IntStream.range(0, image.getWidth()).forEach(
                        x -> image.setRGB(x, y, getColorFrom(orig.getPixel(x, y)))));
    }

    private int getColorFrom(float[] pixel) {
        Color color;
        switch (pixel.length) {
            case 1:
                color = new Color(pixel[0], pixel[0], pixel[0]);
                break;
            case 3:
                color = new Color(pixel[0], pixel[1], pixel[2]);
                break;
            default:
                color = new Color(pixel[3], pixel[0], pixel[1], pixel[2]);
        }
        return color.getRGB();
    }

    private int getTypeFrom(FloatImage floatImage) {
        switch (floatImage.getChannels()) {
            case 1:
                return TYPE_BYTE_GRAY;
            case 3:
                return TYPE_INT_RGB;
            case 4:
                return TYPE_INT_ARGB;
            default:
                throw new UnsupportedOperationException("Number of channels not supported!");
        }
    }
}
