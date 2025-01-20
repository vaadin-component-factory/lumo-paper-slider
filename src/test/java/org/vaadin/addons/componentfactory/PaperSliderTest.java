package org.vaadin.addons.componentfactory;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.dom.ThemeList;

public class PaperSliderTest {

    @Test
    public void setValue_nullWorks() {
        PaperSlider paperSlider = new PaperSlider();
        paperSlider.setValue(null);
        Assert.assertEquals(Integer.valueOf(0), paperSlider.getValue());
    }

    @Test
    public void setValue_clearWorks() {
        PaperSlider paperSlider = new PaperSlider();
        paperSlider.clear();;
        Assert.assertEquals(Integer.valueOf(0), paperSlider.getValue());
    }

    @Test
    public void setValue_valueChangeIsTriggered() {
        PaperSlider paperSlider = new PaperSlider();
        AtomicInteger count = new AtomicInteger(0);
        paperSlider.addValueChangeListener(event -> {
            count.addAndGet(1);
        });
        paperSlider.setValue(10);
        Assert.assertEquals(1, count.get());
        Assert.assertEquals(Integer.valueOf(10), paperSlider.getValue());
    }

    @Test
    public void addThemeVariant_themeNamesContainsThemeVariant() {
        PaperSlider paperSlider = new PaperSlider();
        paperSlider.addThemeVariants(PaperSliderVariant.LUMO_ERROR);

        ThemeList themeNames = paperSlider.slider.getElement().getThemeList();
        Assert.assertTrue(themeNames
                .contains(PaperSliderVariant.LUMO_ERROR.getVariantName()));
    }

    @Test
    public void binderTest() {
        PaperSlider paperSlider = new PaperSlider();
        Binder<Value> binder = new Binder<>();
        binder.forField(paperSlider).withValidator(value -> value > 5, "Error")
                .bind(Value::getValue, Value::setValue);
        binder.readBean(new Value(null));
        Assert.assertEquals(Integer.valueOf(0), paperSlider.getValue());
        binder.validate();
        Assert.assertEquals("Error", paperSlider.getErrorMessage());
        Assert.assertTrue(paperSlider.isInvalid());
        binder.readBean(new Value(10));
        Assert.assertEquals(Integer.valueOf(10), paperSlider.getValue());
        binder.validate();
        Assert.assertFalse(paperSlider.isInvalid());
    }

    public class Value {
        private Integer value;

        public Value(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}
