package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import org.junit.Test;

import java.util.Arrays;

import static java.lang.Math.sqrt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author <a href="mailto:jonas.egert@torq-dev.de">Jonas Egert</a>
 * @version 1.0
 */
public class PrewittEdgeDetectionFilterTest extends AbstractEdgeDetectionFilterTest {
    public static final float B = (float) sqrt(0.5F);

    public PrewittEdgeDetectionFilterTest() {
        super(new PrewittEdgeDetectionFilter(), B);
    }

    @Test
    public void givenImageWithVerticalEdge_marksVerticalEdge() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("0 1 0\n0 1 0\n0 1 0\n");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = CONVERTER.toFloatImage(
                "1 " + B + " 0.5\n1 " + B + " 0.5\n1 " + B + " 0.5");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }

    @Test
    public void givenImageWithHorizontalEdge_marksHorizontalEdge() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("0 0 0\n1 1 1\n0 0 0\n");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = CONVERTER.toFloatImage(
                "1 1 1\n" + B + " " + B + " " + B + "\n0.5 0.5 0.5\n");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }

    @Test
    public void givenImageWithDiagonalEdge_marksDiagonalEdge() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("1 0 0\n0 1 0\n0 0 1\n");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = CONVERTER.toFloatImage("0 0.5 1\n0.5 " + B + " 1\n1 1 1\n");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }

}