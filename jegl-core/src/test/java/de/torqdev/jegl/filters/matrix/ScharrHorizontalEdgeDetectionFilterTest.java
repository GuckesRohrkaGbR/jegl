package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

/**
 * Created by <a href="mailto:jonas.egert@torq-dev.de 26.05.17.
 */
public class ScharrHorizontalEdgeDetectionFilterTest extends AbstractEdgeDetectionFilterTest {
    public ScharrHorizontalEdgeDetectionFilterTest() {
        super(new ScharrHorizontalEdgeDetectionFilter());
    }

    @Test
    public void givenImageWithHorizontalEdge_marksEdge() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("0 0 0\n1 1 1\n0 0 0");

        // execute
        image = filter.processImage(image);

        // verify
        /*System.out.println(CONVERTER.fromFloatImage(image)); */
        FloatImage expected = CONVERTER.toFloatImage("0 0 0\n0.5 0.5 0.5\n1 1 1");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }

    @Test
    public void givenImageWithVerticalEdges_findsNothing() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("0 1 0\n0 1 0\n0 1 0");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = CONVERTER.toFloatImage("0.5 0.5 0.5\n0.5 0.5 0.5\n0.5 0.5 0.5");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }
}