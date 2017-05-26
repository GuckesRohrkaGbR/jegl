package de.torqdev.jegl.core;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class FloatImageTest {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int CHANNELS = 4;

    @Test
    public void givenHeightWidthAndChannels_byteArrayHasCorrectSize() throws Exception {
        // setup
        FloatImage floatImage = new FloatImage(WIDTH, HEIGHT, CHANNELS);

        // execute + verify
        assertThat(floatImage.getRawData().length, is(WIDTH * HEIGHT * CHANNELS));
    }

    @Test
    public void givenWidthAndRawDataArray_heightIsCalculatedCorrectly() throws Exception {
        // setup
        FloatImage image = new FloatImage(WIDTH, HEIGHT, CHANNELS);

        // execute + verify
        assertThat(image.getHeight(), is(HEIGHT));
    }

    @Test
    public void givenEmptyImage_getPixelReturnsEmptyArray() throws Exception {
        // setup
        FloatImage image = new FloatImage(0, 0, 1);

        // execute + verify
        assertThat(image.getPixel(1, 1).length, is(0));
    }

    @Test
    public void givenOnePixelImage_getPixelReturnsOnlyPixel() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 1);
        float[] pixel = new float[]{0.5F};
        image.setPixel(0, 0, pixel);

        // execute + verify
        assertThat(ArrayUtils.toObject(image.getPixel(0, 0)), hasItemInArray(0.5F));
    }
}