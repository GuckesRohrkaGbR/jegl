package de.torqdev.jegl.core;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class GrayscaleFloatImageFromTextMatrixConverter
        implements AbstractFloatImageConverter<String> {
    private static final int CHANNELS = 1;
    private static final String WHITESPACE = "\\s+";

    public FloatImage toFloatImage(String imageSource) {
        int width = getWidthFromString(imageSource);
        float[] raw = stringMatrixToByteArray(imageSource);

        FloatImage image = new FloatImage(width, raw.length / width, CHANNELS);
        image.setRawData(raw);
        return image;
    }

    private int getWidthFromString(String imageSource) {
        return imageSource.split("\n")[0].split(WHITESPACE).length;
    }

    private float[] stringMatrixToByteArray(final String input) {
        if (isEmpty(input)) {
            return new float[0];
        }
        String[] split = input.split(WHITESPACE);
        return stringToFloat(split);
    }

    private float[] stringToFloat(String[] split) {
        float[] myReturn = new float[split.length];

        for (int i = 0; i < split.length; i++) {
            myReturn[i] = Float.valueOf(split[i]);
        }
        return myReturn;
    }

    @Override
    public String fromFloatImage(FloatImage floatImage) {
        StringBuilder image = new StringBuilder();

        for (int y = 0; y < floatImage.getHeight(); y++) {
            for (int x = 0; x < floatImage.getWidth(); x++) {
                image.append(floatImage.getPixel(x, y)[0]);
                image.append('\t');
            }
            image.append('\n');
        }

        return image.toString();
    }
}