package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author <a href="mailto:jonas.egert@torq-dev.de">Jonas Egert</a>
 * @version 1.0
 */
public class RobertsEdgeDetectionFilterTest extends AbstractEdgeDetectionFilterTest{
    public RobertsEdgeDetectionFilterTest() {
        super(new RobertsEdgeDetectionFilter());
    }

    @Test
    public void givenImageWithHorizontalEdge_marksEdge() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("0 0 0\n1 1 1\n0 0 0");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = CONVERTER.toFloatImage("0.5 0.5 0.5\n0 0 0\n1 1 1");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }

    @Test
    public void givenImageWithVerticalEdge_marksEdge() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("0 1 0\n0 1 0\n0 1 0");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = CONVERTER.toFloatImage("0.5 0 1\n 0.5 0 1\n0.5 0 1");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }


}