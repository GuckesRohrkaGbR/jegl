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
public class GaussianMatrixFilterTest extends AbstractMatrixFilterTest {
    public GaussianMatrixFilterTest() {
        super(new GaussianBlurFilter());
    }

    @Test
    public void givenTwoDifferentPixels_blursTheImage() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("1 0");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(3 / 4F));
        assertThat(image.getRawData()[1], is(1 / 4F));
    }

    @Test
    public void givenFourDifferentPixels_blursTheImage() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("1 0\n0 1");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(5 / 8F));
        assertThat(image.getRawData()[1], is(3 / 8F));
        assertThat(image.getRawData()[2], is(3 / 8F));
        assertThat(image.getRawData()[3], is(5 / 8F));
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
        assertTrue(Arrays.equals(image.getRawData(), new float[]{
                                         // @formatter:off
                0.2F, 3 / 4F, 3 / 4F, 5 / 8F,
                0.4F, 3 / 4F, 1 / 4F, 3 / 8F,
                0.6F, 1 / 4F, 3 / 4F, 3 / 8F,
                0.8F, 1 / 4F, 1 / 4F, 5 / 8F}
                // @formatter:on
        ));
    }
}