package de.torqdev.jegl.filters.matrixFilters;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.core.GrayscaleFloatImageFromTextMatrixConverter;
import de.torqdev.jegl.filters.ImageFilter;
import de.torqdev.jegl.filters.matrixFilter.PrewittVerticalEdgeDetectionFilter;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jonas on 26.05.17.
 */
public class PrewittVerticalEdgeDetectionFilterTest {
    private AbstractFloatImageConverter<String> converter = new
            GrayscaleFloatImageFromTextMatrixConverter();
    private ImageFilter filter = new PrewittVerticalEdgeDetectionFilter();

    @Test
    public void givenAnyImage_returnsImageOfTheSameSize() throws Exception {
        // setup
        FloatImage image = new FloatImage(300,200,4);

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getWidth(), is(300));
        assertThat(image.getHeight(), is(200));
    }

    @Test
    public void givenEmptyImage_returnsEmptyImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(0,0,1);

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData().length, is (0));
    }

    @Test
    public void givenColoredImage_returnsGrayScaleImage() throws Exception {
        // setup
        FloatImage image = new FloatImage(1,1,4);

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getChannels(), is(1));
    }

    @Test
    public void givenOneBlackPixel_returnsGrayPixel() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("0");

        // execute
        image = filter.processImage(image);

        // verify
        assertThat(image.getRawData()[0], is(0.5F));
    }

    @Test
    public void givenOneWhitePixel_returnsGrayPixel() throws Exception {
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
    public void givenImageWithVerticalEdge_MarksEdge() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("0 1 0\n0 1 0\n0 1 0");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = converter.toFloatImage("1 0.5 0\n1 0.5 0\n1 0.5 0");
        assertThat(Arrays.equals(image.getRawData(),expected.getRawData()),is(true));
    }

    @Test
    public void givenImageWithHorizontalEdge_findsNothing() throws Exception {
        // setup
        FloatImage image = converter.toFloatImage("0 0 0\n1 1 1\n0 0 0");

        // execute
        image = filter.processImage(image);

        // verify
        FloatImage expected = converter.toFloatImage("0.5 0.5 0.5\n0.5 0.5 0.5\n0.5 0.5 0.5");
        assertThat(Arrays.equals(image.getRawData(),expected.getRawData()),is(true));
    }
}