package de.torqdev.jegl.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;

/**
 * Created by chris on 28.05.17.
 */

public class FloatImageFromImageConverter implements AbstractFloatImageConverter<Image> {
    private static final String TAG = FloatImageFromImageConverter.class.getSimpleName();

    @Override
    public FloatImage toFloatImage(Image image) {
        FloatImage floatImage = null;
        return floatImage;
    }

    @Override
    public Image fromFloatImage(FloatImage floatImage) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = floatImage.getWidth();
        options.outHeight = floatImage.getHeight();

        Image image = null;

        return image;
    }

    private int[] toColorArray(FloatImage image) {
        int[] array = new int[image.getWidth() * image.getHeight()];

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                array[x + y * image.getWidth()] = toInt(image.getPixel(x, y));
            }
        }

        return array;
    }

    private int toInt(float[] pixel) {
        int a = 255, r, g, b, index = pixel.length;
        if(pixel.length == 1) {
            r = (int) (pixel[0] * 255F);
            g = (int) (pixel[0] * 255F);
            b = (int) (pixel[0] * 255F);
        } else {
            index = pixel.length;
            b = (int) (pixel[--index] * 255F);
            g = (int) (pixel[--index] * 255F);
            r = (int) (pixel[--index] * 255F);
        }
        if(index >0) {
            a = (int) (pixel[--index] * 255F);
        }

        return (a & 0xff) << 24 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
    }


    private float[] toFloatArray(int pixel) {
        float r = byteToFloat((pixel & 0xFF0000) >> 16);
        float g = byteToFloat((pixel & 0xFF00) >> 8);
        float b = byteToFloat(pixel & 0xFF);

        return new float[]{r, g, b};
    }

    private float byteToFloat(int i) {
        return (i / 255F);
    }
}
