package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by <a href="mailto:jonas.egert@torq-dev.de 27.05.17.
 */
public class ScharrVerticalEdgeDetectionFilterTest extends AbstractEdgeDetectionFilterTest {
    public ScharrVerticalEdgeDetectionFilterTest() {
        super(new ScharrVerticalEdgeDetectionFilter());
    }

    @Test
    public void givenImageWithVerticalEdge_marksEdge() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("0 1 0\n0 1 0\n0 1 0");

        // execute
        image = filter.processImage(image);

        // verify
        System.out.println(CONVERTER.fromFloatImage(image));
        FloatImage expected = CONVERTER.toFloatImage("0 0.5 1\n0 0.5 1\n0 0.5 1");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }

    @Test
    public void givenImageWithHorizontalEdges_findsNothing() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("0 0 0\n1 1 1\n0 0 0");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = CONVERTER.toFloatImage("0.5 0.5 0.5\n0.5 0.5 0.5\n0.5 0.5 0.5");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }

}