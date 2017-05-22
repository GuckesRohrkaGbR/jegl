package de.torqdev.jegl.awt;

import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FloatImage;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class FloatImageFromBufferedImageFactoryTest {
    AbstractFloatImageConverter<BufferedImage> converter = new FloatImageFromBufferedImageConverter();

    @Test
    public void givenBlack3ChannelBufferedImage_returnsBlackFloatImage() throws Exception {
        // setup
        BufferedImage bi = createBufferedImage(1, 1, Color.BLACK, TYPE_INT_RGB);

        // execute
        FloatImage image = converter.toFloatImage(bi);

        // verify
        assertThat(image.getRawData().length, is(3));
        assertThat(image.getRawData()[0], is(0F));
        assertThat(image.getRawData()[1], is(0F));
        assertThat(image.getRawData()[2], is(0F));
    }

    @Test
    public void givenBlack4ChannelBufferedImage_returnsBlackFloatImage() throws Exception {
        // setup
        BufferedImage bi = createBufferedImage(1, 1, Color.BLACK, TYPE_INT_ARGB);

        // execute
        FloatImage image = converter.toFloatImage(bi);

        // verify
        assertThat(image.getRawData().length, is(4));
        assertThat(image.getRawData()[0], is(1F));
        assertThat(image.getRawData()[1], is(0F));
        assertThat(image.getRawData()[2], is(0F));
        assertThat(image.getRawData()[3], is(0F));
    }

    @Test
    public void givenWhite3ChannelBufferedImage_returnsWhiteFloatImage() throws Exception {
        // setup
        BufferedImage bi = createBufferedImage(1, 1, Color.WHITE, TYPE_INT_RGB);

        // execute
        FloatImage image = converter.toFloatImage(bi);

        // verify
        assertThat(image.getRawData().length, is(3));
        assertThat(image.getRawData()[0], is(1F));
        assertThat(image.getRawData()[1], is(1F));
        assertThat(image.getRawData()[2], is(1F));
    }

    @Test
    public void givenWhite4ChannelBufferedImage_returnsWhiteFloatImage() throws Exception {
        // setup
        BufferedImage bi = createBufferedImage(1, 1, Color.WHITE, TYPE_INT_ARGB);

        // execute
        FloatImage image = converter.toFloatImage(bi);

        // verify
        assertThat(image.getRawData().length, is(4));
        assertThat(image.getRawData()[0], is(1F));
        assertThat(image.getRawData()[1], is(1F));
        assertThat(image.getRawData()[2], is(1F));
        assertThat(image.getRawData()[3], is(1F));
    }

    @Test
    public void given4ChannelImage_returnsBufferedImageWithTypeARGB() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 4);

        // execute
        BufferedImage result = converter.fromFloatImage(image);

        // verify
        assertThat(result.getType(), is(TYPE_INT_ARGB));
    }

    @Test
    public void given3ChannelImage_returnsBufferedImageWithTypeRGB() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 3);

        // execute
        BufferedImage result = converter.fromFloatImage(image);

        // verify
        assertThat(result.getType(), is(TYPE_INT_RGB));
    }

    @Test
    public void given1ChannelImage_returnsBufferedImageWithTypeByteGrayscale() throws Exception {
        // setup
        FloatImage image = new FloatImage(1, 1, 1);

        // execute
        BufferedImage result = converter.fromFloatImage(image);

        // verify
        assertThat(result.getType(), is(TYPE_BYTE_GRAY));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void givenUnsupportedChannelImage_throwsException() throws Exception {
        // setup
        FloatImage image = new FloatImage(1,1,2);

        // execute + verify
        converter.fromFloatImage(image);
    }

    private BufferedImage createBufferedImage(int width, int height, Color fillColor, int type) {
        BufferedImage bi = new BufferedImage(width, height, type);
        Graphics2D graphics = bi.createGraphics();
        graphics.setColor(fillColor);
        graphics.fillRect(0, 0, width, height);
        graphics.dispose();
        return bi;
    }
}