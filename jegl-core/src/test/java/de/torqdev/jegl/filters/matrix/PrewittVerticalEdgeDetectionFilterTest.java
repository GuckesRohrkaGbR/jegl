package de.torqdev.jegl.filters.matrix;

import de.torqdev.jegl.core.FloatImage;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

/**
 * Created by jonas on 26.05.17.
 */
public class PrewittVerticalEdgeDetectionFilterTest extends AbstractEdgeDetectionFilterTest {
    public PrewittVerticalEdgeDetectionFilterTest() {
        super(new PrewittVerticalEdgeDetectionFilter());
    }


    @Test
    public void givenImageWithVerticalEdge_MarksEdge() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("0 1 0\n0 1 0\n0 1 0");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = CONVERTER.toFloatImage("1 0.5 0\n1 0.5 0\n1 0.5 0");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }

    @Test
    public void givenImageWithHorizontalEdge_findsNothing() throws Exception {
        // setup
        FloatImage image = CONVERTER.toFloatImage("0 0 0\n1 1 1\n0 0 0");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = CONVERTER.toFloatImage("0.5 0.5 0.5\n0.5 0.5 0.5\n0.5 0.5 0.5");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }
}