package de.torqdev.jegl.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class FloatImageTest {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int CHANNELS = 4;

    @org.junit.Test
    public void givenHeightWidthAndChannels_byteArrayHasCorrectSize() throws Exception {
        // setup
        FloatImage floatImage = new FloatImage(WIDTH, HEIGHT, CHANNELS);

        // execute + verify
        assertThat(floatImage.getRawData().length, is(WIDTH * HEIGHT * CHANNELS));
    }
}