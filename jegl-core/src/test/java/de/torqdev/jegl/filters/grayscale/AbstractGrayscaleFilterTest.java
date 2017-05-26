package de.torqdev.jegl.filters.grayscale;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.AbstractSameSizeImageFilterTest;
import de.torqdev.jegl.filters.ImageFilter;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public abstract class AbstractGrayscaleFilterTest extends AbstractSameSizeImageFilterTest {
    protected AbstractGrayscaleFilterTest(ImageFilter filter) {
        super(filter);
    }

    @Test
    public void givenThreeChannelImage_returnsOneChannelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(0, 0, 3);
        // execute
        image = filter.processImage(image);
        // verify
        assertThat(image.getChannels(), is(1));
    }

    @Test
    public void givenGrayscaleImage_returnsTheImageItself() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 1);
        image.setRawData(new float[]{0.3F});

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.3F));
    }

    @Test
    public void givenOneBlackPixelImage_returnsOneBlackPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setRawData(new float[]{0F, 0F, 0F});

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0F));
    }

    @Test
    public void givenOneWhitePixelImage_returnsOneWhitePixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setRawData(new float[]{1F, 1F, 1F});

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(1F));
    }
}
