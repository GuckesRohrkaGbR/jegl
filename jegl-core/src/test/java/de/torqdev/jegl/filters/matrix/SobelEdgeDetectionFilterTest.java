package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.core.GrayscaleFloatImageFromTextMatrixConverter;
import de.torqdev.jegl.filters.ImageFilter;
import org.junit.Test;

import java.util.Arrays;

import static java.lang.Math.sqrt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class SobelEdgeDetectionFilterTest {
    private static final AbstractFloatImageConverter<String> converter = new
            GrayscaleFloatImageFromTextMatrixConverter();
    private static final ImageFilter filter = new SobelEdgeDetectionFilter();
    public static final float B = (float) sqrt(0.5F);

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
    public void givenAnyImage_returnsGrayscaleImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 4);

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getChannels(), is(1));
    }

    @Test
    public void givenImageWithVerticalEdge_marksVerticalEdge() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("0 1 0\n0 1 0\n0 1 0\n");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = converter.toFloatImage(
                "0.5 " + B + " 1\n0.5 " + B + " 1\n0.5 " + B + " 1");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }

    @Test
    public void givenImageWithHorizontalEdge_marksHorizontalEdge() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("0 0 0\n1 1 1\n0 0 0\n");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = converter.toFloatImage(
                "0.5 0.5 0.5\n" + B + " " + B + " " + B + "\n1 1 1\n");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }

    @Test
    public void givenImageWithDiagonalEdge_marksDiagonalEdge() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("1 0 0\n0 1 0\n0 0 1\n");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = converter.toFloatImage(
                "1 1 1\n1 " + B + " 1\n1 1 0\n");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }
}