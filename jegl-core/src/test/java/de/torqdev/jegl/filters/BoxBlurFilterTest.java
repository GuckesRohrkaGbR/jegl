package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.core.GrayscaleFloatImageFromTextMatrixConverter;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class BoxBlurFilterTest {
    private AbstractFloatImageConverter<String> converter = new
            GrayscaleFloatImageFromTextMatrixConverter();
    private ImageFilter filter = new BoxBlurFilter();

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
    public void givenAnyImage_returnsDifferentInstance() throws Exception {
        // setup
        FloatImage image = new FloatImage(0, 0, 1);

        // execute
        FloatImage processed = filter.processImage(image);

        // verify
        assertFalse(image == processed);
    }

    @Test
    public void givenOnePixelImage_doesNothing() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("1");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(1F));
    }

    @Test
    public void givenTwoEqualPixels_doesNothing() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("1 1");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(1F));
        assertThat(image.getRawData()[1], is(1F));
    }

    @Test
    public void givenTwoDifferentPixels_blursTheImage() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("1 0");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(2 / 3F));
        assertThat(image.getRawData()[1], is(1 / 3F));
    }

    @Test
    public void givenFourDifferentPixels_blursTheImage() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("1 0\n0 1");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(5 / 9F));
        assertThat(image.getRawData()[1], is(4 / 9F));
        assertThat(image.getRawData()[2], is(4 / 9F));
        assertThat(image.getRawData()[3], is(5 / 9F));
    }

    @Test
    public void givenImageWithAlpha_alphaChannelIsUntouched() throws Exception {
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
        assertThat(image.getRawData()[0], is(0.2F));
        assertThat(image.getRawData()[4], is(0.4F));
        assertThat(image.getRawData()[8], is(0.6F));
        assertThat(image.getRawData()[12], is(0.8F));
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
                0.2F, 6/9F, 6/9F, 5/9F,
                0.4F, 6/9F, 3/9F, 4/9F,
                0.6F, 3/9F, 6/9F, 4/9F,
                0.8F, 3/9F, 3/9F, 5/9F
        }));
    }
}