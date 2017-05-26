package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.core.GrayscaleFloatImageFromTextMatrixConverter;
import de.torqdev.jegl.filters.AbstractSameSizeImageFilterTest;
import de.torqdev.jegl.filters.ImageFilter;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public abstract class AbstractEdgeDetectionFilterTest extends AbstractSameSizeImageFilterTest {
    protected static final AbstractFloatImageConverter<String> CONVERTER = new
            GrayscaleFloatImageFromTextMatrixConverter();

    protected final float mean;

    protected AbstractEdgeDetectionFilterTest(ImageFilter filter) {
        this(filter, 0.5F);
    }

    protected AbstractEdgeDetectionFilterTest(ImageFilter filter, float mean) {
        super(filter);
        this.mean = mean;
    }

    @Test
    public void givenColoredImage_returnsGrayscaleImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 4);

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getChannels(), is(1));
    }

    @Test
    public void givenOneBlackPixelImage_returnsGrayPixel() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("0");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(mean));
    }

    @Test
    public void givenOneWhitePixelImage_returnsGrayPixel() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("1");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(mean));
    }

    @Test
    public void givenAnyOnePixelImage_returnsGrayPixel() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("0.6");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(mean));
    }
}
