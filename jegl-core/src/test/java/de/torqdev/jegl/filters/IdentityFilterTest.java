package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.core.GrayscaleFloatImageFromTextMatrixConverter;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class IdentityFilterTest {
    private AbstractFloatImageConverter<String> converter = new GrayscaleFloatImageFromTextMatrixConverter();
    private ImageFilter filter = new IdentityFilter();

    @Test
    public void givenAnyImage_returnsTheSameInstance() throws Exception {
        // setup
        FloatImage image = new FloatImage(0, 0, 1);

        // execute
        FloatImage processed = filter.processImage(image);

        // verify
        assertTrue(image == processed);
    }
}