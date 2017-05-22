package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.core.GrayscaleFloatImageFromTextMatrixConverter;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class BlackAndWhiteFilterTest {
    private AbstractFloatImageConverter<String> converter = new
            GrayscaleFloatImageFromTextMatrixConverter();
    private ImageFilter filter = new BlackAndWhiteFilter();

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
    public void givenAnyImage_returnsImageWithTheSameChannelCount() throws Exception {
        // setup
        FloatImage image = new FloatImage(0, 0, 3);

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getChannels(), is(3));
    }

    @Test
    public void givenAnyImage_returnsImageOfTheSameSize() throws Exception {
        // setup
        FloatImage image = new FloatImage(300, 200, 4);

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getWidth(), is(300));
        assertThat(image.getHeight(), is(200));
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

    @Test
    public void givenImageWithAlphaChannel_alphaValuesAreNotChanged() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 4);
        image.setRawData(new float[] { 0.2F, 0.2F, 0.8F, 0.8F });

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.2F));
        assertThat(image.getRawData()[1], is(1F));
        assertThat(image.getRawData()[2], is(1F));
        assertThat(image.getRawData()[3], is(1F));
    }
}