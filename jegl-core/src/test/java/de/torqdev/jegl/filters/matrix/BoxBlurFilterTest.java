package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class BoxBlurFilterTest extends AbstractBlurFilterTest {
    public BoxBlurFilterTest() {
        super(new BoxBlurFilter());
    }

    @Test
    public void givenTwoDifferentPixels_blursTheImage() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("1 0");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(2 / 3F));
        assertThat(image.getRawData()[1], is(1 / 3F));
    }

    @Test
    public void givenFourDifferentPixels_blursTheImage() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("1 0\n0 1");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(5 / 9F));
        assertThat(image.getRawData()[1], is(4 / 9F));
        assertThat(image.getRawData()[2], is(4 / 9F));
        assertThat(image.getRawData()[3], is(5 / 9F));
    }

    @Test
    public void givenImageWithAlphaAndColors_blursTheImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(2, 2, 4);
        // @formatter:off
        image.setRawData(new float[] {
                0.2F, 1F, 1F, 1F,
                0.4F, 1F, 0F, 0F,
                0.6F, 0F, 1F, 0F,
                0.8F, 0F, 0F, 1F
        });
        // @formatter:on

        // execute
        image = filter.processImage(image);

        // verify
        assertTrue(Arrays.equals(image.getRawData(),
                                 new float[]{0.2F, 6 / 9F, 6 / 9F, 5 / 9F, 0.4F, 6 / 9F, 3 / 9F,
                                             4 / 9F, 0.6F, 3 / 9F, 6 / 9F, 4 / 9F, 0.8F, 3 / 9F,
                                             3 / 9F, 5 / 9F}));
    }
}