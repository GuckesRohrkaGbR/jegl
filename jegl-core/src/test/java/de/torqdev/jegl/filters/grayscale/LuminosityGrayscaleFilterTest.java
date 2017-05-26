package de.torqdev.jegl.filters.grayscale;

import de.torqdev.jegl.core.FloatImage;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;

/**
 * @author <a href="mailto:jonas.egert@torq-dev.de">Jonas Egert</a>
 * @version 1.0
 */
public class LuminosityGrayscaleFilterTest extends AbstractGrayscaleFilterTest {
    public LuminosityGrayscaleFilterTest() {
        super(new LuminosityGrayscaleFilter());
    }

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
    public void givenGrayscaleImage_returnsIdentity() throws Exception {
        // setup
        FloatImage image1 = new FloatImage(1, 1, 1);

        // execute
        FloatImage image2 = filter.processImage(image1);

        // verify
        assertThat(image1, is(equalTo(image2)));
    }

    @Test
    public void givenOneRedPixelImage_returnsOneGrayPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setRawData(new float[]{1F, 0F, 0F});

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.21F));
    }

    @Test
    public void givenOneGreenPixelImage_returnsOneGrayPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setRawData(new float[]{0F, 1F, 0F});

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.72F));
    }


    @Test
    public void givenOneBluePixelImage_returnsOneGrayPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setRawData(new float[]{0F, 0F, 1F});

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.07F));
    }

    @Test
    public void givenOneYellowPixelImage_returnsOneGrayPixelImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);
        image.setRawData(new float[]{1F, 1F, 0F});

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.93F));
    }

    @Test
    public void givenFourPixelImage_returnsFourPixelGrayscaleImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(2, 2, 3);
        image.setRawData(new float[]{1F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 1F, 1F, 1F, 0F});

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.21F));
        assertThat(image.getRawData()[1], is(0.72F));
        assertThat(image.getRawData()[2], is(0.07F));
        assertThat(image.getRawData()[3], is(0.93F));
    }
}