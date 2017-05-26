package de.torqdev.jegl.filters.color;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class SepiaFilterTest {

    private final ImageFilter filter = new SepiaFilter();

    @Test
    public void givenEmptyImage_returnsEmptyImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(0, 0, 1);

        // execute
        image = filter.processImage(image);

        // verify
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
    public void givenOneRedPixelImage_returnsSepiaPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setPixel(0, 0, new float[] {1F, 0F, 0F});

        // execute
        image = filter.processImage(image);

        // verify
        float[] expected = {0.393F, 0.349F, 0.272F};
        assertThat(Arrays.equals(image.getPixel(0, 0), expected), is(true));
    }

    @Test
    public void givenOneGreenPixelImage_returnsSepiaPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setPixel(0, 0, new float[] {0F, 1F, 0F});

        // execute
        image = filter.processImage(image);

        // verify
        float[] expected = {0.769F, 0.686F, 0.534F};
        assertThat(Arrays.equals(image.getPixel(0, 0), expected), is(true));
    }

    @Test
    public void givenOneBluePixelImage_returnsSepiaPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setPixel(0, 0, new float[] {0F, 0F, 1F});

        // execute
        image = filter.processImage(image);

        // verify
        float[] expected = {0.189F, 0.168F, 0.131F};
        assertThat(Arrays.equals(image.getPixel(0, 0), expected), is(true));
    }

    @Test
    public void givenOneYellowPixelImage_returnsCappedSepiaPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setPixel(0, 0, new float[] {1F, 1F, 0F});

        // execute
        image = filter.processImage(image);

        // verify
        float[] expected = {1F, 1F, 0.806F};
        assertThat(Arrays.equals(image.getPixel(0, 0), expected), is(true));
    }

    @Test
    public void givenMultiPixelImage_returnsSepiaImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(2, 2, 3);
        image.setPixel(0, 0, new float[] {1F, 0F, 0F});
        image.setPixel(1, 0, new float[] {0F, 1F, 0F});
        image.setPixel(0, 1, new float[] {0F, 0F, 1F});
        image.setPixel(1, 1, new float[] {1F, 1F, 0F});

        // execute
        image = filter.processImage(image);

        // verify
        float[] expected = {
                0.393F, 0.349F, 0.272F,
                0.769F, 0.686F, 0.534F,
                0.189F, 0.168F, 0.131F,
                1F, 1F, 0.806F
        };
        assertThat(Arrays.equals(image.getRawData(), expected), is(true));
    }
}