package de.torqdev.jegl.filters.color;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.core.GrayscaleFloatImageFromTextMatrixConverter;
import de.torqdev.jegl.filters.AbstractSameSizeImageFilterTest;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class BlackAndWhiteFilterTest extends AbstractSameSizeImageFilterTest {
    private AbstractFloatImageConverter<String> converter = new
            GrayscaleFloatImageFromTextMatrixConverter();

    public BlackAndWhiteFilterTest() {
        super(new BlackAndWhiteFilter());
    }

    @Test
    public void givenAnyImage_returnsImageWithOneChannel() throws Exception {
        // setup
        FloatImage image = new FloatImage(0, 0, 3);

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getChannels(), is(1));
    }

    @Test
    public void givenOneLightGrayPixelImage_returnsWhiteImage() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("0.7");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getPixel(0, 0)[0], is(1F));
    }

    @Test
    public void givenOneDarkGrayPixelImage_returnsBlackImage() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("0.3");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getPixel(0, 0)[0], is(0F));
    }
}