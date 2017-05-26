package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.core.GrayscaleFloatImageFromTextMatrixConverter;
import de.torqdev.jegl.filters.AbstractSameSizeImageFilterTest;
import de.torqdev.jegl.filters.ImageFilter;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public abstract class AbstractBlurFilterTest extends AbstractSameSizeImageFilterTest {
    protected static final AbstractFloatImageConverter<String> CONVERTER = new
            GrayscaleFloatImageFromTextMatrixConverter();

    protected AbstractBlurFilterTest(ImageFilter filter) {
        super(filter);
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
        FloatImage image = CONVERTER.toFloatImage("1");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(1F));
    }

    @Test
    public void givenTwoEqualPixels_doesNothing() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("1 1");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(1F));
        assertThat(image.getRawData()[1], is(1F));
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
}
