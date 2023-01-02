package org.vaadin.addons.componentfactory;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * Slider component wrapping paper-slider web component.
 */
@Tag("paper-slider")
@NpmPackage(value = "@polymer/paper-slider", version = "3.0.1")
@JsModule("@polymer/paper-slider/paper-slider.js")
@CssImport("./paper-slider.css")
class PaperSliderComponent
        extends AbstractSinglePropertyField<PaperSliderComponent, Integer>
        implements HasSize, HasTheme {

    public PaperSliderComponent() {
        this(0);
    }

    public PaperSliderComponent(Integer defaultValue) {
        super("value", defaultValue, true);
        getElement().executeJs(
                "this.$.sliderContainer.style.marginLeft = 'var(--lumo-space-xs)'");
        getElement().executeJs(
                "this.$.sliderKnob.addEventListener('click', function() { "
                        + "$0.$server.setMinValueIfClickedInitially();" + "})",
                getElement());
    }

    @ClientCallable
    public void setMinValueIfClickedInitially() {
        if (getValue() < getMin()) {
            setValue(getMin());
        }
    }

    public void setMin(int min) {
        getElement().setProperty("min", min);
        getElement().setProperty("secondaryProgress", min);
    }

    public int getMin() {
        return getElement().getProperty("min", 0);
    }

    public void setMax(int max) {
        getElement().setProperty("max", max);
    }

    public int getMax() {
        return getElement().getProperty("max", 0);
    }

    public void setSecondaryProgress(int secondary) {
        getElement().setProperty("secondaryProgress", secondary);
    }

    public int getSecondaryProgress() {
        return getElement().getProperty("secondaryProgress", 0);
    }

    @Override
    public void setValue(Integer value) {
        super.setValue(value);
    }

    public void setPinned(boolean pin) {
        getElement().setAttribute("pin", pin);
    }

    public boolean isPinned() {
        return getElement().getAttribute("pin") != null;
    }

    public void setSnaps(boolean snaps) {
        getElement().setAttribute("snaps", snaps);
    }

    public boolean hasSnaps() {
        return getElement().getProperty("snaps", false);
    }

    public void setMaxMarkers(int markers) {
        getElement().setProperty("maxMarkers", markers);
    }

    public int setMaxMarkers() {
        return getElement().getProperty("maxMarkers", 0);
    }
}
