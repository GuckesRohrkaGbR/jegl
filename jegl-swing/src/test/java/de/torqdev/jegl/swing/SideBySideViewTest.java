package de.torqdev.jegl.swing;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunnable;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class SideBySideViewTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;

    @Override
    protected void onSetUp() {
        SideBySideView frame = GuiActionRunner.execute(new GuiQuery<SideBySideView>() {
            @Override
            protected SideBySideView executeInEDT() throws Throwable {
                return new SideBySideView();
            }
        });
        window = new FrameFixture(robot(), frame);
        window.show();
    }

    @Test
    public void frameNameIsSetCorrectly() throws Exception {
        // execute + verify
        window.requireTitle("SideBySideView");
    }

    @Test
    public void changingFilterDropDown_changesFilter() throws Exception {
        // execute + verify
        window.comboBox().selectItem(0);
        String firstFilter = window.comboBox().selectedItem();
        window.comboBox().selectItem(1);
        String nextFilter = window.comboBox().selectedItem();

        assertThat(firstFilter, is(not(equalTo(nextFilter))));
    }

    @Test
    public void clickingOpenFileButton_opensFileDialog() throws Exception {
        // execute
        window.button(JButtonMatcher.withText("Open File")).click();

        // verify
        window.fileChooser().requireVisible();
    }
}