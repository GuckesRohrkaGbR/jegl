package de.torqdev.jegl.core;

import de.torqdev.jegl.filters.ImageFilter;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class FilterUtilTest {
    @Test
    public void canListAllExportedFilters() throws Exception {
        // setup + execute
        Collection<ImageFilter> filters = FilterUtil.getAllFilters();

        // verify
        assertThat(filters.size(), is(not(0)));
    }

    @Test
    public void testConstructorIsPrivate()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {
        Constructor<FilterUtil> constructor = FilterUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}