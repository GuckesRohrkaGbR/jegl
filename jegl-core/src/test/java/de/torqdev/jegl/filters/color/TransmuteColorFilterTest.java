package de.torqdev.jegl.filters.color;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by jonas on 26.05.17.
 */
public class TransmuteColorFilterTest {

    private final ImageFilter filter = new TransmuteColorFilter();

    @Test
    public void givenEmptyImage_returnsEmptyImage() throws Exception {
        FloatImage image = new FloatImage(0, 0, 1);

        image = filter.processImage(image);

        assertThat(image.getRawData().length, is(0));
    }

    @Test
    public void givenAnyImage_returnsImageOfTheSameSize() throws Exception {
        // setup
        FloatImage image = new FloatImage(300, 200, 3);

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getWidth(), is(300));
        assertThat(image.getHeight(), is(200));
    }

    @Test
    public void givenAnyImage_returnsImageWithAtLeastThreeChannels() throws Exception {
        // setup
        FloatImage image = new FloatImage(0, 0, 1);

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getChannels(), is(greaterThan(2)));
    }

    @Test
    public void givenOneRedPixelImage_returnsTransmuteColorPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setPixel(0, 0, new float[]{1F, 0F, 0F});

        // execute
        image = filter.processImage(image);


        // verify
        float[] expected = {0F, 1F, 0F};
        assertThat(Arrays.equals(image.getPixel(0, 0), expected), is(true));
    }

    @Test
    public void givenOneGreenPixelImage_returnsTransmuteColorPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setPixel(0, 0, new float[]{0F, 1F, 0F});

        // execute
        image = filter.processImage(image);


        // verify
        float[] expected = {0F, 0F, 1F};
        assertThat(Arrays.equals(image.getPixel(0, 0), expected), is(true));
    }

    @Test
    public void givenOneBluePixelImage_returnsTransmuteColorPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setPixel(0, 0, new float[]{0F, 0F, 1F});

        // execute
        image = filter.processImage(image);


        // verify
        float[] expected = {1F, 0F, 0F};
        assertThat(Arrays.equals(image.getPixel(0, 0), expected), is(true));
    }

    @Test
    public void givenOneYellowPixelImage_returnsDoubleTransmuteColorPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setPixel(0, 0, new float[]{1F, 1F, 0F});

        // execute
        image = filter.processImage(image);


        // verify
        float[] expected = {0F, 1F, 1F};
        assertThat(Arrays.equals(image.getPixel(0, 0), expected), is(true));
    }

    @Test
    public void givenMultiplePixelImage_returnsTransmuteColorImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(2, 2, 3);
        image.setPixel(0, 0, new float[]{1F, 0F, 0F});
        image.setPixel(1, 0, new float[]{0F, 1F, 0F});
        image.setPixel(0, 1, new float[]{0F, 0F, 1F});
        image.setPixel(1, 1, new float[]{1F, 1F, 0F});

        // execute
        image = filter.processImage(image);

        // verify
        float[] expected = {0F, 1F, 0F, 0F, 0F, 1F, 1F, 0F, 0F, 0F, 1F, 1F};
        assertThat(Arrays.equals(image.getRawData(), expected), is(true));
    }
}
