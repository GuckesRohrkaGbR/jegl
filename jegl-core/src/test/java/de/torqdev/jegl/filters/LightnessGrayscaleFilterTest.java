package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.FloatImage;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

/**
 * @author <a href="mailto:jonas.egert@torq-dev.de">Jonas Egert</a>
 * @version 1.0
 */
public class LightnessGrayscaleFilterTest {
    private ImageFilter filter = new LightnessGrayscaleFilter();

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
    public void givenThreeChannelImage_returnsOneChannelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(0, 0, 3);
        // execute
        image = filter.processImage(image);
        // verify
        assertThat(image.getChannels(), is(1));
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
    public void givenOneBluePixelImage_returnsOneGrayPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1,1,3);
        image.setRawData(new float[] { 0F, 0F, 1F });

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.5F));
    }

    @Test
    public void givenOneBlackPixelImage_returnsOneBlackPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1,1,3);
        image.setRawData(new float[] { 0F, 0F, 0F });

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0F));
    }

    @Test
    public void givenOneWhitePixelImage_returnsOneWhitePixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1,1,3);
        image.setRawData(new float[] { 1F, 1F, 1F });

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(1F));
    }

    @Test
    public void givenOneRedPixelImageWithAlpha_returnsOneGrayPixelImageWithoutAlpha() throws Exception {
        // setup
        FloatImage image = new FloatImage(1,1,4);
        image.setRawData(new float[] { 0.5F, 1F, 0F, 0F });

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.5F));
    }
}