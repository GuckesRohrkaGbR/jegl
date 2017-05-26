package de.torqdev.jegl.filters.crop;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.core.GrayscaleFloatImageFromTextMatrixConverter;
import de.torqdev.jegl.filters.AbstractImageFilterTest;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class ZealousCropFilterTest extends AbstractImageFilterTest {
    private AbstractFloatImageConverter<String> converter = new
            GrayscaleFloatImageFromTextMatrixConverter();

    public ZealousCropFilterTest() {
        super(new ZealousCropFilter());
    }

    @Test
    public void givenOnePixelImage_returnsEmptyImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 1);

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData().length, is(0));
    }

    @Test
    public void givenTwoVerticalPixels_returnsEmptyImage() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("1\n0");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData().length, is(0));
    }

    @Test
    public void givenTwoHorizontalPixels_returnsEmptyImage() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("1 0");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData().length, is(0));
    }

    @Test
    public void givenThreeRows_returnsOnlyTheDifferingRows() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("1 1 1\n0 1 1\n1 0 0");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = converter.toFloatImage("0 1 1\n1 0 0");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }

    @Test
    public void givenThreeColums_returnsOnlyTheDifferingColumns() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("1 1 0\n0 1 0\n 0 1 1");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = converter.toFloatImage("1 0\n0 0\n0 1");
        assertThat(Arrays.equals(image.getRawData(), expected.getRawData()), is(true));
    }
}