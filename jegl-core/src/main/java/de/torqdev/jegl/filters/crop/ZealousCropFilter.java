package de.torqdev.jegl.filters.crop;

import de.torqdev.jegl.core.FloatImage;
import de.torqdev.jegl.filters.ImageFilter;
import org.kohsuke.MetaInfServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
@MetaInfServices
public class ZealousCropFilter implements ImageFilter {
    private List<Integer> heterogeneousRows;
    private List<Integer> heterogeneousColumns;

    @Override
    public FloatImage processImage(FloatImage image) {
        heterogeneousRows = new ArrayList<>();
        heterogeneousColumns = new ArrayList<>();
        return zealousCrop(image);
    }

    private FloatImage zealousCrop(FloatImage image) {
        findHorizontalHeterogenity(image);
        findVerticalHeterogenity(image);
        return crop(image);
    }

    private void findHorizontalHeterogenity(FloatImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            boolean differenceInLine = false;
            float[] firstPixel = image.getPixel(0, y);
            for (int x = 1; x < image.getWidth() && !differenceInLine; x++) {
                float[] currentPixel = image.getPixel(x, y);
                differenceInLine = !Arrays.equals(firstPixel, currentPixel);
            }

            if (differenceInLine) {
                heterogeneousRows.add(y);
            }
        }
    }

    private void findVerticalHeterogenity(FloatImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            boolean differenceInRow = false;
            float[] firstPixel = image.getPixel(x, 0);
            for (int y = 0; y < image.getHeight() && !differenceInRow; y++) {
                float[] currentPixel = image.getPixel(x, y);
                differenceInRow = !Arrays.equals(firstPixel, currentPixel);
            }

            if (differenceInRow) {
                heterogeneousColumns.add(x);
            }
        }
    }

    private FloatImage crop(FloatImage image) {
        FloatImage cropped = new FloatImage(heterogeneousColumns.size(), heterogeneousRows.size(),
                                            image.getChannels());

        for (int y = 0; y < heterogeneousRows.size(); y++) {
            for (int x = 0; x < heterogeneousColumns.size(); x++) {
                cropped.setPixel(x, y, image.getPixel(heterogeneousColumns.get(x),
                                                      heterogeneousRows.get(y)));
            }
        }
        return cropped;
    }
}
