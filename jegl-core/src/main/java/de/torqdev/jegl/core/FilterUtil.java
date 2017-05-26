package de.torqdev.jegl.core;

import de.torqdev.jegl.filters.ImageFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ServiceLoader;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public final class FilterUtil {
    private FilterUtil() {
        // private utility constructor
    }

    public static Collection<ImageFilter> getAllFilters() {
        Collection<ImageFilter> filters = new ArrayList<>();
        ServiceLoader<ImageFilter> loader = ServiceLoader.load(ImageFilter.class);
        loader.forEach(filters::add);
        return filters;
    }
}
