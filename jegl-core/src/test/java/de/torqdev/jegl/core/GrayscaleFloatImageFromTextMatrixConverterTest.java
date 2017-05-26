package de.torqdev.jegl.core;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class GrayscaleFloatImageFromTextMatrixConverterTest {
    private AbstractFloatImageConverter<String> converter = new
            GrayscaleFloatImageFromTextMatrixConverter();

    @Test
    public void givenNoTextInput_createsEmptyImage() throws Exception {
        // setup
        String imageSource = "";

        // execute
        FloatImage image = converter.toFloatImage(imageSource);

        // verify
        assertThat(image.getRawData().length, is(0));
    }

    @Test
    public void givenOneBlackBitInput_createsBlackOnePixelImage() throws Exception {
        // setup
        String imageSource = "0";

        // execute
        FloatImage image = converter.toFloatImage(imageSource);

        // verify
        assertThat(image.getRawData().length, is(1));
        assertThat(image.getRawData()[0], is(0F));
    }

    @Test
    public void givenAnyInput_createsOneChannelImage() throws Exception {
        // setup
        String imageSource = "";

        // execute
        FloatImage image = converter.toFloatImage(imageSource);

        // verify
        assertThat(image.getChannels(), is(1));
    }

    @Test
    public void givenOneWhiteBitInput_createsWhiteOnePixelImage() throws Exception {
        // setup
        String imageSource = "1";

        // execute
        FloatImage image = converter.toFloatImage(imageSource);

        // verify
        assertThat(image.getRawData().length, is(1));
        assertThat(image.getRawData()[0], is(1F));
    }

    @Test
    public void givenOneGrayBitInput_createsGrayOnePixelImage() throws Exception {
        // setup
        String imageSource = "0.5";

        // execute
        FloatImage image = converter.toFloatImage(imageSource);

        // verify
        assertThat(image.getRawData().length, is(1));
        assertThat(image.getRawData()[0], is(0.5F));
    }

    @Test
    public void givenTwoBitInput_createsTwoPixelImageInTheRightOrder() throws Exception {
        // setup
        String imageSource = "1 0";

        // execute
        FloatImage image = converter.toFloatImage(imageSource);

        // verify
        assertThat(image.getRawData().length, is(2));
        assertThat(image.getRawData()[0], is(1F));
        assertThat(image.getRawData()[1], is(0F));
    }

    @Test
    public void givenInputWithMultipleWhitespaces_imageIsParsedCorrectly() throws Exception {
        // setup
        String imageSource = "1     0";

        // execute
        FloatImage image = converter.toFloatImage(imageSource);

        // verify
        assertThat(image.getRawData().length, is(2));
        assertThat(image.getRawData()[0], is(1F));
        assertThat(image.getRawData()[1], is(0F));
    }

    @Test
    public void given2x2Input_widthIsCalculatedCorrectly() throws Exception {
        // setup
        String imageSource = "1 0\n0 1";

        // execute
        FloatImage image = converter.toFloatImage(imageSource);

        // verify
        assertThat(image.getWidth(), is(2));
    }

    @Test
    public void given2x2Input_heightIsCalculatedCorrectly() throws Exception {
        // setup
        String imageSource = "1 0\n0 1";

        // execute
        FloatImage image = converter.toFloatImage(imageSource);

        // verify
        assertThat(image.getHeight(), is(2));
    }

    @Test
    public void given2x2Input_createsFourPixelImageInTheRightOrder() throws Exception {
        // setup
        String imageSource = "1 0\n0 1";

        // execute
        FloatImage image = converter.toFloatImage(imageSource);

        // verify
        assertThat(image.getRawData().length, is(4));
        assertThat(image.getRawData()[0], is(1F));
        assertThat(image.getRawData()[1], is(0F));
        assertThat(image.getRawData()[2], is(0F));
        assertThat(image.getRawData()[3], is(1F));
    }

    @Test
    public void given1PixelBlackImage_returnsZeroString() throws Exception {
        // setup
        FloatImage image = new FloatImage(1,1, 1);

        // execute
        String text = converter.fromFloatImage(image);

        // verify
        assertThat(text, containsString("0.0"));
    }

    @Test
    public void given1PixelWhiteImage_returnsOneString() throws Exception {
        // setup
        FloatImage image = new FloatImage(1,1, 1);
        image.setPixel(0, 0, new float[] {1F});

        // execute
        String text = converter.fromFloatImage(image);

        // verify
        assertThat(text, containsString("1.0"));
    }

    @Test
    public void given3x3Image_returnsThreeLineText() throws Exception {
        // setup
        FloatImage image = new FloatImage(3,3, 1);

        // execute
        String text = converter.fromFloatImage(image);

        // verify
        assertThat(text.split("\n").length, is(3));
    }
}