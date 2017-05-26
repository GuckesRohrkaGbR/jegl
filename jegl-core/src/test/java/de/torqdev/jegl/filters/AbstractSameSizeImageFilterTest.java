package de.torqdev.jegl.filters;

import de.torqdev.jegl.core.FloatImage;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public abstract class AbstractSameSizeImageFilterTest extends AbstractImageFilterTest {
    protected AbstractSameSizeImageFilterTest(ImageFilter filter) {
        super(filter);
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
}
