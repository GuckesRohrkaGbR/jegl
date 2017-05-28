package de.torqdev.jegl.android;

import android.graphics.Bitmap;
import android.util.Log;

import org.junit.Test;

import java.nio.ByteBuffer;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by chris on 28.05.17.
 */
public class FloatImageFromBitmapConverterTest {
    private AbstractFloatImageConverter<Bitmap> converter = new FloatImageFromBitmapConverter();

    @Test
    public void givenOnePixelFloatImage_returnsOnePixelBitmap() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 4);
        image.setRawData(new float[] {1F, 1F, 0.5F, 0F});

        // execute
        Bitmap bitmap = converter.fromFloatImage(image);

        // verify
        Log.e("TEST", String.format("%08X", bitmap.getPixel(0,0)));
        assertThat(bitmap.getPixel(0, 0), is(0xFFFF7F00));
    }

    @Test
    public void givenOnePixelBitmap_returnsOnePixelFloatImage() throws Exception {
        // setup
        Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_4444);
        bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(new byte[] {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}));

        // execute
        FloatImage image = converter.toFloatImage(bitmap);

        // verify
        assertThat(image.getRawData()[0], is((1F)));
        assertThat(image.getRawData()[1], is((1F)));
        assertThat(image.getRawData()[2], is((1F)));
    }
}