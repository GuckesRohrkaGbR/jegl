package de.torqdev.jegl.filters.trivial;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.core.GrayscaleFloatImageFromTextMatrixConverter;
import de.torqdev.jegl.filters.ImageFilter;
import de.torqdev.jegl.filters.trivial.CopyFilter;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class CopyFilterTest {
    private ImageFilter filter = new CopyFilter();

    @Test
    public void givenAnyImage_returnsADifferentInstance() throws Exception {
        // setup
        FloatImage image = new FloatImage(0, 0, 1);

        // execute
        FloatImage processed = filter.processImage(image);

        // verify
        assertFalse(image == processed);
    }

    @Test
    public void givenAnyImage_rawDataArrayIsNotTheSameInstance() throws Exception {
        // setup
        FloatImage image = new FloatImage(0, 0, 1);

        // execute
        FloatImage processed = filter.processImage(image);

        // verify
        assertFalse(image.getRawData() == processed.getRawData());
    }

    @Test
    public void givenAnyImage_arrayContentIsTheSameOnOriginAndTarget() throws Exception {
        // setup
        FloatImage image = new GrayscaleFloatImageFromTextMatrixConverter().toFloatImage("1 0 1");

        // execute
        FloatImage processed = filter.processImage(image);

        // verify
        assertTrue(Arrays.equals(image.getRawData(), processed.getRawData()));
    }
}