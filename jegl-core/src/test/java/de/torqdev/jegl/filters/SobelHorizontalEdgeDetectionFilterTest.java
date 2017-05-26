package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.core.GrayscaleFloatImageFromTextMatrixConverter;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class SobelHorizontalEdgeDetectionFilterTest {
    private AbstractFloatImageConverter<String> converter = new
            GrayscaleFloatImageFromTextMatrixConverter();
    private ImageFilter filter = new SobelHorizontalEdgeDetectionFilter();

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
        FloatImage image = converter.toFloatImage("0");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.5F));
    }

    @Test
    public void givenOneWhitePixelImage_returnsGrayPixel() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("1");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.5F));
    }

    @Test
    public void givenAnyOnePixelImage_returnsGrayPixel() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("0.6");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.5F));
    }

    @Test
    public void givenImageWithHorizontalEdge_marksEdge() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("0 0 0\n1 1 1\n0 0 0");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = converter.toFloatImage("0 0 0\n0.5 0.5 0.5\n1 1 1");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }

    @Test
    public void givenImageWithVerticalEdge_findsNothing() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("0 1 0\n0 1 0\n0 1 0");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = converter.toFloatImage("0.5 0.5 0.5\n0.5 0.5 0.5\n0.5 0.5 0.5");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }
}