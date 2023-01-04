package org.vaadin.addons.componentfactory;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.customfield.testbench.CustomFieldElement;
import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.testbench.TestBenchElement;

public class PaperSliderIT extends AbstractViewTest {

    public PaperSliderIT() {
        super("slider");
    }

    @Before
    public void init() {
        $(PaperSliderElement.class).waitForFirst();
    }

    @Test
    public void sliderValueChangeWorks() {
        PaperSliderElement slider = $(PaperSliderElement.class).first();
        $(ButtonElement.class).first().click();
        NotificationElement notification = $(NotificationElement.class).last();
        Assert.assertTrue(notification.getText().startsWith("Answer: 5"));
        Assert.assertEquals(5, slider.getValue());
    }

    @Test
    public void sliderDisabledWorks() {
        PaperSliderElement slider = $(PaperSliderElement.class).get(2);
        Assert.assertEquals("true", slider.getAttribute("disabled"));
    }

    @Test
    public void sliderKeyControlsFocusBlurWork() {
        PaperSliderElement slider = $(PaperSliderElement.class).first();
        slider.sendKeys(Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT);
        NotificationElement notification = $(NotificationElement.class).last();
        Assert.assertTrue(notification.getText().startsWith("Answer: 4"));
        slider.sendKeys(Keys.TAB);
        notification = $(NotificationElement.class).last();
        Assert.assertTrue(notification.getText().startsWith("Focused"));
        slider = $(PaperSliderElement.class).get(1);
        slider.sendKeys(Keys.TAB);
        notification = $(NotificationElement.class).last();
        Assert.assertTrue(notification.getText().startsWith("Blurred"));
    }

    @Test
    public void sliderThemedWorks() {
        PaperSliderElement slider = $(PaperSliderElement.class).get(1);
        Assert.assertEquals("secondary", slider.getAttribute("theme"));
    }
    
    @Test
    public void sliderReadOnlyWorks() {
        PaperSliderElement slider = $(PaperSliderElement.class).get(3);
        Assert.assertEquals("true", slider.getAttribute("disabled"));
        CustomFieldElement field = $(CustomFieldElement.class).get(3);
        Assert.assertEquals("true", field.getAttribute("readonly"));
    }

    @Test
    public void sliderMarkersWorks() {
        PaperSliderElement slider = $(PaperSliderElement.class).get(4);
        List<DivElement> markers = slider.getSliderMarkers();
        Assert.assertEquals(10, markers.size());
    }

    @Test
    public void labelWorks() {
        CustomFieldElement field = $(CustomFieldElement.class).get(0);
        Assert.assertEquals("Do you like this component?", field.getLabel());
        field = $(CustomFieldElement.class).get(1);
        Assert.assertEquals(null, field.getLabel());
    }

    @Test
    public void sliderClickChangeWorks() {
        PaperSliderElement slider = $(PaperSliderElement.class).first();
        TestBenchElement progress = slider.getProgressElement();
        progress.click(20, 2);
        NotificationElement notification = $(NotificationElement.class).last();
        Assert.assertEquals("Answer: 6", notification.getText());
        Assert.assertEquals(6, slider.getValue());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void sliderClickChangeReadOnlyNoEvent() {
        PaperSliderElement slider = $(PaperSliderElement.class).get(3);
        TestBenchElement progress = slider.getProgressElement();
        progress.click(20, 2);
        // No notification shown, thus throws
        $(NotificationElement.class).last();
    }
}
