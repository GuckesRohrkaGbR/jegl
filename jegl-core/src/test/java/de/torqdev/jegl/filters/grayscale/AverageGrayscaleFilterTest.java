package de.torqdev.jegl.filters.grayscale;

import de.torqdev.jegl.core.FloatImage;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

/**
 * @author <a href="mailto:jonas.egert@torq-dev.de">Jonas Egert</a>
 * @version 1.0
 */
public class AverageGrayscaleFilterTest extends AbstractGrayscaleFilterTest {
    public AverageGrayscaleFilterTest() {
        super(new AverageGrayscaleFilter());
    }

    @Test
    public void givenOneRedPixelImageWithAlpha_returnsOneGrayPixelImageWithoutAlpha()
            throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 4);
        image.setRawData(new float[]{0.5F, 1F, 0F, 0F});

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(1 / 3F));
    }

    @Test
    public void givenOneGreenPixelImageWithAlpha_returnsOneGrayPixelImageWithoutAlpha()
            throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 4);
        image.setRawData(new float[]{0.5F, 0F, 1F, 0F});

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(1 / 3F));
    }

    @Test
    public void givenOneBluePixelImage_returnsOneGrayPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setRawData(new float[]{0F, 0F, 1F});

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(1 / 3F));
    }
}